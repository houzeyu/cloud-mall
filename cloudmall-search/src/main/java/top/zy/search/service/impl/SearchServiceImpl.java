package top.zy.search.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.stereotype.Service;
import top.cloudmall.goods.dto.*;
import top.zy.common.util.utils.exception.CloudmalException;
import top.zy.common.util.utils.exception.ExceptionEnum;
import top.zy.common.util.utils.util.JsonUtils;
import top.zy.search.client.BrandClient;
import top.zy.search.client.CategoryClient;
import top.zy.search.client.ProductClient;
import top.zy.search.client.SpecClient;
import top.zy.search.dto.GoodsDto;
import top.zy.search.dto.SearchSkuDto;
import top.zy.search.respository.SearchRepository;
import top.zy.search.service.SearchService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private SpecClient specClient;

    @Override
    public void productDataToEs(List<Product> products) {
        //判断参数是否为空
      if (products.isEmpty()){
          throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
      }
      List<IndexQuery> goodsDtos=new ArrayList<>();
      //构建导入es的对象
        products.stream().forEach(product -> {
          GoodsDto goodsDto=buildGoodsDto(product);
          IndexQuery query=new IndexQuery();
          query.setObject(goodsDto);
          query.setIndexName("goods");
          query.setType("docs");
          goodsDtos.add(query);
        });
        //批量导入es
        if (goodsDtos.size()>0){
            elasticsearchTemplate.bulkIndex(goodsDtos);
        }

    }

    /****
     * 根据商品信息构建导入es的数据
     * @param product
     * @return
     */
    private GoodsDto buildGoodsDto(Product product) {
        //根据商品id查询商品信息,分类信息，品牌信息，规格参数

        //查询分类
        List<Category> categoryList =(List<Category>) categoryClient.selectCategoryLit(Arrays.asList(product.getCid1(), product.getCid2(), product.getCid3())).getData();
        if (categoryList.isEmpty()){
            throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
        }
        //分类名称集合
        List<String> categoryNames=categoryList.stream().map(Category::getName).collect(Collectors.toList());
        //查询品牌
        Brand brand = (Brand)brandClient.selectBrand(product.getBrandId()).getData();
        //用于搜素的信息
        String all=product.getTitle()+StringUtils.join(categoryNames," ",brand.getName());
        //查询sku
        List<ProductSpecification> productSpecifications=(List<ProductSpecification>) productClient.selectProductSpecification(product.getId()).getData();
           if (productSpecifications.isEmpty()){
             throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
           }
        //处理sku
        List<SearchSkuDto> skus=new ArrayList<>();
        //价格集合
        Set<Long> prices=new TreeSet<>();
        productSpecifications.stream().forEach(productSpecification -> {
            SearchSkuDto searchSkuDto=new SearchSkuDto();
            BeanUtils.copyProperties(productSpecification,searchSkuDto);
            searchSkuDto.setImages(StringUtils.substringBefore(productSpecification.getImages(),","));
            skus.add(searchSkuDto);
            //处理价格
            prices.add(productSpecification.getPrice());
        });
        //处理规格参数
        //查询分类对应的规格参数
        List<SpecParam> params= (List<SpecParam>) specClient.selectSpec(product.getCid3()).getData();
         if (params.isEmpty()){
             throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
         }
        //查询商品详情
        ProductDetail productDetail= (ProductDetail) productClient.selectProductDetail(product.getId()).getData();
        if (productDetail==null){
            throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
        }
         //通用规格参数
        Map<String, String> genericSpec = JsonUtils.parseMap(productDetail.getGenericSpec(), String.class, String.class);
        if (genericSpec.isEmpty()){
            throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
        }
        //特有规格参数
        Map<String, List<String>> specialSpec = JsonUtils.nativeRead(productDetail.getSpecialSpec(), new TypeReference<Map<String, List<String>>>() {});
        if (specialSpec.isEmpty()){
            throw new CloudmalException(ExceptionEnum.INVALID_EMPTY);
        }
        //商品规格参数的集合 key是规格 value是参数值可能为集合
        Map<String,Object> specs=new HashMap<>();
        params.stream().forEach(param->{
            //参数名称
            String paramKey=param.getName();
            //参数值
            Object paramValue;
            if (param.getGeneric()){
               //是通用的
                paramValue=genericSpec.get(param.getId().toString());
                //判断是否是数值类型
                if (param.getNumeric()){
                   paramValue=choosesegment(paramValue.toString(),param);
                }
            }else {
               //是特殊的
                paramValue=specialSpec.get(param.getId().toString());
            }
            specs.put(paramKey,paramValue);
        });
        //处理规格参数
        //构建导入es的GoodsDto
        GoodsDto goodsDto=new GoodsDto();
        goodsDto.setId(product.getId());
        goodsDto.setAll(all);
        goodsDto.setBrandId(product.getBrandId());
        goodsDto.setCid1(product.getCid1());
        goodsDto.setCid2(product.getCid2());
        goodsDto.setCid3(product.getCid3());
        goodsDto.setCreateTime(new Date());
        goodsDto.setSubTitle(product.getSubTitle());
        goodsDto.setPrices(prices);
        goodsDto.setSkus(JsonUtils.toString(skus));
        goodsDto.setSpecs(specs);
        return goodsDto;
    }

    /***
     * 将商品规格餐宿分段处理
     * @param paramValue
     * @param param
     * @return
     */
    private Object choosesegment(String paramValue, SpecParam param) {
        //将参数值转换为double
        double value= NumberUtils.toDouble(paramValue);
        String result="其他";
        for (String segment : param.getSegments().split(",")) {
            //获取取值范围
            String[] segs = segment.split("-");
            double begin=NumberUtils.toDouble(segs[0]);
            double end=Double.MAX_VALUE;
            if (segs.length==2){
                end=NumberUtils.toDouble(segs[1]);
            }
           if (value>=begin&&value<end){
              if (segs.length==1){
               result=segs[0]+param.getUnit()+"以上";
              }else if (begin==0){
                  result=segs[0]+param.getUnit()+"以下";
              }else {
                  result=param.getSegments()+param.getUnit();
              }
           }
        }
        return result;
    }
}

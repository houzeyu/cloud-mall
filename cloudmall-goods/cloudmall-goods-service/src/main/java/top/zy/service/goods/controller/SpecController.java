package top.zy.service.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.zy.common.util.utils.resp.ApiResponse;
import top.zy.common.util.utils.resp.ResponseUtil;
import top.zy.service.goods.entity.SpecParam;
import top.zy.service.goods.service.SpecService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("spec")
public class SpecController {
    @Autowired
    @Qualifier("specServiceImpl")
    private SpecService specService;

    /*****
     * 通过分类id查询规格参数
     * @param categoryId
     * @return
     */
    @GetMapping("categoryId")
    public ApiResponse selectSpec(@RequestParam("categoryId") Long categoryId){
        List<SpecParam> specParams=specService.selectSpecParamsBycategoryId(categoryId);
        return ResponseUtil.ok(specParams);
    }

}

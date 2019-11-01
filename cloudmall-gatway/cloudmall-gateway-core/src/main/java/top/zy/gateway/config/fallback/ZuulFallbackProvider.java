package top.zy.gateway.config.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Component
@Slf4j
public class ZuulFallbackProvider implements FallbackProvider {
    @Override
    public String getRoute() {
        //所有路由进行回退
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("--> route:{}进行熔断降级", route);
        if (cause instanceof HystrixTimeoutException){
            return response(HttpStatus.GATEWAY_TIMEOUT);
        }else {
             return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ClientHttpResponse response(final HttpStatus status){
        {
       return new ClientHttpResponse() {
           @Override
           public HttpStatus getStatusCode() throws IOException {
               return HttpStatus.OK;
           }

           @Override
           public int getRawStatusCode() throws IOException {
               return HttpStatus.OK.value();
           }

           @Override
           public String getStatusText() throws IOException {
               return HttpStatus.OK.getReasonPhrase();
           }

           @Override
           public void close() {

           }

           @Override
           public InputStream getBody() throws IOException {
               return new ByteArrayInputStream(("The service is unavailable".getBytes()));
           }

           @Override
           public HttpHeaders getHeaders() {
               HttpHeaders headers=new HttpHeaders();
               headers.setContentType(MediaType.APPLICATION_JSON);
               return headers;
           }
       };
        }
    }
}

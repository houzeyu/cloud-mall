package top.zy.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import top.zy.user.api.UserApi;

@FeignClient(value = "user-service",fallback = UserClientFallback.class)
public interface UserClient extends UserApi {

}

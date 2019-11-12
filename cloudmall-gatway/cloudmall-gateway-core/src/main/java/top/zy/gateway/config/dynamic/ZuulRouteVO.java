package top.zy.gateway.config.dynamic;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.catalina.LifecycleState;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@TableName("gateway_api_define")
public class ZuulRouteVO implements Serializable {


    /**
     * The ID of the route (the same as its map key by default).
     */
    @TableId
    private String id;

    /**
     * The path (pattern) for the route, e.g. /foo/**.
     */
    private String path;

    /**
     * The service ID (if any) to map to this route. You can specify a physical URL or
     * a service, but not both.
     */
    private String serviceId;

    /**
     * A full physical URL to map to the route. An alternative is to use a service ID
     * and service discovery to find the physical address.
     */
    private String url;

    /**
     * Flag to determine whether the prefix for this route (the path, minus pattern
     * patcher) should be stripped before forwarding.
     */
    private boolean stripPrefix = true;

    /**
     * Flag to indicate that this route should be retryable (if supported). Generally
     * retry requires a service ID and ribbon.
     */
    private Boolean retryable;

    private Boolean enabled;
    /***
     * gateway path prefix
     */
    private String apiPath;

    private Integer gatewayApiGroupId;

    private Set<String> sensitiveHeadersSet = new LinkedHashSet<>();

    /** 字符串格式，与sensitiveHeaders对应，多个用逗号隔开 */
    private String sensitiveHeaders;

    private boolean customSensitiveHeaders;

    public void setSensitiveHeadersSet(Set<String> sensitiveHeadersSet) {
        this.sensitiveHeadersSet = sensitiveHeadersSet;
        StringBuilder sb = new StringBuilder("");
        if (!CollectionUtils.isEmpty(sensitiveHeadersSet)) {
            for (String item : sensitiveHeadersSet) {
                if (sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(item);
            }
        }
        this.sensitiveHeaders = sb.toString();
    }

    public Set<String> getSensitiveHeaders() {
        Set<String> sensitiveHeadersSet=new LinkedHashSet<>();
        sensitiveHeadersSet.add(sensitiveHeaders);
        return sensitiveHeadersSet;
    }

    public void setSensitiveHeaders(String sensitiveHeaders) {
        this.sensitiveHeaders = sensitiveHeaders;
        if (!StringUtils.isEmpty(sensitiveHeaders)) {
            this.sensitiveHeadersSet = new LinkedHashSet<>(Arrays.asList(sensitiveHeaders.split(",")));
        } else {
            this.sensitiveHeadersSet = new LinkedHashSet<String>();
        }
    }
    public Set<String> getSensitiveHeadersSet() {
        return sensitiveHeadersSet;
    }
}

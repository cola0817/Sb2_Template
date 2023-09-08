package cola.template.sb2_template.system.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Cola0817
 * @date 2023/9/8 15:46
 * @since 1.0
 */
@ConfigurationProperties(prefix = "security.ignore")
@Component
public class SecurityIgnoreUrl {
    private String[] urls;

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(String[] urls) {
        this.urls = urls;
    }
}

package cola.template.sb2_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author Cola0817
 * @date 2023/9/8 15:04
 * @since 1.0
 */
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Sb2TemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb2TemplateApplication.class, args);
    }

}

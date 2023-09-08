package cola.template.sb2_template.system.config;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * @author Cola0817
 * @date 2023/9/8 15:42
 * @since 1.0
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    @Bean(value = "dockerBean")
    public Docket dockerBean() {
        //指定使用Swagger2规范
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        //描述字段支持Markdown语法
                        .description("# Knife4j RESTful APIs")
                        .termsOfServiceUrl("https://github.com/cola0817")
                        .contact(new Contact("Cola", "https://github.com/cola0817", "1203952894@qq.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("用户服务")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("cola.template.sb2_template.system.controller"))
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<SecurityContext> securityContexts() {
        return List.of(SecurityContext.builder()
                .securityReferences(List.of(
                        new SecurityReference("Authorization",
                                new AuthorizationScope[]{
                                        new AuthorizationScope("global", "")})))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
    }

    private List<? extends SecurityScheme> securitySchemes() {
        // 安全模式，这里指定token通过Authorization请求头传递
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", In.HEADER.toValue());

        return List.of(apiKey);

    }
}


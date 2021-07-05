package ro.fortech.comments.config;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)

                .select()
                .paths(regex(DEFAULT_INCLUDE_PATTERN))
                .paths(Predicates.not(PathSelectors.ant("/error/**")))
                .paths(Predicates.not(PathSelectors.ant("/refresh-token")))


// TODO: revise swagger endpoints display configuration

//                .select()
//                .apis(RequestHandlerSelectors.basePackage(MAIN_CONTROLLER))
//                .apis(RequestHandlerSelectors.basePackage(USER_CONTROLLER))

                .build();
    }
}

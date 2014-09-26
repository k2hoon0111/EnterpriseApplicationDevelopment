package lv.javaguru.ee.bookshop.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.javaguru.ee.bookshop.integrations"})
@Import(SpringSwaggerConfig.class)
public class WebMVCConfig {

}

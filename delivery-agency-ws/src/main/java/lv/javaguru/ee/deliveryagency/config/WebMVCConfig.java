package lv.javaguru.ee.deliveryagency.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"lv.javaguru.ee.deliveryagency.integrations"})
@Import(CoreConfig.class)
public class WebMVCConfig {


}

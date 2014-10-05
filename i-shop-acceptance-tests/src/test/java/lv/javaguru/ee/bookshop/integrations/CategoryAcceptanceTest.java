package lv.javaguru.ee.bookshop.integrations;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import lv.javaguru.ee.bookshop.integrations.jetty.EmbeddedJettyTest;
import org.junit.runner.RunWith;



@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:category.feature",
		format = "html:target/cucumber-html-report")
public class CategoryAcceptanceTest extends EmbeddedJettyTest {

}

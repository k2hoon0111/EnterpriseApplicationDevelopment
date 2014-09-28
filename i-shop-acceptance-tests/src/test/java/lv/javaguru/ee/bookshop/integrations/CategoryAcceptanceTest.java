package lv.javaguru.ee.bookshop.integrations;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:category.feature",
		format = "html:target/cucumber-html-report")
public class CategoryAcceptanceTest {

}

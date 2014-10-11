package lv.javaguru.ee.warehouse.integrations;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:futures/", 
		format = "html:target/cucumber-html-report")
public class AcceptanceTestsRunner {

}

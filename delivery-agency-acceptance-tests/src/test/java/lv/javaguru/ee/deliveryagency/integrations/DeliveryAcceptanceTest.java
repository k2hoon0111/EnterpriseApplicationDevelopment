package lv.javaguru.ee.deliveryagency.integrations;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:delivery.feature", 
		format = "html:target/cucumber-html-report")
public class DeliveryAcceptanceTest {

}

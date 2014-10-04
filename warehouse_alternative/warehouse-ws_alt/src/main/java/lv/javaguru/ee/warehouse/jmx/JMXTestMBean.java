package lv.javaguru.ee.warehouse.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

/**
 * Created by Viktor on 23/09/2014.
 */
@Component
@ManagedResource(objectName="WAREHOUSE.beans:name=JMXTestMBean",
        description="Warehouse Managed Bean (JMX)")
public class JMXTestMBean {

    private String testAttribute;

    @ManagedAttribute(description="Get test attribute")
    public String getTestAttribute() {
        return testAttribute;
    }

    @ManagedAttribute(description="Set test attribute")
    public void setTestAttribute(String testAttribute) {
        this.testAttribute = testAttribute;
    }

    @ManagedOperation(description="Add two numbers")
    @ManagedOperationParameters({
            @ManagedOperationParameter(name = "x", description = "The first number"),
            @ManagedOperationParameter(name = "y", description = "The second number")})
    public int add(int x, int y) {
        return x + y;
    }

}

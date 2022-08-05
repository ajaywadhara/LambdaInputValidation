package com.wadhara;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.ApplicationLoadBalancerRequestEvent;
import com.wadhara.model.Employee;
import software.amazon.lambda.powertools.validation.Validation;
import software.amazon.lambda.powertools.validation.ValidationException;
import software.amazon.lambda.powertools.validation.ValidationUtils;

/**
 * Hello world lambda that performs Request Validation
 *
 */
public class HelloWorldLambda implements RequestHandler<Employee, String>
{

    @Override
    //@Validation(inboundSchema = "classpath:/schema.json")
    public String handleRequest(Employee name, Context context) {
        try {
            ValidationUtils.validate(name, "classpath:/schema.json");
        } catch(ValidationException exception) {
            return "Input Not valid: " + exception.getMessage();
        }

        return "All OK";
    }
}

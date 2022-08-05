package com.wadhara;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.wadhara.model.Response;

import java.net.HttpURLConnection;
import java.util.List;

public class LambdaSNSExample implements RequestHandler<SNSEvent, Response>{


    @Override
    public Response handleRequest(SNSEvent snsEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        List<SNSEvent.SNSRecord> snsRecords = snsEvent.getRecords();
        snsRecords.forEach(v -> logger.log(v.getSNS().getMessage()));
        return Response.builder()
                .withMessage("OK")
                .withHttpCode(HttpURLConnection.HTTP_OK)
                .build();
    }
}
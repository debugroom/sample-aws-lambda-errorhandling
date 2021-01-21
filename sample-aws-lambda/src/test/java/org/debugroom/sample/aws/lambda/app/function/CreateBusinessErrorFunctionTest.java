package org.debugroom.sample.aws.lambda.app.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.debugroom.sample.aws.lambda.config.App;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

public class CreateBusinessErrorFunctionTest {

    ObjectMapper mapper = new ObjectMapper();

    String apiGatewayEvent = "{\n" +
            "    \"resource\": \"/uppercase2\",\n" +
            "    \"path\": \"/uppercase2\",\n" +
            "    \"httpMethod\": \"POST\",\n" +
            "    \"headers\": {\n" +
            "        \"accept\": \"*/*\",\n" +
            "        \"content-type\": \"application/json\",\n" +
            "        \"Host\": \"fhul32ccy2.execute-api.eu-west-3.amazonaws.com\",\n" +
            "        \"User-Agent\": \"curl/7.54.0\",\n" +
            "        \"X-Amzn-Trace-Id\": \"Root=1-5ece339e-e0595766066d703ec70f1522\",\n" +
            "        \"X-Forwarded-For\": \"90.37.8.133\",\n" +
            "        \"X-Forwarded-Port\": \"443\",\n" +
            "        \"X-Forwarded-Proto\": \"https\"\n" +
            "    },\n" +
            "    \"multiValueHeaders\": {\n" +
            "        \"accept\": [\n" +
            "            \"*/*\"\n" +
            "        ],\n" +
            "        \"content-type\": [\n" +
            "            \"application/json\"\n" +
            "        ],\n" +
            "        \"Host\": [\n" +
            "            \"fhul32ccy2.execute-api.eu-west-3.amazonaws.com\"\n" +
            "        ],\n" +
            "        \"User-Agent\": [\n" +
            "            \"curl/7.54.0\"\n" +
            "        ],\n" +
            "        \"X-Amzn-Trace-Id\": [\n" +
            "            \"Root=1-5ece339e-e0595766066d703ec70f1522\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-For\": [\n" +
            "            \"90.37.8.133\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Port\": [\n" +
            "            \"443\"\n" +
            "        ],\n" +
            "        \"X-Forwarded-Proto\": [\n" +
            "            \"https\"\n" +
            "        ]\n" +
            "    },\n" +
            "    \"queryStringParameters\": null,\n" +
            "    \"multiValueQueryStringParameters\": null,\n" +
            "    \"pathParameters\": null,\n" +
            "    \"stageVariables\": null,\n" +
            "    \"requestContext\": {\n" +
            "        \"resourceId\": \"qf0io6\",\n" +
            "        \"resourcePath\": \"/uppercase2\",\n" +
            "        \"httpMethod\": \"POST\",\n" +
            "        \"extendedRequestId\": \"NL0A1EokCGYFZOA=\",\n" +
            "        \"requestTime\": \"27/May/2020:09:32:14 +0000\",\n" +
            "        \"path\": \"/test/uppercase2\",\n" +
            "        \"accountId\": \"313369169943\",\n" +
            "        \"protocol\": \"HTTP/1.1\",\n" +
            "        \"stage\": \"test\",\n" +
            "        \"domainPrefix\": \"fhul32ccy2\",\n" +
            "        \"requestTimeEpoch\": 1590571934872,\n" +
            "        \"requestId\": \"b96500aa-f92a-43c3-9360-868ba4053a00\",\n" +
            "        \"identity\": {\n" +
            "            \"cognitoIdentityPoolId\": null,\n" +
            "            \"accountId\": null,\n" +
            "            \"cognitoIdentityId\": null,\n" +
            "            \"caller\": null,\n" +
            "            \"sourceIp\": \"90.37.8.133\",\n" +
            "            \"principalOrgId\": null,\n" +
            "            \"accessKey\": null,\n" +
            "            \"cognitoAuthenticationType\": null,\n" +
            "            \"cognitoAuthenticationProvider\": null,\n" +
            "            \"userArn\": null,\n" +
            "            \"userAgent\": \"curl/7.54.0\",\n" +
            "            \"user\": null\n" +
            "        },\n" +
            "        \"domainName\": \"fhul32ccy2.execute-api.eu-west-3.amazonaws.com\",\n" +
            "        \"apiId\": \"fhul32ccy2\"\n" +
            "    },\n" +
            "    \"body\":\"hello\",\n" +
            "    \"isBase64Encoded\": false\n" +
            "}";

    @SuppressWarnings("rawtypes")
    @Test
    public void testApiGatewayStringEventBody() throws Exception {
        System.setProperty("MAIN_CLASS", App.class.getName());
        System.setProperty("spring.cloud.function.definition", "createBusinessErrorFunction");
        FunctionInvoker invoker = new FunctionInvoker();

        InputStream targetStream = new ByteArrayInputStream(this.apiGatewayEvent.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        invoker.handleRequest(targetStream, output, null);
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.readValue(output.toByteArray(), Map.class);
    }

}

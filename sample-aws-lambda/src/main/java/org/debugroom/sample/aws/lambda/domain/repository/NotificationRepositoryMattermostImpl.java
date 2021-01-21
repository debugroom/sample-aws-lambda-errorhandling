package org.debugroom.sample.aws.lambda.domain.repository;

import java.util.Map;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterRequest;
import com.amazonaws.services.simplesystemsmanagement.model.GetParameterResult;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class NotificationRepositoryMattermostImpl implements NotificationRepository{

    @Autowired
    AWSSimpleSystemsManagement awsSimpleSystemsManagement;

    private String getParameterFromParameterStore(String paramName, boolean isEncripted){
        GetParameterRequest request = new GetParameterRequest();
        request.setName(paramName);
        request.setWithDecryption(isEncripted);
        GetParameterResult getParameterResult = awsSimpleSystemsManagement.getParameter(request);
        return getParameterResult.getParameter().getValue();
    }

    @Override
    public void save(Map<String, Object> messages) {
        WebClient webClient = WebClient.builder()
                .baseUrl(getParameterFromParameterStore("sample-aws-lambda-errorhandling-mattermost-webhook-url", false))
                .build();
        webClient.post()
                .bodyValue(messages)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

}

package org.debugroom.sample.aws.lambda.config;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import org.debugroom.sample.aws.lambda.domain.repository.NotificationRepository;
import org.debugroom.sample.aws.lambda.domain.repository.NotificationRepositoryMattermostImpl;
import org.debugroom.sample.aws.lambda.domain.service.SampleService;
import org.debugroom.sample.aws.lambda.domain.service.SampleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    SampleService sampleService(){
        return new SampleServiceImpl();
    }

    @Bean
    NotificationRepository notificationRepository(){
        return new NotificationRepositoryMattermostImpl();
    }

    @Bean
    AWSSimpleSystemsManagement awsSimpleSystemsManagement(){
        return AWSSimpleSystemsManagementClientBuilder.standard().defaultClient();
    }


}

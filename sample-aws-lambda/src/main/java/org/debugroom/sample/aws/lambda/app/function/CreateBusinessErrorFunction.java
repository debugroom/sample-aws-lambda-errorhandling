package org.debugroom.sample.aws.lambda.app.function;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import org.debugroom.sample.aws.lambda.app.model.Sample;
import org.debugroom.sample.aws.lambda.common.exception.BusinessException;
import org.debugroom.sample.aws.lambda.domain.service.SampleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreateBusinessErrorFunction implements Function<Map<String, Object>, Message<Sample>> {

    @Autowired
    SampleService sampleService;

    @Override
    public Message<Sample> apply(Map<String, Object> stringObjectMap){
        log.info(this.getClass().getName() + " has started!");

        for(String key : stringObjectMap.keySet()){
            Object value = stringObjectMap.get(key);
            if(Objects.nonNull(value)){
                log.info("[Key]" + key + " [Value]" + value.toString());
            }else {
                log.info("[Key]" + key + " [Value]" + "null");
            }
        }

        try{
            sampleService.executeThrowsBusinessException();
        }catch (BusinessException e){
            Map<String, Object> headers = new HashMap<>();
            headers.put("statusCode", 400);
            headers.put("X-Amzn-ErrorType", "BadReeuest");
            MessageHeaders messageHeaders = new MessageHeaders(headers);
            return MessageBuilder
                    .createMessage(
                            Sample.builder().text("Error!").build(), messageHeaders);
        }
        return MessageBuilder.withPayload(Sample.builder().text("Complete!").build()).build();
    }

}

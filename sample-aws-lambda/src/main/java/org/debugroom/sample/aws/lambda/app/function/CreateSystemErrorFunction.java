package org.debugroom.sample.aws.lambda.app.function;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;
import org.debugroom.sample.aws.lambda.domain.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import org.debugroom.sample.aws.lambda.app.model.Sample;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
public class CreateSystemErrorFunction implements Function<Map<String, Object>, Message<Sample>> {

    @Autowired
    SampleService sampleService;

    @Override
    public Message<Sample> apply(Map<String, Object> stringObjectMap) {
        log.info(this.getClass().getName() + " has started!");
        for(String key : stringObjectMap.keySet()){
            Object value = stringObjectMap.get(key);
            if(Objects.nonNull(value)){
                log.info("[Key]" + key + " [Value]" + value.toString());
            }else {
                log.info("[Key]" + key + " [Value]" + "null");
            }
        }
        sampleService.executeThrowsSystemException();
        return MessageBuilder.withPayload(Sample.builder().text("Complete!").build()).build();
    }

}

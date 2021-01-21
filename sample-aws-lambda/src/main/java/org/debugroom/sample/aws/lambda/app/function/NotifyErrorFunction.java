package org.debugroom.sample.aws.lambda.app.function;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.zip.GZIPInputStream;

import com.amazonaws.util.Base64;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import org.debugroom.sample.aws.lambda.app.model.Sample;
import org.debugroom.sample.aws.lambda.domain.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyErrorFunction implements Function<Map<String, Object>, Message<Sample>> {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    NotificationRepository notificationRepository;

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
        Map<String, Object> dataMap = (Map)stringObjectMap.get("awslogs");
        byte[] compressedDecodeLogs = Base64.decode(
                ((String)dataMap.get("data")).getBytes(StandardCharsets.UTF_8));
        Map<String, Object> decodeLogMap = new HashMap<>();
        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedDecodeLogs);
                GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream);
                InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)){

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            decodeLogMap = objectMapper.readValue(stringBuilder.toString(), new TypeReference<Map<String, Object>>() {});
            log.info(decodeLogMap.toString());
        } catch (IOException e){
            log.info(decodeLogMap.toString());
        }

        Map<String, Object> message = new HashMap<>();
        message.put("text", decodeLogMap.toString());
        message.put("channel", "aws-lambda-test-notification");
        notificationRepository.save(message);

        return MessageBuilder.withPayload(Sample.builder().text("Complete!").build()).build();

    }


}

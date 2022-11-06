package com.ipo.subscribe.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractEvent {

    String eventType;
    String timestamp;

    public AbstractEvent(){
        this.setEventType(this.getClass().getSimpleName());
        SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
        this.timestamp = defaultSimpleDateFormat.format(new Date());
    }

    public String toJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }

        return json;
    }

    // public void publish(String json){
    //     if( json != null ){
    //         log.info("null  ! ({})", json);
    //         /**
    //          * spring streams 방식
    //          */
    //         KafkaProcessor processor = SubscribeApplication.applicationContext.getBean(KafkaProcessor.class);
    //         MessageChannel outputChannel = processor.outboundTopic();
    //         log.info("publish ({})", outputChannel);
    //         outputChannel.send(MessageBuilder
    //                 .withPayload(json)
    //                 .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
    //                 .build());
    //         log.info("send ({})", json);
    //     }
    //     log.info("null ({})", json);
    // }

    // public void publish(){
    //     this.publish(this.toJson());
    // }

    // public void publishAfterCommit(){
    //     TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
    //         @Override
    //         public void afterCompletion(int status) {
    //             AbstractEvent.this.publish();
    //         }
    //     });
    // }


    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public boolean validate(){
        return getEventType().equals(getClass().getSimpleName());
    }
}

package com.ipo.subscribe.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipo.subscribe.domain.event.SubscribeCanceled;
import com.ipo.subscribe.domain.event.SubscribeCompleted;

import java.util.concurrent.ExecutionException;

public interface SubscribeProducer {


    void saveSubscribe(SubscribeCompleted subscribeCompleted) throws ExecutionException, InterruptedException, JsonProcessingException;

    void cancelSubscribe(SubscribeCanceled subscribeCanceled) throws InterruptedException, ExecutionException, JsonProcessingException;

}

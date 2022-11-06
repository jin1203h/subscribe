package com.ipo.subscribe.domain.event;

import com.ipo.subscribe.config.AbstractEvent;
import com.ipo.subscribe.domain.SubscribeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscribeCompleted extends AbstractEvent {

    private Long subscribeId;
    private Long memberId;
    private Long ipoStockId;
    private String ipoStockName;
    private String subscribeDate;
    private Long depositQuantity;
    private Long depositAmount;
    private String returnAccountNo;
    private String receiveAccountNo;
    private SubscribeStatus subscribeStatus;

}
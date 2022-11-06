package com.ipo.subscribe.rest.dto;

import java.util.List;

import com.ipo.subscribe.domain.SubscribeStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SubscribeDTO {
    private Long subscribeId;
    private Long memberId;
    private Long ipoStockId;
    private String ipoStockName;
    private String subscribeDate;
    private Long depositQuantity;
    private Long depositAmount;
    private Long allocateQuantity;
    private Long allocateAmount;
    private Long returnAmount;
    private String returnAccountNo;
    private String receiveAccountNo;
    private String refuseYn;
    private String subscribeCategory;
    private String subscribeMethod;
    private String contactNo;
    private SubscribeStatus subscribeStatus;
}

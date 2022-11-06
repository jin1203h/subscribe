package com.ipo.subscribe.domain.event;

import com.ipo.subscribe.config.AbstractEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllocateCompleted extends AbstractEvent {
    private String eventType;
    private String timestamp;
    private Long subscribeId;
    private Long memberId;
    private Long ipoStocktId;
    private Long allocateQuantity;
    private Long allocateAmount;

}
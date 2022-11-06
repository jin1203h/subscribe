package com.ipo.subscribe.rest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionRateDTO implements Serializable {
    private Long ipoStockId;
    private Long CompetitionRate;

}

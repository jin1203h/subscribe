package com.ipo.subscribe.adapter;

import com.ipo.subscribe.config.FeignConfiguration;
import com.ipo.subscribe.rest.dto.CompetitionRateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name="Ipo", url = "http://localhost:8081", configuration = {FeignConfiguration.class})
public interface IpoClient {

    @GetMapping("/ipo/ipos/competition/{ipoStockId}")
    ResponseEntity<CompetitionRateDTO> findDompetitionRate(@PathVariable("ipoStockId") Long ipoStockId);
    
}

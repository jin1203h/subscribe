package com.ipo.subscribe.controller;

import com.ipo.subscribe.domain.Subscribe;
import com.ipo.subscribe.rest.dto.CompetitionRateDTO;
import com.ipo.subscribe.rest.dto.SubscribeDTO;
import com.ipo.subscribe.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SubscribeController {
    
    private final SubscribeService subscribeService;
    
    @GetMapping("/subscribes/{memberId}")
    public ResponseEntity<List<Subscribe>> subscribeList(@PathVariable("memberId") Long memberId) {

        List<Subscribe> subscribe = subscribeService.findSubscribe(memberId);

        return ResponseEntity.ok().body(subscribe);
    }

    @PostMapping("/subscribes")
    public ResponseEntity<SubscribeDTO> Subscribe(@RequestBody SubscribeDTO subscribeDTO) {
   
        subscribeService.subscribe(subscribeDTO);

        return new ResponseEntity<>(subscribeDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/subscribes/{subcribesId}")
    public ResponseEntity<SubscribeDTO> cancel(@PathVariable("subcribesId") Long subcribesId) {

        subscribeService.cancelSubscribe(subcribesId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @GetMapping("/subscribes/competitionRate/{ipoStockId}")
    public ResponseEntity<CompetitionRateDTO> competitionRate(@PathVariable("ipoStockId") Long ipoStockId) {

        ResponseEntity<CompetitionRateDTO> competitionRateDTO = subscribeService.findCompetitionRate(ipoStockId);

        return competitionRateDTO;
    }
}

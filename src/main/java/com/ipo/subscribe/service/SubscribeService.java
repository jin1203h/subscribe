package com.ipo.subscribe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipo.subscribe.adapter.IpoClient;
import com.ipo.subscribe.adapter.SubscribeProducer;
import com.ipo.subscribe.domain.Subscribe;
import com.ipo.subscribe.domain.event.AllocateCompleted;
import com.ipo.subscribe.domain.event.SubscribeCanceled;
import com.ipo.subscribe.domain.event.SubscribeCompleted;
import com.ipo.subscribe.repository.SubscribeRepository;
import com.ipo.subscribe.rest.dto.CompetitionRateDTO;
import com.ipo.subscribe.rest.dto.SubscribeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SubscribeService {
    
    private final SubscribeRepository subscribeRepository;
    private final SubscribeProducer subscribeProducer;
    private final IpoClient ipoClient;
    
    @Transactional
    public Long subscribe(SubscribeDTO subscribeDTO) {

        Subscribe subscribe = Subscribe.createSubscribe(subscribeDTO.getMemberId(),
                                                   subscribeDTO.getIpoStockId(),
                                                   subscribeDTO.getIpoStockName(),
                                                   subscribeDTO.getSubscribeDate(), 
                                                   subscribeDTO.getDepositQuantity(), 
                                                   subscribeDTO.getDepositAmount(), 
                                                   subscribeDTO.getAllocateQuantity(), 
                                                   subscribeDTO.getAllocateAmount(), 
                                                   subscribeDTO.getReturnAmount(), 
                                                   subscribeDTO.getReturnAccountNo(), 
                                                   subscribeDTO.getReceiveAccountNo(), 
                                                   subscribeDTO.getRefuseYn(),
                                                   subscribeDTO.getSubscribeCategory(),
                                                   subscribeDTO.getSubscribeMethod(),
                                                   subscribeDTO.getContactNo());

        subscribeRepository.save(subscribe);

        subscribeDTO.setSubscribeId(subscribe.getSubscribeId());

        SubscribeCompleted subscribeCompleted =  new SubscribeCompleted();
        BeanUtils.copyProperties(subscribeDTO, subscribeCompleted);

        log.info("subscribeCompleted ({})" , subscribeCompleted.toJson());

        try {
            subscribeProducer.saveSubscribe(subscribeCompleted);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return subscribe.getSubscribeId();

    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelSubscribe(Long subscribeId) {
        
        SubscribeCanceled subscribeCanceled =  new SubscribeCanceled();

        Subscribe subscribe = subscribeRepository.findOne(subscribeId);
        subscribe.cancelSubscribe();
        
        BeanUtils.copyProperties(subscribe, subscribeCanceled);
       
        log.info("subscribeCanceled ({})" , subscribeCanceled.toJson());

        try {
            subscribeProducer.cancelSubscribe(subscribeCanceled);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateSubscribe(AllocateCompleted allocateCompleted) {

        log.info("allocateCompleted ({})" , allocateCompleted.toJson());
        Subscribe subscribe = subscribeRepository.findOne(allocateCompleted.getSubscribeId());
        subscribe.updateAllocate(subscribe, allocateCompleted);
        log.info("allocateCompleted ({})" , allocateCompleted.toJson());
        
    }

    public List<Subscribe> findSubscribe(Long memberId) {

        List<Subscribe> subscribe = subscribeRepository.findByMember(memberId);

        return subscribe;
    }

    public ResponseEntity<CompetitionRateDTO> findCompetitionRate(Long ipoStockId) {

        ResponseEntity<CompetitionRateDTO> competitionRateDTO = ipoClient.findDompetitionRate(ipoStockId);
        log.info("competitionRate ({})" , competitionRateDTO.getBody().getCompetitionRate());

        return competitionRateDTO;
    }
}

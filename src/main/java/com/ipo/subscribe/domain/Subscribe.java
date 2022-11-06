package com.ipo.subscribe.domain;

import com.ipo.subscribe.domain.event.AllocateCompleted;
import com.ipo.subscribe.exception.IllegalStateException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter @Setter
@Slf4j
public class Subscribe {
    
    @Id
    @GeneratedValue
    @Column(name = "subscribe_id")
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

    @Enumerated(EnumType.STRING)
    private SubscribeStatus subscribeStatus; // SUBSCRIBE, CANCLE

    




    // create method
    public static Subscribe createSubscribe(Long memberId,
                                            Long ipoStockId,
                                            String ipoStockName,
                                            String subscribeDate,
                                            Long depositQuantity,
                                            Long depositAmount,
                                            Long allocateQuantity,
                                            Long allocateAmount,
                                            Long returnAmount,
                                            String returnAccountNo,
                                            String receiveAccountNo,
                                            String refuseYn,
                                            String subscribeCategory,
                                            String subscribeMethod,
                                            String contactNo) {
        Subscribe subscribe = new Subscribe();
        subscribe.setMemberId(memberId);
        subscribe.setIpoStockId(ipoStockId);
        subscribe.setIpoStockName(ipoStockName);
        subscribe.setSubscribeDate(subscribeDate);
        subscribe.setDepositQuantity(depositQuantity);
        subscribe.setDepositAmount(depositAmount);
        subscribe.setAllocateQuantity(allocateQuantity);
        subscribe.setAllocateAmount(allocateAmount);
        subscribe.setReturnAmount(returnAmount);
        subscribe.setReturnAccountNo(returnAccountNo);
        subscribe.setReceiveAccountNo(receiveAccountNo);
        subscribe.setRefuseYn(refuseYn);
        subscribe.setSubscribeCategory(subscribeCategory);
        subscribe.setSubscribeMethod(subscribeMethod);
        subscribe.setContactNo(contactNo);
        subscribe.setSubscribeStatus(SubscribeStatus.SUBSCRIBE);

        return subscribe;
    }

    // cancel subscribe
    public void cancelSubscribe() {
        if (allocateQuantity > 0) {
            throw new IllegalStateException("공모주 배정이 완료되어 취소가 불가능합니다.");
        } else {
            this.setSubscribeStatus(SubscribeStatus.CANCEL);
        }
    }

    // update subscribe
    public void updateAllocate(Subscribe subscribe, AllocateCompleted allocateCompleted) {

        this.setAllocateQuantity(allocateCompleted.getAllocateQuantity());
        this.setAllocateAmount(allocateCompleted.getAllocateAmount());
        log.info("allocateCompleted entity({})" , allocateCompleted.toJson());

    }

}

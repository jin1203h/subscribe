package com.ipo.subscribe.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.ipo.subscribe.domain.Subscribe;

@Repository
public class SubscribeRepository {
    
    private final EntityManager em;

    public SubscribeRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Subscribe subscribe) {
        em.persist(subscribe);
    }

    public Subscribe findOne(Long id) {
        return em.find(Subscribe.class, id);
    }

    public List<Subscribe> findAll() {
        return em.createQuery("select s from Subscribe s", Subscribe.class)
                .getResultList();
    }

    public List<Subscribe> findByMember(Long memberId) {
        return em.createQuery("select s from Subscribe s where s.memberId = :memberId", Subscribe.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

}

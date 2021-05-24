package com.example.demo.Service;

import com.example.demo.Entity.SubscriptionEntity;
import com.example.demo.Repository.SubscriptionRepository;
import com.example.demo.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository repository;

    public boolean checkSubscriptionID(String tenantId, String serviceId) {
        boolean checkSub = repository.checkSubscriptionID(tenantId,serviceId);
       return checkSub;
    }
    public List<String> getServiceId(String tenantId) {
        List<String > getServiceId = repository.getServiceId(tenantId);
        return getServiceId;
    }


    public SubscriptionEntity create(SubscriptionEntity entity) throws RecordNotFoundException
    {
        entity = repository.save(entity);
        return entity;
    }
}

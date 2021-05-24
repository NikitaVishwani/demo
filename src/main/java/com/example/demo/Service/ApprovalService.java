package com.example.demo.Service;

import com.example.demo.Entity.ApprovalEntity;
import com.example.demo.Entity.EmployeeEntity;
import com.example.demo.Repository.ApprovalRepository;
import com.example.demo.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalService {
    @Autowired
    ApprovalRepository repository;

    public ApprovalEntity create(ApprovalEntity entity) throws RecordNotFoundException
    {
        entity = repository.save(entity);
        return entity;
    }

     public ApprovalEntity createApproval(String tenantId,String serviceId ,String Status ,String update_req_id) throws RecordNotFoundException
     {
         ApprovalEntity entity = new ApprovalEntity();
         entity.setId(tenantId);
         entity.setSubscription_id(serviceId);
         entity.setStatus(Status);
         entity.setUpdate_req_id(update_req_id);
         entity = repository.save(entity);
         return entity;
     }

    public List<ApprovalEntity> getPendingStatus(String tenantId,String serviceId,String new_id) {
        List<ApprovalEntity> getServiceId = repository.getPendingStatus(tenantId,serviceId,new_id);
        return getServiceId;
    }

    public boolean checkStatus(String tenantId) {
        boolean checkSub = repository.checkStatus(tenantId);
        return checkSub;
    }

    public void saveApproval(ApprovalEntity entity){
        repository.save(entity);
    }
    public void delete(ApprovalEntity entity){
      repository.delete(entity);
    }

   public EmployeeEntity getById(String Update_id){
      EmployeeEntity bpId = repository.getBpId(Update_id);
        return bpId;
   }


   public List<ApprovalEntity> getRecordsByTenantIdAndUpdateId(String tenantId, String new_id){
        return repository.getAllStatusByTenantIdAndUpdateId(tenantId, new_id);

    }

}

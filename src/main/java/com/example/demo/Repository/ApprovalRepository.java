package com.example.demo.Repository;

import com.example.demo.Entity.ApprovalEntity;
import com.example.demo.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository
         extends JpaRepository<ApprovalEntity, Long> {

   // List<ApprovalEntity> getServiceId(String tenantId);

    @Query(value = "SELECT id,subscription_id,update_req_id FROM Approval where status = Pending" +
            " and id = :tenanatId and subscription_id = :serviceId and update_req_id = :new_id", nativeQuery = true)
    List<ApprovalEntity> getPendingStatus(@Param("tenantId") String tenantId,@Param("serviceId") String serviceId ,@Param("new_id") String new_id);

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Approval s where s.status = ALL( select status from Approval where status = Approved) " , nativeQuery = true)
    boolean checkStatus(String tenantId);

    @Query(value = "SELECT id,subscription_id,update_req_id FROM Approval where" +
            " id = :tenanatId update_req_id = :newId", nativeQuery = true)
    List<ApprovalEntity> getAllStatusByTenantIdAndUpdateId(@Param("tenantId") String tenantId,@Param("newId") String newId);

    @Query(value = "Select id from Business_profile where id =:update_id",nativeQuery = true)
    EmployeeEntity getBpId(@Param("update_id") String update_id);


}
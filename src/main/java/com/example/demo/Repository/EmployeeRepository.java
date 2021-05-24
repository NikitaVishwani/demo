package com.example.demo.Repository;

import com.example.demo.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {

    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM Business_profile bp WHERE bp.id = :tenantID",nativeQuery = true)
     boolean checkBusinessProfile(@Param("tenantID") String tenantID);


}
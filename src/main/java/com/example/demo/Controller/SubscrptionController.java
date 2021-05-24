package com.example.demo.Controller;

import com.example.demo.Entity.ApprovalEntity;
import com.example.demo.Entity.EmployeeEntity;
import com.example.demo.Entity.SubscriptionEntity;
import com.example.demo.Service.ApprovalService;
import com.example.demo.Service.BusinessProfileService;
import com.example.demo.Service.SubscriptionService;
import com.example.demo.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    @RestController
    @RequestMapping("/subcription")
    class SubscriptionController {
      @Autowired
        BusinessProfileService service;
        @Autowired
        SubscriptionService service2;
        ApprovalService service3;

        private Map<String, String> getHeadersInfo(HttpServletRequest request) {
            Map<String, String> map = new HashMap<String, String>();
            Enumeration headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                map.put(key, value);
            }
            return map;
        }


        @GetMapping("/details")
        public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
            List<EmployeeEntity> list = service.getAllEmployees();

            return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
        }

        @Autowired
        private HttpServletRequest request;

        @PostMapping("/createsubscription")
        public ResponseEntity<SubscriptionEntity> create(SubscriptionEntity entity)
                throws RecordNotFoundException {
            String tenantId = request.getHeader("tenantid");
            String serviceId = request.getHeader("serviceid");
            Boolean bpCheck = service.checkBusinessProfile(tenantId);
            Boolean subsCheck = service2.checkSubscriptionID(tenantId,serviceId);
           if(bpCheck) {
               if (subsCheck) {
                   return new ResponseEntity<SubscriptionEntity>(new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
               } else {
                   SubscriptionEntity createSubscription = service2.create(entity);
                   return new ResponseEntity<SubscriptionEntity>(createSubscription, new HttpHeaders(), HttpStatus.OK);
               }
           }
           else{
                    return new ResponseEntity<SubscriptionEntity>(new HttpHeaders(), HttpStatus.EXPECTATION_FAILED);
                }
            }

        @PostMapping("/updatesubscription")
        public String update(@RequestBody EmployeeEntity entity) throws RecordNotFoundException {
            String tenantId = request.getHeader("tenantid");
            String serviceId = request.getHeader("serviceid");
            String new_id = null;
            Boolean subsCheck = service2.checkSubscriptionID(tenantId,serviceId);
            if(subsCheck){
                EmployeeEntity saveNewEmployeeEntity = service.create(entity);
                 new_id = saveNewEmployeeEntity.getId();
                List<String> s1 = service2.getServiceId(tenantId);
                for(int i =0;i<s1.size();i++){
                ApprovalEntity approvalEntity = service3.createApproval(tenantId,s1.get(i),"PENDING",new_id);
                }
            }
            return new_id;
        }

        @PostMapping("/validation/")
        public ResponseEntity<ApprovalEntity> validation(ApprovalEntity approvalEntity, @RequestBody String new_id) throws RecordNotFoundException {
        //assumption : we know what need to validate, changes are already done
            String tenantId = request.getHeader("tenantid");
            String serviceId = request.getHeader("serviceid");

            Boolean subsCheck = service2.checkSubscriptionID(tenantId,serviceId);
            if(subsCheck){
                List<ApprovalEntity> s1 = service3.getPendingStatus(tenantId,serviceId,new_id);
                s1.forEach(e -> {
                    e.setStatus("Approved");
                    service3.saveApproval(e);
                });
            }
            Boolean allApprovedStatus = true;
            List<ApprovalEntity> entities =service3.getRecordsByTenantIdAndUpdateId(tenantId, new_id);
            for(ApprovalEntity e : entities){
                if(!e.getStatus().equals("Approved")){
                    allApprovedStatus = false;
                    break;
                }
            };
            if(allApprovedStatus) {
                //String bp_id = service3.getBpId(new_id);
                EmployeeEntity newA = service3.getById(new_id);
                newA.setId(tenantId);
                service3.delete(new_id);
                EmployeeEntity updated = service.update(newA);
            }
            return new ResponseEntity<ApprovalEntity>( new HttpHeaders(), HttpStatus.OK);
        }
    }

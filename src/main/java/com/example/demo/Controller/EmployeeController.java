package com.example.demo.Controller;

import com.example.demo.Entity.EmployeeEntity;
import com.example.demo.Service.BusinessProfileService;
import com.example.demo.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController
{
    @Autowired
    BusinessProfileService service;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
        List<EmployeeEntity> list = service.getAllEmployees();

        return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        EmployeeEntity entity = service.getEmployeeById(id);

        return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EmployeeEntity> create(EmployeeEntity employee)
            throws RecordNotFoundException {
        EmployeeEntity updated = service.create(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    public ResponseEntity<EmployeeEntity> update(EmployeeEntity employee)
            throws RecordNotFoundException {
        EmployeeEntity updated = service.create(employee);
        return new ResponseEntity<EmployeeEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
            throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }

}

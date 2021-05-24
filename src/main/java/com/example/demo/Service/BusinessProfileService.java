package com.example.demo.Service;

import com.example.demo.Entity.EmployeeEntity;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessProfileService {

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeEntity> getAllEmployees()
    {
        List<EmployeeEntity> employeeList = repository.findAll();

        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<EmployeeEntity>();
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("yes");
        }
    }

    public EmployeeEntity create(EmployeeEntity entity) throws RecordNotFoundException
    {

        entity = repository.save(entity);
         return entity;
    }
    public EmployeeEntity update(EmployeeEntity entity) throws RecordNotFoundException
    {
        entity = repository.save(entity);
        return entity;
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<EmployeeEntity> employee = repository.findById(id);

        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException( "yes" );
        }
    }

    public boolean checkBusinessProfile(String tenantId){
        boolean checkBp = repository.checkBusinessProfile(tenantId);
        return checkBp;
    }

}
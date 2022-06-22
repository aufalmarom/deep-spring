package com.domain.explore.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.explore.dto.ResponseData;
import com.domain.explore.dto.SearchDTO;
import com.domain.explore.dto.SupplierDTO;
import com.domain.explore.models.entities.Supplier;
import com.domain.explore.services.SupplierService;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                responseData.getMessages().add(objectError.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // # Option 1 - manual
        // mapping DTO to service
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierDTO.getName());
        // supplier.setAddress(supplierDTO.getAddress());
        // supplier.setEmail(supplierDTO.getEmail());
        // # Option 2 - Model Mapper

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Supplier> findAll() {
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDTO supplierDTO, Errors errors) {
        ResponseData<Supplier> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError objectError : errors.getAllErrors()) {
                responseData.getMessages().add(objectError.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        // # Option 1 - manual
        // mapping DTO to service
        // Supplier supplier = new Supplier();
        // supplier.setName(supplierDTO.getName());
        // supplier.setAddress(supplierDTO.getAddress());
        // supplier.setEmail(supplierDTO.getEmail());
        // # Option 2 - Model Mapper

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/search/email")
    public Supplier findSupplierByEmail(@RequestBody SearchDTO searchDTO) {
        return supplierService.findSupplierByEmail(searchDTO.getSearchKey());
    }

    @PostMapping("/search/name")
    public List<Supplier> findSupplierByName(@RequestBody SearchDTO searchDTO) {
        return supplierService.findSupplierByName(searchDTO.getSearchKey());
    }

    @PostMapping("/search/name/prefix")
    public List<Supplier> findSupplierByNameStartsWith(@RequestBody SearchDTO searchDTO) {
        return supplierService.findSupplierByNameStartsWith(searchDTO.getSearchKey());
    }

    @PostMapping("/search/nameoremail")
    public List<Supplier> findSupplierByNameOrEmail(@RequestBody SearchDTO searchDTO) {
        return supplierService.findSupplierByNameOrEmail(searchDTO.getSearchKey(), searchDTO.getOtherSearchKey());
    }

}

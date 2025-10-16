package com.etiya.searchservice.controller;

import com.etiya.searchservice.domain.CustomerSearch;
import com.etiya.searchservice.service.CustomerSearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer-search")
public class CustomerSearchController {

    private final CustomerSearchService customerSearchService;

    public CustomerSearchController(CustomerSearchService customerSearchService) {
        this.customerSearchService = customerSearchService;
    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List<CustomerSearch> findAll(){
//        return customerSearchService.findAll();
//    }
//
//    @DeleteMapping("{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public void delete(@PathVariable String id){
//        customerSearchService.delete(id);
//    }

    @GetMapping("Fulltext")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> search(@RequestParam String keyword) {
        return customerSearchService.searchAllFields(keyword);
    }
    @GetMapping("firstName")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByFirstName(@RequestParam String firstName) {
        return customerSearchService.findByFirstNameUsingMatch(firstName);
    }

    @GetMapping("natId")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByNatId(@RequestParam String natId) {
        return customerSearchService.findByNationalId(natId);
    }

    @GetMapping("misspelledFirstName")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerSearch> searchByMisspelledFirstName(@RequestParam String misspelledFirstName) {
        return customerSearchService.findByFirstNameUsingFuzzy(misspelledFirstName);
    }


}
package com.github.furi.sutao.salesworker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.github.furi.sutao.salesworker.exception.ResourceNotFoundException;
import com.github.furi.sutao.salesworker.model.Estimate;
import com.github.furi.sutao.salesworker.repository.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/v1/")
public class EstimateController {

    @Autowired
    private EstimateRepository estimateRepository;

    // get all estimates
    @GetMapping("/estimates")
    public List<Estimate> getAllEstimates() {
        return estimateRepository.findAll();
    }

    // get all estimates by like id
    @GetMapping("/estimates/like-id/{id}")
    public List<Estimate> getEstimatesByLikeId(@PathVariable String id) {
        return estimateRepository.getEstimatesByLikeId(id);
    }

    // get all estimates by like name
    @GetMapping("/estimates/like-name/{name}")
    public List<Estimate> getEstimatesByLikeName(@PathVariable String name) {
        return estimateRepository.getEstimatesByLikeName(name);
    }

    // get all estimates by like status
    @GetMapping("/estimates/like-status/{status}")
    public List<Estimate> getEstimatesByLikeStatus(@PathVariable String status) {
        return estimateRepository.getEstimatesByLikeStatus(status);
    }
    
    // get all estimates by customer code
    @GetMapping("/estimates/customer-cd/{cd}")
    public List<Estimate> getEstimatesByCustomerCd(@PathVariable String cd) {
        return estimateRepository.getEstimatesByCustomerCd(cd);
    }

    // get all estimates by employee code
    @GetMapping("/estimates/employee-cd/{cd}")
    public List<Estimate> getEstimatesByEmployeeCd(@PathVariable String cd) {
        return estimateRepository.getEstimatesByEmployeeCd(cd);
    }

    // create estimate rest api
    @PostMapping("/estimates")
    public Estimate createEstimate(@RequestBody Estimate estimate) {
        return estimateRepository.save(estimate);
    }

    // get estimate by id rest api
    @GetMapping("/estimates/{id}")
    public ResponseEntity<Estimate> getEstimateById(@PathVariable Long id) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estimate not exist with id :" + id));
        return ResponseEntity.ok(estimate);
    }

    // update estimate rest api
    @PutMapping("/estimates/{id}")
    public ResponseEntity<Estimate> updateEstimate(@PathVariable Long id,
            @RequestBody Estimate estimate) {
        Estimate estimateRepo = estimateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estimate not exist with id :" + id));

        estimateRepo.setName(estimate.getName());
        estimateRepo.setAmount(estimate.getAmount());
        estimateRepo.setBudgetedAmount(estimate.getBudgetedAmount());
        estimateRepo.setCustomerCd(estimate.getCustomerCd());
        estimateRepo.setEmployeeCd(estimate.getEmployeeCd());
        estimateRepo.setDate(estimate.getDate());
        estimateRepo.setStatus(estimate.getStatus());
        estimateRepo.setOrderId(estimate.getOrderId());

        Estimate updatedEstimate = estimateRepository.save(estimateRepo);
        return ResponseEntity.ok(updatedEstimate);
    }

    // delete estimate rest api
    @DeleteMapping("/estimates/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEstimate(@PathVariable Long id) {
        Estimate estimate = estimateRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estimate not exist with id :" + id));

        estimateRepository.delete(estimate);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}

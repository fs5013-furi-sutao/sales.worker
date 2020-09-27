package com.github.furi.sutao.salesworker.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.github.furi.sutao.salesworker.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Page<Customer> findByNameContaining(String name, Pageable pageable);
    List<Customer> findByNameContaining(String name, Sort sort);
}

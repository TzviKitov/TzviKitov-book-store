package com.example.ex4template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT sum(amount) from Purchase")
    double sumAllAmounts();
    List<Purchase> findAllByOrderByTimestamp();
}

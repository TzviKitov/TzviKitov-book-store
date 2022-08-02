package com.example.ex4template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findFirst5ByOrderByDiscountDesc();
    List<Product> findByNameContaining(@Param("product")String partOfName);
}

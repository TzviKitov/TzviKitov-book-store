package com.example.ex4template.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findFirst5ByOrderByDiscountDesc();
    List<Product> findByNameContaining(String partOfName);

    /* add here the queries you may need - in addition to CRUD operations
        List<Product> findByName(String name);
    List<User> findUserByUserName(String userName);
    List<User> findByEmail(String email);
    List<User> findByUserNameAndEmail(String userName, String email);

     */
}

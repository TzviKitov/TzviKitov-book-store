package com.example.ex4template.services;

import com.example.ex4template.repo.Product;
import com.example.ex4template.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional
    public void updateQuantities(ArrayList<Long> productsBasketIds) {
        //int repoCounter = 1;
        for (Long id : productsBasketIds) {
            Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            int quantity = product.getQuantity();
            //int repoCounter=1;repoCounter<=productsBasketSize;repoCounter++
            if (quantity > 0)
                product.setQuantity(quantity - 1);
            else
                throw new ProductNotExistException("The marked products are out of stock. You should complete the purchase and delete the missing products from the basket.");
            repository.save(product);
        }
    }
//    List<Product> findFirst5ByOrderByDiscountDesc();
//    List<Product> findByName(String name);
//    List<Product> findByNameContaining(String partOfName);

//    public void saveUser(User user) {
//        repository.save(user);
//    }

//    public void deleteUser(long id) {
//        repository.deleteById(id);
//    }
//
//    public void deleteUser(User u) {
//        repository.delete(u);
//    }
//
//    public void updateUser(User user) {
//        repository.save(user);
//    }
//
//    public Optional<Product> getProduct(Long id) {
//        return repository.findById(id);
//    }
//
//    public List<User> getUsers() {
//        return repository.findAll();
//    }
}

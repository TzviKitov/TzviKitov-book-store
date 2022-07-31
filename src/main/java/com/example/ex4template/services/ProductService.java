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
        final String notExistMessage="The marked products are out of stock. You should complete the purchase and delete the missing products from the basket.";
        //int repoCounter = 1;
        for (Long id : productsBasketIds) {
            Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            int quantity = product.getQuantity();
            //int repoCounter=1;repoCounter<=productsBasketSize;repoCounter++
            if (quantity > 0)
                product.setQuantity(quantity - 1);
            else
                throw new ProductNotExistException(notExistMessage);
            repository.save(product);
        }
    }
}

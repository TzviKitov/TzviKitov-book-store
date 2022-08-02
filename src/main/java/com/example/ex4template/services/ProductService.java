package com.example.ex4template.services;

import com.example.ex4template.repo.Product;
import com.example.ex4template.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    static final String notExistMessage = "The marked products are out of stock. To complete the purchase, the missing products must be deleted from the basket.";

    /**
     * Transactional Updates data in the database only if all products were in stock
     * @param productsBasketIds for dec if quantity product > 0
     */
    @Transactional
    public void updateQuantities(ArrayList<Long> productsBasketIds) {
        for (Long id : productsBasketIds) {
            Product product = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            int quantity = product.getQuantity();
            if (quantity > 0)
                product.setQuantity(quantity - 1);
            else
                throw new ProductNotExistException(notExistMessage);
            repository.save(product);
        }
    }
}

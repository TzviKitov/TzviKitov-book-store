package com.example.ex4template.controllers;

import com.example.ex4template.repo.Product;
import com.example.ex4template.repo.ProductRepository;
import com.example.ex4template.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;

@Controller
public class AdminController {


    @Autowired
    private ProductRepository repository;
    private ProductRepository getProductRepo() {
        return repository;
    }

    @Autowired
    private PurchaseRepository purchaseRepository;

    /**
     *
     * @param model modal
     * @return product-management page
     */
    @GetMapping("/admin")
    public String main(Model model) {
        model.addAttribute("products", getProductRepo().findAll());
        return "product-management";
    }

    /**
     *
     * @param model model
     * @return purchases page
     */
    @GetMapping("/admin/purchases")
    public String showPurchase(Model model) {
        model.addAttribute("purchases", purchaseRepository.findAllByOrderByTimestamp());
        model.addAttribute("totalPurchases", purchaseRepository.sumAllAmounts());
        return "purchases";
    }

    /**
     *
     * @param product current product
     * @return add-product form
     */
    @GetMapping("/admin/addproduct")
    public String showAddProductForm(Product product) {
        return "add-product";
    }

    /**
     *
     * @param product update product
     * @param result of validations
     * @return add-product form if not valid or redirect:/admin - product-management page
     */
    @PostMapping("/admin/addproduct")
    public String addProduct(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "add-product";
        }
        getProductRepo().save(product);
        return "redirect:/admin";//"product-management";
    }

    /**
     *
     * @param id of edit product
     * @param model modal
     * @return update-product form
     */
    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {
        Product product = getProductRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        return "update-product";
    }

    /**
     *
     * @param id of product to update
     * @param product input details to update
     * @param result of validations inputs
     * @return update-product form if not valid or redirect:/admin - product-management page
     */
    @PostMapping("/admin/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        getProductRepo().save(product);
        //model.addAttribute("products", getRepo().findAll());
        return "redirect:/admin";//"product-management";
    }

    /**
     *
     * @param id froduct for delete from DB
     * @return "redirect:/admin" after deleted
     */
    @PostMapping("/admin/delete")
    public String deleteProduct(@RequestParam("id") long id) {
        Product product = getProductRepo()
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );
        getProductRepo().delete(product);
        return "redirect:/admin";//"product-management";
    }

}

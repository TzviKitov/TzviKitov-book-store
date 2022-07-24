package com.example.ex4template.controllers;

import com.example.ex4template.repo.Product;
import com.example.ex4template.repo.ProductRepository;
import com.example.ex4template.repo.PurchaseRepository;
import com.example.ex4template.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProductController {

//    @Value( "${demo.coursename}" )
//    private String someProperty;

    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {
        return repository;
    }

    @Autowired
    private PurchaseRepository purchaseRepository;


    @GetMapping("/admin")
    public String main(Product product, Model model, Principal principal) {
//        model.addAttribute("course", someProperty);

        // the name "users"  is bound to the VIEW
        model.addAttribute("products", getRepo().findAll());
        return "product-management";
    }

    @GetMapping("/admin/purchase")
    public String showPurchase(Model model){
        model.addAttribute("purchases",purchaseRepository.findAll());
        model.addAttribute("totalPurchases",purchaseRepository.sumAllAmounts());
        return "purchase";
    }

    //@GetMapping("/addproduct")
    //public String showSignUpForm(Product product, Model model) {
        //model.addAttribute("user", new User("noname","noemail"));
      //  return "add-product";
    //}
    @GetMapping("/admin/addproduct")
    public String showAddProductForm(Product product) {
        return "add-product";
    }

    @PostMapping("/admin/addproduct")
    public String addProduct(@Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-product";
        }
        getRepo().save(product);
        //model.addAttribute("products", getRepo().findAll());
        return "redirect:/admin";//"product-management";
    }

    @GetMapping("/admin/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model) {

        Product product = getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        // the name "user"  is bound to the VIEW
        model.addAttribute("product", product);
        return "update-product";
    }

    @PostMapping("/admin/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            product.setId(id);
            return "update-product";
        }
        getRepo().save(product);
        //model.addAttribute("products", getRepo().findAll());
        return "redirect:/admin";//"product-management";
    }

    @PostMapping("/admin/delete")
    public String deleteProduct(@RequestParam("id") long id, Model model) {

        Product product = getRepo()
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Invalid product Id:" + id)
                );
        getRepo().delete(product);
     //   model.addAttribute("products", getRepo().findAll());
        return "redirect:/admin";//"product-management";
    }




    @Override
    public String toString() {
        return "fdsfa";
    }

    }

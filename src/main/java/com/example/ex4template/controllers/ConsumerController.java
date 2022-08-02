package com.example.ex4template.controllers;

import com.example.ex4template.beans.ShoppingBasket;
import com.example.ex4template.repo.Product;
import com.example.ex4template.repo.ProductRepository;
import com.example.ex4template.repo.Purchase;
import com.example.ex4template.repo.PurchaseRepository;
import com.example.ex4template.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;

/**
 * Customer care: store, shopping basket and payment
 */
@Controller
public class ConsumerController {
    static final String amountMessage="The payment in the amount ";
    static final String successPeyMessage="$ was made successfully!";

    @Autowired
    private ProductRepository repository;
    private ProductRepository getProductRepo() {
        return repository;
    }

    @Resource(name = "ShoppingBasket")
    private ShoppingBasket shoppingBasket;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    /**
     *
     * @param model modal
     * @return main store page
     */
    @GetMapping("/")
    public String main( Model model) {
        model.addAttribute("products", getProductRepo().findFirst5ByOrderByDiscountDesc());
        model.addAttribute("basketSize",shoppingBasket.size());
        return "index";
    }

    /**
     *
     * @param partOfName part string input for searching
     * @param model modal
     * @return the store page  after filtering products
     */
    @GetMapping("/search")
    public String productSearch(@RequestParam String partOfName, Model model){
       ////////////////need exception?
        model.addAttribute("products", getProductRepo().findByNameContaining(partOfName));
        model.addAttribute("basketSize",shoppingBasket.size());
        return "index";
    }

    /**
     *
     * @param id product for added to basket
     * @return redirect to main store page
     */
    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam("id") Long id) {
            shoppingBasket.add(id);
        return "redirect:/";
    }

    /**
     * Displays the products in the shopping cart, which displays the products in the basket, the updated message and the total price of the products available at the current moment
     * @param model modal
     * @return shopping-basket page
     */
    @GetMapping("/basket")
    public String shoppingBasket(Model model) {
        ArrayList<Product> products= new ArrayList<>();
        Product product;
        double totalPrice=0;
        for (Long id: shoppingBasket.getIdProducts()) {
            product=getProductRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            products.add(product);
            if(product.getQuantity()>0)totalPrice+=(product.getPrice()- product.getPrice()*product.getDiscount()/100);
        }
        shoppingBasket.setTotalPrice(totalPrice);
        model.addAttribute("basketMessage",shoppingBasket.getPaymentMessage());
        model.addAttribute("shoppingBasket", products);
        model.addAttribute("totalPrice", String.format("%.2f", totalPrice));
        shoppingBasket.setPaymentMessage("");
        return "shopping-basket";
    }

    /**
     * remove product 1 from the basket
     * @param id of product
     * @return redirect:/basket
     */
    @PostMapping("/basket/remove")
    public String removeFromBasket(@RequestParam("id") Long id) {
        shoppingBasket.remove(id);
        return "redirect:/basket";
    }

    /**
     * empty the basket
     * @return redirect:/basket
     */
    @PostMapping("/basket/removeall")
    public String emptyTheBasket() {
        shoppingBasket.removeAll();
        return "redirect:/basket";
    }

    /**
     *Handles payment for the products, in case a product is missing from the stock at the moment of payment, the payment will not be made and the basket will be displayed with appropriate messages. If all the products are in stock, the purchase will be approved, the product will be deducted from the stock, the product basket will be emptied and displayed with a success message about the purchase amount, and the purchase will be saved in the database
     * @return Redirection to the shopping cart page after updating the message and the current amount on the shopping cart object
     *  Exception in the event of an error in checking the inventory in the database, or in the event that the product is out of stock
     */
    @GetMapping("/basket/payment")
    public String payment(Principal principal) {
        try {    // this transaction will fail and throw an exception
            productService.updateQuantities(shoppingBasket.getIdProducts());
        } catch (Exception e) {
            shoppingBasket.setPaymentMessage(e.getMessage());
            return "redirect:/basket";
        }
        shoppingBasket.setPaymentMessage(amountMessage + String.format("%.2f", shoppingBasket.getTotalPrice()) + successPeyMessage);
        purchaseRepository.save(new Purchase(  shoppingBasket.getTotalPrice(),principal.getName()));
        return emptyTheBasket();
    }
}

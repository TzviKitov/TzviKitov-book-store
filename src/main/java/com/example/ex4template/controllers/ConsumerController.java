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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class ConsumerController {
    @Autowired
    private ProductRepository repository;
    private ProductRepository getRepo() {
        return repository;
    }

    @Resource(name = "ShoppingBasket")
    private ShoppingBasket shoppingBasket;

    @Autowired
    private ProductService productService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @GetMapping("/")
    public String main(Product product, Model model) {
//        model.addAttribute("course", someProperty);

        // the name "users"  is bound to the VIEW
        model.addAttribute("products", getRepo().findFirst5ByOrderByDiscountDesc());
        model.addAttribute("basketSize",shoppingBasket.size());

        return "index";
    }

    @GetMapping("/search")
    public String productSearch(@RequestParam String partOfName, Model model){
       ////////////////need exception?
        model.addAttribute("products", getRepo().findByNameContaining(partOfName));
        model.addAttribute("basketSize",shoppingBasket.size());
        return "index";
    }

    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam("id") Long id) {
//        if (id == null)/////////////////////////////////////////needed??????????????
//            System.out.println("null product !");
//        else
            shoppingBasket.add(id);
        //model.addAttribute("basketSize",shoppingBasket.size());
        return "redirect:/";
    }


    @GetMapping("/basket")
    public String process(Model model) {
        // we modify an application scoped bean
        //appMessages.add("hello"); // just for demo
        ArrayList<Product> products=new ArrayList<Product>();
        Product product= new Product();
        double totalPrice=0;
        for (Long id: shoppingBasket.getIdProducts()) {
            product=getRepo().findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
            products.add(product);
            if(product.getQuantity()>0)totalPrice+=(product.getPrice()- product.getPrice()*product.getDiscount()/100);
        }
        shoppingBasket.setTotalPrice(totalPrice);
        model.addAttribute("basketMessage",shoppingBasket.getPaymentMessage());
        model.addAttribute("shoppingBasket", products);
        model.addAttribute("totalPrice", totalPrice);
        shoppingBasket.setPaymentMessage("");
//        if((double)model.getAttribute("totalPrice")>0.00)
//        System.out.println("111111");
//        else         System.out.println("00000");

        return "shopping-basket";
    }

    @PostMapping("/basket/remove")
    public String removeFromBasket(@RequestParam("id") Long id) {
        shoppingBasket.remove(id);
        return "redirect:/basket";
    }

    @PostMapping("/basket/removeall")
    public String emptyTheBaske() {
        shoppingBasket.removeAll();
        return "redirect:/basket";
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/basket/payment")
    public String payment(Principal principal) throws Exception {
        try {
            // this transaction will fail and throw an exception
            productService.updateQuantities(shoppingBasket.getIdProducts());
        } catch (Exception e) {
            System.out.println(e.toString());
            shoppingBasket.setPaymentMessage(e.getMessage());
            return "redirect:/basket";
//            model.addAttribute("message", "Sorry we could not perform your request!");
        } finally {
//            model.addAttribute("users", userService.getUsers());
        }
        shoppingBasket.setPaymentMessage("The payment in the amount " + shoppingBasket.getTotalPrice() + "$ was made successfully!");
        purchaseRepository.save(new Purchase(shoppingBasket.getTotalPrice(),principal.getName()));
        return emptyTheBaske();//"redirect:/basket/removeall";
       // Product product=getRepo().findById((long)6).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
//      if((double)model.getAttribute("totalPrice")>0.00)
//        return "redirect:/";
//      else return "redirect:/basket";
    }

}


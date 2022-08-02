package com.example.ex4template.repo;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String image;

    @NotNull(message = "Quantity is mandatory")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be greater than 0")
    private double price;

    @NotNull(message = "Discount is mandatory")
    @DecimalMin(value = "0", message = "Discount must be greater than or equal to 0")
    @DecimalMax(value = "100", message = "Discount cannot be greater than 100")
    private double discount;

    public Product() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
         this.image= image.isEmpty()? "/images/default_product_cover_image.jpg" :image.trim();
    }

    public String getImage() {
        return image;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + "," +
                " image= " + image + ", discount=" + discount + ", price=" + price +
                ", quantity=" + quantity + '}';
    }

}

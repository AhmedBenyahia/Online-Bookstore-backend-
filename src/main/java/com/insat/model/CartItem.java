package com.insat.model;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cart_item")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    private Long bookid;


    private Long quantity ;


    private  Float price;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public  CartItem(){}
    public CartItem(@NotBlank Long bookid, @NotBlank Long quantity, @NotBlank Float price, Order order) {
        this.bookid = bookid;
        this.quantity = quantity;
        this.price = price * quantity;
        this.order = order;

    }

    public Float getPrice() {
        return price;
    }



    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getBook_id() {
        return bookid;
    }

    public void setBook_id(Long bookid) {
        this.bookid = bookid;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

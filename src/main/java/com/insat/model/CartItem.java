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

    @NotBlank
    private Long book_id;

    @NotBlank
    private Long quantity ;

    @NotBlank
    private Float price;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    public CartItem(@NotBlank Long book_id, @NotBlank Long quantity, @NotBlank Float price, Order order) {
        this.book_id = book_id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getBook_id() {
        return book_id;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

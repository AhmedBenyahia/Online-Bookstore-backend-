package com.insat.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    static Integer DELIVERED = 2;
    static Integer INPROGRESS = 1;
    static Integer NOTCONFIRMED = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer status;

    @NotBlank
    private String fullAddress;

    @NotBlank
    private String telephoneNumber;

    private Float totalCost ;

    @OneToMany(mappedBy = "order")
    private List<CartItem> cartItemList ;


    public Order(@NotBlank String fullAddress,
                 @NotBlank String telephoneNumber) {
        this.fullAddress = fullAddress;
        this.telephoneNumber = telephoneNumber;
        this.totalCost = (float) 0;
    }

    public void deleteItemsFromCart(CartItem item) {
        cartItemList.remove(item);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }



    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItemList.add(cartItem) ;
        this.totalCost += cartItem.getPrice();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

package com.insat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
public class Order {

    public static Integer DELIVERED = 2;
    public static Integer INPROGRESS = 1;
    public static Integer NOTCONFIRMED = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Integer status = 0;

    @NotBlank
    private String fullAddress;

    @NotBlank
    private String telephoneNumber;

    private  Float totalCost = (float) 0  ;

    @ManyToOne
    @JoinColumn(name="client_id")
    private Person client;

    @OneToMany(mappedBy = "order")
    private List<CartItem> cartItemList ;

    public Order(){}

    public Order(@NotBlank String fullAddress,
                 @NotBlank String telephoneNumber) {
        this.fullAddress = fullAddress;
        this.telephoneNumber = telephoneNumber;
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


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void updateTotalCost(Float totalCost) {
        this.totalCost += totalCost;
    }

    @JsonIgnore
    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }
}

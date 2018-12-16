package com.insat.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "order")
public class Order {


    @Id
    @GeneratedValue(  strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Integer amount ;

    @NotBlank
    private Long bookId;

    private boolean validation;


    public Order() {}

    public Order(@NotBlank Integer amount,
                 @NotBlank Long bookId) {
        this.amount = amount;
        this.bookId = bookId;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}

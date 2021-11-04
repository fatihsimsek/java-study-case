package com.readingisgood.ordermanagement.adapter;

import io.swagger.models.auth.In;

import java.math.BigDecimal;

public class BookDto {
    private String id;
    private String name;
    private String writerName;
    private BigDecimal amount;
    private Integer stock;

    public BookDto(String id, String name, String writerName, BigDecimal amount, Integer stock) {
        this.id = id;
        this.name = name;
        this.writerName = writerName;
        this.amount = amount;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

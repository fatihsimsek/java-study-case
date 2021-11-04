package com.readingisgood.ordermanagement.adapter;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto {
    public String id;
    private String bookId;
    private String customerId;
    private Integer count;
    private BigDecimal amount;
    private Date date;

    public OrderDto(String id, String bookId, String customerId, Integer count, BigDecimal amount, Date date) {
        this.id = id;
        this.bookId = bookId;
        this.customerId = customerId;
        this.count = count;
        this.amount = amount;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

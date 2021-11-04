package com.readingisgood.ordermanagement.adapter;

import io.swagger.models.auth.In;

import java.math.BigDecimal;

public class StatisticsDto {
    private String monthName;
    private Integer orderCount;
    private Integer bookCount;
    private BigDecimal amount;

    public  StatisticsDto() {
    }

    public StatisticsDto(String monthName, Integer orderCount, Integer bookCount, BigDecimal amount) {
        this.monthName = monthName;
        this.orderCount = orderCount;
        this.bookCount = bookCount;
        this.amount = amount;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

package com.readingisgood.ordermanagement.adapter;

public class BookUpdateStockRequest {
    private String bookId;
    private Integer stock;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

package br.edu.entities;

import java.time.LocalDate;

public class Product {
    private String description;
    private Double price;
    private String category;
    private LocalDate data;

    public Product(String description, Double price, String category, LocalDate data) {
        this.description = description;
        this.price = price;
        this.category = category;
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", data=" + data +
                '}';
    }
}

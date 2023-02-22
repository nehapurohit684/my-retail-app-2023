package com.target.retail.myretailapp.product;

public class Product {

    private String productId;
    private String name;
    private ProductPrice productPrice;

    public Product(String productId, String name, ProductPrice productPrice) {
        this.productId = productId;
        this.name = name;
        this.productPrice =productPrice;
    }
    public Product() { }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPrice getProductsPrice() {
        return productPrice;
    }

    public void setProductsPrice(ProductPrice productPrice) {
        this.productPrice = productPrice;
    }
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", productsPrice=" + productPrice +
                '}';
    }
}

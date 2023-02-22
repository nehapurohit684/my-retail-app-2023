package com.target.retail.myretailapp.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection="productsprice")
@AllArgsConstructor
public class ProductPrice {

    @Id
    private String id;
    private Double price;
    private String currencyCode;

    @JsonIgnore
    @JsonProperty(value = "productId")
    public String getProductId() {
        return id;
    }

    public void setProductId(String productId) {
        this.id = productId;
    }

}

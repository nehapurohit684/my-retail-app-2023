package com.target.retail.myretailapp.product;

import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;


@RestController
public class ProductController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;

    @GetMapping(value="product/{id}")
    public Product getProducts(@PathVariable String id) throws HttpClientErrorException, MongoException {
        logger.info("getting Product Info for ID: "+id);
        Product product;
        product=productService.getProductById(id);
        logger.info("Product Info :"+product);
        return product;
    }

    @PutMapping(value="product/{id}")
    public Product putProductDetails(@PathVariable String id,@RequestBody Product prodDetails) throws Exception{
        Product updatedProd=productService.updateProduct(id, prodDetails);
        return updatedProd;
    }
}

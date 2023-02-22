package com.target.retail.myretailapp.product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductsPriceRepository extends MongoRepository<ProductPrice,String> {

    public Optional<ProductPrice> findProductById(String id);

}

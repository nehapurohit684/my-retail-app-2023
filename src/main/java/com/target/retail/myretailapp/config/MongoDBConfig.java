package com.target.retail.myretailapp.config;

import com.target.retail.myretailapp.product.ProductPrice;
import com.target.retail.myretailapp.product.ProductsPriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackageClasses = ProductsPriceRepository.class)
public class MongoDBConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductsPriceRepository productsPriceReposiotry){
        List<ProductPrice> list = new ArrayList<>();
        list.add(new ProductPrice("13860428", 20.90, "USD"));
        list.add(new ProductPrice("54456119", 87.90, "USD"));
        list.add(new ProductPrice("12954218", 80.20, "USD"));
        return string->productsPriceReposiotry.saveAll(list);

 }

}

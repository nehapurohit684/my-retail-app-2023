package com.target.retail.myretailapp.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.target.retail.myretailapp.exception.ResourceNotFoundException;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;
import java.util.Map;

@Service
public class ProductService {

    private final Logger log = Logger.getLogger(ProductService.class.getName());

    @Autowired
    private ProductServiceProxy serviceProxy;

    @Autowired
    private ProductsPriceRepository productRepository;

    public Product getProductById(String productId) {
        ProductPrice productPrice = productRepository.findProductById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Price Info not found in Mongo DB for ID :" + productId));
        Product product = new Product();
        product.setProductId(productId);
        product.setProductsPrice(productPrice);
        product.setName(getProductName(productId));
        return product;

    }

    private String getProductName(String productId)  {
        Map<String, Map> prodMap = getProductDetailsFromRESTCall(productId);
        Map<String,Map> data = prodMap.get("data");
        Map<String,Map> productMap = data.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> prodDescrMap = itemMap.get(("product_description"));

        return prodDescrMap.get("title");
    }

    private Map<String, Map> getProductDetailsFromRESTCall(String tcin) {

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseEntity<String> response = serviceProxy.getProductInfoById("3yUxt7WltYG7MFKPp7uyELi1K40ad2ys",tcin).
                orElseThrow(() -> new ResourceNotFoundException("Product Info not found in REST API for ID :" + tcin));
        Map<String, Map> prodInfoFromREST = null;
        try {
            prodInfoFromREST = objectMapper.readValue(response.getBody(), Map.class);
        } catch (JsonProcessingException e) {
            log.error("Product Price info cant not be read");
        }

        return prodInfoFromREST;
    }

    public Product updateProduct(String id, Product product) {

        ProductPrice updatedProductsPrice=product.getProductsPrice();
        updatedProductsPrice.setProductId(id);
        if(productRepository.findProductById(updatedProductsPrice.getProductId())!=null){
            updatedProductsPrice=productRepository.save(product.getProductsPrice());
        }else{
            throw new MongoException("Price for product with id="+id+" not found in mongo db for collection 'productsprice'");
        }
        log.info("Price updated :" + updatedProductsPrice);
        product.setProductsPrice(updatedProductsPrice);
        return product;
    }
}

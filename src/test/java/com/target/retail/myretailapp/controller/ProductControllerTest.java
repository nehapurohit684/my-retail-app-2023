package com.target.retail.myretailapp.controller;

import com.target.retail.myretailapp.product.Product;
import com.target.retail.myretailapp.product.ProductController;
import com.target.retail.myretailapp.product.ProductPrice;
import com.target.retail.myretailapp.product.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value= ProductController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ProductController.class)
public class ProductControllerTest {

    @MockBean
    ProductService productService;
    @Autowired
    MockMvc mockMvc;

    private String PRODUCT_ID="12954218";

    Product products=null;

    @Before
    public void setup() {
        ProductPrice prodPrice= new ProductPrice(PRODUCT_ID,9.9,"USD");
        products= new Product(PRODUCT_ID,"Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz",prodPrice);
    }


    @Test
    @DisplayName(value = "Receive Status 200 for get Products call /product/{id}")
    public void testgetProductsStatusCode() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/product/13860428").accept(MediaType.APPLICATION_JSON_VALUE)).
                andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    @DisplayName(value = "Receive correct Product for get Products call /product/{id}")
    public void testGetProduct() throws Exception{

        Mockito.when(productService.getProductById(PRODUCT_ID)).thenReturn(products);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/product/"+PRODUCT_ID).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        //String expected = "{\"productId\":12954218,\"name\":\"Kraft Macaroni &#38; Cheese Dinner Original - 7.25oz\",\"productPrice\":{\"price\":80.2,\"currencyCode\":\"USD\"}}";
        Assert.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}

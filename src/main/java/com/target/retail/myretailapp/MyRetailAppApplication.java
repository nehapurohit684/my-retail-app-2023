package com.target.retail.myretailapp;

import com.target.retail.myretailapp.product.ProductServiceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackageClasses = ProductServiceProxy.class)
public class MyRetailAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyRetailAppApplication.class, args);
	}

}

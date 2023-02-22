package com.target.retail.myretailapp.product;

import lombok.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@FeignClient(name = "product-service",url="https://redsky-uat.perf.target.com/redsky_aggregations/v1/redsky/case_study_v1",
        configuration = FeignClientProperties.FeignClientConfiguration.class )
public interface ProductServiceProxy {

    /**
     * @param productId
     * @return
     */
    @GetMapping()
    public Optional<ResponseEntity<String>> getProductInfoById(@RequestParam(name = "key", required = true) String authorization,
                                                               @RequestParam(name="tcin") String productId);
}

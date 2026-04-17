package com.xoslu.tech.accountservice.feign;

import com.xoslu.tech.accountservice.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "customer-client",
        url = "http://localhost:8081"
)
public interface CustomerClient {
    @GetMapping("/api/v1/customers/{id}")
    CustomerDTO getCustomer(@PathVariable Long id);
}

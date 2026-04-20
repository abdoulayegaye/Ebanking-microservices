package com.xoslu.tech.operationservice.feign;

import com.xoslu.tech.operationservice.dto.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "account-client",
        url = "http://localhost:8082"
)
public interface AccountClient {
    @GetMapping("/api/v1/accounts/search/{numero}")
    AccountDTO getAccount(@PathVariable String numero);

    @PutMapping("/api/v1/accounts/update-balance/{numero}")
    AccountDTO updateAccountBalance(@PathVariable String numero, @RequestParam("new-balance") double newBalance);
}

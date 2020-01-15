package com.gorin.account.controller;

import com.gorin.account.DTO.OperationDto;
import com.gorin.account.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

   private AccountService accountService;

    public RestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/operation", consumes = "application/json")
    public OperationDto addOperation(@RequestBody OperationDto operation) {
            accountService.addOperation(operation);
     return operation;
    }


}

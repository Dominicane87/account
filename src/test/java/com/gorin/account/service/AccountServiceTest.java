package com.gorin.account.service;

import com.gorin.account.DTO.OperationDto;
import com.gorin.account.entity.Account;
import com.gorin.account.entity.Operation;
import com.gorin.account.repository.AccountRepository;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
    @MockBean
    private AccountRepository repository;

    @Autowired
    private AccountService accountService;

    @Test
    public void serviceTest() {
        // given
        Account account = new Account(new BigDecimal(1), 2L);
        Operation operation=new Operation(new BigDecimal(1),account);
        HashSet<Operation> operations = new HashSet<Operation>(){{add(operation);}};
        account.setOperations(operations);

        OperationDto operationDto = new OperationDto();
        operationDto.setIdAccount(2L);
        operationDto.setSumm(new BigDecimal(1));
        accountService.addOperation(operationDto);

        ArgumentCaptor<Account> captor=ArgumentCaptor.forClass(Account.class);
        Mockito.verify(repository).save(captor.capture());
        assertEquals(account.toString().trim(),captor.getValue().toString().trim());
    }

}
package com.gorin.account.service;

import com.gorin.account.entity.Account;
import com.gorin.account.entity.Operation;
import com.gorin.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository repository;

    @Test
    public void repositoryTest() {
        // given
        BigDecimal bigDecimal = new BigDecimal(1);
        Account account = new Account(bigDecimal, 2L);
        Operation operation=new Operation(bigDecimal,account);
        HashSet<Operation> operations = new HashSet<Operation>(){{add(operation);}};
        account.setOperations(operations);
        repository.save(account);

        // when
        Optional<Account> found = repository.findAccountById(2L);

        // then
        assertEquals(account.toString().trim(),found.get().toString().trim());
    }
}
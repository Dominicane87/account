package com.gorin.account.repository;

import com.gorin.account.entity.Account;
import com.gorin.account.entity.Operation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findAccountById(Long id);
    Set<Account> findAll();
    boolean existsAccountById(Long id);
    Account save(Account account);

}

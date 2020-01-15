package com.gorin.account.service;

import com.gorin.account.DTO.OperationDto;
import com.gorin.account.entity.Account;
import com.gorin.account.entity.Operation;
import com.gorin.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AccountService {


    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addOperation(OperationDto dto) throws IllegalArgumentException{
       if (accountRepository.existsAccountById(dto.getIdAccount())){
        Account account=accountRepository.findAccountById(dto.getIdAccount()).get();
        if (account.getSumm().add(dto.getSumm()).compareTo(BigDecimal.ZERO)==-1){
            throw new IllegalArgumentException("Account is exist. Insufficient fund");
        } else{
            Set<Operation> operations=account.getOperations();
            operations.add(new Operation(dto.getSumm(),account));
            BigDecimal summ=account.getSumm().add(dto.getSumm());
            account.setOperations(operations);
            account.setSumm(summ);
            accountRepository.save(account);
        }
       }else {
          if (dto.getSumm().floatValue()<0){
              throw new IllegalArgumentException("Account is not exist. You try to take money");
          }else {

              Account account=new Account(dto.getSumm(),dto.getIdAccount());
              Set<Operation> operations=new HashSet<Operation>(){
                  {
                      add(new Operation(dto.getSumm(),account));
                  }
              };
              account.setOperations(operations);
              accountRepository.save(account);
          }
       }
    }
}

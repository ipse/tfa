package com.thefinancialapplication.persistence.account;

import com.thefinancialapplication.domain.actions.transfer.TransactionActions;
import com.thefinancialapplication.domain.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service
public class AccountService {

  private AccountCRUDService service;
  private final EntityManager em;

  @Autowired
  public AccountService(AccountCRUDService service, EntityManager em) {
    this.service = service;
    this.em = em;
  }

  @Transactional
  public void doDeposit(@NonNull String uuid, @NonNull BigDecimal amount) {
    Account to = service.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Account was not found"));
    TransactionActions.performDeposit(to, amount);
    em.merge(to);
  }

  @Transactional
  public void doWithdrawal(@NonNull String uuid, @NonNull BigDecimal amount) {
    Account from = service.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Account was not found"));
    TransactionActions.performWithdrawal(from, amount);
    em.merge(from);
  }
}

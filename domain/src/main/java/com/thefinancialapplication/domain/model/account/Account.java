package com.thefinancialapplication.domain.model.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thefinancialapplication.domain.model.abstractions.IdentifiableEntity;
import com.thefinancialapplication.domain.model.transaction.Transaction;
import org.springframework.lang.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account extends IdentifiableEntity {

  protected Account() {
  }

  @NonNull
  @Column(nullable = false)
  private BigDecimal balance = BigDecimal.ZERO;

  @Version
  @JsonIgnore
  private Long version;

  @NonNull
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  private List<Transaction> transactions;

  public Account(@NonNull List<Transaction> transactions) {
    super();
    this.transactions = new ArrayList<>(transactions);
  }

  public List<Transaction> getTransactions() {
    return new ArrayList<>(transactions);
  }

  public BigDecimal getBalance() {
    return new BigDecimal(balance.toString());
  }

  public void acceptTransaction(@NonNull Transaction transaction) {
    transactions.add(transaction);
    this.balance = balance.add(transaction.getChange());
  }

  public void emitTransaction(@NonNull Transaction transaction) {
    transactions.add(transaction);
    this.balance = balance.subtract(transaction.getChange());
  }

  @Override
  public boolean equals(Object anObject) {
    if (this == anObject) {
      return true;
    }
    if (anObject instanceof Account) {
      Account anotherAccount = (Account) anObject;
      return this.getUUID().equals(anotherAccount.getUUID());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return getUUID().hashCode();
  }
}

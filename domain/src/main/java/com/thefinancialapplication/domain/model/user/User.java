package com.thefinancialapplication.domain.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thefinancialapplication.domain.model.abstractions.IdentifiableEntity;
import com.thefinancialapplication.domain.model.account.Account;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends IdentifiableEntity {
  protected User() {
  }

  @NonNull
  @Getter
  @Column(nullable = false)
  private String email;

  @Version
  @JsonIgnore
  private Long version;

  @NonNull
  @OneToMany(
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  private List<Account> accounts;

  public User(@NonNull String email, @NonNull List<Account> accounts) {
    super();
    this.email = email;
    this.accounts = new ArrayList<>(accounts);
  }

  public List<Account> getAccounts() {
    return new ArrayList<>(accounts);
  }
}

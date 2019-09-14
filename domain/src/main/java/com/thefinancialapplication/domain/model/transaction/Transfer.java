package com.thefinancialapplication.domain.model.transaction;

import com.thefinancialapplication.domain.model.account.Account;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(of = "uuid")
public class Transfer extends Transaction {
  protected Transfer() {}

  @NonNull
  @ManyToOne
  private Account from;

  @ManyToOne
  private Account to;

  public Transfer(
      @NonNull Date timestamp,
      @NonNull BigDecimal change,
      @NonNull Account from,
      @NonNull Account to) {
    super(timestamp, change);
    this.from = from;
    this.to = to;
  }
}

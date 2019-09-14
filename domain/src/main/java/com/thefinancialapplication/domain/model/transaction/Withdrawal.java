package com.thefinancialapplication.domain.model.transaction;

import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(of = "uuid")
public class Withdrawal extends Transaction {
  protected Withdrawal() {} //For JPA

  public Withdrawal(@NonNull Date timestamp, @NonNull BigDecimal change) {
    super(timestamp, change);
  }
}

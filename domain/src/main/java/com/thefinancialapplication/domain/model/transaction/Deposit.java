package com.thefinancialapplication.domain.model.transaction;

import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(of = "uuid")
public class Deposit extends Transaction {
  protected Deposit() {}

  public Deposit(@NonNull Date timestamp, @NonNull BigDecimal change) {
    super(timestamp, change);
  }
}

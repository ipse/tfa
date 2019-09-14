package com.thefinancialapplication.domain.model.transaction;

import com.thefinancialapplication.domain.model.abstractions.IdentifiableEntity;
import lombok.EqualsAndHashCode;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EqualsAndHashCode(of = "uuid")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Transaction extends IdentifiableEntity {

  protected Transaction() {}

  public Transaction(@NonNull Date timestamp, @NonNull BigDecimal change) {
    super();
    this.timestamp = new Date(timestamp.getTime());
    this.change = new BigDecimal(change.toString());
  }

  @NonNull
  @Column(nullable = false)
  private Date timestamp;

  @NonNull
  @Column(nullable = false)
  private BigDecimal change;

  public Date getTimestamp() {
    return new Date(timestamp.getTime());
  }

  public BigDecimal getChange() {
    return new BigDecimal(change.toString());
  }

}

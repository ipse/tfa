package com.thefinancialapplication.domain.model.abstractions;

import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
public abstract class IdentifiableEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String uuid;

  public String getUUID() {
    return uuid;
  }

  public static String provideIdentifier() {
    return UUID.randomUUID().toString();
  }
}

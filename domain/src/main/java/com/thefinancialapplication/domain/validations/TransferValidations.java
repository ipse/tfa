package com.thefinancialapplication.domain.validations;

import com.thefinancialapplication.domain.model.account.Account;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public abstract class TransferValidations {

  public static boolean isAccountSame(@NonNull Account a1, @NonNull Account a2) {
    return a1.getUUID().equals(a2.getUUID());
  }

  public static boolean isMoneyInsufficient(
      @NonNull Account account,
      @NonNull BigDecimal amount) {
    return account.getBalance().compareTo(amount) < 0;
  }

  public static boolean isAmountNonPositive(@NonNull BigDecimal amount) {
    return amount.compareTo(BigDecimal.ZERO) <= 0;
  }

}

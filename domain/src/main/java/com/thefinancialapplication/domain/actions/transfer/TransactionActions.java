package com.thefinancialapplication.domain.actions.transfer;


import com.thefinancialappilcation.util.exception.Preconditions;
import com.thefinancialapplication.domain.model.account.Account;
import com.thefinancialapplication.domain.model.errors.TransactionError;
import com.thefinancialapplication.domain.model.transaction.Deposit;
import com.thefinancialapplication.domain.model.transaction.Transfer;
import com.thefinancialapplication.domain.model.transaction.Withdrawal;
import com.thefinancialapplication.domain.validations.TransferValidations;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionActions {

  public static void performTransfer(
      @NonNull Account from,
      @NonNull Account to,
      @NonNull BigDecimal amount) {

    Preconditions.check(TransferValidations.isAccountSame(from, to),
        () -> new TransactionError("The accounts are the same"));

    Preconditions.check(TransferValidations.isMoneyInsufficient(from, amount),
        () -> new TransactionError("The money is insufficient to make a transaction"));

    Preconditions.check(TransferValidations.isAmountNonPositive(amount),
        () -> new TransactionError("It is not possible to transfer negative amount or no money"));

    Transfer newTransfer = new Transfer(new Date(), amount, from, to);
    from.emitTransaction(newTransfer);
    to.acceptTransaction(newTransfer);
  }

  public static void performDeposit(@NonNull Account to, @NonNull BigDecimal amount) {

    Preconditions.check(TransferValidations.isAmountNonPositive(amount),
        () -> new TransactionError("It is not possible to deposit a negative amount or no money"));

    Deposit newDeposit = new Deposit(new Date(), amount);

    to.acceptTransaction(newDeposit);
  }

  public static void performWithdrawal(@NonNull Account from, @NonNull BigDecimal amount) {

    Preconditions.check(TransferValidations.isAmountNonPositive(amount),
        () -> new TransactionError("It is not possible to withdraw a negative amount or no money"));

    Preconditions.check(TransferValidations.isMoneyInsufficient(from, amount),
        () -> new TransactionError("The money is insufficient to make a withdrawal"));

    Withdrawal newWithdrawal = new Withdrawal(new Date(), amount.negate());

    from.acceptTransaction(newWithdrawal);
  }
}

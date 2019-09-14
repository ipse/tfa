package com.thefinancialapplication.domain.actions.transfer;

import com.thefinancialapplication.domain.model.abstractions.IdentifiableEntity;
import com.thefinancialapplication.domain.model.account.Account;
import com.thefinancialapplication.domain.model.errors.TransactionError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class TransactionActionsTest {

  @Test
  void performDeposit_returns_success() {
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.TEN;
    TransactionActions.performDeposit(to, amount);

    Assertions.assertEquals(BigDecimal.TEN, to.getBalance());
    Assertions.assertEquals(1, to.getTransactions().size());
  }

  @Test
  void performDeposit_doesnt_allow_zero_deposit() {
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.ZERO;
    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performDeposit(to, amount));
  }

  @Test
  void performDeposit_doesnt_allow_negative_deposit() {
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.ONE.negate();
    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performDeposit(to, amount));
  }

  @Test
  void performWithdrawal_returns_success() {
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.TEN;
    TransactionActions.performDeposit(to, amount); // we need to get money in first

    TransactionActions.performWithdrawal(to, new BigDecimal(5));

    Assertions.assertEquals(new BigDecimal(5), to.getBalance());
    Assertions.assertEquals(2, to.getTransactions().size());
  }

  @Test
  void performWithdrawal_doesnt_allow_zero_withdrawal() {
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.TEN;
    TransactionActions.performDeposit(to, amount); // we need to get money in first

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performWithdrawal(to, BigDecimal.ZERO));
  }

  @Test
  void performWithdrawal_doesnt_allow_negative_withdrawal() {
    Account to = new Account(new ArrayList<>());

    TransactionActions.performDeposit(to, BigDecimal.TEN);

    BigDecimal amount = BigDecimal.ONE.negate();
    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performWithdrawal(to, amount));
  }

  @Test
  void performWithdrawal_fails_with_insufficient_balance() {
    Account to = new Account(new ArrayList<>());
    TransactionActions.performDeposit(to, BigDecimal.ONE);

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performWithdrawal(to, BigDecimal.TEN));
  }

  @Test
  void performTransfer_returns_success() {
    Account from = new Account(new ArrayList<>());
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.TEN;
    TransactionActions.performDeposit(from, amount); //we need to deposit money first, so that there is something to transfer

    TransactionActions.performTransfer(from, to, amount);

    Assertions.assertEquals(BigDecimal.ZERO, from.getBalance());
    Assertions.assertEquals(BigDecimal.TEN, to.getBalance());

    Assertions.assertEquals(2, from.getTransactions().size());
    Assertions.assertEquals(1, to.getTransactions().size());
  }

  @Test
  void performTransfer_doesnt_allow_transfer_to_same_account() {
    Account from = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.TEN;
    TransactionActions.performDeposit(from, amount);

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performTransfer(from, from, amount));
  }

  @Test
  void performTransfer_doesnt_allow_zero_amount() {
    Account from = new Account(new ArrayList<>());
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.ZERO;
    TransactionActions.performDeposit(from, BigDecimal.TEN);

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performTransfer(from, to, amount));
  }

  @Test
  void performTransfer_doesnt_allow_negative_amount() {
    Account from = new Account(new ArrayList<>());
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.ONE.negate(); // -1
    TransactionActions.performDeposit(from, BigDecimal.TEN);

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performTransfer(from, to, amount));
  }

  @Test
  void performTransfer_fails_with_insufficient_amount() {
    Account from = new Account(new ArrayList<>());
    Account to = new Account(new ArrayList<>());

    BigDecimal amount = BigDecimal.ZERO;
    TransactionActions.performDeposit(from, BigDecimal.TEN);

    Assertions.assertThrows(TransactionError.class, () -> TransactionActions.performTransfer(from, to, amount));
  }

}
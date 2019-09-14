package com.thefinancialappilcation.util.exception;

import java.util.function.Supplier;

public class Preconditions {

  private Preconditions() {
    //this is a util class, no instances of it are required
  }

  public static void check(boolean result, Supplier<RuntimeException> excSupplier) {
    if (result) {
      throw excSupplier.get();
    }

  }
}

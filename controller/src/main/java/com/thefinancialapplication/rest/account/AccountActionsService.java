package com.thefinancialapplication.rest.account;

import com.thefinancialapplication.persistence.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/v1/accounts")
public class AccountActionsService {

  @Autowired
  private AccountService service;

  @RequestMapping(value = "/{account}/deposit", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> depositMoney(@PathVariable("account") String uuid,
                           @RequestBody BigDecimal amount) {
    service.doDeposit(uuid, amount);

    return ResponseEntity.accepted().build();
  }

  @RequestMapping(value = "/{account}/withdraw", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<Object> withdrawMoney(@PathVariable("account") String uuid,
                                             @RequestBody BigDecimal amount) {
    service.doWithdrawal(uuid, amount);
    return ResponseEntity.accepted().build();
  }
}

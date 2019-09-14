package com.thefinancialapplication.persistence.user;

import com.thefinancialapplication.domain.model.account.Account;
import com.thefinancialapplication.domain.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

  private UserCRUDService userService;

  @Autowired
  public UserService(UserCRUDService service) {
    this.userService = service;
  }

  @Transactional
  public Optional<User> getUserByUUID(@NonNull String uuid) {
    return userService.findById(uuid);
  }

  @Transactional
  public User createUser(@NonNull String email) {

    Account account = new Account(Collections.EMPTY_LIST);
    User user = new User(email, Collections.singletonList(account));

    return userService.save(user);
  }
}

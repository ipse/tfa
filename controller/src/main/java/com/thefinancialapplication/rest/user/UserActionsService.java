package com.thefinancialapplication.rest.user;

import com.thefinancialapplication.domain.model.user.User;
import com.thefinancialapplication.persistence.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserActionsService {

  @Autowired
  private UserService userService;

  @RequestMapping("/{uuid}")
  public ResponseEntity getUser(@PathVariable("uuid") String uuid) {
    Optional<User> user = userService.getUserByUUID(uuid);
    return user.isPresent() ? new ResponseEntity<>(user.get(), HttpStatus.OK) : ResponseEntity.notFound().build();
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<User> createUser( @RequestBody String email) {
    return new ResponseEntity<>(userService.createUser(email), HttpStatus.OK);
  }
}

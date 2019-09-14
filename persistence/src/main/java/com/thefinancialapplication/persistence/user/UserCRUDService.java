package com.thefinancialapplication.persistence.user;

import com.thefinancialapplication.domain.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCRUDService extends CrudRepository<User, String> {
}

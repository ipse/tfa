package com.thefinancialapplication.persistence.account;

import com.thefinancialapplication.domain.model.account.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountCRUDService extends CrudRepository<Account, String> {
}

package com.atsistemas.restcontrollers;

import java.security.Principal;
import java.util.Collection;

import javax.validation.Valid;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.atsistemas.entities.Account;
import com.atsistemas.services.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;
	
	@Autowired
	public AccountService accountService;

	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Page<Account>> findAll(Pageable pageable) {

		Page<Account> accounts = accountService.findAll(pageable);
		System.out.println(accounts.getContent().toString());
		return new ResponseEntity<Page<Account>>(accounts, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Account> create(@RequestBody @Valid Account account) {

		return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/getAllSessionFromUser", method = RequestMethod.GET)
	public ResponseEntity<Account> getAllSessionFromUser() {
	    Collection<? extends ExpiringSession> usersSessions = this.sessions
	            .findByIndexNameAndIndexValue(
	                    FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME,
	                    SecurityContextHolder.getContext().getAuthentication().getName())
	            .values();
	    for (ExpiringSession expiringSession : usersSessions) {
			System.out.println(expiringSession.getId());
			
			//Doesnt work but at least I we got the id to delete it directly from the database!
			sessions.delete(expiringSession.getId());
		}
	    return new ResponseEntity<Account>(HttpStatus.OK);
	}

}

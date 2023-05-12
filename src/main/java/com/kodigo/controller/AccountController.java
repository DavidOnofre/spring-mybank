package com.kodigo.controller;

import com.kodigo.model.Account;
import com.kodigo.service.IAccountService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constant.ACCOUNTS)
public class AccountController {

    @Autowired
    private IAccountService service;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() throws Exception {
        List<Account> obj = service.findAll();
        return new ResponseEntity<List<Account>>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") Integer id) throws Exception {
        Account obj = service.findById(id);
        return new ResponseEntity<Account>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> save(@Valid @RequestBody Account account) throws Exception {
        Account obj = service.saveAccount(account);
        return new ResponseEntity<Account>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Account> edit(@Valid @RequestBody Account account) throws Exception {
        Account obj = service.edit(account);
        return new ResponseEntity<Account>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

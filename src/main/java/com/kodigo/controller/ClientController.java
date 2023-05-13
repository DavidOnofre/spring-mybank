package com.kodigo.controller;

import com.kodigo.model.Client;
import com.kodigo.service.IClientService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Constant.CLIENTS)
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @GetMapping
    public ResponseEntity<List<Client>> findAll() throws Exception {
        List<Client> list = service.findAll();
        return new ResponseEntity<List<Client>>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Integer id) throws Exception {
        Client obj = service.findById(id);
        return new ResponseEntity<Client>(obj, HttpStatus.OK);
    }

    //hateos - level 2 Richardson
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Client client) throws Exception {

        //set encoder pass
        client.setPasswordClient(encodePass(client));

        Client obj = service.save(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdClient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Client> edit(@Valid @RequestBody Client client) throws Exception {
        Client obj = service.edit(client);
        return new ResponseEntity<Client>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    private String encodePass(Client client) {
        return bcrypt.encode(client.getPasswordClient());
    }

}

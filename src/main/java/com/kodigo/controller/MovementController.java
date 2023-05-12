package com.kodigo.controller;

import com.kodigo.model.Movement;
import com.kodigo.service.IMovementService;
import com.kodigo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Constant.MOVEMENTS)
public class MovementController {

    @Autowired
    private IMovementService service;

    @GetMapping
    public ResponseEntity<List<Movement>> findAll() throws Exception {
        List<Movement> obj = service.findAll();
        return new ResponseEntity<List<Movement>>(obj, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> findById(@PathVariable("id") Integer id) throws Exception {
        Movement obj = service.findById(id);
        return new ResponseEntity<Movement>(obj, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movement> save(@Valid @RequestBody Movement movement) throws Exception {
        Movement obj = service.saveTransactional(movement);
        return new ResponseEntity<Movement>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Movement> edit(@Valid @RequestBody Movement movement) throws Exception {
        Movement obj = service.edit(movement);
        return new ResponseEntity<Movement>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}

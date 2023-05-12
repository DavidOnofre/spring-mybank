package com.kodigo.service;

import com.kodigo.model.Movement;

public interface IMovementService extends ICRUD<Movement, Integer> {

    Movement saveTransactional(Movement movement);

}
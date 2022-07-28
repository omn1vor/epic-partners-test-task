package org.omn1vor.epicpartnerstesttask.controller;

import org.omn1vor.epicpartnerstesttask.api.CountersApi;
import org.omn1vor.epicpartnerstesttask.models.Counter;
import org.omn1vor.epicpartnerstesttask.models.CounterIncrementRequest;
import org.omn1vor.epicpartnerstesttask.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountersController implements CountersApi {
    @Autowired
    CounterService counterService;

    @Override
    public ResponseEntity<Counter> createNewCounter(Counter body) {
        return new ResponseEntity<>(counterService.createNewCounter(body), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> getCounterValue(String counterId) {
        return new ResponseEntity<>(counterService.getCounterValue(counterId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> incrementCounter(CounterIncrementRequest body) {
        return null;
    }
}

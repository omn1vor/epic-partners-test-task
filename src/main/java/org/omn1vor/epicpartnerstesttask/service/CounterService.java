package org.omn1vor.epicpartnerstesttask.service;

import org.omn1vor.epicpartnerstesttask.models.Counter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CounterService {

    public Counter createNewCounter(Counter prototype) {
        Counter newCounter = new Counter();
        newCounter.setId(prototype.getId());
        newCounter.setValue(prototype.getValue());

        return newCounter;
    }

    public Long getCounterValue(String counterId) {
        if (counterId.equals("one")) {
            return 100L;
        } else if (counterId.equals("two")) {
            return 200L;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

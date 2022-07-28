package org.omn1vor.epicpartnerstesttask.service;

import org.omn1vor.epicpartnerstesttask.models.Counter;
import org.omn1vor.epicpartnerstesttask.models.CounterIncrementRequest;
import org.omn1vor.epicpartnerstesttask.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CounterService {

    @Autowired
    CounterRepository counterRepo;

    public Counter createNewCounter(Counter prototype) {
        if (counterRepo.existsById(prototype.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Counter newCounter = new Counter();
        newCounter.setId(prototype.getId());
        newCounter.setValue(prototype.getValue());
        newCounter.title(prototype.getTitle());
        counterRepo.save(newCounter);

        return newCounter;
    }

    public Long getCounterValue(String counterId) {
        Counter counter = counterRepo.findById(counterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return counter.getValue();
    }

    public Long incrementCounter(CounterIncrementRequest request) {
        Counter counter = counterRepo.findById(request.getCounterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        long newValue = counter.getValue() + request.getIncrementCount();
        counter.setValue(newValue);
        counterRepo.save(counter);

        return newValue;
    }

    public List<Counter> getTop50Counters() {
        return counterRepo.findTop50ByOrderById();
    }
}

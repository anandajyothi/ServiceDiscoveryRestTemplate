package com.setplex.ott.subscription.service;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.setplex.ott.subscription.model.Event;
import com.setplex.ott.subscription.repository.EventRepository;


@Service
@Transactional
public class EventService {

	@Autowired
    private EventRepository repo;
    public Iterable<Event> listAll() {
        return repo.findAll();
    }
     
    public void save(Event event) {
        repo.save(event);
    }
     
    public Event get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
	
}

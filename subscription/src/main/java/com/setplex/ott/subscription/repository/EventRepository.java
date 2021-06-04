package com.setplex.ott.subscription.repository;


import org.springframework.data.repository.CrudRepository;

import com.setplex.ott.subscription.model.Event;

public interface EventRepository  extends CrudRepository<Event, Integer>{

	
}


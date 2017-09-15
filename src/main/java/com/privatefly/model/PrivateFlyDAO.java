package com.privatefly.model;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.privatefly.model.PrivateFlyModel;

@Transactional
public interface PrivateFlyDAO extends CrudRepository<PrivateFlyModel, Long> {
	public Iterable<PrivateFlyModel> findAll();

	public Iterable<PrivateFlyModel> findAllByOrderByAirfieldAsc();

	public PrivateFlyModel findByAircraftname(String airecraft);
}

package com.privatefly.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import com.privatefly.model.PrivateFlyModel;

@Transactional
public interface PrivateFlyDAO extends CrudRepository<PrivateFlyModel, Long> {
	public List<PrivateFlyModel> findAll();

	public List<PrivateFlyModel> findAllByOrderByAirfieldAsc();

	public PrivateFlyModel findByAircraftname(String airecraft);
}

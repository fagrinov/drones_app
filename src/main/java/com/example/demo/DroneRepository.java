package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.io.entity.DroneEntity;

@Repository
public interface DroneRepository extends CrudRepository<DroneEntity,Long>{
	
}

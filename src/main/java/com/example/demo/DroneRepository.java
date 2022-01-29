package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.io.entity.DroneEntity;

@Repository
public interface DroneRepository extends RevisionRepository<DroneEntity, Long, Long>,CrudRepository<DroneEntity,Long>{
	public DroneEntity findBySerial(String serial);
	public List<DroneEntity> findByState (int state);
	
}

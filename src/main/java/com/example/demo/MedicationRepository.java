package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.io.entity.MedicationEntity;

public interface MedicationRepository extends CrudRepository<MedicationEntity,Long>{

}

package com.neueda.test.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.test.data.AtmEntity;


@Repository
public interface  AtmRepository extends JpaRepository<AtmEntity, Long> {

}

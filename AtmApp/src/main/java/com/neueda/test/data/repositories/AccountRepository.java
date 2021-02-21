package com.neueda.test.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neueda.test.data.AccountEntity;

@Repository
public interface  AccountRepository extends JpaRepository<AccountEntity, Long>{



}

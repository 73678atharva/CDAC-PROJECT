package com.app.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}

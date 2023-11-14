package com.ped.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ped.entities.Pedido;

public interface PedRepository extends JpaRepository <Pedido, Long>{

}

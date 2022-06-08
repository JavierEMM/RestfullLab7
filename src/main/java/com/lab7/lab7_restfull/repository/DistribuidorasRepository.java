package com.lab7.lab7_restfull.repository;


import com.lab7.lab7_restfull.entity.Distribuidora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistribuidorasRepository extends JpaRepository<Distribuidora,Integer> {
}

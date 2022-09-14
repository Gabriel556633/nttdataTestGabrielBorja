package com.GabrielBorja.nttdataTest.Repository;


import com.GabrielBorja.nttdataTest.Entity.Movimientos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {
    List<Movimientos> findByIdCuenta(int idCuenta) ;
  }

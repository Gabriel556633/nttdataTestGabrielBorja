package com.GabrielBorja.nttdataTest.Repository;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
    List<Cuenta> findByIdUsuario(int idUsuario) ;
    List<Cuenta> findByNumeroCuenta(String numeroCuenta) ;
    Cuenta getByNumeroCuenta(String numeroCuenta) ;


}

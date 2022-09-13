package com.GabrielBorja.nttdataTest.Repository;

import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    List<Usuarios> findByIdentificacion(String identificacion) ;
    //Usuarios getById(int id);

}

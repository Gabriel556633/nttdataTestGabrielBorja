package com.GabrielBorja.nttdataTest.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Nombre;
    private String Apellido;
    private String Direccion;
    private String Telefono;
    private String Contraseña;
    private Boolean Estado;
    private String Genero;
    private String Edad;
    private String identificacion;
}

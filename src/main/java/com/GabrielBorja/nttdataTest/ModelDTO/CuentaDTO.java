package com.GabrielBorja.nttdataTest.ModelDTO;

import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String NumeroCuenta;
    private String Tipo;
    private Double Saldo;
    private Boolean Estado;
    private int idUsuario;
}

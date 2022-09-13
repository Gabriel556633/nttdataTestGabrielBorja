package com.GabrielBorja.nttdataTest.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String TipoMovieminto;
    private Integer Valor;
    private Double Saldo;
    private String numeroCuenta;
    private String Fecha= LocalDateTime.now().toString();

}

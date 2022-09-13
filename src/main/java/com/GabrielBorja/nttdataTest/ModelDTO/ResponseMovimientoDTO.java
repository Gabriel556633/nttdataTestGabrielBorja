package com.GabrielBorja.nttdataTest.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovimientoDTO {

    private String Fecha= LocalDateTime.now().toString();
    private String Cliente;
    private String NumeroCuenta;
    private String Tipo;
    private String SaldoInicial;
    private String Movimiento;
    private String SaldoDisponible;
    private Boolean Estado;
}

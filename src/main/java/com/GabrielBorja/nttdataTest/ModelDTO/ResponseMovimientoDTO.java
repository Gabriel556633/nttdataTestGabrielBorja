package com.GabrielBorja.nttdataTest.ModelDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMovimientoDTO {

    private LocalDate Fecha= LocalDate.now();
    private String Cliente;
    private String NumeroCuenta;
    private String Tipo;
    private String SaldoInicial;
    private String Movimiento;
    private String SaldoDisponible;
    private Boolean Estado;

}

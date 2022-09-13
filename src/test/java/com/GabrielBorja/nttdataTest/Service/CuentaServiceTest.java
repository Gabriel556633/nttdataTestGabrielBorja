package com.GabrielBorja.nttdataTest.Service;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import com.GabrielBorja.nttdataTest.Repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;
    @InjectMocks
    private CuentaService cuentaService;

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setIdUsuario(1);
        cuenta.setTipo("Ahorros");
        cuenta.setEstado(true);
        cuenta.setSaldo(10.0);
        cuenta.setId(1);

    }

    @Test
    void getAll() {
        when(cuentaRepository.findAll()).thenReturn(Arrays.asList(cuenta));
        assertNotNull(cuentaService.getAll());
    }
}
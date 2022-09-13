package com.GabrielBorja.nttdataTest.Controller;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import com.GabrielBorja.nttdataTest.Entity.Movimientos;
import com.GabrielBorja.nttdataTest.ModelDTO.CuentaDTO;
import com.GabrielBorja.nttdataTest.ModelDTO.MovimientosDTO;
import com.GabrielBorja.nttdataTest.ModelDTO.ResponseMovimientoDTO;
import com.GabrielBorja.nttdataTest.Service.CuentaService;
import com.GabrielBorja.nttdataTest.Service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientosService movimientosService;

    @GetMapping
    public ResponseEntity<List<Movimientos>> getAll() {
        List<Movimientos> movimientos = movimientosService.getAll().getBody();
        return ResponseEntity.ok(movimientos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) throws Exception {
        ResponseEntity<Optional> movimientos = movimientosService.getMovimientosById(id);
        return ResponseEntity.ok(movimientos);
    }



    @GetMapping("/idCuenta/{idCuenta}")
    public ResponseEntity<?> getByCuentaId(@PathVariable("idCuenta") int idCuenta) throws Exception {
        List<Movimientos> movimientos = movimientosService.getMovimientosByCuentaId(idCuenta).getBody();
        return ResponseEntity.ok(movimientos);
    }



    @PostMapping()
    public ResponseEntity<?> save(@RequestBody MovimientosDTO movimientosDTO) {
        return movimientosService.saveMovimiento(movimientosDTO);
    }

    @GetMapping("/reporte/")
    public ResponseEntity<List<?>> reporte(@RequestParam("numeroCuenta") String numeroCuenta) throws Exception {
        return  movimientosService.reporte(numeroCuenta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento (@PathVariable("id") int id) {
        return movimientosService.deleteMovimiento(id);
    }

}

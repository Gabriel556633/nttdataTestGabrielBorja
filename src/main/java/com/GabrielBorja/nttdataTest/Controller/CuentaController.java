package com.GabrielBorja.nttdataTest.Controller;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import com.GabrielBorja.nttdataTest.ModelDTO.CuentaDTO;
import com.GabrielBorja.nttdataTest.ModelDTO.UsuarioDTO;
import com.GabrielBorja.nttdataTest.Service.CuentaService;
import com.GabrielBorja.nttdataTest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;


    @GetMapping
    public ResponseEntity<List<Cuenta>> getAll() {
        List<Cuenta> cuentas = cuentaService.getAll().getBody();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) throws Exception {
        ResponseEntity<Optional> cuenta = cuentaService.getCuentaById(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping("/idUsuario/{userId}")
    public ResponseEntity<?> getByuserId(@PathVariable("userId") int userId) throws Exception {
        List<Cuenta> cuentas = cuentaService.getCuentaByUserId(userId).getBody();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/numeroCuenta/")
    public ResponseEntity<?> getByNumeroCuenta(@RequestParam("numeroCuenta") String numeroCuenta) throws Exception {
        Cuenta cuenta = cuentaService.getCuentaByNumeroCuentaId(numeroCuenta).getBody();
        return ResponseEntity.ok(cuenta);
    }


    @PostMapping()
    public ResponseEntity<?> save(@RequestBody CuentaDTO cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta (@RequestBody CuentaDTO cuenta,@PathVariable("id") int id) {
        return cuentaService.updateCuenta(cuenta,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta (@PathVariable("id") int id) {
        return cuentaService.deleteCuenta(id);
    }

}

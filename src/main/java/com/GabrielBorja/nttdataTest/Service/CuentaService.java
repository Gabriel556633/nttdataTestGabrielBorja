package com.GabrielBorja.nttdataTest.Service;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import com.GabrielBorja.nttdataTest.ModelDTO.CuentaDTO;
import com.GabrielBorja.nttdataTest.Repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CuentaService {
    @Autowired
    CuentaRepository cuentaRepository;


    public ResponseEntity <List<Cuenta>> getAll() {
    try{
        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty() || cuentas == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cuentas);
    }catch (HttpClientErrorException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
        else {throw ex;}
    }
    }

    public ResponseEntity <Optional> getCuentaById(int id) {
        try{
            Optional<Cuenta> cuenta = cuentaRepository.findById(id);
            if (cuenta.isEmpty() || cuenta == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cuenta);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <List<Cuenta>> getCuentaByUserId(int id) {
        try{
            List<Cuenta> cuentas = cuentaRepository.findByIdUsuario(id);
            if (cuentas.isEmpty() || cuentas == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cuentas);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <Cuenta> getCuentaByNumeroCuentaId(String numeroCuenta) {
        try{

            Cuenta cuenta = cuentaRepository.getByNumeroCuenta(numeroCuenta);
            if (numeroCuenta.isEmpty() || numeroCuenta == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cuenta);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }


    public ResponseEntity <?> saveCuenta(CuentaDTO cuentaDTO) {
        try{
            cuentaDTO.setNumeroCuenta(UUID.randomUUID().toString());
            cuentaDTO.setNumeroCuenta(StringUtils.right(cuentaDTO.getNumeroCuenta(),8));
            List<Cuenta> cuenta = (cuentaRepository.findByNumeroCuenta(cuentaDTO.getNumeroCuenta()));
            if ( cuenta.size()> 0) {
                return ResponseEntity.badRequest().body("YA EXISTE ESA CUENTA");
            }
            Cuenta cuenta1 = new Cuenta();
            cuenta1.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            cuenta1.setTipo(cuentaDTO.getTipo());
            cuenta1.setEstado(cuentaDTO.getEstado());
            cuenta1.setSaldo(cuentaDTO.getSaldo());
            cuenta1.setIdUsuario(cuentaDTO.getIdUsuario());
            cuentaRepository.save(cuenta1);
            return ResponseEntity.ok(cuenta1);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> updateCuenta(CuentaDTO cuentaDTO, int id) {
        try{

            Optional<Cuenta> cuenta1 = cuentaRepository.findById(id);
            if ( !cuenta1.isPresent()) {
                return ResponseEntity.badRequest().body("NO EXISTE ESA CUENTA");
            }
            Cuenta cuenta = new Cuenta();
            cuenta.setId(cuenta1.get().getId());
            cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
            cuenta.setTipo(cuentaDTO.getTipo());
            cuenta.setEstado(cuentaDTO.getEstado());
            cuenta.setSaldo(cuentaDTO.getSaldo());
            cuenta.setIdUsuario(cuentaDTO.getIdUsuario());
            cuentaRepository.save(cuenta);
            return ResponseEntity.ok(cuenta);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> deleteCuenta(int id) {
        try{

            Optional<Cuenta> cuenta1 = cuentaRepository.findById(id);
            if ( !cuenta1.isPresent()) {
                return ResponseEntity.badRequest().body("NO EXISTE ESE USUARIO");
            }
            cuentaRepository.deleteById(id);
            return ResponseEntity.ok().body("BORRADO CON EXITO");
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }





}

package com.GabrielBorja.nttdataTest.Service;

import com.GabrielBorja.nttdataTest.Entity.Cuenta;
import com.GabrielBorja.nttdataTest.Entity.Movimientos;
import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import com.GabrielBorja.nttdataTest.ModelDTO.CuentaDTO;
import com.GabrielBorja.nttdataTest.ModelDTO.MovimientosDTO;
import com.GabrielBorja.nttdataTest.ModelDTO.ResponseMovimientoDTO;
import com.GabrielBorja.nttdataTest.Repository.CuentaRepository;
import com.GabrielBorja.nttdataTest.Repository.MovimientoRepository;
import com.GabrielBorja.nttdataTest.Repository.UsuarioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovimientosService {
    @Autowired
    MovimientoRepository movimientoRepository;
    @Autowired
    CuentaRepository cuentaRepository;
    @Autowired
    UsuarioService usuarioService;


    public ResponseEntity <List<Movimientos>> getAll() {
    try{
        List<Movimientos> movimientos = movimientoRepository.findAll();
        if (movimientos.isEmpty() || movimientos == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientos);
    }catch (HttpClientErrorException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
        else {throw ex;}
    }
    }

    public ResponseEntity <Optional> getMovimientosById(int id) {
        try{
            Optional<Movimientos> movimientos = movimientoRepository.findById(id);
            if (movimientos.isEmpty() || movimientos == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(movimientos);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <List<Movimientos>> getMovimientosByCuentaId(int id) {
        try{
            List<Movimientos> movimientos = movimientoRepository.findByIdCuenta(id);
            if (movimientos.isEmpty() || movimientos == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(movimientos);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }



    public ResponseEntity <?> saveMovimiento(MovimientosDTO movimientosDTO) {
        try{

            List<Cuenta> cuentas = (cuentaRepository.findByNumeroCuenta(movimientosDTO.getNumeroCuenta()));
            if ( cuentas.isEmpty()||cuentas==null) {
                return ResponseEntity.badRequest().body("NO EXISTE ESA CUENTA");
            }

            Cuenta cuenta = cuentaRepository.getByNumeroCuenta(movimientosDTO.getNumeroCuenta());
            Integer usuarioId = cuenta.getIdUsuario();
            Optional<Usuarios> cliente = ( usuarioService.getUserById(usuarioId)).getBody();
            String nombres = cliente.map(x->x.getApellido()+" "+x.getNombre()).toString();
            String saldoInicial=cuenta.getSaldo().toString();
            String movimiento="";
            Movimientos movimiento1 = new Movimientos();
            movimiento1.setTipoMovieminto(movimientosDTO.getTipoMovieminto());
            movimiento1.setValor(movimientosDTO.getValor());
            movimiento1.setIdCuenta(cuenta.getId());
            if(movimientosDTO.getTipoMovieminto().contains("DEPOSITO")){
                movimiento1.setSaldo(cuenta.getSaldo()+movimiento1.getValor());
                movimiento="+"+movimiento1.getValor();
            }
            if(movimientosDTO.getTipoMovieminto().contains("DEBITO")){
                if(cuenta.getSaldo()>0) {
                    movimiento1.setSaldo(cuenta.getSaldo() - movimiento1.getValor());
                    movimiento = "-" + movimiento1.getValor();
                }else{
                    movimiento1.setSaldo(0.0);
                    movimientoRepository.save(movimiento1);
                    return  ResponseEntity.badRequest().body("SALDO NO DISPONIBLE");
                }
            }
            movimientoRepository.save(movimiento1);
            cuenta.setSaldo(movimiento1.getSaldo());
            cuentaRepository.save(cuenta);
            ResponseMovimientoDTO responseMovimientoDTO = new ResponseMovimientoDTO();
            responseMovimientoDTO.setCliente(nombres);
            responseMovimientoDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            responseMovimientoDTO.setTipo(cuenta.getTipo());
            responseMovimientoDTO.setSaldoInicial(saldoInicial);
            responseMovimientoDTO.setMovimiento(movimiento);
            responseMovimientoDTO.setSaldoDisponible(cuenta.getSaldo().toString());
            responseMovimientoDTO.setEstado(true);


            return ResponseEntity.ok(responseMovimientoDTO);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity<List<?>> reporte(String numeroCuenta) {
        try{

            List<Cuenta> cuentas = (cuentaRepository.findByNumeroCuenta(numeroCuenta));
            if ( cuentas.isEmpty()||cuentas==null) {
                return ResponseEntity.noContent().build();
            }
            Cuenta cuenta = cuentaRepository.getByNumeroCuenta(numeroCuenta);
            List<Movimientos> movimientos = movimientoRepository.findByIdCuenta(cuenta.getId());
            if (movimientos.isEmpty() || movimientos == null) {
                return ResponseEntity.noContent().build();
            }
            List<ResponseMovimientoDTO> responseMovimientoDTO1=new ArrayList<>();
            for(int i=1; i<movimientos.size();i++){
                Integer usuarioId = cuenta.getIdUsuario();
                Optional<Usuarios> cliente = ( usuarioService.getUserById(usuarioId)).getBody();
                String nombres = cliente.map(x->x.getApellido()+" "+x.getNombre()).toString();
                String saldoInicial=(movimientos.get(i-1).getSaldo().toString());
                ResponseMovimientoDTO responseMovimientoDTO = new ResponseMovimientoDTO();
                responseMovimientoDTO.setCliente(nombres);
                responseMovimientoDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
                responseMovimientoDTO.setTipo(cuenta.getTipo());
                responseMovimientoDTO.setSaldoInicial(saldoInicial);
                responseMovimientoDTO.setMovimiento(movimientos.get(i).getValor().toString());
                responseMovimientoDTO.setSaldoDisponible(movimientos.get(i).getSaldo().toString());
                responseMovimientoDTO.setEstado(true);
                System.out.println(responseMovimientoDTO);
                responseMovimientoDTO1.add(responseMovimientoDTO);
            }
           return ResponseEntity.ok(responseMovimientoDTO1);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }
    public ResponseEntity <?> deleteMovimiento(int id) {
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

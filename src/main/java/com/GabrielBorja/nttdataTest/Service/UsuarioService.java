package com.GabrielBorja.nttdataTest.Service;

import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import com.GabrielBorja.nttdataTest.ModelDTO.UsuarioDTO;
import com.GabrielBorja.nttdataTest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService  {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity <List<Usuarios>> getAll() {
    try{
        List<Usuarios> users = usuarioRepository.findAll();
        if (users.isEmpty() || users == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }catch (HttpClientErrorException ex) {
        if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
        else {throw ex;}
    }
    }

    public ResponseEntity<Optional> getUserById(int id) {
        try{
            Optional<Usuarios> user = usuarioRepository.findById(id);
            if (user.isEmpty() || user == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(user);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> saveUsuario(UsuarioDTO usuarioDTO) {
        try{

            List<Usuarios> users = (usuarioRepository.findByIdentificacion(usuarioDTO.getIdentificacion()));
            if ( users.size()> 0) {
                return ResponseEntity.badRequest().body("YA EXISTE ESE USUARIO");
            }
            Usuarios usuario = new Usuarios();
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEdad(usuarioDTO.getEdad());
            usuario.setEstado(usuarioDTO.getEstado());
            usuario.setDireccion(usuarioDTO.getDireccion());
            usuario.setGenero(usuarioDTO.getGenero());
            usuario.setContraseña(usuarioDTO.getContraseña());
            usuario.setIdentificacion(usuarioDTO.getIdentificacion());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> updateUsuario(UsuarioDTO usuarioDTO, int id) {
        try{

            Optional<Usuarios> usuario1 = usuarioRepository.findById(id);
            if ( !usuario1.isPresent()) {
                return ResponseEntity.badRequest().body("NO EXISTE ESE USUARIO");
            }
            Usuarios usuario = new Usuarios();
            usuario.setId(usuario1.get().getId());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEdad(usuarioDTO.getEdad());
            usuario.setEstado(usuarioDTO.getEstado());
            usuario.setDireccion(usuarioDTO.getDireccion());
            usuario.setGenero(usuarioDTO.getGenero());
            usuario.setContraseña(usuarioDTO.getContraseña());
            usuario.setIdentificacion(usuarioDTO.getIdentificacion());
            usuario.setTelefono(usuarioDTO.getTelefono());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok(usuario);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> deleteUsuario(int id) {
        try{

            Optional<Usuarios> usuario1 = usuarioRepository.findById(id);
            if ( !usuario1.isPresent()) {
                return ResponseEntity.badRequest().body("NO EXISTE ESE USUARIO");
            }
            usuarioRepository.deleteById(id);
            return ResponseEntity.ok().body("BORRADO CON EXITO");
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }

    public ResponseEntity <?> getById(int id) {
        try{
            Usuarios usuario = usuarioRepository.getById(id);
            if (usuario == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(usuario);
        }catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND && (ex.getMessage() == null)) {return ResponseEntity.noContent().build();}
            else {throw ex;}
        }
    }





}

package com.GabrielBorja.nttdataTest.Controller;

import com.GabrielBorja.nttdataTest.Entity.Usuarios;
import com.GabrielBorja.nttdataTest.ModelDTO.UsuarioDTO;
import com.GabrielBorja.nttdataTest.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuarios>> getAll() {
        List<Usuarios> users = usuarioService.getAll().getBody();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById (@PathVariable("id") int id) throws Exception {
        ResponseEntity<Optional> user = usuarioService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody UsuarioDTO user) {
        return usuarioService.saveUsuario(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario (@RequestBody UsuarioDTO user,@PathVariable("id") int id) {
        return usuarioService.updateUsuario(user,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario (@PathVariable("id") int id) {
        return usuarioService.deleteUsuario(id);
    }

}

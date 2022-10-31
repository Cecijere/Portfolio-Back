/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.portfolio.Controller;

import com.proyecto.portfolio.Dto.dtoHys;
import com.proyecto.portfolio.Entity.hys;
import com.proyecto.portfolio.Security.Controller.Mensaje;
import com.proyecto.portfolio.Service.Shys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://frontproyect.web.app", "http://localhost:4200"})
@RequestMapping("/habilidades")
public class CHys {

    @Autowired
    Shys shys;

    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtoHardSoft) {
        if (StringUtils.isBlank(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (shys.existsByNombre(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("ERROR, ya existe"), HttpStatus.BAD_REQUEST);
        }

        hys hys = new hys(dtoHardSoft.getNombre(), dtoHardSoft.getPorcentaje());
        shys.save(hys);

        return new ResponseEntity(new Mensaje("Añadido correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtoHardSoft) {

        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (shys.existsByNombre(dtoHardSoft.getNombre()) && shys.getByNombre(dtoHardSoft.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ERROR; Ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        hys hYs = shys.getOne(id).get();
        hYs.setNombre(dtoHardSoft.getNombre());
        hYs.setPorcentaje((dtoHardSoft.getPorcentaje()));

        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ID"), HttpStatus.NOT_FOUND);
        }
        shys.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado con éxito"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }
}



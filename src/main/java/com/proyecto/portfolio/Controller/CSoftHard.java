/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.portfolio.Controller;

import com.proyecto.portfolio.Dto.dtoHardSoft;
import com.proyecto.portfolio.Entity.hardSoft;
import com.proyecto.portfolio.Security.Controller.Mensaje;
import com.proyecto.portfolio.Service.SHardSoft;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/SoftHard")
public class CSoftHard {

    @Autowired
    SHardSoft sHardSoft;

    @GetMapping("/lista")
    public ResponseEntity<List<hardSoft>> list() {
        List<hardSoft> list = sHardSoft.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHardSoft dtoHardSoft) {
        if (StringUtils.isBlank(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (sHardSoft.existsByNombre(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("ERROR, ya existe"), HttpStatus.BAD_REQUEST);
        }

        hardSoft hardSoft = new hardSoft(dtoHardSoft.getNombre(), dtoHardSoft.getPorcentaje());
        sHardSoft.save(hardSoft);

        return new ResponseEntity(new Mensaje("Añadido correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHardSoft dtoHardSoft) {

        if (!sHardSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if (sHardSoft.existsByNombre(dtoHardSoft.getNombre()) && sHardSoft.getByNombre(dtoHardSoft.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("ERROR; Ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoHardSoft.getNombre())) {
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }

        hardSoft hardSoft = sHardSoft.getOne(id).get();
        hardSoft.setNombre(dtoHardSoft.getNombre());
        hardSoft.setPorcentaje((dtoHardSoft.getPorcentaje()));

        sHardSoft.save(hardSoft);
        return new ResponseEntity(new Mensaje("Actualizado correctamente"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHardSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe ID"), HttpStatus.NOT_FOUND);
        }
        sHardSoft.delete(id);
        return new ResponseEntity(new Mensaje("Eliminado con éxito"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<hardSoft> getById(@PathVariable("id") int id) {
        if (!sHardSoft.existsById(id)) {
            return new ResponseEntity(new Mensaje("ID no existe"), HttpStatus.NOT_FOUND);
        }
        hardSoft hardSoft = sHardSoft.getOne(id).get();
        return new ResponseEntity(hardSoft, HttpStatus.OK);
    }
}


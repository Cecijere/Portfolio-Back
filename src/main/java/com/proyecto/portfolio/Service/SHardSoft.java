/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyecto.portfolio.Service;

import com.proyecto.portfolio.Entity.hardSoft;
import com.proyecto.portfolio.Repository.RHardSoft;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHardSoft {
    @Autowired
    RHardSoft rHardSoft;
    
    public List<hardSoft> list(){
        return rHardSoft.findAll();
    }
    
    public Optional<hardSoft>getOne (int id){
        return rHardSoft.findById(id);
    }
    
    public Optional<hardSoft> getByNombre (String nombre){
        return rHardSoft.findByNombre(nombre);
    }
    
    public void save(hardSoft skill){
        rHardSoft.save(skill);
    }
    
    public void delete(int id){
        rHardSoft.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rHardSoft.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rHardSoft.existsByNombre(nombre);
    }
}

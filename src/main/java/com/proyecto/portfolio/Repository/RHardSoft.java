/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.proyecto.portfolio.Repository;

import com.proyecto.portfolio.Entity.hardSoft;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author CECI
 */
public interface RHardSoft extends JpaRepository<hardSoft, Integer>{
    Optional<hardSoft> findByNombre (String nombre);
    public boolean existsByNombre(String nombre);    
}

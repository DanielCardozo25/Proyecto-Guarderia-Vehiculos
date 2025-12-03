package com.proyectofinal.guarderia.guarderia_web.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectofinal.guarderia.guarderia_web.modelos.Persona;

public interface PersonaBaseRepository<T extends Persona> extends JpaRepository<T, Integer> {
    T findByDniAndContrasenia(Integer dni, String contrasenia);
    T findByDni(Integer dni);
}
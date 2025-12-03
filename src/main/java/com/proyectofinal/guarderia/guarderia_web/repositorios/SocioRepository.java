package com.proyectofinal.guarderia.guarderia_web.repositorios;

import com.proyectofinal.guarderia.guarderia_web.modelos.Socio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocioRepository extends PersonaBaseRepository<Socio> {

    @Override
    @Query("SELECT s FROM Socio s WHERE s.dni = :dni AND s.contrasenia = :contrasenia")
    Socio findByDniAndContrasenia(@Param("dni") Integer dni, @Param("contrasenia") String contrasenia);

    @Override
    @Query("SELECT s FROM Socio s WHERE s.dni = :dni")
    Socio findByDni(@Param("dni") Integer dni);
}
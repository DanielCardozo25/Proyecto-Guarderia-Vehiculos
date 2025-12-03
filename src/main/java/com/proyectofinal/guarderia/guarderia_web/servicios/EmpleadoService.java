package com.proyectofinal.guarderia.guarderia_web.servicios;

import com.proyectofinal.guarderia.guarderia_web.modelos.Empleado;
import com.proyectofinal.guarderia.guarderia_web.repositorios.EmpleadoRepository;
import com.proyectofinal.guarderia.guarderia_web.servicios.base.PersonaServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService extends PersonaServiceBase<Empleado> {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    protected com.proyectofinal.guarderia.guarderia_web.repositorios.PersonaBaseRepository<Empleado> getRepository() {
        return empleadoRepository;
    }

    @Override
    protected String getNombreEntidad() {
        return "empleado";
    }

    public Empleado guardarConDNIString(Empleado empleado, String dniStr) {
        return guardar(empleado, dniStr);
    }
}

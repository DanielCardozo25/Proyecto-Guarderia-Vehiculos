package com.proyectofinal.guarderia.guarderia_web.servicios;

import com.proyectofinal.guarderia.guarderia_web.modelos.Administrador;
import com.proyectofinal.guarderia.guarderia_web.repositorios.AdministradorRepository;
import com.proyectofinal.guarderia.guarderia_web.servicios.base.PersonaServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService extends PersonaServiceBase<Administrador> {

    @Autowired
    private AdministradorRepository administradorRepository;

    @Override
    protected com.proyectofinal.guarderia.guarderia_web.repositorios.PersonaBaseRepository<Administrador> getRepository() {
        return administradorRepository;
    }

    @Override
    protected String getNombreEntidad() {
        return "administrador";
    }

    public Administrador guardarConDNIString(Administrador administrador, String dniStr) {
        return guardar(administrador, dniStr);
    }
}

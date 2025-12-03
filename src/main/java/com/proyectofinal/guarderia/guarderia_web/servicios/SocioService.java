package com.proyectofinal.guarderia.guarderia_web.servicios;

import com.proyectofinal.guarderia.guarderia_web.modelos.Socio;
import com.proyectofinal.guarderia.guarderia_web.repositorios.SocioRepository;
import com.proyectofinal.guarderia.guarderia_web.servicios.base.PersonaServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioService extends PersonaServiceBase<Socio> {

    @Autowired
    private SocioRepository socioRepository;

    @Override
    protected com.proyectofinal.guarderia.guarderia_web.repositorios.PersonaBaseRepository<Socio> getRepository() {
        return socioRepository;
    }

    @Override
    protected String getNombreEntidad() {
        return "socio";
    }

    public Socio guardarConDNIString(Socio socio, String dniStr) {
        return guardar(socio, dniStr);
    }
}

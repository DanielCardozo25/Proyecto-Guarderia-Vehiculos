package com.proyectofinal.guarderia.guarderia_web.servicios.base;

import com.proyectofinal.guarderia.guarderia_web.modelos.Persona;
import com.proyectofinal.guarderia.guarderia_web.repositorios.PersonaBaseRepository;
import com.proyectofinal.guarderia.guarderia_web.excepciones.CampoObligatorioException;
import com.proyectofinal.guarderia.guarderia_web.excepciones.ValidacionException;
import com.proyectofinal.guarderia.guarderia_web.utilidades.ValidacionUtil;

public abstract class PersonaServiceBase<T extends Persona> {
    
    protected abstract PersonaBaseRepository<T> getRepository();
    protected abstract String getNombreEntidad();
    
    public T login(Integer dni, String contrasenia) {
        try {
            return getRepository().findByDniAndContrasenia(dni, contrasenia);
        } catch (Exception e) {
            throw new ValidacionException("Error en el login: " + e.getMessage());
        }
    }
    
    public T guardar(T entidad) {
        try {
            ValidacionUtil.validarDNI(entidad.getDni());
            ValidacionUtil.validarNombre(entidad.getNombre(), "nombre");
            ValidacionUtil.validarDireccion(entidad.getDireccion());
            ValidacionUtil.validarTelefono(entidad.getTelefono());
            
            boolean esNuevo = entidad.getId() == null;
            
            if (entidad.getId() != null) {
                T existente = getRepository().findById(entidad.getId()).orElse(null);
                
                if (existente != null) {
                    if (entidad.getContrasenia() == null || entidad.getContrasenia().trim().isEmpty()) {
                        entidad.setContrasenia(existente.getContrasenia());
                    } else {
                        ValidacionUtil.validarContrasenia(entidad.getContrasenia(), false);
                    }
                    
                    if (!existente.getDni().equals(entidad.getDni())) {
                        T existentePorDNI = getRepository().findByDni(entidad.getDni());
                        if (existentePorDNI != null) {
                            throw new ValidacionException(
                                String.format("Ya existe otro %s con el DNI: %d", getNombreEntidad(), entidad.getDni())
                            );
                        }
                    }
                }
            } else {
                T existente = getRepository().findByDni(entidad.getDni());
                if (existente != null) {
                    throw new ValidacionException(
                        String.format("Ya existe un %s con el DNI: %d", getNombreEntidad(), entidad.getDni())
                    );
                }
                
                ValidacionUtil.validarContrasenia(entidad.getContrasenia(), true);
            }
            
            return getRepository().save(entidad);
        } catch (CampoObligatorioException | ValidacionException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidacionException(
                String.format("Error al guardar %s: %s", getNombreEntidad().toLowerCase(), e.getMessage())
            );
        }
    }
    
    public T guardar(T entidad, String dniStr) {
        try {
            ValidacionUtil.validarDNI(dniStr);
            Integer dni = Integer.parseInt(dniStr);
            entidad.setDni(dni);
            
            ValidacionUtil.validarNombre(entidad.getNombre(), "nombre");
            ValidacionUtil.validarDireccion(entidad.getDireccion());
            ValidacionUtil.validarTelefono(entidad.getTelefono());
            
            boolean esNuevo = entidad.getId() == null;
            
            if (entidad.getId() != null) {
                T existente = getRepository().findById(entidad.getId()).orElse(null);
                
                if (existente != null) {
                    if (entidad.getContrasenia() == null || entidad.getContrasenia().trim().isEmpty()) {
                        entidad.setContrasenia(existente.getContrasenia());
                    } else {
                        ValidacionUtil.validarContrasenia(entidad.getContrasenia(), false);
                    }
                    
                    if (!existente.getDni().equals(entidad.getDni())) {
                        T existentePorDNI = getRepository().findByDni(entidad.getDni());
                        if (existentePorDNI != null) {
                            throw new ValidacionException(
                                String.format("Ya existe otro %s con el DNI: %d", getNombreEntidad(), entidad.getDni())
                            );
                        }
                    }
                }
            } else {
                T existente = getRepository().findByDni(entidad.getDni());
                if (existente != null) {
                    throw new ValidacionException(
                        String.format("Ya existe un %s con el DNI: %d", getNombreEntidad(), entidad.getDni())
                    );
                }
                
                ValidacionUtil.validarContrasenia(entidad.getContrasenia(), true);
            }
            
            return getRepository().save(entidad);
        } catch (CampoObligatorioException | ValidacionException e) {
            throw e;
        } catch (Exception e) {
            throw new ValidacionException(
                String.format("Error al guardar %s: %s", getNombreEntidad().toLowerCase(), e.getMessage())
            );
        }
    }
    
    public T buscarPorId(Integer id) {
        try {
            return getRepository().findById(id).orElse(null);
        } catch (Exception e) {
            throw new ValidacionException(
                String.format("Error al buscar %s: %s", getNombreEntidad().toLowerCase(), e.getMessage())
            );
        }
    }
    
    public void eliminar(Integer id) {
        try {
            getRepository().deleteById(id);
        } catch (Exception e) {
            throw new ValidacionException(
                String.format("Error al eliminar %s: %s", getNombreEntidad().toLowerCase(), e.getMessage())
            );
        }
    }
    
    public Iterable<T> listarTodos() {
        try {
            return getRepository().findAll();
        } catch (Exception e) {
            throw new ValidacionException(
                String.format("Error al listar %s: %s", getNombreEntidad().toLowerCase() + "s", e.getMessage())
            );
        }
    }
}
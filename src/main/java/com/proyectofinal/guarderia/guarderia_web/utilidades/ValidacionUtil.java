package com.proyectofinal.guarderia.guarderia_web.utilidades;

import com.proyectofinal.guarderia.guarderia_web.excepciones.CampoObligatorioException;
import com.proyectofinal.guarderia.guarderia_web.excepciones.ValidacionException;

public class ValidacionUtil {

    public static final int DNI_MIN_DIGITOS = 8;
    public static final int DNI_MAX_DIGITOS = 9;
    public static final int NOMBRE_MIN_LONGITUD = 2;
    public static final int NOMBRE_MAX_LONGITUD = 50;
    public static final int DIRECCION_MIN_LONGITUD = 5;
    public static final int DIRECCION_MAX_LONGITUD = 100;
    public static final int TELEFONO_MIN_DIGITOS = 7;
    public static final int TELEFONO_MAX_DIGITOS = 15;
    public static final int CONTRASENIA_MIN_LONGITUD = 6;
    public static final int CONTRASENIA_MAX_LONGITUD = 25;

    private ValidacionUtil() {
    }

    public static void validarDNI(Integer dni) {
        if (dni == null) {
            throw new CampoObligatorioException("DNI");
        }

        String dniStr = dni.toString();
        int longitud = dniStr.length();

        if (dni < 0) {
            throw new ValidacionException("El DNI no puede ser negativo");
        }

        if (longitud < DNI_MIN_DIGITOS) {
            throw new ValidacionException(
                    String.format("El DNI debe tener al menos %d dígitos", DNI_MIN_DIGITOS)
            );
        }

        if (longitud > DNI_MAX_DIGITOS) {
            throw new ValidacionException(
                    String.format("El DNI no puede tener más de %d dígitos", DNI_MAX_DIGITOS)
            );
        }
    }

    public static void validarDNI(String dniStr) {
        if (dniStr == null || dniStr.trim().isEmpty()) {
            throw new CampoObligatorioException("DNI");
        }

        String dniTrim = dniStr.trim();

        if (!dniTrim.matches("^[0-9]+$")) {
            throw new ValidacionException("El DNI solo puede contener números");
        }

        if (dniTrim.length() < DNI_MIN_DIGITOS) {
            throw new ValidacionException(
                    String.format("El DNI debe tener al menos %d dígitos", DNI_MIN_DIGITOS)
            );
        }

        if (dniTrim.length() > DNI_MAX_DIGITOS) {
            throw new ValidacionException(
                    String.format("El DNI no puede tener más de %d dígitos", DNI_MAX_DIGITOS)
            );
        }

        try {
            Long.parseLong(dniTrim);
        } catch (NumberFormatException e) {
            throw new ValidacionException("El DNI es demasiado grande");
        }
    }

    public static void validarNombre(String nombre, String campo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new CampoObligatorioException(campo);
        }

        String nombreTrim = nombre.trim();
        int longitud = nombreTrim.length();

        if (longitud < NOMBRE_MIN_LONGITUD) {
            throw new ValidacionException(
                    String.format("El campo '%s' debe tener al menos %d caracteres", campo, NOMBRE_MIN_LONGITUD)
            );
        }

        if (longitud > NOMBRE_MAX_LONGITUD) {
            throw new ValidacionException(
                    String.format("El campo '%s' no puede tener más de %d caracteres", campo, NOMBRE_MAX_LONGITUD)
            );
        }
    }

    public static void validarDireccion(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new CampoObligatorioException("dirección");
        }

        String direccionTrim = direccion.trim();
        int longitud = direccionTrim.length();

        if (longitud < DIRECCION_MIN_LONGITUD) {
            throw new ValidacionException(
                    String.format("La dirección debe tener al menos %d caracteres", DIRECCION_MIN_LONGITUD)
            );
        }

        if (longitud > DIRECCION_MAX_LONGITUD) {
            throw new ValidacionException(
                    String.format("La dirección no puede tener más de %d caracteres", DIRECCION_MAX_LONGITUD)
            );
        }
    }

    public static void validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new CampoObligatorioException("teléfono");
        }

        String telefonoTrim = telefono.trim();
        String telefonoSoloNumeros = telefonoTrim.replaceAll("[^0-9]", "");

        if (telefonoSoloNumeros.length() < TELEFONO_MIN_DIGITOS) {
            throw new ValidacionException(
                    String.format("El teléfono debe tener al menos %d dígitos", TELEFONO_MIN_DIGITOS)
            );
        }

        if (telefonoSoloNumeros.length() > TELEFONO_MAX_DIGITOS) {
            throw new ValidacionException(
                    String.format("El teléfono no puede tener más de %d dígitos", TELEFONO_MAX_DIGITOS)
            );
        }
    }

    public static void validarContrasenia(String contrasenia, boolean esNuevo) {
        if (contrasenia == null || contrasenia.trim().isEmpty()) {
            if (esNuevo) {
                throw new CampoObligatorioException("contraseña");
            }
            return;
        }

        int longitud = contrasenia.length();

        if (longitud < CONTRASENIA_MIN_LONGITUD) {
            throw new ValidacionException(
                    String.format("La contraseña debe tener al menos %d caracteres", CONTRASENIA_MIN_LONGITUD)
            );
        }

        if (longitud > CONTRASENIA_MAX_LONGITUD) {
            throw new ValidacionException(
                    String.format("La contraseña no puede tener más de %d caracteres", CONTRASENIA_MAX_LONGITUD)
            );
        }
    }

    public static void validarMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new CampoObligatorioException("matrícula");
        }

        String matriculaTrim = matricula.trim().toUpperCase();

        if (!matriculaTrim.matches("^[A-Z]{2}[0-9]{3}[A-Z]{3}$")
                && !matriculaTrim.matches("^[A-Z]{2}[0-9]{6}$")) {
            throw new ValidacionException(
                    "La matrícula debe tener formato: 2 letras + 3 números + 3 letras (AB123CD) o 2 letras + 6 números"
            );
        }
    }

    public static void validarFecha(String fecha, String campo) {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new CampoObligatorioException(campo);
        }

        if (!fecha.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new ValidacionException(
                    String.format("El campo '%s' debe tener formato YYYY-MM-DD", campo)
            );
        }
    }

    public static void validarNumeroPositivo(Integer numero, String campo) {
        if (numero == null) {
            throw new CampoObligatorioException(campo);
        }

        if (numero < 0) {
            throw new ValidacionException(
                    String.format("El campo '%s' no puede ser negativo", campo)
            );
        }
    }

    public static void validarNumeroDecimalPositivo(Double numero, String campo) {
        if (numero == null) {
            throw new CampoObligatorioException(campo);
        }

        if (numero < 0.0) {
            throw new ValidacionException(
                    String.format("El campo '%s' no puede ser negativo", campo)
            );
        }
    }
}

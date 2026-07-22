package com.diegomartinez.exceptions;

public class ExcepcionesPersonalizadas extends Exception {
    public ExcepcionesPersonalizadas(String mensajeError) {
        super(mensajeError);
    }
    
    public ExcepcionesPersonalizadas() {
        
    }
    
    public void validarCampoNumericoEntero(String cadenaTexto) throws ExcepcionesPersonalizadas {
        String cadenaNumeros = "0123456789";
        for (char letraIngresada : cadenaTexto.toCharArray()) {
            if(!cadenaNumeros.contains(String.valueOf(letraIngresada)))
                throw new ExcepcionesPersonalizadas("INGRESASTE LETRAS EN LA CLAVE");
        }
    }
    
    public void validarLongitudTexto(String cadenaTexto, int cantidadMaxima) throws ExcepcionesPersonalizadas {
        if(cadenaTexto.length() > cantidadMaxima)
            throw new ExcepcionesPersonalizadas("SUPERASTE LIMITE DE LETRAS: total " + cantidadMaxima);
    }
}

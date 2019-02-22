/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Arrays;

/**
 *
 * @author Ragnar
 */
public class Utilidades {

    public int controlarCampoDNI(String DNI) {
        int resultado = 0;
        char[] arrayDNI = DNI.toCharArray();
        if (arrayDNI.length == 9) {
            resultado = 1;
            for (int i = 0; i < arrayDNI.length; i++) {
                if (arrayDNI[i] < '0' || arrayDNI[i] > '9') {
                    resultado = -1;
                }
                if (i == 8) {
                    if (arrayDNI[8] >= 'A' || arrayDNI[8] <= 'Z') {
                        resultado = 1;
                    }
                }

            }
        } else {
            resultado = -2;
        }
        return resultado;
    }

    public int controlarCampoTelefono(String telefono) {
        int resultado = 0;
        char[] arrayTelefono = telefono.toCharArray();
        if (arrayTelefono.length == 9) {
            resultado = 1;
            for (int i = 0; i < arrayTelefono.length; i++) {
                if (arrayTelefono[i] <= 47 || arrayTelefono[i] >= 58) {
                    resultado = -1;
                }
            }
        } else {
            resultado = -2;
        }
        return resultado;
    }

    public int comprobarCorreo(String correo) {
        int resultado = 0;
        String[] splitCorreo = correo.split("@");
        String correoConCorchetes = "[" + correo + "]";
        if (correoConCorchetes.equals(Arrays.toString(splitCorreo))) {
            resultado = -1;
        } else if (splitCorreo[0].equals("")) {
            resultado = -1;
        } else {

            switch (splitCorreo[1]) {
                case "gmail.com":
                    resultado = 1;
                    break;
                case "yahoo.com":
                    resultado = 1;
                    break;
                case "hotmail.com":
                    resultado = 1;
                    break;
                case "gmail.es":
                    resultado = 1;
                    break;
                case "yahoo.es":
                    resultado = 1;
                    break;
                case "hotmail.es":
                    resultado = 1;
                    break;
                default:
                    resultado = -1;
            }
        }
        return resultado;
    }

}

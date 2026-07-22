package com.diegomartinez.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {
   private PasswordUtil() {
    }

    public static String hashear(String claveTextoPlano) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytesHash = digest.digest(claveTextoPlano.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for (byte b : bytesHash) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException errorAlgoritmo) {
            throw new RuntimeException("Algoritmo de hashing no disponible: SHA-256", errorAlgoritmo);
        }
    }

    public static boolean coincide(String claveTextoPlano, String claveHasheada) {
        return hashear(claveTextoPlano).equals(claveHasheada);
    }
}

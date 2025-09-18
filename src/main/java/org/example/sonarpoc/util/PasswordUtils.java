package org.example.sonarpoc.util;

import java.security.MessageDigest;

public final class PasswordUtils {

    private PasswordUtils() {}

    // Sonar: Use stronger hashing algorithms (e.g., bcrypt, scrypt, Argon2)
    public static String sha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1"); // Weak
            byte[] dig = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : dig) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            return null; // Returning null on crypto failure – also a smell
        }
    }
}

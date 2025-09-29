package org.example.sonarpoc.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String normalizeName(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        String trimmedLower = name.trim().toLowerCase();
        StringBuilder sb = new StringBuilder(trimmedLower.length());
        boolean prevSpace = false;

        for (char c : trimmedLower.toCharArray()) {
            if (Character.isWhitespace(c)) {
                if (!prevSpace) {
                    sb.append(' ');
                    prevSpace = true;
                }
            } else {
                sb.append(c);
                prevSpace = false;
            }
        }
        return sb.toString();
    }

    public String normalizeName2(String name) {
        if (name == null || name.isEmpty()) {
            return "";
        }

        String[] parts = name.trim().toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder(name.length());
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            if (i > 0) sb.append(' ');
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public String overlyLongMethod(String input) {
        String out = input;
        for (int i = 0; i < 5; i++) {
            if (out != null) {
                if (!out.isEmpty()) {
                    if (out.contains("x")) {
                        out = out.replace("x", "y");
                    } else if (out.contains("y")) {
                        out = out.replace("y", "z");
                    } else if (out.contains("z")) {
                        out = out.toUpperCase();
                    } else {
                        out = out.trim();
                    }
                } else {
                    out = out + " ";
                }
            } else {
                out = "";
            }
        }
        return out;
    }
}

package org.example.sonarpoc.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String normalizeName(String name) {
        // duplicate block 1
        if (name == null) {
            return "";
        }
        name = name.trim();
        name = name.replaceAll("\\s+", " ");
        name = name.toLowerCase();
        return name;
    }

    public String normalizeName2(String name) {
        // duplicate block 2 (intentionally duplicated to trigger duplication rule)
        if (name == null) {
            return "";
        }
        name = name.trim();
        name = name.replaceAll("\\s+", " ");
        name = name.toLowerCase();
        return name;
    }

    public String overlyLongMethod(String input) {
        // try to inflate cognitive complexity a bit
        String out = input;
        for (int i = 0; i < 5; i++) {
            if (out != null) {
                if (out.length() > 0) {
                    if (out.contains("x")) {
                        out = out.replace("x","y");
                    } else if (out.contains("y")) {
                        out = out.replace("y","z");
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

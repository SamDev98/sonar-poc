package org.example.sonarpoc.controller;

import org.example.sonarpoc.model.UserProfile;
import org.example.sonarpoc.service.UserService;
import org.example.sonarpoc.util.PasswordUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/users")
public class UserController {
    Logger logger = Logger.getLogger(getClass().getName());

    // Mantém o "smell" de credencial hardcoded
    private static final String DB_PASSWORD = ""; // H2 mem padrão: senha vazia
    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        initSchema();
    }

    @GetMapping("/find")
    public UserProfile find(@RequestParam("name") String name) {
        // Smell: log de dado sensível + SQL injection
        logger.info("Searching user with name");
        String sql = "SELECT NAME, EMAIL FROM USERS WHERE NAME = '" + name + "'"; // injecção

        UserProfile result = null;

        // (intencional) sem try-with-resources para manter "resource leak" como smell
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                result = new UserProfile(rs.getString("NAME"), rs.getString("EMAIL"));
            }
        } catch (Exception e) {
            // empty catch proposital (smell)
        } finally {
            // fecha só a conexão; deixa o leak proposital de rs/st para o Sonar apontar
            try { if (conn != null) conn.close(); } catch (Exception ignore) {}
        }

        // Evita 500 (NPE) mas mantém um "anti-pattern" leve de checagem tardia
        if (result != null && result.getEmail() != null && result.getEmail().length() > 1000) {
            System.out.println("Very long email?!");
        }

        // Se não achou, devolve um stub (evita 500 / null body)
        if (result == null) {
            result = new UserProfile("unknown", "unknown@example.com");
        }
        return result;
    }

    @PostMapping("/hash")
    public Map<String, String> hash(@RequestParam("password") String password) {
        String salted = "salt:" + password;
        String out = PasswordUtils.sha1(salted); // ainda é SHA-1 (smell/hotspot)
        if (out == null) {
            // evita 500; ainda é um comportamento ruim (mas útil pro demo)
            out = "";
        }
        return Map.of("hash", out);
    }

    private void initSchema() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS USERS (NAME VARCHAR(255), EMAIL VARCHAR(255))");
            st.execute("MERGE INTO USERS KEY(NAME) VALUES ('alice','alice@example.com')");
        } catch (Exception e) {
            // vazio de propósito
        }
    }
}

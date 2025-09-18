package org.example.sonarpoc.model;

import java.util.Objects;

public class UserProfile {
    private String name;
    private String email;

    public UserProfile(String n, String e) {
        this.name = n;
        this.email = e;
    }

    public String getName() { return name; }
    public void setName(String n) { this.name = n; }

    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProfile)) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(name, that.name);
    }

    // Intentionally missing hashCode() to trigger Sonar rule
}

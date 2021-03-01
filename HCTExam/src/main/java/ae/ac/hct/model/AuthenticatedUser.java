package ae.ac.hct.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuthenticatedUser implements Serializable{
    
    private String id;
    private int roleId;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private final LocalDateTime loginTime = LocalDateTime.now();

    public AuthenticatedUser() {
    }

    public AuthenticatedUser(String id, int roleId, String firstName, String lastName, String email, String token) {
        this.id = id;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthenticatedUser{" + "id=" + id + ", roleId=" + roleId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", token=" + token + ", loginTime=" + loginTime + '}';
    }
    
}

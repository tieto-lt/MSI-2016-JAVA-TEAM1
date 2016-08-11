package lt.tieto.msi2016.user.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class User {

    private Long id;

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Pattern(regexp="[A-Za-z]{1,25}$")
    private String fullName;

    @Pattern(regexp="^.+@.+\\..+$")
    private String email;

    @Pattern(regexp="^(\\+370) *(\\d{8})$")
    @NotNull
    private String phone;

    private Boolean enabled;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("username", username)
                .append("password", password)
                .append("enabled", enabled)
                .append("name", fullName)
                .append("email", email)
                .append("phone", phone)
                .toString();
    }
}
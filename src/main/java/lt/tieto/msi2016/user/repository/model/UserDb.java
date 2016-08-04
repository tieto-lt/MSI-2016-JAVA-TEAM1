package lt.tieto.msi2016.user.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserDb extends DbModel {

    private Long id;
    private String username;
    private String password;
    private Boolean enabled;
    private String name;
    private String email;
    private String phone;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                .append("name", name)
                .append("email", email)
                .append("phone", phone)
                .toString();
    }
}

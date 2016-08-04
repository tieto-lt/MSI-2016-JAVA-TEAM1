package lt.tieto.msi2016.Registration.model;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

    private Long id;

    @NotNull
    private String userName;

    @NotNull
    @Pattern(regexp="/[A-Z][a-z]{3,20}$/")
    private String name;

    @Pattern(regexp="^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    private String email;


    @Pattern(regexp="^(\\+3706) *(\\d{8})$")
    @NotNull
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName){this.userName = userName;}

    public String getUserName(){return userName;}

    public void setEmail (String email){this.email = email;}

    public String getEmail (){return email;}

    public void setPhone(String phone){this.phone = phone;}

    public String getPhone(){return phone;}

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("user name", userName)
                .append("email", email)
                .append("phone", phone)
                .toString();
    }
}

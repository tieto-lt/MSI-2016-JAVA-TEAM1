package lt.tieto.msi2016.roles.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Role {

    private Long id;
    private Long userId;
    private String username;
    private String authority;

    public Role(){

    }

    public Role(Long userId, String username, String authority){
        this.userId = userId;
        this.username = username;
        this.authority = authority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("userId", userId)
                .append("username", username)
                .append("authority", authority)
                .toString();
    }
}

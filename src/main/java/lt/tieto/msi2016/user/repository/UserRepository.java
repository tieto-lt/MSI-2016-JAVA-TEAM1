package lt.tieto.msi2016.user.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository<UserDb> {

    public static final String SELECT_BY_USERNAME = "SELECT * FROM users where username = ?";

    @Autowired
    private JdbcTemplate template;

    private static final RowMapper<UserDb> ROW_MAPPER = (rs, rowNum) -> {
        UserDb user = new UserDb();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPhone(rs.getString("phone"));
        return user;
    };

    private static final RowUnmapper<UserDb> ROW_UNMAPPER = userDb -> mapOf(
            "id", userDb.getId(),
            "username", userDb.getUsername(),
            "password", userDb.getPassword(),
            "enabled", userDb.getEnabled(),
            "name", userDb.getName(),
            "email", userDb.getEmail(),
            "phone", userDb.getPhone()
    );

    public UserRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "users", "id");
    }

    public UserDb getUserByUsername(String username) {
        List<UserDb> users = template.query(SELECT_BY_USERNAME, new Object[]{username}, ROW_MAPPER);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

}
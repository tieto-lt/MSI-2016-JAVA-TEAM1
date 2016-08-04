package lt.tieto.msi2016.user.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<UserDb> {

    private static final RowMapper<UserDb> ROW_MAPPER = (rs, rowNum) -> {
        UserDb item = new UserDb();
        item.setId(rs.getLong("id"));
        item.setUsername(rs.getString("username"));
        item.setPassword(rs.getString("password"));
        item.setEnabled(rs.getBoolean("enabled"));
        item.setName(rs.getString("name"));
        item.setEmail(rs.getString("email"));
        item.setPhone(rs.getString("phone"));
        return item;
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
}
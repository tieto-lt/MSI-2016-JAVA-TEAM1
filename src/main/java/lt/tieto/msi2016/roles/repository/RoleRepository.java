package lt.tieto.msi2016.roles.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.roles.repository.model.RoleDb;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class RoleRepository extends BaseRepository<RoleDb> {

    public static final String SELECT_ADMINS_COUNT = "SELECT COUNT(*) FROM authorities where authority = 'ROLE_ADMIN'";

    @Autowired
    private JdbcTemplate template;

    private static final RowMapper<RoleDb> ROW_MAPPER = (rs, rowNum) -> {
        RoleDb item = new RoleDb();
        item.setId(rs.getLong("id"));
        item.setUserId(rs.getLong("user_id"));
        item.setUsername(rs.getString("username"));
        item.setAuthority(rs.getString("authority"));
        return item;
    };

    private static final RowUnmapper<RoleDb> ROW_UNMAPPER = roleDb -> mapOf(
            "id", roleDb.getId(),
            "user_id", roleDb.getUserId(),
            "username", roleDb.getUsername(),
            "authority", roleDb.getAuthority()
    );

    public RoleRepository() {
        super(ROW_MAPPER, ROW_UNMAPPER, "authorities", "id");
    }


    public Integer getAdminsCount() {
        return template.queryForObject(SELECT_ADMINS_COUNT, Integer.class);
    }


}
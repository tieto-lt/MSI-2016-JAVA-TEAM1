package lt.tieto.msi2016.roles.repository;

import com.nurkiewicz.jdbcrepository.RowUnmapper;
import lt.tieto.msi2016.roles.repository.model.RoleDb;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.repository.BaseRepository;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends BaseRepository<RoleDb> {

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
}
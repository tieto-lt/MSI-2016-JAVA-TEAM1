package lt.tieto.msi2016.user.service;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.repository.ItemRepository;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.item.service.ItemService;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.roles.model.Role;
import lt.tieto.msi2016.roles.service.RoleService;
import lt.tieto.msi2016.user.model.User;
import lt.tieto.msi2016.user.repository.UserRepository;
import lt.tieto.msi2016.user.repository.model.UserDb;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import lt.tieto.msi2016.utils.exception.ValidationException;
import lt.tieto.msi2016.utils.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private SecurityService securityService;

    @Transactional(readOnly = true)
    public User get(Long id) {
        if (securityService.getCurrentUser().getId() != id) {
            throw new AccessDeniedException("Cannot access user data id=" + id);
        }
        UserDb user = repository.findOne(id);
        if (user != null) {
            return mapToUser(user);
        } else {
            throw new DataNotFoundException("User with id " + id + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<User> all() {
        return repository.findAll().stream().map(UserService::mapToUser).collect(Collectors.toList());
    }

    @Transactional
    public User createUser(User user) {
        UserDb userByUsername = getUserDbByUsername(user.getUsername());
        if (userByUsername != null) {
            throw new ValidationException("username", "already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        UserDb db = repository.create(mapToUserDb(user));
        roleService.createRole(new Role(db.getId(), db.getUsername(), Roles.CUSTOMER));
        return mapToUser(db);
    }

    @Transactional(readOnly = true)
    public User getUserByUsername(String username) {
        UserDb user = repository.getUserByUsername(username);
        return mapToUser(user);
    }

    private static User mapToUser(UserDb db) {
        User api = new User();
        api.setId(db.getId());
        api.setUsername(db.getUsername());
        api.setEnabled(db.getEnabled());
        api.setName(db.getName());
        api.setEmail(db.getEmail());
        api.setPhone(db.getPhone());
        return api;
    }

    private static UserDb mapToUserDb(Long id, User api) {
        UserDb db = new UserDb();
        db.setId(id);
        db.setUsername(api.getUsername());
        db.setPassword(api.getPassword());
        db.setEnabled(Boolean.TRUE);
        db.setName(api.getName());
        db.setEmail(api.getEmail());
        db.setPhone(api.getPhone());
        return db;
    }

    private UserDb getUserDbByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    private static UserDb mapToUserDb(User api) {
        return mapToUserDb(api.getId(), api);
    }

}

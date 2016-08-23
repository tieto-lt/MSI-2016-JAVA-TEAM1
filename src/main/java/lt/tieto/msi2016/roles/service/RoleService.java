package lt.tieto.msi2016.roles.service;

import lt.tieto.msi2016.operator.services.OperatorVerificationService;
import lt.tieto.msi2016.roles.Roles;
import lt.tieto.msi2016.roles.model.Role;
import lt.tieto.msi2016.roles.repository.RoleRepository;
import lt.tieto.msi2016.roles.repository.model.RoleDb;
import lt.tieto.msi2016.utils.exception.BusinessException;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;
    @Autowired
    private OperatorVerificationService operatorVerificationService;

    @Transactional(readOnly = true)
    public Role get(Long id) {
        RoleDb role = repository.findOne(id);
        if (role != null) {
            return mapToRole(role);
        } else {
            throw new DataNotFoundException("Role with id " + id + " not found");
        }
    }

    @Transactional(readOnly = true)
    public List<Role> all() {
        return repository.findAll().stream().map(lt.tieto.msi2016.roles.service.RoleService::mapToRole).collect(Collectors.toList());
    }

    @Transactional
    public Role createOrUpdateRole(Long id, Role role) {
        if (repository.exists(id)) {
            if (Roles.OPERATOR.equals(role.getAuthority())){
                operatorVerificationService.createOperatorVerificationStatus(role.getUserId());
            } else if (Roles.OPERATOR.equals(get(id).getAuthority())) {
                operatorVerificationService.deleteOperatorVerificationStatus(role.getUserId());
            }
            return updateRole(id, role);
        } else {
            RoleDb created = repository.create(mapToRoleDb(id, role));
            return mapToRole(created);
        }
    }

    @Transactional
    public Boolean isLastAdmin(Long authorityId){
        return Roles.ADMIN.equals(get(authorityId).getAuthority()) && (repository.getAdminsCount() == 1);
    }


    @Transactional
    private synchronized Role updateRole(Long id, Role role) {
        if (!isLastAdmin(id)) {
            return mapToRole(repository.update(mapToRoleDb(id, role)));
        } else {
            throw new BusinessException("You can't remove last admin from the system");
        }
    }

    @Transactional
    public Role createRole(Role role) {
        return mapToRole(repository.create(mapToRoleDb(role)));
    }

    @Transactional
    public void remove(Long id) {
        if (!repository.exists(id)) {
            throw new DataNotFoundException("Role with id " + id + " doesn't exist");
        }
        repository.delete(id);
    }

    private static Role mapToRole(RoleDb db) {
        Role api = new Role();
        api.setId(db.getId());
        api.setUserId(db.getUserId());
        api.setUsername(db.getUsername());
        api.setAuthority(db.getAuthority());
        return api;
    }

    private static RoleDb mapToRoleDb(Long id, Role api) {
        RoleDb db = new RoleDb();
        db.setId(id);
        db.setUserId(api.getUserId());
        db.setUsername(api.getUsername());
        db.setAuthority(api.getAuthority());
        return db;
    }

    private static RoleDb mapToRoleDb(Role api) {
        return mapToRoleDb(api.getId(), api);
    }

}
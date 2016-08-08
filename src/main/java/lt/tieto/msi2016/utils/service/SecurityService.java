package lt.tieto.msi2016.utils.service;


import lt.tieto.msi2016.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;

    public String getCurrentUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUsername();
    }

    public lt.tieto.msi2016.user.model.User getCurrentUser() {
        String currentUsername = getCurrentUsername();
        if (currentUsername != null) {
            return userService.getUserByUsername(currentUsername);
        }
        return null;
    }
}

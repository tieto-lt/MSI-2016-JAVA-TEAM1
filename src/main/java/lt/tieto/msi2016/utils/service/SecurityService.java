package lt.tieto.msi2016.utils.service;


import lt.tieto.msi2016.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private UserService userService;
    @Autowired
    private TokenStore tokenStore;

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

    public void removeToken(){
        if (SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null) {
            String tokenValue = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

}

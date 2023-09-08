package cola.template.sb2_template.system.service.impl;

import cola.template.sb2_template.system.dao.UserDao;
import cola.template.sb2_template.system.entity.Permission;
import cola.template.sb2_template.system.entity.Role;
import cola.template.sb2_template.system.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Cola0817
 * @date 2023/9/8 17:15
 * @since 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        List<Role> roles = user.getRoles();
        List<Permission> permissions = roles.stream().flatMap(r->r.getPermissions().stream()).distinct().toList();
        user.setAuthorities(permissions);
        return user;
    }
}

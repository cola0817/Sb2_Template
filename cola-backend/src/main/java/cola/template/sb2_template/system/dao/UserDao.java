package cola.template.sb2_template.system.dao;


import cola.template.sb2_template.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户持久层
 * @author Cola0817
 * @date 2023/9/8 17:03
 * @since 1.0
 */
@Repository
public interface UserDao extends BaseDao<User> {

    /**
     * 根据用户名查询用户
     */
    User findByUsername(String username);
}

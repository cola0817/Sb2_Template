package cola.template.sb2_template.system.service;

/**
 * @author Cola0817
 * @date 2023/9/8 15:40
 * @since 1.0
 */
import cola.template.sb2_template.system.entity.BaseEntity;
import org.springframework.data.domain.Page;

public interface BaseService<T extends BaseEntity> {


    T findById(Long id);

    void save(T entity);

    void updateById(T entity);

    void deleteById(Long id);

    Page<T> findAll(Integer page, Integer size, String sort, T condition);
}


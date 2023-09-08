package cola.template.sb2_template.system.service.impl;

import cola.template.sb2_template.common.utils.JpaUtils;
import cola.template.sb2_template.common.utils.SecurityUtils;
import cola.template.sb2_template.system.dao.BaseDao;
import cola.template.sb2_template.system.entity.BaseEntity;
import cola.template.sb2_template.system.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
 * @author Cola0817
 * @date 2023/9/8 17:05
 * @since 1.0
 */
@Service
public abstract class BaseServiceImpl<D extends BaseDao<T>, T extends BaseEntity> implements BaseService<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected D dao;


    @Override
    public T findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public void save(T entity) {
        if (entity.getIsDeleted() == null) {
            entity.setIsDeleted(0);
        }
        SecurityUtils.getLoginUser()
                .ifPresent(user -> {
                    entity.setCreatedBy(user.getId());
                    entity.setUpdatedBy(user.getId());
                });
        if (entity.getCreatedTime() == null) {
            entity.setCreatedTime(LocalDateTime.now());
        }
        entity.setUpdatedTime(LocalDateTime.now());

        dao.save(entity);
    }

    @Override
    public void updateById(T entity) {
        SecurityUtils.getLoginUser()
                .ifPresent(user -> entity.setUpdatedBy(user.getId()));
        entity.setUpdatedTime(LocalDateTime.now());
        dao.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        dao.findById(id)
                .ifPresent(entity -> {
                    SecurityUtils.getLoginUser()
                            .ifPresent(user -> entity.setUpdatedBy(user.getId()));
                    entity.setUpdatedTime(LocalDateTime.now());
                    entity.setIsDeleted(1);
                    dao.save(entity);
                });
    }

    @Override
    public Page<T> findAll(Integer page, Integer size, String sort, T condition) {
        // 将sort解析为Sort对象
        Sort sortObj = JpaUtils.parseSort(sort);

        // 构造分页对象
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        // 构造查询条件，将isDeleted设置为0，表示未删除的数据
        condition.setIsDeleted(0);
        Example<T> example = Example.of(condition);
        return dao.findAll(example, pageable);
    }
}


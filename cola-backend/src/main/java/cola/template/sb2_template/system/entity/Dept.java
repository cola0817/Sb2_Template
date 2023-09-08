package cola.template.sb2_template.system.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Cola0817
 * @date 2023/9/8 15:07
 * @since 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_dept")
@Data
public class Dept extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门层级路径（逗号分隔，不包含本级）
     */
    @Column
    private String ancestors;

    /**
     * 部门名称
     */
    @Column
    private String name;

    /**
     * 排序
     */
    @Column
    private Integer orderNum;

    /**
     * 父级部门ID
     */
    @Column
    private Long parentId;


}

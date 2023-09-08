package cola.template.sb2_template.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Cola0817
 * @date 2023/9/8 15:07
 * @since 1.0
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_role")
@Data
public class Role extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 代表权限的字符串
     */
    @ApiModelProperty("代表权限的字符串")
    @Column
    private String roleKey;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    @Column
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    @Column
    private Integer sort;

    @ApiModelProperty(hidden = true)
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @ApiModelProperty(hidden = true)
    @ManyToMany
    @JoinTable(name="system_role_permission",
            joinColumns=
            @JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="permission_id", referencedColumnName="id")
    )
    private List<Permission> permissions;


}


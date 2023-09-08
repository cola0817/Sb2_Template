package cola.template.sb2_template.system.entity;

/**
 * @author Cola0817
 * @date 2023/9/8 15:08
 * @since 1.0
 */
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

/**
 * 权限实体类
 * @author Cola0817
 * @date 2023/9/8 15:08
 * @since 1.0
 */
@ApiModel(description = "权限实体类")
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "system_permission")
@Data
public class Permission extends BaseEntity implements GrantedAuthority {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限字符串
     */
    @ApiModelProperty("权限字符串")
    @Column
    private String name;

    @ApiModelProperty(hidden = true)
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles;

    @Override
    public String getAuthority() {
        return name;
    }
}


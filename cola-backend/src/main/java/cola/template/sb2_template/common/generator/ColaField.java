package cola.template.sb2_template.common.generator;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Cola0817
 * @date 2023/9/8 15:13
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class ColaField {
    private String name;
    private ColaFieldType type;
    private String comment;
}

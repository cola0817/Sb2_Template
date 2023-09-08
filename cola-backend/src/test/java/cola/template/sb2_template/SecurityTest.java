package cola.template.sb2_template;

import cola.template.sb2_template.common.utils.SecurityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Cola0817
 * @date 2023/9/8 17:54
 * @since 1.0
 */

public class SecurityTest {
    @Test
    void testBCryptPasswordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String encode = encoder.encode("Ccc.0515");

        System.out.println(encode);
    }
}

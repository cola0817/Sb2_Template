package cola.template.sb2_template.system.service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

/**
 * @author Cola0817
 * @date 2023/9/8 17:13
 * @since 1.0
 */
@Component
public class JobHandler {

    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        System.out.println("XXL-JOB, Hello World.");
        return ReturnT.SUCCESS;
    }
}

package cola.template.sb2_template.system.controller;

import cola.template.sb2_template.system.entity.Dept;
import cola.template.sb2_template.system.entity.Result;
import cola.template.sb2_template.system.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Cola0817
 * @date 2023/9/8 17:16
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/dept")
@Slf4j
public class DeptController {


    @Resource
    private DeptService deptService;

    @GetMapping("/{id}")
    Result<Dept> getById(@PathVariable Long id) {
        return Result.ok(deptService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody Dept dept) {
        deptService.save(dept);
        return Result.ok();
    }

    @PutMapping("/update")
    Result<Void> update(@RequestBody Dept dept) {
        deptService.updateById(dept);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    Result<Void> deleteById(@PathVariable Long id) {
        deptService.deleteById(id);
        return Result.ok();
    }


}


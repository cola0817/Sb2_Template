package cola.template.sb2_template.system.controller;


import cola.template.sb2_template.system.annotation.OperationLogging;
import cola.template.sb2_template.system.annotation.SystemLogging;
import cola.template.sb2_template.system.entity.Result;
import cola.template.sb2_template.system.entity.User;
import cola.template.sb2_template.system.enums.OperationType;
import cola.template.sb2_template.system.service.UserService;
import cola.template.sb2_template.system.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Cola0817
 * @date 2023/9/8 17:06
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据用户ID获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasAuthority('system:user:list')")
    @SystemLogging
    Result<User> getById(@PathVariable Long id) {
        return Result.ok(userService.findById(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表")
    @PreAuthorize("hasAuthority('system:user:list')")
    @SystemLogging
    Result<Page<User>> list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String sort, User user) {
        return Result.ok(userService.findAll(page, size, sort, user));
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存用户")
    @PreAuthorize("hasAuthority('system:user:add')")
    Result<Void> save(@RequestBody User user) {
        userService.save(user);
        return Result.ok();
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新用户")
    @PreAuthorize("hasAuthority('system:user:edit')")
    Result<Void> update(@RequestBody User user) {
        userService.updateById(user);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据用户ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @PreAuthorize("hasAuthority('system:user:remove')")
    @OperationLogging(value = "删除用户", type = OperationType.DELETE)
    Result<Void> deleteById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return Result.error("id为" + id + "的用户不存在");
        }
        userService.deleteById(id);
        return Result.ok();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "登录")
    Result<String> login(@RequestBody User user) {
        userService.login(user);
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        return Result.ok(token);
    }

}
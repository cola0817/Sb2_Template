# SpringBoot2_Template

## 一、概述

SpringBoot2 模版项目

### 1.1 版本信息

|   Tools    | Version  |
|:----------:|:--------:|
|    JDK     |  17.0.8  |
| SpringBoot |  2.7.8   |
|   Maven    |   3.x    |
|   Mysql    |  8.0.x   |
|    IDEA    | 2023.2.1 |
|    Node    | 18.17.1  |
|    Yarn    | 1.22.19  |

### 1.2 Features

- 系统层面
  1. 支持代码生成
  2. 日志：系统日志、业务日志（登录、操作、异常）
  3. 定时任务
  4. 接口文档
  5. 监控数据库连接池状态
  6. 系统鉴权——对应用户、角色、权限管理

- 业务层面
  1. 用户管理
  2. 角色管理/权限管理
  3. 菜单管理
  4. 部门管理
  5. 数据字典
  6. 系统通知
  7. 文件服务
  8. 其他常用功能：系统参数配置、在线状态监控


## 二、使用

### 2.1 git

```cmd
git clone https://github.com/cola0817/Sb2_Template.git
```

### 2.2 import maven dependencies
maven import

### 2.3 choose jdk
jdk 17.0.8

### 2.4 sql init
run sql script (in the sql dir)
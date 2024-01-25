-- 创建库
create database if not exists xinapi;

-- 切换库
use xinapi;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userName     varchar(256)                           null comment '用户昵称',
    userAccount  varchar(256)                           not null comment '账号',
    userAvatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword varchar(512)                           not null comment '密码',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount
        unique (userAccount)
) comment '用户';


-- 切换库
use xinapi;
-- 用户-接口关系表
create table if not exists user_interface_info
(
    id                bigint auto_increment comment 'id' primary key,
    userId            bigint                             not null comment '用户 id',
    interface_info_id bigint                             not null comment '接口 id',
    total_num         int      default 0                 not null comment '总调用次数',
    left_num          int      default 0                 not null comment '剩余调用次数',
    status            int      default 0                 not null comment '0-正常，1-禁用',
    createTime        datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime        datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete          tinyint  default 0                 not null comment '是否删除'
) comment '用户接口关系表';
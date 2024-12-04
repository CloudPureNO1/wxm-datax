```sql
create table wxm_api
(
    api_id      bigint primary key auto_increment comment 'Api 接口 ID',
    api_code    varchar(50)   not null comment '接口唯一编码',
    api_title   varchar(500)  not null comment 'api 标题',
    api_url     varchar(1000) not null comment 'api 地址',
    api_status  varchar(1)    not null comment '1 有效 0 无效',
    creator     varchar(255)  null comment '创建者',
    create_time datetime      null comment '创建时间',
    operator    varchar(255)  null comment '最近一次操作者',
    update_time datetime      null comment '更新时间',
    api_desc    varchar(500)  null comment '接口描述'
) character set utf8 comment '接口资源api';

create table wxm_c_role
(
    role_id     bigint auto_increment comment '主键'
        primary key,
    role_code   varchar(50)  not null comment '角色唯一编码',
    role_name   varchar(100) not null comment '角色名称',
    role_type   varchar(1)   not null comment '角色类型：0 超级管理员角色，1 系统管理员角色，2 系统管理员角色，3 业务员角色，4 普通角色',
    role_status varchar(1)   not null comment '0无效 1有效',
    role_desc   varchar(500) null comment '描述',
    creator     varchar(50)  null comment '创建者',
    create_time datetime     null comment '创建时间',
    operator    varchar(255) null comment '最近一次操作者',
    update_time datetime     null comment '更新时间'
) character set utf8 comment 'oauth_client_details 客户端的角色 关联wxm_api';

create table wxm_role_api
(
    role_api_id bigint primary key auto_increment comment '主键',
    api_id      bigint       not null comment 'wxm_api 主键',
    role_id     bigint       not null comment 'wxm_c_role 表的role_id',
    creator     varchar(50)  null comment '创建者',
    create_time datetime     null comment '创建时间',
    operator    varchar(255) null comment '最近一次操作者',
    update_time datetime     null comment '更新时间'
) character set utf8 comment '角色api 关联表';

create table wxm_client_role
(
    client_role_id bigint primary key auto_increment comment '主键',
    client_id      varchar(255) not null comment 'oauth_client_details，客户端ID',
    role_id        bigint       not null comment 'wxm_c_role 表的role_id',
    creator        varchar(50)  null comment '创建者',
    create_time    datetime     null comment '创建时间',
    operator       varchar(255) null comment '最近一次操作者',
    update_time    datetime     null comment '更新时间'
) character set utf8 comment 'oauth_client_details 关联 wxm_c_role';

create table wxm_user_client
(
    user_client_id bigint primary key auto_increment comment '主键',
    user_id        bigint       not null comment 'wxm_user 主键',
    client_id      varchar(255) not null comment 'oauth_client_details表，客户端ID',
    creator        varchar(50)  null comment '创建者',
    create_time    datetime     null comment '创建时间',
    operator       varchar(255) null comment '最近一次操作者',
    update_time    datetime     null comment '更新时间'
) character set utf8 comment 'wxm_user 关联 oauth_client_details表';

/*create table oauth_client_details
(
    client_id               varchar(255)                        not null comment '主键，客户端ID'
        primary key,
    resource_ids            varchar(255)                        null comment '客户端所能访问的资源id集合，多个资源时用逗号(,)分隔',
    client_secret           varchar(255)                        null comment '客户端访问密匙',
    scope                   varchar(255)                        null comment '客户端申请的权限范围，可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔',
    authorized_grant_types  varchar(255)                        null comment '客户端支持的授权许可类型(grant_type)，可选值包括authorization_code,password,refresh_token,implicit,client_credentials,若支持多个授权许可类型用逗号(,)分隔',
    web_server_redirect_uri varchar(255)                        null comment '客户端重定向URI，当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与数据库内的redirect_uri是否一致',
    authorities             varchar(255)                        null comment '客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔',
    access_token_validity   int                                 null comment '设定客户端的access_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
    refresh_token_validity  int                                 null comment '设定客户端的refresh_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)',
    additional_information  varchar(4096)                       null comment '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
    autoapprove             varchar(255)                        null comment '设置用户是否自动批准授予权限操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’.',
    create_time             timestamp default CURRENT_TIMESTAMP null
)
    comment 'OAuth 2.0 客户端';*/

create table data_x_details
(
    file_id     bigint auto_increment comment '主键'
        primary key,
    file_name   varchar(1000) not null comment '文件名称：去掉文件名后缀.json >>> 1、前面加T 则位 qrtz_job_details.JOB_NAME，前面加T 则为 qrtz_job_details.JOB_GROUP ',
    file_status varchar(1)    not null comment '1 有效 0 无效',
    creator     varchar(255)  null comment '创建者',
    create_time datetime      null comment '创建时间',
    operator    varchar(255)  null comment '最近一次操作者',
    update_time datetime      null comment '更新时间',
    file_desc   varchar(500)  null comment '接口描述',
    file_path   varchar(2000) not null comment '文件所在路径',
    constraint uidx_data_x_details_fileName
        unique (file_name)
)
    comment 'datax 定时任务文件信息';


```
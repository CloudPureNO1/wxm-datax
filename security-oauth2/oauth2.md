```sql
create table oauth_client_details
(
    client_id               VARCHAR(255) PRIMARY KEY comment '主键，客户端ID',
    resource_ids            VARCHAR(255) comment '客户端所能访问的资源id集合，多个资源时用逗号(,)分隔',
    client_secret           VARCHAR(255) comment '客户端访问密匙',
    scope                   VARCHAR(255) comment '客户端申请的权限范围，可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔',
    authorized_grant_types  VARCHAR(255) comment '客户端支持的授权许可类型(grant_type)，可选值包括authorization_code,password,refresh_token,implicit,client_credentials,若支持多个授权许可类型用逗号(,)分隔',
    web_server_redirect_uri VARCHAR(255) comment '客户端重定向URI，当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与数据库内的redirect_uri是否一致',
    authorities             VARCHAR(255) comment '客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔',
    access_token_validity   INTEGER comment '设定客户端的access_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 12, 12小时)',
    refresh_token_validity  INTEGER comment '设定客户端的refresh_token的有效时间值(单位:秒)，若不设定值则使用默认的有效时间值(60 * 60 * 24 * 30, 30天)',
    additional_information  VARCHAR(4096) comment '这是一个预留的字段,在Oauth的流程中没有实际的使用,可选,但若设置值,必须是JSON格式的数据',
    autoapprove             VARCHAR(255) comment '设置用户是否自动批准授予权限操作, 默认值为 ‘false’, 可选值包括 ‘true’,‘false’, ‘read’,‘write’.',
    create_time             TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) character set utf8 comment 'OAuth 2.0 客户端';

create table oauth_client_token
(
    token_id          VARCHAR(255),
    token             BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255)
) character set utf8 comment '';

create table oauth_access_token
(
    token_id          VARCHAR(255),
    token             BLOB,
    authentication_id VARCHAR(255) PRIMARY KEY,
    user_name         VARCHAR(255),
    client_id         VARCHAR(255),
    authentication    BLOB,
    refresh_token     VARCHAR(255)
) character set utf8 comment 'OAuth 2.0 访问令牌';

create table oauth_refresh_token
(
    token_id       VARCHAR(255),
    token          BLOB,
    authentication BLOB
) character set utf8 comment 'OAuth 2.0 刷新令牌';

create table oauth_code
(
    code           VARCHAR(255),
    authentication BLOB
) character set utf8 comment 'OAuth 2.0 授权码';

create table oauth_approvals
(
    userId         VARCHAR(255),
    clientId       VARCHAR(255),
    scope          VARCHAR(255),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) character set utf8 comment '';
```
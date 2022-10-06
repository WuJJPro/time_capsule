create table user
(
    id         varchar(255)                        not null,
    uid        char(10)                            null comment '教职工号',
    user_name  varchar(255)                        null,
    avatar     varchar(255)                        null,
    manage     varchar(255)                        null comment '是否是管理员，1是，0不是',
    created_at timestamp default CURRENT_TIMESTAMP not null,
    constraint user_id_uindex
        unique (id)
)
    charset = utf8;

create index user_uid_index
    on user (uid);

alter table user
    add primary key (id);
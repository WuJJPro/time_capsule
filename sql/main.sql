-- auto-generated definition
create table capsule_pool
(
    id         int auto_increment,
    title      varchar(255)                       null,
    picture    json                               null,
    active     int                                null comment '1是正在进行
0是不在进行',
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint capsule_pool_id_uindex
        unique (id)
);

alter table capsule_pool
    add primary key (id);

-- auto-generated definition
create table love
(
    id         varchar(255)                       not null,
    `key`      varchar(255)                       null,
    uid        varchar(10)                        null,
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint report_id_uindex
        unique (id)
);

alter table love
    add primary key (id);

-- auto-generated definition
create table notice
(
    id         varchar(255)                       not null,
    uid        varchar(10)                        null,
    content    varchar(255)                       null,
    type       varchar(255)                       null,
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint report_id_uindex
        unique (id)
);

alter table notice
    add primary key (id);

-- auto-generated definition
create table private_capsule
(
    id          int auto_increment,
    uid         int(10)                            null,
    title       varchar(255)                       null,
    content     text                               null,
    open_time   date                               null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    record      json                               null,
    inform_time int                                null,
    constraint private_capsule_id_uindex
        unique (id)
);

alter table private_capsule
    add primary key (id);

-- auto-generated definition
create table private_capsule_finished
(
    id          int auto_increment,
    uid         int(10)                            null,
    title       varchar(255)                       null,
    content     text                               null,
    open_time   date                               null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    record      json                               null,
    inform_time int                                null,
    success     int                                null,
    constraint private_capsule_finished_id_uindex
        unique (id)
);

alter table private_capsule_finished
    add primary key (id);

-- auto-generated definition
create table public_capsule
(
    id         int auto_increment,
    uid        int(10)                            null,
    pool_id    int                                null,
    title      varchar(255)                       null,
    content    text                               null,
    picture    json                               null,
    record     json                               null,
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint public_capsule_id_uindex
        unique (id)
);

alter table public_capsule
    add primary key (id);

-- auto-generated definition
create table report
(
    id         varchar(255)                       not null,
    `key`      varchar(255)                       null,
    uid        varchar(10)                        null,
    created_at datetime default CURRENT_TIMESTAMP null,
    constraint report_id_uindex
        unique (id)
);

alter table report
    add primary key (id);

-- auto-generated definition
create table user
(
    id         varchar(255)                        not null,
    uid        char(10)                            null comment '教职工号',
    user_name  varchar(255)                        null,
    avatar     varchar(255)                        null,
    manage     varchar(255)                        null comment '是否是管理员，1是，0不是',
    created_at timestamp default CURRENT_TIMESTAMP not null,
    email      varchar(255)                        null,
    password   varchar(255)                        null,
    salt       varchar(255)                        null,
    constraint user_id_uindex
        unique (id)
)
    charset = utf8;

create index user_uid_index
    on user (uid);

alter table user
    add primary key (id);


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


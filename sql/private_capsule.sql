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
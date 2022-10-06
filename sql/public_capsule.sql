create table public_capsule
(
    id          int auto_increment,
    uid         int(10)                            null,
    pool_id     int                                null,
    title       varchar(255)                       null,
    content     text                               null,
    picture     json                               null,
    record      json                               null,
    created_at  datetime default CURRENT_TIMESTAMP null,
    inform_time int                                null,
    constraint public_capsule_id_uindex
        unique (id)
);

alter table public_capsule
    add primary key (id);

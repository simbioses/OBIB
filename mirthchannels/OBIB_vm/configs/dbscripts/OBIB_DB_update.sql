use OBIB_DB;

drop table if exists cdx_id;

create table if not exists cdx_id
(
    id        int auto_increment
        primary key,
    code      varchar(36) not null,
    name      varchar(50) null,
    category  varchar(36) null,
    type      varchar(36) null
);

drop table if exists cdx_loinc_code;

create table if not exists cdx_loinc_code
(
    id            int auto_increment
        primary key,
    loinc_code    varchar(10) not null,
    loinc_name    varchar(36) null,
    template_id   varchar(36) not null,
    template_name varchar(50) null
);


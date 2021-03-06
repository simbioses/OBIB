use OBIB_DB;

/* error_message - table */
create table if not exists error_message
(
    id                int auto_increment primary key,
    date              datetime null,
    clinic_id         varchar(36) null,
    message_id        varchar(36) null,
    context           varchar(255) null,
    trace             mediumtext null,
    obib_version      varchar(36) null,
    connector_version varchar(36) null
);

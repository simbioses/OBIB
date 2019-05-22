/* OBIB_DB - database/schema */
-- drop schema if exists OBIB_DB;
create schema OBIB_DB collate utf8_general_ci;

use OBIB_DB;

/* cdx_id - table */ 
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

/* cdx_loinc_code - table */ 
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

/* clinic_credential - table */ 
drop table if exists clinic_credential;
create table if not exists clinic_credential
(
    clinic_id            varchar(36)  not null
        primary key,
    clinic_name          varchar(200) null,
    clinic_username      varchar(100) not null,
    clinic_password      varchar(100) not null,
    certificate_file     varchar(100) null,
    certificate_password varchar(100) null
);

/* document - table */ 
drop table if exists document;
create table if not exists document
(
    document_id      varchar(36) not null,
    document_date    datetime    not null,
    document_content mediumtext  not null,
    location_id      varchar(36) not null,
    destination_id   varchar(36) not null,
    constraint document_document_id_uindex
        unique (document_id),
    primary key (document_id)
);

/* document_attachment - table */ 
drop table if exists document_attachment;
create table if not exists document_attachment
(
    document_id    varchar(36) not null,
    media_type     varchar(50) not null,
    reference_name varchar(50) not null,
    content        mediumtext  not null,
    hash_value     varchar(64) not null,
    primary key (document_id, media_type, reference_name),
    constraint document_attachment_document_document_id_fk
        foreign key (document_id) references document (document_id)
);

create index if not exists document_attachment_document_id_media_type_reference_name_index
    on document_attachment (document_id, media_type, reference_name);

/* document_response - table */ 
drop table if exists document_response;
create table if not exists document_response
(
    response_id      varchar(36) not null,
    response_date    datetime    not null,
    response_status  varchar(3)  null,
    response_content text        null,
    document_id      varchar(36) not null,
    constraint document_response_response_id_uindex
        unique (response_id),
    constraint document_response_document_document_id_fk
        foreign key (document_id) references document (document_id),
    primary key (response_id)
);

/* error_message - table */
drop table if exists error_message;
create table if not exists error_message
(
	id int auto_increment
		primary key,
	date datetime null,
	clinic_id varchar(36) null,
	message_id varchar(36) null,
	context varchar(255) null,
	trace mediumtext null
);


/* OBIB_DB - database/schema */
create schema OBIB_DB collate utf8_general_ci;

use OBIB_DB;

/* cdx_id - table */ 
create table cdx_id
(
    id        int auto_increment primary key,
    code      varchar(36) not null,
    name      varchar(50) null,
    category  varchar(36) null,
    type      varchar(36) null,
    constraint cdx_id_code_unique unique (code)
);

/* cdx_loinc_code - table */ 
create table cdx_loinc_code
(
    id            int auto_increment primary key,
    loinc_code    varchar(10) not null,
    loinc_name    varchar(36) null,
    template_id   varchar(36) not null,
    template_name varchar(50) null
);

/* clinic_credential - table */ 
create table clinic_credential
(
    clinic_id            varchar(36)  not null primary key,
    clinic_name          varchar(200) null,
    clinic_username      varchar(100) not null,
    clinic_password      varchar(100) not null,
    certificate_file     varchar(100) null,
    certificate_password varchar(100) null,
    location_id          varchar(36)  null,
    constraint clinic_credential_location_id_unique unique (location_id)
);

/* document - table */ 
create table document
(
    id                int auto_increment primary key,
    document_id       varchar(36) not null,
    document_date     datetime    not null,
    document_content  mediumtext  null,
    location_id       varchar(36) not null,
    obib_version      varchar(36) null,
    connector_version varchar(36) null
);

/* document_attachment - table */ 
create table document_attachment
(
    id                int auto_increment primary key,
    document_id       varchar(36) not null,
    media_type        varchar(50) not null,
    reference_name    varchar(50) not null,
    content           mediumtext  null,
    hash_value        varchar(64) not null,
    obib_version      varchar(36) null,
    connector_version varchar(36) null
);

/* document_response - table */
create table document_response
(
    id                int auto_increment primary key,
    response_id       varchar(36) not null,
    response_date     datetime    not null,
    response_status   varchar(3)  null,
    response_content  text        null,
    document_id       varchar(36) not null,
    obib_version      varchar(36) null,
    connector_version varchar(36) null
);

/* error_message - table */
create table error_message
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


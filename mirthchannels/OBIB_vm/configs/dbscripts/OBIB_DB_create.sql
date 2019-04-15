create schema OBIB_DB collate utf8_general_ci;

use OBIB_DB;

create table cdx_id
(
	id int auto_increment
		primary key,
	root varchar(36) null,
	extension varchar(36) null,
	name varchar(36) null
);

create table clinic_credential
(
	clinic_id varchar(36) not null
		primary key,
	clinic_name varchar(200) null,
	clinic_username varchar(100) not null,
	clinic_password varchar(100) not null,
	certificate_file varchar(100) null,
	certificate_password varchar(100) null
);

create table document
(
	document_id varchar(36) not null,
	document_date datetime not null,
	document_content mediumtext not null,
	location_id varchar(36) not null,
	destination_id varchar(36) not null,
	constraint document_document_id_uindex
		unique (document_id)
);

alter table document
	add primary key (document_id);

create table document_response
(
	response_id varchar(36) not null,
	response_date datetime not null,
	response_status varchar(3) null,
	response_content text null,
	document_id varchar(36) not null,
	constraint document_response_response_id_uindex
		unique (response_id),
	constraint document_response_document_document_id_fk
		foreign key (document_id) references document (document_id)
);

alter table document_response
	add primary key (response_id);



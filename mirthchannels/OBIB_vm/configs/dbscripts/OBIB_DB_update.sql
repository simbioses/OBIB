use OBIB_DB;

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

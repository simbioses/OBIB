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

/* add unique constraint for cdx_id.code */
set @check_constraint = (SELECT count(CONSTRAINT_NAME) 
						 FROM information_schema.TABLE_CONSTRAINTS 
						 WHERE TABLE_NAME = 'cdx_id'  and CONSTRAINT_NAME = 'cdx_id_code_unique'); 

DELIMITER |
IF (0 = @check_constraint) THEN
	BEGIN NOT ATOMIC
    	ALTER TABLE cdx_id ADD CONSTRAINT cdx_id_code_unique UNIQUE (code);
    END;
END IF|
DELIMITER ;

/* add clinic_credential.location_id column */
ALTER TABLE clinic_credential ADD COLUMN IF NOT EXISTS location_id varchar(36) null;

/* add unique constraint for clinic_credential.location_id */
set @check_constraint = (SELECT count(CONSTRAINT_NAME) 
						 FROM information_schema.TABLE_CONSTRAINTS 
						 WHERE TABLE_NAME = 'clinic_credential'  and CONSTRAINT_NAME = 'clinic_credential_location_id_unique'); 

DELIMITER |
IF (0 = @check_constraint) THEN
	BEGIN NOT ATOMIC
    	ALTER TABLE clinic_credential ADD CONSTRAINT clinic_credential_location_id_unique UNIQUE (location_id);
    END;
END IF|
DELIMITER ;

/* insert OSCAR Ids */
INSERT IGNORE INTO cdx_id (code, name, category, type)
VALUES ('2.16.840.1.113883.3.277.126', 'OSCAR EMR Root OID', 'Default', 'Software'),
       ('2.16.840.1.113883.3.277.126.1', 'OSCAR EMR Document ID', 'OSCAR', 'Document'),
       ('2.16.840.1.113883.3.277.126.2', 'OSCAR EMR Patient ID', 'OSCAR', 'Patient'),
       ('2.16.840.1.113883.3.277.126.3', 'OSCAR EMR Personnel ID', 'OSCAR', 'Personnel'),
       ('2.16.840.1.113883.3.277.126.4', 'OSCAR EMR Location ID', 'OSCAR', 'Location'),
       ('2.16.840.1.113883.3.277.126.5', 'OSCAR EMR Organization ID', 'OSCAR', 'Organization');

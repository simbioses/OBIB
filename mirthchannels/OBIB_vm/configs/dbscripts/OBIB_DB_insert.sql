INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (1, null, '2.16.840.1.113883.3.1818.10.1.5', 'e2e Referral (Unstructured)');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (2, null, '2.16.840.1.113883.3.277.100.3', 'CDX Clinical Document ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (3, null, '2.16.840.1.113883.3.277.100.7', 'IHA Report ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (4, null, '2.16.840.1.113883.3.277.1.81', 'Document ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (5, null, '2.16.840.1.113883.3.277.100.2', 'CDX Clinic Registry ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (6, null, '2.16.840.1.113883.3.277.100.2.1', 'POI Clinic ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (7, null, '2.16.840.1.113883.3.277.1.62', 'IHA Meditech Location Identifier');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (8, null, '2.16.840.1.113883.3.40.2.11', 'BC Ministry Practitioner ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (9, null, '2.16.840.1.113883.3.277.1.61', 'IHA Meditech ID');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (10, null, '2.16.840.1.113883.4.50', 'BC Patient Health Number');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (11, null, '2.16.840.1.113883.3.277.1.71', 'IHA Patient Unit Number');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (12, null, '2.16.840.1.113883.3.277.1.72', 'IHA Patient Account Number');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (13, null, '2.16.840.1.113883.3.277.1.73', 'IHA Patient EMR Number');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (14, null, '2.16.840.1.113883.3.277.1.12', 'IHA Software Code');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (15, null, '2.16.840.1.113883.3.1818.10.1.4', 'Unstructured Document');
INSERT INTO OBIB_DB.cdx_id (id, root, extension, name) VALUES (16, null, '2.16.840.1.113883.3.1818.10.1.2', 'Generic Episodic Document');


INSERT INTO OBIB_DB.clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password) 
  VALUES ('cdxpostprod-obctc', 'Oscar Test Clinic C', 'cdxpostprod-obctc', '2LsTebRe', 'OscarTestClinic.pfx', 'LEADlab');
INSERT INTO OBIB_DB.clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password) 
  VALUES ('cdxpostprod-otca', 'Oscar Test Clinic A', 'cdxpostprod-otca', 'VMK31', 'LEADlab_IHA_cert.pfx', 'LEADlab');

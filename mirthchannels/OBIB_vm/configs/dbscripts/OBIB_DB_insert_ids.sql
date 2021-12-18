use OBIB_DB;

/* CDX Id - Providers */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.40.2.11', 'BC Ministry Practitioner ID', 'Default', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.40.2.20', 'Registered Nurse Practitioner ID', 'Default', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.61', 'IHA Meditech ID', 'IHA', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.523.1.76', 'NHA Cerner ID', 'NHA', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.523.1.77', 'NHA Crescendo ID', 'NHA', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.100.30.1', 'Provider Group ID', 'NHA', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.100.61', 'CDX Recipient ID', 'CDX', 'Provider');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.851.1.61', 'VIHA Cerner Provider ID', 'VIHA', 'Provider');

/* CDX Id - Patients */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.4.50', 'BC Patient Health Number', 'Default', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.4.20', 'Alberta Universal Lifetime Identifier', 'Default', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.74', 'IHA Meditech Internal Patient ID', 'IHA', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.72', 'IHA Meditech Patient Account Number', 'IHA', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.73', 'IHA Patient EMR Number', 'IHA', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.71', 'IHA Patient Unit Number', 'IHA', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.523.1.71', 'NHA MRN', 'NHA', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.851.1.71', 'VIHA Patient ID', 'VIHA', 'Patient');

/* CDX Id - Document Templates */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.7', 'Admit Notification', 'Facility Admissions', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.1', 'Lab Results Report', 'Lab Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.2', 'Anatomic Pathology Report', 'Lab Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.3', 'Procedure Note', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.4', 'Discharge Summary', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.5', 'Diagnostic Imaging Report', 'Medical Imaging', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.51.60.2.6', 'Discharge Notification', 'Facility Admissions', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document', 'Bidirectional Exchange', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.1818.10.1.5', 'e2e Referral (Unstructured)', 'Bidirectional Exchange', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.1818.10.1.2', 'e2e Generic Episodic Document', 'Bidirectional Exchange', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.10.20.2', 'History and Physical Note', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.10.20.4', 'Consultation Note', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.10.20.7', 'Operative Note', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.10.20.21', 'Progress Note', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.10.20.19', 'Unstructured Report', 'Clinical Report', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.1818.10.1.6', 'e2e Referral (Structured)', 'Bidirectional Exchange', 'Template');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.1818.10.1.3', 'e2e Patient Chart Transfer', 'Bidirectional Exchange', 'Template');

/* CDX Id - Facilities */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.100.2', 'CDX Clinic Registry ID', 'Default', 'Facility');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.62', 'IHA Meditech Location Identifier', 'IHA', 'Facility');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.523.1.64', 'Northern Health AuthorityFacility', 'NHA', 'Facility');

/* CDX Id - Other */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.100.3', 'CDX Clinical Document ID', 'Default', 'Document');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.81', 'IHA Message Number', 'Default', 'Message');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.1.12', 'IHA Software Code', 'Default', 'Software');

/* OSCAR Ids */
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126', 'OSCAR EMR Root OID', 'Default', 'Software');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126.1', 'OSCAR EMR Document ID', 'OSCAR', 'Document');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126.2', 'OSCAR EMR Patient ID', 'OSCAR', 'Patient');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126.3', 'OSCAR EMR Personnel ID', 'OSCAR', 'Personnel');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126.4', 'OSCAR EMR Location ID', 'OSCAR', 'Location');
INSERT IGNORE INTO cdx_id (code, name, category, type) VALUES ('2.16.840.1.113883.3.277.126.5', 'OSCAR EMR Organization ID', 'OSCAR', 'Organization');

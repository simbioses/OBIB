use OBIB_DB;

/* Delete previous values*/
DELETE FROM cdx_loinc_code;

/* LOINC Codes mapped to templates */
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (1, '57133-1', 'eReferral Note', '2.16.840.1.113883.3.1818.10.1.5', 'e2e Referral (Unstructured)');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (2, '11506-3', 'Progress Note', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (3, '11488-4', 'Consult Note', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (4, '18842-5', 'Discharge Summary', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (5, '81200-8', 'Interdisciplinary Plan of Care', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (6, 'X10916', 'Information Request',  '2.16.840.1.113883.3.1818.10.1.5', 'e2e Referral (Unstructured)');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (7, '34109-9', 'Note', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (8, 'X10915', 'General-Purpose Notification', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (9, '60591-5', 'Patient Summary', '2.16.840.1.113883.3.1818.10.1.4', 'e2e Unstructured Document');
INSERT INTO cdx_loinc_code (id, loinc_code, loinc_name, template_id, template_name) VALUES (10, 'X10914', 'Advice Request', '2.16.840.1.113883.3.1818.10.1.5', 'e2e Referral (Unstructured)');


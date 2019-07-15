use OBIB_DB;

INSERT INTO clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password) 
  VALUES ('cdxpostprod-obctc', 'Oscar Test Clinic C', 'cdxpostprod-obctc', '2LsTebRe', 'OscarTestClinic.pfx', 'LEADlab');
INSERT INTO clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password) 
  VALUES ('cdxpostprod-otca', 'Oscar Test Clinic A', 'cdxpostprod-otca', 'VMK31', 'LEADlab_IHA_cert.pfx', 'LEADlab');
INSERT INTO clinic_credential (clinic_id, clinic_name, clinic_username, clinic_password, certificate_file, certificate_password)
  VALUES ('cdxpostprod-ctc', 'Conformance Test Clinic', 'cdxpostprod-ctc', 'conformance123', 'Conformance_Test.pfx', 'LEADlab!');

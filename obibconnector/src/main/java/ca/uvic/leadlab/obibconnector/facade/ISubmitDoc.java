package ca.uvic.leadlab.obibconnector.facade;

enum phoneType {home, work, mobile}
enum addressType {home, work}
enum emailType {home, work}
enum attachmentType {PDF, image}

public interface ISubmitDoc {

    public ISubmitDoc myClinicID(String id);

    public ISubmitDoc patientName(String firstname, String lastname, String prefix, String suffix);
    public ISubmitDoc patientPhone(phoneType type, String number);
    public ISubmitDoc patientEmail(emailType type, String number);
    public ISubmitDoc patientAddress(addressType type, String street, String city, String province, String postal, String Country);

    public ISubmitDoc authorName(String firstname, String lastname, String prefix, String suffix);
    public ISubmitDoc authorID(String id);
    public ISubmitDoc authorPhone(phoneType type, String number);
    public ISubmitDoc authorEmail(emailType type, String number);
    public ISubmitDoc authorAddress(addressType type, String street, String city, String province, String postal, String Country);

    public ISubmitDoc authoredTime(String time);

    public ISubmitDoc recipientName(String firstname, String lastname, String prefix, String suffix);
    public ISubmitDoc recipientID(String id);
    public ISubmitDoc recipientPhone(phoneType type, String number);
    public ISubmitDoc recipientEmail(emailType type, String number);
    public ISubmitDoc recipientAddress(addressType type, String street, String city, String province, String postal, String Country);

    public ISubmitDoc recipientOrg(String id, String name);

    public ISubmitDoc content(String text);

    public ISubmitDoc attach(attachmentType type, Byte[] data);

    public String submit();

}

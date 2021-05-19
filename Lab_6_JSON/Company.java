import java.io.FileWriter;
import java.io.IOException;

public class Company {
    private String name;
    private String shortTitle;
    private String dateUpdate;
    private String address;
    private String dateFoundation;
    private String countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String link;

    public Company(String name, String shortTitle, String dateUpdate, String address, String dateFoundation,
                String countEmployees, String auditor, String phone, String email, String branch, String activity, String link) {
           this.name = name;
           this.shortTitle = shortTitle;
           this.dateUpdate = dateUpdate;
           this.address = address;
           this.dateFoundation = dateFoundation;
           this.countEmployees = countEmployees;
           this.auditor = auditor;
           this.phone = phone;
           this.email = email;
           this.branch = branch;
           this.activity = activity;
           this.link = link;
    }

    public Company() {
        this("", "", "", "", "", "0", "", "", "", "", "", "");
    }

    public String getLink() {
        return link;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAuditor() {
        return auditor;
    }

    public String getAddress() {
        return address;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public String getName() {
        return name;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getBranch() {
        return  branch;
    }

    public String getActivity() {
        return  activity;
    }

    public String getDateFoundation() {
        return  dateFoundation;
    }

    public String getCountEmployees() {
        return countEmployees;
    }

    public void addToXML(FileWriter fw) throws IOException {
        fw.write("<name>" + name + "</name>\n");
        fw.write("<shortTitle>" + shortTitle + "</shortTitle>\n");
        fw.write("<dateUpdate>" + dateUpdate + "</dateUpdate>\n");
        fw.write("<address>" + address + "</address>\n");
        fw.write("<dateFoundation>" + dateFoundation + "</dateFoundation>\n");
        fw.write("<countEmployees>" + countEmployees + "</countEmployees>\n");
        fw.write("<auditor>" + auditor + "</auditor>\n");
        fw.write("<phone>" + phone + "</phone>\n");
        fw.write("<email>" + email + "</email>\n");
        fw.write("<branch>" + branch + "</branch>\n");
        fw.write("<activity>" + activity + "</activity>\n");
        fw.write("<link>" + link + "</link>\n");
    }

    @Override
    public  String toString() {
        return new StringBuilder(name).append(";").append(shortTitle).append(";").append(dateUpdate)
                .append(";").append(address).append(";").append(dateFoundation).append(";").append(countEmployees)
                .append(";").append(auditor).append(";").append(phone).append(";").append(email).append(";")
                .append(branch).append(";").append(activity).append(";").append(link).append(";").toString();
    }
}

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Companies {
    private List<Company> list;
    private int matchAmount;
    private String inquiry;
    private Date date;

    public Companies() {
        list = new ArrayList<>();
        matchAmount = 0;
        inquiry = "";
        date = new Date();
    }

    public void readFile(String path) throws IOException {
        File file = new File(path);
        try(Scanner sc = new Scanner(file)) {
            while(sc.hasNextLine()) {
                try (Scanner scanner = new Scanner(sc.nextLine()).useDelimiter("[;]")) {
                    list.add(new Company(scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(),
                            scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(), scanner.next(),
                            scanner.next()));
                }
            }
        }
    }

    public int option() throws NumberFormatException {
        System.out.println("Choose an option: ");
        System.out.println("1. Find company by shortName");
        System.out.println("2. Find companies by branch");
        System.out.println("3. Find companies by activity");
        System.out.println("4. Find companies by dateFoundation");
        System.out.println("5. Find companies by countEmployees");
        int option = 0;
        Scanner sc = new Scanner(System.in);
        inquiry = sc.next();
        option = Integer.parseInt(inquiry);
        return option;
    }

    public Company findByShortName() {
        Company company = new Company();
        String sName;
        System.out.println("Enter short name: ");
        Scanner sc = new Scanner(System.in);
        sName = sc.next();
        StringBuilder sb = new StringBuilder(inquiry);
        sb.append(" " + sName);
        inquiry = sb.toString();
        for (Company item : list) {
            if(item.getShortTitle().toLowerCase().equals(sName.toLowerCase())) {
                company = item;
                matchAmount++;
                break;
            }
        }
        return company;
    }

    public List<Company> findByBranch() {
        List<Company> byBranch;
        System.out.println("Enter branch: ");
        Scanner sc = new Scanner(System.in);
        String branch = sc.next();
        StringBuilder sb = new StringBuilder(inquiry);
        sb.append(" " + branch);
        inquiry = sb.toString();
        byBranch = list.stream().filter(item->item.getBranch().toLowerCase().equals(branch.toLowerCase())).collect(Collectors.toList());
        matchAmount = byBranch.size();
        return byBranch;
    }

    public List<Company> findByActivity() {
        List<Company> byActivity;
        System.out.println("Enter activity: ");
        Scanner sc = new Scanner(System.in);
        String activity = sc.next();
        StringBuilder sb = new StringBuilder(inquiry);
        sb.append(" " + activity);
        inquiry = sb.toString();
        byActivity = list.stream().filter(item->item.getActivity().toLowerCase().equals(activity.toLowerCase())).collect(Collectors.toList());
        matchAmount = byActivity.size();
        return byActivity;
    }

    public List<Company> findByDate() {
        List<Company> byDateFoundation;
        System.out.println("Enter period: ");
        Scanner sc = new Scanner(System.in);
        String dateFoundation = sc.next();
        StringBuilder sb = new StringBuilder(inquiry);
        sb.append(" " + dateFoundation);
        inquiry = sb.toString();
        byDateFoundation = list.stream().filter(item->item.getDateFoundation().toLowerCase().equals(dateFoundation.toLowerCase())).collect(Collectors.toList());
        matchAmount = byDateFoundation.size();
        return byDateFoundation;
    }

    public List<Company> findByEmployees() {
        List<Company> byEmployees;
        System.out.println("Enter employees amount range: ");
        Scanner sc = new Scanner(System.in);
        int countEmployees = sc.nextInt();
        StringBuilder sb = new StringBuilder(inquiry);
        sb.append(" " + countEmployees);
        inquiry = sb.toString();
        byEmployees = list.stream().filter(item->item.getCountEmployees().equals(countEmployees)).collect(Collectors.toList());
        matchAmount = byEmployees.size();
        return  byEmployees;
    }

    public void outputByShortName(Company company) {
        System.out.println(company.toString());
    }

    public void  outputCompanies(List<Company> searchCompanies) {
        searchCompanies.stream().forEach(item->System.out.print(item + "\n"));
    }

    public void outputXML(List<Company> searchCompanies) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        int count = 0;
        for(Company item : searchCompanies) {
            addToXML(item, count, document);
            count++;
        }

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(new FileOutputStream("output.xml")));
    }

    private void addToXML(Company item, int count, Document document) throws TransformerException, FileNotFoundException {
        Element output = document.createElement("output");
        Element company = document.createElement("company");;
        output.appendChild(company);

       /* if(count == 0) {
            document.appendChild(output);
            company = document.createElement("company");
            output.appendChild(company);
        }
        else {
            company = document.createElement("company");
            output.appendChild(company);
        }*/



        Element name = document.createElement("name");
        Element shortName = document.createElement("shortName");
        Element dateUpdate = document.createElement("dateUpdate");
        Element address = document.createElement("address");
        Element dateFoundation = document.createElement("dateFoundation");
        Element countEmployees = document.createElement("countEmployees");
        Element auditor = document.createElement("auditor");
        Element phone = document.createElement("phone");
        Element email = document.createElement("email");
        Element branch = document.createElement("branch");
        Element activity = document.createElement("activity");
        Element link = document.createElement("link");

        Text text = document.createTextNode(item.getName());
        company.appendChild(name);
        name.appendChild(text);
        text = document.createTextNode(item.getShortTitle());
        company.appendChild(shortName);
        shortName.appendChild(text);
        text = document.createTextNode(item.getDateUpdate());
        company.appendChild(dateUpdate);
        dateUpdate.appendChild(text);
        text = document.createTextNode(item.getAddress());
        company.appendChild(address);
        address.appendChild(text);
        text = document.createTextNode(item.getDateFoundation());
        company.appendChild(dateFoundation);
        dateFoundation.appendChild(text);
        text = document.createTextNode(item.getCountEmployees());
        company.appendChild(countEmployees);
        countEmployees.appendChild(text);
        text = document.createTextNode(item.getAuditor());
        company.appendChild(auditor);
        auditor.appendChild(text);
        text = document.createTextNode(item.getPhone());
        company.appendChild(phone);
        phone.appendChild(text);
        text = document.createTextNode(item.getEmail());
        company.appendChild(email);
        email.appendChild(text);
        text = document.createTextNode(item.getBranch());
        company.appendChild(branch);
        branch.appendChild(text);
        text = document.createTextNode(item.getActivity());
        company.appendChild(activity);
        activity.appendChild(text);
        text = document.createTextNode(item.getLink());
        company.appendChild(link);
        link.appendChild(text);
    }

    public void writeXML(List<Company> searchCompanies) throws IOException{
        int count = searchCompanies.size();
        try(FileWriter fw = new FileWriter("output1.xml")) {
            fw.write("<output>\n");
            for(int i = 0; i < count; i++) {
                fw.write("<company" + i + ">\n");
                searchCompanies.get(i).addToXML(fw);
                fw.write("</company" + i + ">\n");
            }
            fw.write("</output>\n");
        }
    }

    public void writeJSON() {
        JSONObject jsOut = new JSONObject();
    }

    public void workStatistics() throws IOException {
        try(FileWriter fw = new FileWriter("logfile.txt", true)) {
            fw.write(date.toString() + ", inquiry: " + inquiry + ", amount: " + matchAmount + " \n");
            //fw.append(date.toString());
        }
    }

    public void show() {
        list.stream().forEach(System.out::println);
    }

    public void showShortTitle() {
        System.out.println();
        list.stream().forEach(item-> System.out.println(item.getShortTitle()));
    }
}

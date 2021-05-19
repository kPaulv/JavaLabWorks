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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Companies companies = new Companies();
            int option;
            Company company;
            companies.readFile("input.csv");
            List<Company> searchCompanies = new ArrayList<>();
            option = companies.option();
            switch (option) {
                case  1:
                    company = companies.findByShortName();
                    searchCompanies.add(company);
                    if(company.getShortTitle() != "") {
                        companies.outputByShortName(company);
                    }
                    else {
                        System.out.println("Company not found!");
                    }
                    break;
                case 2:
                    searchCompanies = companies.findByBranch();
                    if(!searchCompanies.isEmpty()) {
                        companies.outputCompanies(searchCompanies);
                    }
                    else {
                        System.out.println("Companies not found!");
                    }
                    break;
                case 3:
                    searchCompanies = companies.findByActivity();
                    if(!searchCompanies.isEmpty()) {
                        companies.outputCompanies(searchCompanies);
                    }
                    else {
                        System.out.println("Companies not found!");
                    }
                    break;
                case 4:
                    searchCompanies = companies.findByDate();
                    if(!searchCompanies.isEmpty()) {
                        companies.outputCompanies(searchCompanies);
                    }
                    else {
                        System.out.println("Companies not found!");
                    }
                    break;
                case 5:
                    searchCompanies = companies.findByEmployees();
                    if(!searchCompanies.isEmpty()) {
                        companies.outputCompanies(searchCompanies);
                    }
                    else {
                        System.out.println("Companies not found!");
                    }
                    break;
                default:
                    System.out.println("зелибоба");
            }

            if(!searchCompanies.isEmpty()) {
                //companies.outputXML(searchCompanies);
                companies.writeXML(searchCompanies);
            }
            else {
                System.out.println("No output file produced.");
            }
            companies.workStatistics();
        }
        catch (NumberFormatException e ) {
            System.out.println("Wrong input value!");
        }
        catch (IOException e) {
            System.out.println("File error!");
        }
        /*catch (TransformerException e) {
            System.out.println("Transformer error!");
        }
        catch (ParserConfigurationException e) {
            System.out.println("Parser config error!");
        }*/
    }
}

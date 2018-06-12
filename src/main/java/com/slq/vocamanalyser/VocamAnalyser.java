/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;

/**
 *
 * @author SLQ
 */
//import com.slq.vocamanalyser.Components.Component_Line;
import com.slq.vocamanalyser.Components.*;
import com.slq.vocamanalyser.Reports.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VocamAnalyser{

    /**
     */
    //public static String pad = "C:/Local_data/Vocam_Bravenes/bravenes_jb/project/dialogs/";
    public static String pad = "C:/Local_data/Vocam_Bravenes/bravenes_jb/project/screens/";
    public static String filenaam = "aaa_testscreenslq_aaa_testscreen2_slq.xml";
    public static String pdfDestDir = "C:\\Local_data\\VocamAnalyzer\\";
    public static final String LOGO = "src/main/resources/images/LogoMarineIngenuity.gif";

    public static AnalyseDialog             analyseDialog = new AnalyseDialog();
    public static AnalyseScreen             analyseScreen = new AnalyseScreen();
    public static ReportMessages            reportMessages;
    public static ReportTags                reportTags; 
    public static ReportLinks               reportLink; 
    public com.itextpdf.layout.Document     pdfDoc;
           
    public static Document                  doc;
    public String                           componentType;   
    public File                             fXmlFile; 
    public PdfWriter                        writer1 = null;
 
    
    public static void main(String[] args){ 
        
       
        //create instance of object
        VocamAnalyser Vocal = new VocamAnalyser();
        //call instance method using object
        Vocal.go();   
        
        }
        
public void go(){

    //Open a file selectie winddow
    JFileChooser jfc = new JFileChooser(pad);
    jfc.setDialogTitle("Select a Vocam screen");
    jfc.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Vocam screens", "xml");
    jfc.addChoosableFileFilter(filter);
    jfc.setMultiSelectionEnabled(true);

    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File[] files = jfc.getSelectedFiles();

        //Voor als we in de toekomst hele directories willen gaan selecteren
        //System.out.println("Directories found\n");
        //Arrays.asList(files).forEach(x -> { if (x.isDirectory()){System.out.println(x.getName());}});

        Arrays.asList(files).forEach(x -> {
            if (x.isFile()) {
                filenaam = x.getName();
                pad = jfc.getCurrentDirectory().toString();
                System.out.println("Processing file: " + pad + "\\" + filenaam);

                //================================================================================================
                // Loop for 1 page                             
                //================================================================================================
                //Kijk of de PDF outputfile gemaakt kan worden.

                try {
                    Paragraph p;
                    Paragraph pKop;
                    Cell rapportBlok;

                    //bepaal de filenaam van de pfd-output file.
                    writer1 = new PdfWriter(pdfDestDir + filenaam.replaceAll(".xml", ".pdf"));

                    //Initialize PDF document
                    PdfDocument pdf1 = new PdfDocument(writer1);
                    pdfDoc = new com.itextpdf.layout.Document(pdf1);

                } catch (FileNotFoundException ex) {

                    //De PDF outputfile kan niet gemaakt kan worden.
                    //custom title, error icon
                    JOptionPane.showMessageDialog(new JFrame(), "The destination file could not be opened.\n"+
                                                                "Maybe file is already open\n"+
                                                                "Destination dir  : "+pdfDestDir+"\n"+
                                                                "Destination file : "+filenaam.replaceAll(".xml", ".pdf"),
                                                                "Fatal error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);

                } finally {
                }

                // Als de PDF outputfile gemaakt kan worden dan worden eerst de
                // variabelen hiervoor gedefinieerd.
                reportTags = new ReportTags(pdfDoc);
                reportLink = new ReportLinks(pdfDoc); 
                reportMessages = new ReportMessages(pdfDoc);
                fXmlFile = new File(jfc.getCurrentDirectory()+"\\"+x.getName()); 

                //Probeer of de input file goed ingelezen kan worden.
                try {
                    Element n;

                    NodeList verzamelingElementen  ;

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

                    doc = dBuilder.parse(fXmlFile);
                    doc.getDocumentElement().normalize();

                    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

                    verzamelingElementen = doc.getChildNodes();

                    // When a screen is found do ...
                    if(doc.getDocumentElement().getNodeName().equals("Screen")){

                        //Maak een nieuwe instantie aan en roep deze aan met 
                        //index 0 omdat er toch maar is node is.
                        analyseScreen = new AnalyseScreen(pdfDoc);
                        analyseScreen.update_ScreenComponents(pdfDoc,verzamelingElementen.item(0));
                    }

                    // When a dialog is found do ...
                    if(doc.getDocumentElement().getNodeName().equals("Dialog")){

                        //Maak een nieuwe instantie aan en roep deze aan met 
                        //index 0 omdat er toch maar is node is.
                        analyseDialog = new AnalyseDialog(pdfDoc);
                        analyseDialog.update_DialogComponents(pdfDoc,verzamelingElementen.item(0));
                    }

                    pdfDoc.close();

                } catch (IOException | InterruptedException | ParserConfigurationException | SAXException e) {

                }

                //================================================================================================                             
                //================================================================================================
            }
            }
        );
    }
}
}
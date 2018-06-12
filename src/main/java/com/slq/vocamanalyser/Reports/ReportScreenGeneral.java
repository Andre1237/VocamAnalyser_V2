/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TabAlignment;
import java.net.MalformedURLException;

/**
 *
 * @author SLQ
 */
public class ReportScreenGeneral {
    
    //================================================================ constants


    //=================================================================== fields
    private static String screenHeaderTitle;
    private static String screenDescription; 
    private static String screenFileName;
    private static String screenFilePath;
    private static String screenEnable; 
    private static String screenSubstitutionTable; 
    private static String screentemplate; 
    private static String screenVisible; 
    private static String shortcut1; 
    private static String shortcut2; 
    private static String shortcut3; 
    
               
 
    //============================================================== constructor
public ReportScreenGeneral(Document pdfDoc) {    
    screenHeaderTitle="";
    screenDescription=""; 
    screenFileName="";
    screenFilePath="";
    screenEnable=""; 
    screenSubstitutionTable=""; 
    screentemplate=""; 
    screenVisible="";
    shortcut1="";
    shortcut2="";
    shortcut3="";
}    


    /** Methodes for recieving screen data
     * 
     * Collection of set commands
     */
    public void setScreenHeaderTitle(String screenHeaderTitle){ReportScreenGeneral.screenHeaderTitle = screenHeaderTitle;}
    public void setFileName(String screenFileName){ReportScreenGeneral.screenFileName = screenFileName;}
    public void setFilePath(String screenFilePath){ReportScreenGeneral.screenFilePath = screenFilePath;}
    public void setScreentemplate(String screentemplate){ReportScreenGeneral.screentemplate = screentemplate;}
    public void setdScreenVisible(String screenVisible){ReportScreenGeneral.screenVisible = screenVisible;}
    public void setScreenEnable(String screenEnable){ReportScreenGeneral.screenEnable = screenEnable;}
    public void setscreenSubstitutionTable(String screenSubstitutionTable){ReportScreenGeneral.screenSubstitutionTable = screenSubstitutionTable;}
    public void setShortcut1(String shortcut1){ReportScreenGeneral.shortcut1 = shortcut1;}
    public void setShortcut2(String shortcut2){ReportScreenGeneral.shortcut2 = shortcut2;}
    public void setShortcut3(String shortcut3){ReportScreenGeneral.shortcut3 = shortcut3;}

    /** Methode for recieving screen description
     * 
     * @param screenDescription 
     */
    public void setScreenDescription(String screenDescription){
        if (screenDescription.isEmpty()){
             ReportScreenGeneral.screenDescription ="Not yet defined";}
        else{ReportScreenGeneral.screenDescription = screenDescription;}
        }

    /** Methode to generate a paragraph in the pdf-report
     * 
     * @param screenDescription 
     */
    public void create(Document pdfDoc) throws MalformedURLException{

        Paragraph   p;
        Paragraph   pKop;
        Cell        rapportBlok;
        String      hulpString;

        // Table definitions
        Table tabel = new Table(12);
        tabel.setWidthPercent(100);
        // Font definitions
        pKop = new Paragraph().setFontSize(9).setUnderline().setBold();
        p = new Paragraph().setFontSize(8);
        // Tabstops definitions
        p.addTabStops(new TabStop(60, TabAlignment.LEFT));
        p.addTabStops(new TabStop(65, TabAlignment.LEFT));

        
        pKop.add("\nGENERAL\n");

        p.add("Description");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(screenDescription);
        p.add("\n");

        p.add("Filename");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(screenFileName);
        p.add("\n");

        p.add("File path");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(screenFilePath);
        p.add("\n");

        p.add("Template");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(screentemplate);
        p.add("\n");


        p.add("Visible");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = screenVisible;
        if (screenVisible.isEmpty()){hulpString ="Visible for all users and viewers";}
        if (screenVisible.contentEquals("0")){hulpString ="Only visible in IDE";}
        p.add(hulpString);
        p.add("\n");

        p.add("Enable");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = screenEnable;
        if (screenEnable.isEmpty()){hulpString ="Visible for all users and viewers";}
        p.add(hulpString);
        p.add("\n");

        p.add("Substitution");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = screenSubstitutionTable;
        if (screenSubstitutionTable.isEmpty()){hulpString ="No substitution table";}
        p.add(hulpString);
        p.add("\n");

        p.add("Shortcut 1");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(shortcut1);
        p.add(new Tab());
        p.add("\n");

        p.add("Shortcut 2");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(shortcut2);
        p.add(new Tab());
        p.add("\n");

        p.add("Shortcut 3");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(shortcut3);
        p.add(new Tab());
        p.add("\n");

        // Add the heading and content to a cel
        Cell regel = new Cell(1,12).add(pKop).add(p);
        // Add the cell to the table
        tabel.addCell(regel);  
        
        // Add the table to the document
        pdfDoc.add(tabel);

} 

}


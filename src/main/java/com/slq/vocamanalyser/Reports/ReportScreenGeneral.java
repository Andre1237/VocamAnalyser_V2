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
import static com.slq.vocamanalyser.Reports.ReportDialogGeneral.dialogWidth;
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
    private Screen screen;

    
               
 
    //============================================================== constructor
public ReportScreenGeneral(Document pdfDoc, Screen screen) { 
    
    this.screen =  screen;
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

        //Definition of temporary strings used by the table definition
        String visibleString = screen.visible;
        if (screen.visible.isEmpty()){visibleString ="Visible for all users and viewers";}
        if (screen.visible.contentEquals("0")){visibleString ="Only visible in IDE";}

        String enableString = screen.enable;
        if (screen.enable.isEmpty()){enableString ="Enabled for all users and viewers";}

        String substString = screen.substitutionTable;
        if (screen.substitutionTable.isEmpty()){substString ="No substitution table";}
        
        String dialogDim = screen.width+" x "+ screen.height + "  (Width x Height)";

        String closeActString = screen.dialogCloseAction;
        if (screen.dialogCloseAction.isEmpty()){closeActString ="No close action defined";}

        
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

        Tab ts = new Tab();
        
        switch(screen.vocamScreenType){
        case "Screen":  
                p.add("Description") .add(ts).add(":") .add(ts) .add(screen.description) .add("\n");
                p.add("Filename")    .add(ts).add(":") .add(ts) .add(screen.filename)    .add("\n");
                p.add("File path")   .add(ts).add(":") .add(ts) .add(screen.path)        .add("\n");
                p.add("Template")    .add(ts).add(":") .add(ts) .add(screen.template)    .add("\n");
                p.add("Enable")      .add(ts).add(":") .add(ts) .add(enableString)       .add("\n");
                p.add("Visible")     .add(ts).add(":") .add(ts) .add(visibleString)      .add("\n");
                p.add("Substitution").add(ts).add(":") .add(ts) .add(substString)        .add("\n");
                p.add("Shortcut 1")  .add(ts).add(":") .add(ts) .add(screen.shortcut1)   .add("\n");
                p.add("Shortcut 2")  .add(ts).add(":") .add(ts) .add(screen.shortcut2)   .add("\n");
                p.add("Shortcut 3")  .add(ts).add(":") .add(ts) .add(screen.shortcut3)   .add("\n");
                break;
                
        case "Dialog":        
                p.add("Description") .add(ts).add(":") .add(ts) .add(screen.description) .add("\n");
                p.add("Filename")    .add(ts).add(":") .add(ts) .add(screen.filename)    .add("\n");
                p.add("File path")   .add(ts).add(":") .add(ts) .add(screen.path)        .add("\n");
                p.add("Size")        .add(ts).add(":") .add(ts) .add(dialogDim)          .add("\n");
                p.add("Template")    .add(ts).add(":") .add(ts) .add(screen.template)    .add("\n");
                p.add("Enable")      .add(ts).add(":") .add(ts) .add(enableString)       .add("\n");
                p.add("Visible")     .add(ts).add(":") .add(ts) .add(visibleString)      .add("\n");
                p.add("Substitution").add(ts).add(":") .add(ts) .add(substString)        .add("\n");
                p.add("Edit mode")   .add(ts).add(":") .add(ts) .add(screen.editMode)    .add("\n");
                p.add("Close action").add(ts).add(":") .add(ts) .add(closeActString)     .add("\n");
                break;
        }        

        // Add the heading and content to a cel
        Cell regel = new Cell(1,12).add(pKop).add(p);
        // Add the cell to the table
        tabel.addCell(regel);  
        
        // Add the table to the document
        pdfDoc.add(tabel);

    } 

}


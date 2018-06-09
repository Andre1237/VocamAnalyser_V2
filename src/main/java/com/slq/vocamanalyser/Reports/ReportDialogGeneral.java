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
public class ReportDialogGeneral {
    
    //================================================================ constants


    //=================================================================== fields
    private static String dialogHeaderTitle;
    private static String dialogDescription; 
    private static String dialogCloseAction; 
    private static String dialogFileName;
    private static String dialogFilePath;
    private static String dialogEditMode; 
    private static String dialogEnable; 
    private static String dialogHeight; 
    private static String dialogSubstitutionTable; 
    private static String dialogtemplate; 
    private static String dialogVisible; 
    public static String dialogWidth;
    //============================================================== constructor
public ReportDialogGeneral(Document pdfDoc) {    
    dialogHeaderTitle="";
    dialogDescription=""; 
    dialogCloseAction=""; 
    dialogFileName="";
    dialogFilePath="";
    dialogEditMode=""; 
    dialogEnable=""; 
    dialogHeight=""; 
    dialogSubstitutionTable=""; 
    dialogtemplate=""; 
    dialogVisible=""; 
    dialogWidth="";
}    


    /** Methodes for recieving dialog data
     * 
     * Collection of set commands
     */
    public void setDialogHeaderTitle(String dialogHeaderTitle){ReportDialogGeneral.dialogHeaderTitle = dialogHeaderTitle;}
    public void setFileName(String dialogFileName){ReportDialogGeneral.dialogFileName = dialogFileName;}
    public void setFilePath(String dialogFilePath){ReportDialogGeneral.dialogFilePath = dialogFilePath;}
    public void setDialogtemplate(String dialogtemplate){ReportDialogGeneral.dialogtemplate = dialogtemplate;}
    public void setDialogHeight(String dialogHeight){ReportDialogGeneral.dialogHeight = dialogHeight;}
    public void setDialogWidth(String dialogWidth){ReportDialogGeneral.dialogWidth = dialogWidth;}
    public void setdDialogVisible(String dialogVisible){ReportDialogGeneral.dialogVisible = dialogVisible;}
    public void setDialogEnable(String dialogEnable){ReportDialogGeneral.dialogEnable = dialogEnable;}
    public void setDialogEditMode(String dialogEditMode){ReportDialogGeneral.dialogEditMode = dialogEditMode;}
    public void setDdalogCloseAction(String dialogCloseAction){ReportDialogGeneral.dialogCloseAction = dialogCloseAction;}
    public void setdialogSubstitutionTable(String dialogSubstitutionTable){ReportDialogGeneral.dialogSubstitutionTable = dialogSubstitutionTable;}

    /** Methode for recieving dialog description
     * 
     * @param dialogDescription 
     */
    public void setDialogDescription(String dialogDescription){
        if (dialogDescription.isEmpty()){
             ReportDialogGeneral.dialogDescription ="Not yet defined";}
        else{ReportDialogGeneral.dialogDescription = dialogDescription;}
        }

    /** Methode to generate a paragraph in the pdf-report
     * 
     * @param dialogDescription 
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
        pKop = new Paragraph().setFontSize(10).setUnderline().setBold();
        p = new Paragraph().setFontSize(10);
        // Tabstops definitions
        p.addTabStops(new TabStop(60, TabAlignment.LEFT));
        p.addTabStops(new TabStop(65, TabAlignment.LEFT));

        
        pKop.add("\nGENERAL\n");

        p.add("Description");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogDescription);
        p.add("\n");

        p.add("Filename");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogFileName);
        p.add("\n");

        p.add("File path");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogFilePath);
        p.add("\n");

        p.add("Template");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogtemplate);
        p.add("\n");

        p.add("Size");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogWidth+" x "+dialogHeight+"  (Width x Height)");
        p.add("\n");

        p.add("Edit mode");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        p.add(dialogEditMode);
        p.add(new Tab());
        p.add("\n");

        p.add("Visible");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = dialogVisible;
        if (dialogVisible.isEmpty()){hulpString ="Visible for all users and viewers";}
        if (dialogVisible.contentEquals("0")){hulpString ="Only visible in IDE";}
        p.add(hulpString);
        p.add("\n");

        p.add("Enable");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = dialogEnable;
        if (dialogEnable.isEmpty()){hulpString ="Visible for all users and viewers";}
        p.add(hulpString);
        p.add("\n");

        p.add("Substitution");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = dialogSubstitutionTable;
        if (dialogSubstitutionTable.isEmpty()){hulpString ="No substitution table";}
        p.add(hulpString);
        p.add("\n");

        p.add("Close action");
        p.add(new Tab());
        p.add(":");
        p.add(new Tab());
        hulpString = dialogCloseAction;
        if (dialogCloseAction.isEmpty()){hulpString ="No close action defined";}
        p.add(hulpString);
        p.add("\n");

        // Add the heading and content to a cel
        Cell regel = new Cell(1,12).add(pKop).add(p);
        // Add the cell to the table
        tabel.addCell(regel);  
        
        // Add the table to the document
        pdfDoc.add(tabel);

} 

}


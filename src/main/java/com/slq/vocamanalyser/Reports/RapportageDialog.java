/*
 * To change this license reportDialogHeader, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.slq.vocamanalyser.VocamAnalyser;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

/**
 *
 * @author SLQ
 */
public class RapportageDialog{

    //================================================================ constants

    //=================================================================== fields
    
    public ReportScreenHeader   reportScreenHeader;
    public ReportScreenGeneral  reportScreenGeneral;
    public ReportScreenSummary  reportScreenSummary;
    public ReportScreenMessages reportScreenMessages;
    public ReportScreenLinks    reportScreenLinks;
    
    public ReportDialogHeader   reportDialogHeader;
    public ReportDialogGeneral  reportDialogGeneral;
    public ReportSummary  reportDialogSummary;
    public ReportMessages reportDialogMessages;
    public ReportLinks    reportDialogLinks;

    //============================================================== constructor

public RapportageDialog(){
}
public RapportageDialog(Document pdfDoc){

    reportDialogHeader   = new ReportDialogHeader(pdfDoc);
    reportDialogGeneral  = new ReportDialogGeneral(pdfDoc);
    reportDialogSummary  = new ReportSummary(pdfDoc);
    reportDialogMessages = new ReportMessages(pdfDoc);
    reportDialogLinks    = new ReportLinks(pdfDoc);

    reportScreenHeader   = new ReportScreenHeader(pdfDoc);
    reportScreenGeneral  = new ReportScreenGeneral(pdfDoc);
    reportScreenSummary  = new ReportScreenSummary(pdfDoc);
    reportScreenMessages = new ReportScreenMessages(pdfDoc);
    reportScreenLinks    = new ReportScreenLinks(pdfDoc);

}

    //================================================================= methodes
    public void createFrontPage(Document pdfDoc) throws FileNotFoundException, MalformedURLException {
        Paragraph p;
        Paragraph pKop;
        Cell rapportBlok;

        Table tabel = new Table(12);
        tabel.setWidthPercent(100);

        reportDialogHeader.create(pdfDoc);

        reportDialogGeneral.create(pdfDoc);

        reportDialogSummary.create(pdfDoc);

        //reportDialogLinks.create(pdfDoc);
            
        pdfDoc.add(tabel);
        p = new Paragraph().setFontSize(10);
        p.add("\nIndividual components are analyzed below");
        pdfDoc.add(p);

        pdfDoc.add(new AreaBreak());  
  
        p = new Paragraph().setFontSize(10);
        p.add("\nStart of Component Analysis");
        
        
        pdfDoc.add(p);
        
    } 

    public void createMessages(Document pdfDoc) throws FileNotFoundException, MalformedURLException {
        Paragraph p;
        Paragraph pKop;
        Cell rapportBlok;

        Table tabel = new Table(12);
        tabel.setWidthPercent(100);

        VocamAnalyser.reportMessages.create(pdfDoc);
        VocamAnalyser.reportTags.create(pdfDoc);
        VocamAnalyser.reportLink.create(pdfDoc);
    }
}
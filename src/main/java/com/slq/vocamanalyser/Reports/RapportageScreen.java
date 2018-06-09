/*
 * To change this license header, choose License Headers in Project Properties.
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
public class RapportageScreen {

    //================================================================ constants

    //=================================================================== fields
    
    public ReportScreenHeader   reportScreenHeader;
    public ReportScreenGeneral  reportScreenGeneral;
    public ReportScreenSummary  reportScreenSummary;
    public ReportScreenMessages reportScreenMessages;
    public ReportScreenLinks    reportScreenLinks;
    
    //============================================================== constructor

public RapportageScreen(){
}
public RapportageScreen(Document pdfDoc){

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

        reportScreenHeader.create(pdfDoc);

        reportScreenGeneral.create(pdfDoc);

        reportScreenSummary.create(pdfDoc);

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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author SLQ
 */
public class ReportDialogHeader {
    
    //================================================================ constants
    public static final String LOGO = "src/main/resources/images/LogoMarineIngenuity.gif";
    
    //=================================================================== fields
    static String dialogHeaderTitle;
    
    //============================================================== constructor
public ReportDialogHeader(Document pdfDoc) {    
    dialogHeaderTitle = "Not defined";
}    
    /** Methode for recieving dialog title
     * 
     * @param dialogHeaderTitle 
     */
     public void setDialogHeaderTitle(String dialogHeaderTitle){
        ReportDialogHeader.dialogHeaderTitle = dialogHeaderTitle;
    }
     
    /** Methode to generate the header with logo in the pdf-report
     * 
     * @param dialogDescription 
     */
    public void create(Document pdfDoc) throws MalformedURLException{

        Paragraph p;
        Table tabel = new Table(12);
        tabel.setWidthPercent(100);
            
        // Add image to first 2 cells
        Image img = new Image(ImageDataFactory.create(LOGO));
        img.scale(0.03f, 0.03f);
        Cell kopTekst3 = new Cell(1,2).add(img);
        tabel.addCell(kopTekst3);

        // Add main title in the center 
        p = new Paragraph(" Dialog :  ").setFontSize(14)
            .add(dialogHeaderTitle).setFontSize(14);
        Cell kopTekst1 = new Cell(1,8).add(p);
        tabel.addCell(kopTekst1);         

        // Add date and time to the last cell
        Date dNow = new Date( );
        SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
        SimpleDateFormat ft2 = new SimpleDateFormat ("dd.MM.yy");
        String tijd  = ft1.format(dNow);
        String datum = ft2.format(dNow);

        p = new Paragraph("Printed:\t"+datum+" "+tijd+"\nVersion:\t0.1").setFontSize(6);
        Cell kopTekst2 = new Cell(1,2).add(p);
        tabel.addCell(kopTekst2);  

        // Add the cells to the table
        pdfDoc.add(tabel);

} 
}

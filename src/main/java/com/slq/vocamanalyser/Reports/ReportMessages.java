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
import java.util.ArrayList;

/**
 *
 * @author SLQ
 */

public class ReportMessages {
    
    //================================================================ constants
    
    //=================================================================== fields
    public static ArrayList<String> berichtenSoort;
    public static ArrayList<String> berichtenRegels;
    public static ArrayList<String> berichtenPos;
    
    //============================================================== constructor
public ReportMessages(Document pdfDoc) {    
    berichtenSoort = new ArrayList<>();
    berichtenRegels = new ArrayList<>();
    berichtenPos = new ArrayList<>();
}    

    /** Methode for recieving dialog title
     * 
     * @param soortBericht
     * @param berichtRegel
     * @param positie
     */
    public void addMsg(String soortBericht,String berichtRegel,String positie){
        berichtenSoort.add(soortBericht);  
        berichtenRegels.add(berichtRegel);  
        berichtenPos.add(positie);
    }  
    
    /** Methode to generate the header with logo in the pdf-report
     * 
     * @param pdfDoc
     * @throws java.net.MalformedURLException
     */
    public void create(Document pdfDoc) throws MalformedURLException{
        Paragraph p;
        Paragraph pKop;
        Table tabel = new Table(12);
        tabel.setWidthPercent(100);
  

        pKop = new Paragraph().setFontSize(10).setUnderline().setBold();
        p = new Paragraph().setFontSize(10);
        // Definitie tabstops
        p.addTabStops(new TabStop(25, TabAlignment.RIGHT));
        p.addTabStops(new TabStop(34, TabAlignment.LEFT));
        p.addTabStops(new TabStop(85, TabAlignment.LEFT));
        p.addTabStops(new TabStop(512, TabAlignment.RIGHT));

        pKop.add("\nREMARKS\n");

        p.add(new Tab());
        p.add("#");
        p.add(new Tab());
        p.add("Type");
        p.add(new Tab());
        p.add("Message");
        p.add(new Tab());
        p.add("Component location");
        p.add("\n");


        int indexRegelTeller= 1;
        for (int i = 0; i < berichtenRegels.size(); i++){
            p.add(new Tab());
            if(berichtenSoort.get(i).isEmpty()){
                p.add("  ");
            }else{
                p.add(String.valueOf(indexRegelTeller));
                indexRegelTeller++;
            }                
            p.add(new Tab());

            p.add(berichtenSoort.get(i));
            p.add(new Tab());
            p.add(berichtenRegels.get(i));
            p.add(new Tab());
            p.add(berichtenPos.get(i));
            p.add("\n");
        } 

        Cell regel = new Cell(1,12).add(pKop).add(p);
        tabel.addCell(regel);         

        // Add the cells to the table
        pdfDoc.add(tabel);
      
}
}



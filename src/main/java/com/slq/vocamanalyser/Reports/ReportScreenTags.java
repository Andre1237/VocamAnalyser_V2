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
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Tabs;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author SLQ
 */
public class ReportScreenTags {
      
    //================================================================ constants
    
    //=================================================================== fields
    public static ArrayList<String> component;
    public static ArrayList<String> tagSoort;
    public static ArrayList<String> tagAdres;
    public static ArrayList<String> tagLocation;

    //============================================================== constructor
public ReportScreenTags(Document pdfDoc) {    
    component = new ArrayList<>();
    tagSoort = new ArrayList<>();
    tagAdres = new ArrayList<>();
    tagLocation = new ArrayList<>();
}    

    /** Methode for recieving screen title
     * 
     * @param soortTag
     * @param adresTag
     * @param locationTag
     */
    public void addTag(String soortTag,String adresTag, String locationTag){
        component.add("");
        tagSoort.add(soortTag);  
        tagAdres.add(adresTag);  
        tagLocation.add(locationTag);  
    }  
    public void addTag(String comp, String soortTag,String adresTag, String locationTag){
        component.add(comp);
        tagSoort.add(soortTag);  
        tagAdres.add(adresTag);  
        tagLocation.add(locationTag);  
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
        
        pKop = new Paragraph().setFontSize(9).setUnderline().setBold();
        p = new Paragraph().setFontSize(8);
        // Definitie tabstops
        p.addTabStops(new TabStop(25, TabAlignment.RIGHT));
        p.addTabStops(new TabStop(34, TabAlignment.LEFT));
        p.addTabStops(new TabStop(105, TabAlignment.LEFT));
        p.addTabStops(new TabStop(160, TabAlignment.LEFT));
        p.addTabStops(new TabStop(512, TabAlignment.RIGHT));

        pKop.add("\nUSED TAGS!!!\n");

        p.add(new Tab());
        p.add("#");
        p.add(new Tab());
        p.add("Component");
        p.add(new Tab());
        p.add("Type");
        p.add(new Tab());
        p.add("Tag");
        p.add(new Tab());
        p.add("Tag location");
        p.add("\n");

        int indexRegelTeller= 1;
        for (int i = 0; i < tagAdres.size(); i++){
            p.add(new Tab());
            if(tagSoort.get(i).isEmpty()){
                p.add("  ");
            }else{
                p.add(String.valueOf(indexRegelTeller));
                indexRegelTeller++;
            }                
          
            p.add(new Tab());
            p.add(component.get(i));
            p.add(new Tab());
            p.add(tagSoort.get(i));
            p.add(new Tab());
            p.add(tagAdres.get(i));
            p.add(new Tab());
            p.add(tagLocation.get(i));
            p.add("\n");
        } 

        Cell regel = new Cell(1,12).add(pKop).add(p);
        tabel.addCell(regel);         

        // Add the cells to the table
        pdfDoc.add(tabel);

        p = new Paragraph().setFontSize(10);
        p.add("\n");
        pdfDoc.add(p);
    
}
}




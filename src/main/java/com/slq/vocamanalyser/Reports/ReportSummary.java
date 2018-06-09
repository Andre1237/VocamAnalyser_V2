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
public class ReportSummary {
    
    //================================================================ constants
    public static final String LOGO = "src/main/resources/images/LogoMarineIngenuity.gif";
    
    //=================================================================== fields
    private static ArrayList<String> componentenLijst;
    private static ArrayList<Integer> compTeller;   
    
    //============================================================== constructor
public ReportSummary(Document pdfDoc) {
    componentenLijst = new ArrayList<>();
    compTeller = new ArrayList<>();   
}    

    /**Methode voor cumulatief tellen van de componenten
     * 
     * @param componentNaam
     */
    public void addComponentToSummary(String componentNaam){
        boolean bestaatAl = componentenLijst.contains(componentNaam);
        if (bestaatAl){
            //Als het component al in de arraylist staat dan wordt
            //de teller met 1 verhoogd.
            int index = componentenLijst.indexOf(componentNaam);
            compTeller.set(index, compTeller.get(index)+1);
        }else{
            //de component komt nog niet voor in de arraylist
            //en wordt daarom toegevoegd met teller waarde is 1
            componentenLijst.add(componentNaam);
            compTeller.add(1);
        }
    }

     
    /** Methode to generate the header with logo in the pdf-report
     * 
     * @param dialogDescription 
     */
    public void create(Document pdfDoc) throws MalformedURLException{

        Paragraph p;
        Paragraph pKop;
        Table tabel = new Table(12);
        tabel.setWidthPercent(100);

        pKop = new Paragraph().setFontSize(10).setUnderline().setBold();
        p = new Paragraph().setFontSize(10);
        // Definitie tabstops
        p.addTabStops(new TabStop(100, TabAlignment.LEFT));

        pKop.add("\nNUMBER OF COMPONENTS USED\n");

        int indexRegelTeller= 1;
        String componentType;
        String checkedComponentType;
        
        for (int i = 0; i < componentenLijst.size(); i++){
            componentType = componentenLijst.get(i);
            p.add(componentType);
            p.add(new Tab());
            p.add(String.valueOf(compTeller.get(i)));
            p.add(new Tab());
            if (!componentType.equals("Indicator")&&
                !componentType.equals("Rectangle")&&
                !componentType.equals("Button")&&
                !componentType.equals("ButtonComponent")&&
                !componentType.equals("Label")&&
                !componentType.equals("Line")&&
                !componentType.equals("Value")&&
                !componentType.equals("CheckBox")&&                    
                !componentType.equals("InputField")  
                    ){

                p.add("  <-- Component not analyzed");
            }
            p.add("\n");
        } 
        
        Cell regel = new Cell(1,12).add(pKop).add(p);
        tabel.addCell(regel);         

        // Add the cells to the table
        pdfDoc.add(tabel);

    }
}
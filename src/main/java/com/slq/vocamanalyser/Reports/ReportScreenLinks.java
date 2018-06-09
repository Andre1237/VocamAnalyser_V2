/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.PfdCell;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * @author SLQ
 */
public class ReportScreenLinks {
   
    //================================================================ constants
    String HaakjeOpen = "\\(";
    //=================================================================== fields

    public static ArrayList<String> componentType;
    public static ArrayList<String> componentId;
    public static ArrayList<String> typeLink;
    
    //============================================================== constructor

public ReportScreenLinks(Document pdfDoc) {    
    componentType = new ArrayList<>();
    componentId = new ArrayList<>();
    typeLink = new ArrayList<>();
 
}    

    /** Method for receiving links
     * 
     * @param compType  String like "Label", "Value", "Buttoncomponent"
     * @param compID    String that identifies this component (x,y-pos)
     * @param rawLink   String raw xml-string from link
     */
    public void addTag(String compType,String compID, String rawLink){
        componentType.add(compType);  
        componentId.add(compID);  
        typeLink.add(rawLink);  
    }  
    
    /** Method to generate the header with logo in the pdf-report
     * 
     * @param pdfDoc
     * @throws java.net.MalformedURLException
     */
    public void create(Document pdfDoc) throws MalformedURLException{
        Paragraph p;
        Paragraph pKop;
        String referenceCellContent;
       // Table tabel = new Table(12);
        float[] columnWidths = {5,25,20,70};
 
        Table tabel = new Table(UnitValue.createPercentArray(columnWidths));
        tabel.setWidth(UnitValue.createPercentValue(100)).setFixedLayout();        
        tabel.setWidthPercent(100);
        PfdCell cell = new PfdCell(tabel);

        tabel.addCell(cell.writeB(4, "LINKS ON THIS PAGE"));

        tabel.addCell(cell.write(1, "#"));
        tabel.addCell(cell.write(1, "Type"));
        tabel.addCell(cell.write(1, "Component ID"));
        tabel.addCell(cell.write(1, "Reference"));

        int indexRegelTeller= 1;
        for (int i = 0; i < componentType.size(); i++){

            String[] soortLink = typeLink.get(i).split(HaakjeOpen);


            if (soortLink.length>1) {
                if (soortLink[0].equals("ShowPopup")){
                    String[] linkSoortParams = soortLink[1].split(",");
                    referenceCellContent="Type "+soortLink[0]
                                        + "\nLocation="+linkSoortParams[1]
                                        + "     Pos ("+linkSoortParams[2]
                                        + ","
                                        +linkSoortParams[3]
                                        +"\nPopup :"+linkSoortParams[0];

                    
                    tabel.addCell(cell.write(1, String.valueOf(indexRegelTeller)));
                    tabel.addCell(cell.write(1, componentType.get(i)));
                    tabel.addCell(cell.write(1, componentId.get(i)));
                    tabel.addCell(cell.writeTriple(1, referenceCellContent));
                }else{
                    tabel.addCell(cell.write(1, "X"));
                    tabel.addCell(cell.write(1, ""));
                    tabel.addCell(cell.write(1, ""));
                    tabel.addCell(cell.write(1, "Linktype not yet defined"));
                }   
            } 
        indexRegelTeller++;
        }
        pdfDoc.add(tabel);
    }
}




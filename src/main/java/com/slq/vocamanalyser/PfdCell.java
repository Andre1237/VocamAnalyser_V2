/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

/**
 *
 * @author SLQ
 */
public class PfdCell {
    
    //================================================================ constants
    int FONTSIZE = 8;
    float TOPMARGINE = -2.5f;
    float BOTTOMMARGINE = -2.5f;
        
    //=================================================================== fields
    Paragraph   p;
    Cell        celInhoud;
  
    //============================================================== constructor
public PfdCell(Table tabel){
//   nothing to construct.
}   

    public Cell write(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p); //.setBorder(Border.NO_BORDER);
        return celInhoud;
    }
    public Cell writeGray(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p); 
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY);  
        return celInhoud;
}

    public Cell writeB(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        return celInhoud;
    }

    public Cell writeGrayB(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY);  
        return celInhoud;
    }
    
    public Cell writeTriple(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setHeight(45f)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        return celInhoud;
    }
}


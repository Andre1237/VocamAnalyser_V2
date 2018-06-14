/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.slq.vocamanalyser.PfdCell;

/**
 *
 * @author SLQ
 */
public class TableRow {
    //================================================================ constants
    int FONTSIZE = 8;
    float TOPMARGINE = -2.5f;
    float BOTTOMMARGINE = -2.5f;
        
    //=================================================================== fields
    Paragraph   p;
    Cell        cell;
    Cell        celInhoud;  
    //============================================================== constructor

public TableRow(Document pdfDoc) {
 //   tabel = new Table(6); //.setBorder(Border.NO_BORDER);  
}   
    
/**
 * Methode to write a row in a pdf-table in the format
 * +---------+------------------+---------+------------------+
 * |column1  |column23          |column4  |column56          |
 * +---------+------------------+---------+------------------+
 * @param tabel         tabel in which the row has to be added
 * @param layoutCode
 * @param column1       See above
 * @param column23      See above
 * @param column4       See above
 * @param column56      See above 
 */

public void add( Table  tabel, int layoutCode, String column1, String column23,String column4,String column56){
  
    tabel.addCell(write(1, column1 ));
    tabel.addCell(write(2, column23));
    tabel.addCell(write(1, column4));
    tabel.addCell(write(2, column56));
}    

/**
*   Methode to write a row in a pdf-table in the format
*   Layout 1
*  +----------------------------+---------+------------------+
*  |column1                     |column2  |column3           |
*  +----------------------------+---------+------------------+
* 
*  Layout 2:
*  +---------+------------------+----------------------------+
*  |column1  |column2           |column3                     |
*  +---------+------------------+----------------------------+
* @param tabel         tabel in which the row has to be added
* @param layoutCode
* @param column1       See above
* @param column2       See above 
* @param column3       See above
*/
public void add( Table  tabel, int layoutCode, String column1, String column2,String column3){
  
    if (layoutCode==1){
        tabel.addCell(writeGray(3, column1));
        tabel.addCell(write(1, column2));
        tabel.addCell(write(2, column3));
    } else{ // {if (layoutCode==2 ){
        tabel.addCell(write(1, column1));
        tabel.addCell(write(2, column2));
        tabel.addCell(writeGray(3, column3));
    }
}

/**
 * Methode to write a row in a pdf-table in the format
 * Layout 1:      +-------------------------+-------------------------------+
 *                |column1                  |column2                        |
 *                +-------------------------+-------------------------------+
 *
 * Layout 2:      +-------------------------+-------------------------------+
 *                |column1 (BOLD)           |column2                        |
 *                +-------------------------+-------------------------------+
 *
 * Layout 3:      +-------------------------+-------------------------------+
 *                |column1                  |column2  (BOLD)                |
 *                +-------------------------+-------------------------------+
 *
 * Layout 4:      +-------------------------+-------------------------------+
 *                |column1  (BOLD)          |column2  (BOLD)                |
 *                +-------------------------+-------------------------------+
 *
 * Layout 5:      +---------+-----------------------------------------------+
 *                |column1  |column2                                        |
 *                +---------+-----------------------------------------------+
 * 
 * Layout 6:      +-------------------------------------+-------------------+
 *                |column1                              |           column2 |
 *                +-------------------------------------+-------------------+
 * 
 * @param tabel         tabel in which the row has to be added
 * @param layoutCode
 * @param column1       See above
 * @param column2       See above
 */
public void add( Table  tabel, int layoutCode, String column1, String column2){

    switch(layoutCode){
        case 1:     tabel.addCell(write(3, column1));
                    tabel.addCell(write(3, column2));
                    break;   
        case 2:     tabel.addCell(writeGray(3, column1));
                    tabel.addCell(write(3, column2));
                    break;   
        case 3:     tabel.addCell(write(3, column1));
                    tabel.addCell(writeGray(3, column2));
                    break;   
        case 4:     tabel.addCell(writeGray(3, column1));
                    tabel.addCell(writeGray(3, column2));
                    break;   
        case 5:     tabel.addCell(writeNR5(4, column1));
                    tabel.addCell(writeNR6(2, "Compont ID:     " + column2));
                    break;   
        default:    tabel.addCell(write(1, column1));
                    tabel.addCell(write(5, column2));
                    break;   
    }
            
}

/**
 * Methode to write a row in a pdf-table in the format
 * +---------------------------------------------------------+
 * |column1                                                  |
 * +---------------------------------------------------------+
 * @param tabel         tabel in which the row has to be added
 * @param layoutCode    Optional layout options
 * @param column1       See above
 */
public void add( Table  tabel, int layoutCode, String column1){
  
    tabel.addCell(writeGray(6, column1));
}    



    private Cell write(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p); //.setBorder(Border.NO_BORDER);
        return celInhoud;
    }

    private Cell writeGray(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p); 
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY);  
        return celInhoud;
}

    private Cell writeB(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        return celInhoud;
    }

    private Cell writeGrayB(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY);  
        return celInhoud;
    }

    private Cell writeNR5(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
       
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY); 
        celInhoud.setBorderRight(Border.NO_BORDER);
        return celInhoud;
    }

    private Cell writeNR6(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        celInhoud.setBackgroundColor(Color.LIGHT_GRAY); 
        celInhoud.setTextAlignment(TextAlignment.RIGHT);
        celInhoud.setBorderLeft(Border.NO_BORDER);
        
        return celInhoud;
    }
    
    private Cell writeTriple(int numberOfCells, String celContent){
    
        p = new Paragraph() .setFontSize(FONTSIZE)
                            .setHeight(45f)
                            .setMarginTop(TOPMARGINE)
                            .setMarginBottom(BOTTOMMARGINE);
        p.add(celContent);
        celInhoud = new Cell(1,numberOfCells).add(p);
        return celInhoud;
    }
}

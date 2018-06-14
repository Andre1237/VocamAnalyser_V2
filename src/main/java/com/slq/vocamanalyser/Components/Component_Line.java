/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.slq.vocamanalyser.Check;
import java.io.IOException;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;
/**
 *
 * @author SLQ
 */
public class Component_Line {
   
    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_Line() {
    Table  tabel = new Table(6).setBorder(Border.NO_BORDER);  
}

/**
 * Method that build up the pdf-table for this single component
 * @param pdfDoc    : The destination pdf document 
 * @param c         : The component details
 * @return
 * @throws IOException 
 */
public Component create(Document pdfDoc, Component c) throws IOException{

    // Check parameters of individual component and
    // report on used tags.
    analyse(c);

    tableRow = new TableRow(pdfDoc);    // for adding a new table row
    Paragraph p;                        // for adding a newline

    // Setting up the Pdf table
    float[] columnWidths = {1,1,1,1,1,1};        // 6 equally spaced columns
    Table tabel = new Table(UnitValue.createPercentArray(columnWidths));
    tabel.setWidth(UnitValue.createPercentValue(100)).setFixedLayout();        

    //Build output strings
    String size  =   c.width + " x " + c.height + "   (Width x Height)";
    String position = c.xPos + " , " + c.yPos   + "   (X,Y)";
    String symbolTagDxDy = c.symbolTag        + "   Dx: " + c.symbolTagDx +"   Dy: "+c.symbolTagDy;

    tableRow.add(tabel,5, "LINE",                     c.componentID  );
    tableRow.add(tabel,0, "Description", c.description                                 );

    tableRow.add(tabel,4, "Geometry",                     "Software Tags"              );
    tableRow.add(tabel,0, "Size"    ,     size,           "TagSW",        c.tagSW      );
    tableRow.add(tabel,0, "Position",     position,       "Show",         c.show       );    
    tableRow.add(tabel,4, "General",                      "Layout"                     );
    tableRow.add(tabel,0, "Enable",       c.enable,       "Color",        c.color      );
    tableRow.add(tabel,0, "Visible",      c.visible,      "LineSize",     c.lineSize   );
    tableRow.add(tabel,0, "Mode",         c.mode,         "Mirror" ,      c.mirror     );
    tableRow.add(tabel,0, "Offset",       c.offset,       "Show Arrow",   c.showArrow  );

    tableRow.add(tabel,0, "Actions"                                                    );
    tableRow.add(tabel,0, "OnReleaseAction",   c.onReleaseAction                       );
    tableRow.add(tabel,0, "ReferenceTable",    c.referenceTable                        );
    tableRow.add(tabel,0, "Help popup",        c.helpPopup                             );
    tableRow.add(tabel,0, "Joystick   ",       c.directionOverule                      );
    tableRow.add(tabel,0, "SymbolTag",         symbolTagDxDy                           );
    tableRow.add(tabel,0, "Conditional colors",c.conditional                           );
 
    // Add the cells to the table
    pdfDoc.add(tabel);

    p = new Paragraph().setFontSize(10);
    p.add("\n");
    pdfDoc.add(p);

    return c;
}

    public void analyse(Component c){

    Check check = new Check();
        
    //=================================== Write tags to the tag-tracker database
    if (check.isTag(c.tagSW))      { VocamAnalyser.reportTags.addTag("Line","TagSW", c.tagSW, c.componentID); }
    if (check.isTag(c.show))       { VocamAnalyser.reportTags.addTag("Line","Show", c.show,  c.componentID); }
    
    //=================================== Write messages to the message database
 
    if (c.description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added", c.componentID);}
        
    }
}  
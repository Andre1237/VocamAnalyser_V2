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
import java.io.IOException;
import com.slq.vocamanalyser.Check;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;

/**
 *
 * @author SLQ
 */
public class Component_CheckBox {


    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_CheckBox() {
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


    tableRow.add(tabel,5, "CHECKBOX",                     c.componentID  );
    tableRow.add(tabel,0, "Description", c.description                                       );
    tableRow.add(tabel,0, "Overruled discr.", c.overruledDescription                         );

    tableRow.add(tabel,4, "Geometry",                           "Software Tags"              );
    tableRow.add(tabel,0, "Size"    ,         size,             "TagSW",        c.tagSW      );
    tableRow.add(tabel,0, "Position",         position,         "TagcW",        c.tagCW      );    
    tableRow.add(tabel,2, "General",                            "Show",         c.show       );    
    tableRow.add(tabel,0, "Enable",           c.enable,         "Tag disable",  c.tagDisable );
    tableRow.add(tabel,3, "Visible",          c.visible,        "Layout"                     );
    tableRow.add(tabel,0, "Mode",             c.mode,           "Label font" ,  c.labelFont  );
    tableRow.add(tabel,0, "Show Description", c.showDescription,"Label color",  c.labelColor );
    tableRow.add(tabel,0, " ",                " ",              "Active Color", c.activeColor);

    tableRow.add(tabel,0, "Actions"                                                    );
    tableRow.add(tabel,0, "OnReleaseAction",   c.onReleaseAction                       );
    tableRow.add(tabel,0, "ReferenceTable",    c.referenceTable                        );
    tableRow.add(tabel,0, "Help popup",        c.helpPopup                             );
    tableRow.add(tabel,0, "Joystick   ",       c.directionOverule                      );
    tableRow.add(tabel,0, "SymbolTag",         symbolTagDxDy                           );
 
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
    if (check.isTag(c.tagSW))     { VocamAnalyser.reportTags.addTag("CheckBox","TagSW", c.tagSW, c.componentID); }
    if (check.isTag(c.tagCW))     { VocamAnalyser.reportTags.addTag("CheckBox","tagCW", c.tagCW, c.componentID); }
    if (check.isTag(c.tagDisable)){ VocamAnalyser.reportTags.addTag("CheckBox","TagDisable", c.tagDisable,  c.componentID); }
    if (check.isTag(c.show))      { VocamAnalyser.reportTags.addTag("CheckBox","Show", c.show,  c.componentID); }
    
    //=================================== Write messages to the message database
 
    if (c.description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added", c.componentID);}
        

    }
}
    

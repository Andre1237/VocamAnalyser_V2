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
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import java.io.IOException;

/**
 *
 * @author SLQ
 */
public class Component_ButtonToggle {

    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_ButtonToggle() {
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
    RichtingString richtingsRegel = new RichtingString(c.overruleNorth,c.overruleNorthEast,c.overruleEast,c.overruleSouthEast,
                                                       c.overruleSouth,c.overruleSouthWest,c.overruleWest,c.overruleNorthWest);


    tableRow.add(tabel,0, "BUTTONTOGGLE"                                             );
    tableRow.add(tabel,0, "Description", c.description                               );

    tableRow.add(tabel,4, "Geometry",                   "Software Tags"              );
    tableRow.add(tabel,0, "Size"    ,    size,          "TagSW",        c.tagSW      );
    tableRow.add(tabel,0, "Position",    position,      "TagCW",        c.tagCW      );
    tableRow.add(tabel,0, "Enable",      c.enable,      "Show",         c.show       );
    tableRow.add(tabel,0, "Visible",     c.visible,     "ColorActive",  c.colorActive);
    
    tableRow.add(tabel,0, "Actions"                                                  );
    tableRow.add(tabel,0, "OnReleaseAction",c.onReleaseAction                        );
    tableRow.add(tabel,0, "ReferenceTable", c.referenceTable                         );
    tableRow.add(tabel,0, "Help popup",     c.helpPopup                              );
    tableRow.add(tabel,0, "Joystick   ",    richtingsRegel.regel                     );
    tableRow.add(tabel,0, "SymbolTag",      symbolTagDxDy                            );

    // Add the cells to the table
    pdfDoc.add(tabel);

    p = new Paragraph().setFontSize(10);
    p.add("\n");
    pdfDoc.add(p);

    return c;
}

    public void analyse(){
//        
//    //=================================== Write tags to the tag-tracker database
//    
//    if (check.isTag(tagValue)) { VocamAnalyser.reportTags.addTag
//                            ("Button","TagValue", tagValue, "(Pos "+xPos+","+yPos+")"); }
//    
//    if (check.isTag(tagCW)) { VocamAnalyser.reportTags.addTag
//                            ("Button","tagCW", tagCW, "(Pos "+xPos+","+yPos+")"); }
//    
//    if (check.isTag(tagDisabled)) { VocamAnalyser.reportTags.addTag
//                            ("Button","tagDisabled", tagDisabled, "(Pos "+xPos+","+yPos+")"); }
//
//    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
//                            ("Button","Show", show, "(Pos "+xPos+","+yPos+")"); }
//    
//    if (check.isTag(tagStates))  { VocamAnalyser.reportTags.addTag
//                            ("Button","tagStates", tagStates, "(Pos "+xPos+","+yPos+")"); }
//    
//    //=================================== Write messages to the message database
// 
//    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
//                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
//        
//            
//    }
}    
}
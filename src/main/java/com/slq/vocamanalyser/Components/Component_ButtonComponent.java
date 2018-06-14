/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Components;
//Component_ButtonComponent

import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Check;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;
import java.io.IOException;

/**
 *
 * @author SLQ
 */
public class Component_ButtonComponent {
    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_ButtonComponent() {
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

    tableRow.add(tabel,5, "BUTTON COMPONENT",                     c.componentID  );

    tableRow.add(tabel,0, "Description", c.description                               );
    tableRow.add(tabel,0, "Override Label", c.overrideLabel                          );

    tableRow.add(tabel,4, "Geometry",               "Software Tags"                  );
    tableRow.add(tabel,0, "Size"    ,  size,        "TagSW",         c.tagSW         );
    tableRow.add(tabel,0, "Position",  position,    "TagCW",         c.tagCW         );
    tableRow.add(tabel,1, "General",                "Show",          c.show          );
    tableRow.add(tabel,0, "Enable",    c.enable,    "TagDisabled",   c.tagDisabled   );
    tableRow.add(tabel,0, "Visible",   c.visible,   "Blink",         c.tagBlink      );
    tableRow.add(tabel,0, "Type",      c.type,      "Fault",         c.tagFault      );
    tableRow.add(tabel,3, "Action",    c.action,    "Layout"                         );
    tableRow.add(tabel,0, "Label font",c.fontSize,  "Active color",  c.activeColor   );
    tableRow.add(tabel,0, " ",         "",          "Inactive color",c.inactiveColor );
    
    tableRow.add(tabel,0, "Actions"                                                  );
    tableRow.add(tabel,0, "OnReleaseAction",c.onReleaseAction                        );
    tableRow.add(tabel,0, "ReferenceTable", c.referenceTable                         );
    tableRow.add(tabel,0, "Help popup",     c.helpPopup                              );
    tableRow.add(tabel,0, "Joystick   ",    c.directionOverule                       );
    tableRow.add(tabel,0, "SymbolTag",      symbolTagDxDy                            );

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
  
    if (check.isTag(c.tagSW))      { VocamAnalyser.reportTags.addTag("ButtonComponent","TagSW", c.tagSW, c.componentID); }
    if (check.isTag(c.tagCW))      { VocamAnalyser.reportTags.addTag("ButtonComponent","TagCW", c.tagCW, c.componentID); }
    if (check.isTag(c.tagDisabled)){ VocamAnalyser.reportTags.addTag("ButtonComponent","TagDisabled", c.tagDisabled,  c.componentID); }
    if (check.isTag(c.show))       { VocamAnalyser.reportTags.addTag("ButtonComponent","Show", c.show,  c.componentID); }
    if (check.isTag(c.tagBlink))   { VocamAnalyser.reportTags.addTag("ButtonComponent","TagBlink", c.tagBlink,  c.componentID); }
    if (check.isTag(c.tagFault))   { VocamAnalyser.reportTags.addTag("ButtonComponent","TagFault", c.tagFault,  c.componentID); }

    //=================================== Write messages to the message database
 
    if (c.description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added",c.componentID);}
        
    if (c.activeColor.equals(c.inactiveColor)){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Active and inactive state have the same color",c.componentID);}

    //================================== Write messages to the pagelink database
 
    if (!c.onReleaseAction.isEmpty()){ VocamAnalyser.reportLink.addTag
                            ("ButtonComponent",c.componentID,c.onReleaseAction);
    }

    }
}
    

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
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;
import java.io.IOException;

/**
 *
 * @author SLQ
 */
public class Component_Value {
    //================================================================ constants
   
    //=================================================================== fields
// Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_Value() {
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
    String overForm =  c.overruledformatter + " " + c.overruleddimension;
    String symbolTagDxDy = c.symbolTag        + "   Dx: " + c.symbolTagDx +"   Dy: "+c.symbolTagDy;

    tableRow.add(tabel,5, "VALUE",                     c.componentID  );
    tableRow.add(tabel,0, "Description", c.description                               );
    tableRow.add(tabel,0, "Overruled discr.", c.overruledDescription                 );
    tableRow.add(tabel,0, "Overruled format", overForm                               );
    
    tableRow.add(tabel,4, "Geometry",                               "Software Tags"                     );
    tableRow.add(tabel,0, "Size"    ,         size,                 "Value",       c.tag                );
    tableRow.add(tabel,0, "Position",         position,             "Inactive tag",c.inactivetag        );
    tableRow.add(tabel,0, "Description width",c.descriptionwidth,   "Show",        c.show               );
    tableRow.add(tabel,0, "Dimension width",  c.dimensionwidth,     "Color Index", c.tagcolor           );
    tableRow.add(tabel,1, "General",                                "Index Number",c.indexnumber        );
    tableRow.add(tabel,0, "Enable",           c.enable,             "Index tag",   c.indextag           );
    tableRow.add(tabel,3, "Visible",          c.visible,            "Layout"                            );
    tableRow.add(tabel,0, "Show Descriptio",  c.showDescription,    "Font size",   c.valuefont          );
    tableRow.add(tabel,0, "Show dimension",   c.showdimension,      "Value color", c.valuecolor         );
    tableRow.add(tabel,0, "Value type",       c.valuetype,          "Alignment",   c.alignment          );
    tableRow.add(tabel,0, "Bottom border",    c.drawbottomborder,   "Alignment H", c.valuehoralignment  );
    tableRow.add(tabel,0, "Visible",          c.visible,            "TagStates",   c.tagStates          );
    tableRow.add(tabel,0, "Max decimals",     c.maxexpecteddecimals,"Alignment V", c.vertalign          );
    tableRow.add(tabel,0, "Refresh rate",     c.valuerefreshperiod,  " ",          " "                  );

    tableRow.add(tabel,0, "Actions"                                                  );
    tableRow.add(tabel,0, "OnReleaseAction",c.onReleaseAction                        );
    tableRow.add(tabel,0, "ReferenceTable", c.referenceTable                         );
    tableRow.add(tabel,0, "Help popup",     c.helpPopup                              );
    tableRow.add(tabel,0, "Joystick   ",    c.directionOverule                       );
    tableRow.add(tabel,0, "SymbolTag",      symbolTagDxDy                            );
    tableRow.add(tabel,0, "Color table",    c.colortable                             );

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
    if (check.isTag(c.tag))        { VocamAnalyser.reportTags.addTag("Value","Tag", c.tagValue, c.componentID); }
    if (check.isTag(c.inactivetag)){ VocamAnalyser.reportTags.addTag("Value","Inactivetag", c.inactivetag, c.componentID); }
    if (check.isTag(c.show))       { VocamAnalyser.reportTags.addTag("Value","Show", c.show,  c.componentID); }
    if (check.isTag(c.tagcolor))   { VocamAnalyser.reportTags.addTag("Value","Tagcolor", c.tagcolor,  c.componentID); }
    if (check.isTag(c.indexnumber)){ VocamAnalyser.reportTags.addTag("Value","Indexnumber", c.indexnumber,  c.componentID); }
    if (check.isTag(c.indextag))   { VocamAnalyser.reportTags.addTag("Value","Indextag", c.indextag,  c.componentID); }
    
    //=================================== Write messages to the message database
 
    if (c.description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added", c.componentID);}
        
    if ((c.showdimension.equals("true"))&&(c.overruleddimension.isEmpty())){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Description is selecteed but no text is entered",c.componentID);}
    }
}


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
import com.slq.vocamanalyser.Check;
import com.slq.vocamanalyser.Reports.TableRow;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;
import java.io.IOException;

/**
 *
 * @author SLQ
 */
public class Component_InputField {

    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    String      joystickOverrules;
    TableRow    tableRow;

    //============================================================== constructor 

public Component_InputField() {
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
 
    tableRow.add(tabel,5, "INPUTFIELD",                     c.componentID  );
    tableRow.add(tabel,0, "Description", c.description                                                       );
    tableRow.add(tabel,0, "Override disc.", c.overruledDescription                                           );

    tableRow.add(tabel,4, "Geometry",                                "Software Tags"                         );
    tableRow.add(tabel,0, "Size"    ,         size,                  "Tag Write",        c.tagWrite          );
    tableRow.add(tabel,0, "Position",         position,              "Tag Read",         c.tagRead           );    
    tableRow.add(tabel,3, "General",                                 "Tag Disable",      c.tagDisable        );
    tableRow.add(tabel,0, "Enable",           c.enable,              "Show",             c.show              );
    tableRow.add(tabel,2, "Visible",          c.visible,             "Layout"                                );
    tableRow.add(tabel,0, "Refresh rate",     c.valueRefreshPeriod,  "Font size"  ,      c.labelFont         );
    tableRow.add(tabel,0, "Min.input",        c.minInput,            "Font color" ,      c.labelColor        );
    tableRow.add(tabel,0, "Max.input",        c.maxInput,            "Value font" ,      c.valueFont         );
    tableRow.add(tabel,0, "Expected decimals",c.maxExpectedDecimals, "Value color",      c.valueColor        );
    tableRow.add(tabel,0, "Min. step",        c.smallestStep,        "Input font" ,      c.inputFont         );
    tableRow.add(tabel,0, "Min.step Joystick",c.smallestStepJoystick,"Overruled dim." ,  c.overruledDimension);
    tableRow.add(tabel,0, "Number of steps",  c.numberOfSteps,        "overruled format",c.overruledFormatter);
    tableRow.add(tabel,0, "Show description", c.showDescription,      "Dim. width",      c.dimensionWidth    );
    tableRow.add(tabel,0, "Show dimension",   c.showDimension,        "Alignment",       c.alignment         );


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
    if (check.isTag(c.tagWrite))  { VocamAnalyser.reportTags.addTag("InputField","TagWrite", c.tagWrite, c.componentID); }
    if (check.isTag(c.tagRead))   { VocamAnalyser.reportTags.addTag("InputField","TagRead", c.tagRead, c.componentID); }
    if (check.isTag(c.show))      { VocamAnalyser.reportTags.addTag("InputField","Show", c.show,  c.componentID); }
    if (check.isTag(c.tagDisable)){ VocamAnalyser.reportTags.addTag("InputField","tagDisable", c.tagDisable,  c.componentID); }
    
    //=================================== Write messages to the message database
 
    if (c.description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added", c.componentID);}

    if (c.overruledDescription.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Description of InputField is empty", c.componentID);}
    
    }
}
    

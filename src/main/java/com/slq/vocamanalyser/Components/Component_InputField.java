/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Components;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.AttributeFinder;
import com.slq.vocamanalyser.Check;
import com.slq.vocamanalyser.PfdCell;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author SLQ
 */
public class Component_InputField {
    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;
    Check check;
    
    String width="";
    String visible="";
    String height="";
    String enable="";
    String description="";
    String show="";
    String yPos="";
    String xPos="";
    String onReleaseAction="";
    String joyStick="";
    String referenceTable="";
    String valueRefreshPeriod="";
    String valueFont="";
    String valueColor="";
    String tagWrite="";
    String tagRead="";
    String tagDisable="";
    String smallestStepJoystick="";
    String smallestStep="";
    String showDimension="";
    String showDescription="";
    String overruledFormatter="";
    String overruledDimension="";
    String overruledDescription="";
    String numberOfSteps="";
    String minInput="";
    String maxInput="";
    String maxExpectedDecimals="";
    String labelFont="";
    String labelColor="";
    String inputFont="";
    String hideOnDisable="";
    String dimensionWidth="";
    String descriptionWidth="";
    String alignment="";
    String symbolTagDy="";
    String symbolTagDx="";
    String symbolTag="";
    String overruleWest="";
    String overruleSouthWest="";
    String overruleSouthEast="";
    String overruleSouth="";
    String overruleNorthWest="";
    String overruleNorthEast="";
    String overruleNorth="";
    String overruleEast="";
    String helpPopup="";

public Component_InputField() {
    Table  tabel = new Table(6).setBorder(Border.NO_BORDER);  
    cell = new PfdCell();
    check = new Check();
}
    
    public void create(Document pdfDoc) throws IOException{
        Paragraph p;
        Paragraph pKop;
        Cell celInhoud;

        float[] columnWidths = {1,1,1,1,1,1};
 
        Table tabel = new Table(UnitValue.createPercentArray(columnWidths));
        tabel.setWidth(UnitValue.createPercentValue(100)).setFixedLayout();        
              
        tabel.addCell(cell.writeGrayB(6, "INPUTFIELD"));

        tabel.addCell(cell.write(1, "Description"));
        tabel.addCell(cell.write(5, description));

        tabel.addCell(cell.write(1, "Override disc."));
        tabel.addCell(cell.write(5, overruledDescription));

        tabel.addCell(cell.writeGray(3, "Geometry"));
        tabel.addCell(cell.writeGray(3, "Software Tags"));

        tabel.addCell(cell.write(1, "Size"));
        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));
        tabel.addCell(cell.write(1, "Tag Write"));
        tabel.addCell(cell.write(2, tagWrite));
        
        tabel.addCell(cell.write(1, " "));
        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
        tabel.addCell(cell.write(1, "Tag Read"));
        tabel.addCell(cell.write(2, tagRead));

        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.write(1, "Tag Disable"));
        tabel.addCell(cell.write(2, tagDisable));

        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.writeGray(3, "Layout"));

        tabel.addCell(cell.write(1, "Refresh rate"));
        tabel.addCell(cell.write(2, valueRefreshPeriod));
        tabel.addCell(cell.write(1, "Font size"));
        tabel.addCell(cell.write(2, valueFont));

        tabel.addCell(cell.write(1, "Min.input"));
        tabel.addCell(cell.write(2, minInput));
        tabel.addCell(cell.write(1, "Font color"));
        tabel.addCell(cell.write(2, labelColor));

        tabel.addCell(cell.write(1, "Max.input"));
        tabel.addCell(cell.write(2, maxInput));
        tabel.addCell(cell.write(1, "Value font"));
        tabel.addCell(cell.write(2, valueFont));

        tabel.addCell(cell.write(1, "Expected decimals"));
        tabel.addCell(cell.write(2, maxExpectedDecimals));
        tabel.addCell(cell.write(1, "Value color"));
        tabel.addCell(cell.write(2, valueColor));

        tabel.addCell(cell.write(1, "Min. step"));
        tabel.addCell(cell.write(2, smallestStep));
        tabel.addCell(cell.write(1, "Input font"));
        tabel.addCell(cell.write(2, inputFont));

        tabel.addCell(cell.write(1, "Min.step Joystick"));
        tabel.addCell(cell.write(2, smallestStepJoystick));
        tabel.addCell(cell.write(1, "overruled dim."));
        tabel.addCell(cell.write(2, overruledDimension));

        tabel.addCell(cell.write(1, "Number of steps"));
        tabel.addCell(cell.write(2, numberOfSteps));
        tabel.addCell(cell.write(1, "overruled format"));
        tabel.addCell(cell.write(2, overruledFormatter)); 
        
        tabel.addCell(cell.write(1, "Hide on disable"));
        tabel.addCell(cell.write(2, hideOnDisable));
        tabel.addCell(cell.write(1, "Discr. width"));
        tabel.addCell(cell.write(2, descriptionWidth)); 
        
        tabel.addCell(cell.write(1, "Show description"));
        tabel.addCell(cell.write(2, showDescription));
        tabel.addCell(cell.write(1, "Dim. width"));
        tabel.addCell(cell.write(2, dimensionWidth)); 
        
        tabel.addCell(cell.write(1, "Show dimension"));
        tabel.addCell(cell.write(2, showDimension));
        tabel.addCell(cell.write(1, "Alignment"));
        tabel.addCell(cell.write(2, alignment));
                        
        tabel.addCell(cell.writeGray(6, "Actions"));

        tabel.addCell(cell.write(1, "ReferenceTable"));
        tabel.addCell(cell.write(5, referenceTable));

        tabel.addCell(cell.write(1, "Help popup"));
        tabel.addCell(cell.write(5, helpPopup));

        tabel.addCell(cell.write(1, "OnReleaseAction"));
        tabel.addCell(cell.write(5, onReleaseAction));
        
        RichtingString richtingsRegel = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest);
        tabel.addCell(cell.write(1, "Joystick"));
        tabel.addCell(cell.write(5, joyStick + "   " + richtingsRegel.regel));

        tabel.addCell(cell.write(1, "SymbolTag"));
        tabel.addCell(cell.write(5, symbolTag+"    Dx: " + symbolTagDx +"    Dy: "+symbolTagDy));
                
        // Add the cells to the table
        pdfDoc.add(tabel);

        p = new Paragraph().setFontSize(10);
        p.add("\n");
        pdfDoc.add(p);


}

public void readComponentData(Node indicator, boolean showScreenDump) throws IOException{
    if (indicator.hasChildNodes()){

        verzamelingElementen = indicator.getChildNodes();

        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            childNode = verzamelingElementen.item(j);
            
            if (childNode.getNodeType()==childNode.ELEMENT_NODE){
            
                if(childNode.getNodeName().equals("GeneralProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    width=attributeFinder.result("Width");
                    visible=attributeFinder.result("Visible");
                    height=attributeFinder.result("Height");
                    enable=attributeFinder.result("Enable");
                    description=attributeFinder.result("Description");
                    yPos=attributeFinder.result("Y-pos");
                    xPos=attributeFinder.result("X-pos");
                    show=attributeFinder.result("Show");
                    onReleaseAction=attributeFinder.result("OnReleaseAction");
                    joyStick=attributeFinder.result("JoyStick");
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    valueRefreshPeriod=attributeFinder.result("ValueRefreshPeriod");
                    valueFont=attributeFinder.result("ValueFont");
                    valueColor=attributeFinder.result("ValueColor");
                    tagWrite=attributeFinder.result("TagWrite");
                    tagRead=attributeFinder.result("TagRead");
                    tagDisable=attributeFinder.result("TagDisable");
                    smallestStepJoystick=attributeFinder.result("SmallestStepJoystick");
                    smallestStep=attributeFinder.result("SmallestStep");
                    showDimension=attributeFinder.result("ShowDimension");
                    showDescription=attributeFinder.result("ShowDescription");
                    overruledFormatter=attributeFinder.result("OverruledFormatter");
                    overruledDimension=attributeFinder.result("OverruledDimension");
                    overruledDescription=attributeFinder.result("OverruledDescription");
                    numberOfSteps=attributeFinder.result("NumberOfSteps");
                    minInput=attributeFinder.result("MinInput");
                    maxInput=attributeFinder.result("MaxInput");
                    maxExpectedDecimals=attributeFinder.result("MaxExpectedDecimals");
                    labelFont=attributeFinder.result("LabelFont");
                    labelColor=attributeFinder.result("LabelColor");
                    inputFont=attributeFinder.result("InputFont");
                    hideOnDisable=attributeFinder.result("HideOnDisable");
                    dimensionWidth=attributeFinder.result("DimensionWidth");
                    descriptionWidth=attributeFinder.result("DescriptionWidth");
                    alignment=attributeFinder.result("Alignment");

                }

                if(childNode.getNodeName().equals("ComponentProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    symbolTagDy=attributeFinder.result("SymbolTagDy");
                    symbolTagDx=attributeFinder.result("SymbolTagDx");
                    symbolTag=attributeFinder.result("SymbolTag");
                    overruleWest=attributeFinder.result("OverruleWest");
                    overruleSouthWest=attributeFinder.result("OverruleSouthWest");
                    overruleSouthEast=attributeFinder.result("OverruleSouthEast");
                    overruleSouth=attributeFinder.result("OverruleSouth");
                    overruleNorthWest=attributeFinder.result("OverruleNorthWest");
                    overruleNorthEast=attributeFinder.result("OverruleNorthEast");
                    overruleNorth=attributeFinder.result("OverruleNorth");
                    overruleEast=attributeFinder.result("OverruleEast");
                    helpPopup=attributeFinder.result("HelpPopup");
                }
            }
        } 
 
        RichtingString richtingsRegel = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest);
                    if (showScreenDump){
        System.out.println("Joystick overrules \t: "+richtingsRegel.regel); 
                    }
                    }
}

    public void analyse(){
        
    //=================================== Write tags to the tag-tracker database
    
    if (check.isTag(tagWrite)) { VocamAnalyser.reportTags.addTag
                            ("InputField","WRITE", tagWrite, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagRead)) { VocamAnalyser.reportTags.addTag
                            ("InputField","READ", tagRead, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("InputField","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagDisable))  { VocamAnalyser.reportTags.addTag
                            ("InputField","DISA.", tagDisable, "(Pos "+xPos+","+yPos+")"); }
    
 
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}

    if (overruledDescription.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Description of InputField is empty","(Pos "+xPos+","+yPos+")");}
    
           
    }
}
    

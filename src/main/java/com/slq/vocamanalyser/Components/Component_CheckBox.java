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
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.itextpdf.layout.property.UnitValue;
import com.slq.vocamanalyser.AttributeFinder;
import com.slq.vocamanalyser.Check;
import com.slq.vocamanalyser.PfdCell;
import com.slq.vocamanalyser.RichtingString;
import com.slq.vocamanalyser.VocamAnalyser;

/**
 *
 * @author SLQ
 */
public class Component_CheckBox {
 
// Standard variables
    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;
    String joystickOverrules;
//General Properties        
    String width="";
    String visible="";
    String height="";
    String enable="";
    String description="";
    String yPos="";
    String xPos="";
    String onReleaseAction="";
    String joyStick="";
    String show="";
    String referenceTable="";    
//Custom Properties    
    String tagSW="";
    String mode="";
    String tagCW="";
    String tagDisable="";
    String showDescription="";
    String overruledDescription="";
    String labelFont="";
    String labelColor="";
    String activeColor="";

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


    Check check;

public Component_CheckBox() {
    Table  tabel = new Table(6).setBorder(Border.NO_BORDER);  
    cell = new PfdCell(tabel);
    check = new Check();
}

    public void create(Document pdfDoc) throws IOException{
        Paragraph p;
        Paragraph pKop;
        Cell celInhoud;

        float[] columnWidths = {1,1,1,1,1,1};
 
        Table tabel = new Table(UnitValue.createPercentArray(columnWidths));
        tabel.setWidth(UnitValue.createPercentValue(100)).setFixedLayout();        
              
        tabel.addCell(cell.writeGrayB(6, "CHECKBOX"));

        tabel.addCell(cell.write(1, "Description"));
        tabel.addCell(cell.write(5, description));

        tabel.addCell(cell.write(1, "Overruled discr."));
        tabel.addCell(cell.write(5, overruledDescription));

        tabel.addCell(cell.writeGray(3, "Geometry"));
        tabel.addCell(cell.writeGray(3, "Software Tags"));

        tabel.addCell(cell.write(1, "Size"));
        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));
        tabel.addCell(cell.write(1, "TagSW"));
        tabel.addCell(cell.write(2, tagSW));

        tabel.addCell(cell.write(1, "Position"));
        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
        tabel.addCell(cell.write(1, "TagCW"));
        tabel.addCell(cell.write(2, tagCW));

        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Tag disable"));
        tabel.addCell(cell.write(2, tagDisable));
        
        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.writeGray(3, "Layout"));
        
        tabel.addCell(cell.write(1, "Mode"));
        tabel.addCell(cell.write(2, mode));
        tabel.addCell(cell.write(1, "Label font"));
        tabel.addCell(cell.write(2, labelFont));

        tabel.addCell(cell.write(1, "Show Description"));
        tabel.addCell(cell.write(2, showDescription));
        tabel.addCell(cell.write(1, "Label color"));
        tabel.addCell(cell.write(2, labelColor));

        tabel.addCell(cell.write(1, " "));
        tabel.addCell(cell.write(2, ""));
        tabel.addCell(cell.write(1, "Active Color"));
        tabel.addCell(cell.write(2, activeColor));
              
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
                    onReleaseAction=attributeFinder.result("OnReleaseAction");
                    joyStick=attributeFinder.result("JoyStick");
                    show=attributeFinder.result("Show");
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);
                    
                    tagDisable=attributeFinder.result("TagDisable");
                    tagSW=attributeFinder.result("TagSW");
                    mode=attributeFinder.result("Mode");
                    tagCW=attributeFinder.result("TagCW");
                    showDescription=attributeFinder.result("ShowDescription");
                    overruledDescription=attributeFinder.result("OverruledDescription");
                    labelFont=attributeFinder.result("LabelFont");
                    labelColor=attributeFinder.result("LabelColor");
                    activeColor=attributeFinder.result("ActiveColor");
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
    
    if (check.isTag(tagSW)) { VocamAnalyser.reportTags.addTag
                            ("Checkbox","SW", tagSW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagCW)) { VocamAnalyser.reportTags.addTag
                            ("Checkbox","CW", tagCW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagDisable)) { VocamAnalyser.reportTags.addTag
                            ("Checkbox","DIS", tagDisable, "(Pos "+xPos+","+yPos+")"); }

    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Checkbox","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
        
            
    }
}
    

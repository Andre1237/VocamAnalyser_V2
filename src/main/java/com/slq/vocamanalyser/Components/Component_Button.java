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
public class Component_Button {
    
// Standard variables
    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;
    String joystickOverrules;
    Check check;
//General Properties        
    String visible="";
    String enable="";
    String description="";
    String yPos="";
    String xPos="";
    String width="";
    String show="";
    String referenceTable="";
    String onReleaseAction="";
    String joyStick="";
    String height="";
    String useStates="";
    String useImage="";
    String tagValue="";
    String tagStates="";
    String tagDisabled="";
    String tagCW="";
    String states="";
    String normalImage="";
    String label="";
    String fontSize="";
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


public Component_Button() {
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
              
        tabel.addCell(cell.writeGrayB(6, "Button"));

 /*GP*/       tabel.addCell(cell.write(1, "Description"));
 /*GP*/       tabel.addCell(cell.write(5, description));

/*ST*/        tabel.addCell(cell.writeGray(3, "Geometry"));
/*ST*/        tabel.addCell(cell.writeGray(3, "Software Tags"));

/*GP*/        tabel.addCell(cell.write(1, "Size"));
/*GP*/        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));

              tabel.addCell(cell.write(1, "TagCW"));
              tabel.addCell(cell.write(2, tagCW));

/*GP*/        tabel.addCell(cell.write(1, "Position"));
/*GP*/        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
              tabel.addCell(cell.write(1, "TagDisabled"));
              tabel.addCell(cell.write(2, tagDisabled));

/*ST*/        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
              tabel.addCell(cell.write(1, "TagValue"));
              tabel.addCell(cell.write(2, tagValue));

/*GP*/        tabel.addCell(cell.write(1, "Enable"));
/*GP*/        tabel.addCell(cell.write(2, enable));
/*GP*/        tabel.addCell(cell.write(1, "Show"));
/*GP*/        tabel.addCell(cell.write(2, show));
        
/*GP*/        tabel.addCell(cell.write(1, "Visible"));
/*GP*/        tabel.addCell(cell.write(2, visible));
              tabel.addCell(cell.write(1, "TagStates"));
              tabel.addCell(cell.write(2, tagStates));
        
              tabel.addCell(cell.write(1, "UseImage"));
              tabel.addCell(cell.write(2, useImage));
/*ST*/        tabel.addCell(cell.writeGray(3, "Layout"));

              tabel.addCell(cell.write(1, "NormalImage"));
              tabel.addCell(cell.write(2, normalImage));
              tabel.addCell(cell.write(1, "FontSize"));
              tabel.addCell(cell.write(2, fontSize));

              tabel.addCell(cell.write(1, "Label"));
              tabel.addCell(cell.write(2, label));
              tabel.addCell(cell.write(1, " "));
              tabel.addCell(cell.write(2, ""));
              
/*ST*/        tabel.addCell(cell.writeGray(6, "Actions"));
              tabel.addCell(cell.write(1, "States"));
              tabel.addCell(cell.write(5, states));

/*GP*/        tabel.addCell(cell.write(1, "ReferenceTable"));
/*GP*/        tabel.addCell(cell.write(5, referenceTable));

/*CP*/        tabel.addCell(cell.write(1, "Help popup"));
/*CP*/        tabel.addCell(cell.write(5, helpPopup));

/*GP*/        tabel.addCell(cell.write(1, "OnReleaseAction"));
/*GP*/        tabel.addCell(cell.write(5, onReleaseAction));
        
/*CP*/        RichtingString richtingsRegel = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest);
/*CP*/        tabel.addCell(cell.write(1, "Joystick"));
/*CP*/        tabel.addCell(cell.write(5, joyStick + "   " + richtingsRegel.regel));

/*CP*/        tabel.addCell(cell.write(1, "SymbolTag"));
/*CP*/        tabel.addCell(cell.write(5, symbolTag+"    Dx: " + symbolTagDx +"    Dy: "+symbolTagDy));
                
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
                    
                    visible=attributeFinder.result("Visible");
                    enable=attributeFinder.result("Enable");
                    description=attributeFinder.result("Description");
                    yPos=attributeFinder.result("Y-pos");
                    xPos=attributeFinder.result("X-pos");
                    width=attributeFinder.result("Width");
                    show=attributeFinder.result("Show");
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                    onReleaseAction=attributeFinder.result("OnReleaseAction");
                    joyStick=attributeFinder.result("JoyStick");
                    height=attributeFinder.result("Height");
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);
                    
                    useStates=attributeFinder.result("UseStates").replaceAll("[\\r\\n\\t]", "");
                    useImage=attributeFinder.result("UseImage");
                    tagValue=attributeFinder.result("TagValue");
                    tagStates=attributeFinder.result("TagStates");
                    tagDisabled=attributeFinder.result("TagDisabled");
                    tagCW=attributeFinder.result("TagCW");
                    states=attributeFinder.result("States").replaceAll("[\\r\\n\\t]", "");
                    normalImage=attributeFinder.result("NormalImage");
                    label=attributeFinder.result("Label");
                    fontSize=attributeFinder.result("FontSize");
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
    
    if (check.isTag(tagValue)) { VocamAnalyser.reportTags.addTag
                            ("Button","SW", tagValue, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagCW)) { VocamAnalyser.reportTags.addTag
                            ("Button","CW", tagCW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagDisabled)) { VocamAnalyser.reportTags.addTag
                            ("Button","DIS", tagDisabled, "(Pos "+xPos+","+yPos+")"); }

    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Button","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagStates))  { VocamAnalyser.reportTags.addTag
                            ("Button","MISC", tagStates, "(Pos "+xPos+","+yPos+")"); }
    
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
        
            
    }
}
    

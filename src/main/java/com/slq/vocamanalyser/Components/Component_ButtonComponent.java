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
public class Component_ButtonComponent {
    Node childNode;
    NodeList verzamelingElementen;
    AttributeFinder attributeFinder;
    PfdCell cell;
    Check check;

    String description="";
    String enable="";
    String height="";
    String joystick="";
    String onReleaseAction="";
    String visible="";
    String width="";
    String xPos="";
    String yPos="";
    String referenceTable="";    
    String show="";
    
    String action="";
    String activeColor="";
    String fontSize="";
    String inactiveColor="";
    String overrideLabel="";
    String tagBlink="";
    String tagCW="";
    String tagDisabled="";
    String tagFault="";
    String tagSW="";
    String type="";

    String helppopup="";
    String overruleeast="";
    String overrulenorth="";
    String overrulenortheast="";
    String overrulenorthwest="";
    String overrulesouth="";
    String overrulesoutheast="";
    String overrulesouthwest="";
    String overrulewest="";
    String symboltag="";
    String symboltagdx="";
    String symboltagdy="";
        
public Component_ButtonComponent() {
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
              
        tabel.addCell(cell.writeGrayB(6, "BUTTON"));

        tabel.addCell(cell.write(1, "Description"));
        tabel.addCell(cell.write(5, description));

        tabel.addCell(cell.write(1, "Override Label"));
        tabel.addCell(cell.write(5, overrideLabel));

        tabel.addCell(cell.writeGray(3, "Geometry"));
        tabel.addCell(cell.writeGray(3, "Software Tags"));

        tabel.addCell(cell.write(1, "Size"));
        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));
        tabel.addCell(cell.write(1, "TagSW"));
        tabel.addCell(cell.write(2, tagSW));

        tabel.addCell(cell.write(1, " "));
        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
        tabel.addCell(cell.write(1, "TagCW"));
        tabel.addCell(cell.write(2, tagCW));

        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Disabled"));
        tabel.addCell(cell.write(2, tagDisabled));

        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.write(1, "Blink"));
        tabel.addCell(cell.write(2, tagBlink));

        tabel.addCell(cell.write(1, "Type"));
        tabel.addCell(cell.write(2, type));
        tabel.addCell(cell.write(1, "Fault"));
        tabel.addCell(cell.write(2, tagFault));

        tabel.addCell(cell.write(1, "Action"));
        tabel.addCell(cell.write(2, action));
        tabel.addCell(cell.writeGray(3, "Layout"));
        

        tabel.addCell(cell.write(1, "Label font"));
        tabel.addCell(cell.write(2, fontSize));
        tabel.addCell(cell.write(1, "Active color"));
        tabel.addCell(cell.write(2, activeColor));
        
        tabel.addCell(cell.write(1, ""));
        tabel.addCell(cell.write(2, ""));
        tabel.addCell(cell.write(1, "Inactive color"));
        tabel.addCell(cell.write(2, inactiveColor));
                        
        tabel.addCell(cell.writeGray(6, "Actions"));

        tabel.addCell(cell.write(1, "ReferenceTable"));
        tabel.addCell(cell.write(5, referenceTable));

        tabel.addCell(cell.write(1, "Help popup"));
        tabel.addCell(cell.write(5, helppopup));

        tabel.addCell(cell.write(1, "OnReleaseAction"));
        tabel.addCell(cell.write(5, onReleaseAction));
        
        RichtingString richtingsRegel = new RichtingString(overrulenorth,overrulenortheast,overruleeast,overrulesoutheast,overrulesouth,overrulesouthwest,overrulewest,overrulenorthwest);
        tabel.addCell(cell.write(1, "Joystick"));
        tabel.addCell(cell.write(5, joystick + "   " + richtingsRegel.regel));

        tabel.addCell(cell.write(1, "SymbolTag"));
        tabel.addCell(cell.write(5, symboltag+"    Dx: " + symboltagdx +"    Dy: "+symboltagdy));
        
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

                    attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);
                    
                    description=attributeFinder.result("Description"); 
                    enable=attributeFinder.result("Enable"); 
                    height=attributeFinder.result("Height"); 
                    joystick=attributeFinder.result("JoyStick"); 
                    onReleaseAction=attributeFinder.result("OnReleaseAction"); 
                    visible=attributeFinder.result("Visible"); 
                    width=attributeFinder.result("Width"); 
                    show=attributeFinder.result("Show");
                    xPos=attributeFinder.result("X-pos"); 
                    yPos=attributeFinder.result("Y-pos"); 
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    action=attributeFinder.result("Action"); 
                    activeColor=attributeFinder.result("ActiveColor"); 
                    fontSize=attributeFinder.result("FontSize"); 
                    inactiveColor=attributeFinder.result("InactiveColor"); 
                    overrideLabel=attributeFinder.result("OverrideLabel"); 
                    tagBlink=attributeFinder.result("TagBlink"); 
                    tagCW=attributeFinder.result("TagCW"); 
                    tagDisabled=attributeFinder.result("TagDisabled"); 
                    tagFault=attributeFinder.result("TagFault"); 
                    tagSW=attributeFinder.result("TagSW"); 
                    type=attributeFinder.result("Type"); 

                }

                if(childNode.getNodeName().equals("ComponentProperties")){

                    //AttributeFinder 
                    attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    helppopup=attributeFinder.result("HelpPopup"); 
                    overruleeast=attributeFinder.result("OverruleEast"); 
                    overrulenorth=attributeFinder.result("OverruleNorth"); 
                    overrulenortheast=attributeFinder.result("OverruleNorthEast"); 
                    overrulenorthwest=attributeFinder.result("OverruleNorthWest"); 
                    overrulesouth=attributeFinder.result("OverruleSouth"); 
                    overrulesoutheast=attributeFinder.result("OverruleSouthEast"); 
                    overrulesouthwest=attributeFinder.result("OverruleSouthWest"); 
                    overrulewest=attributeFinder.result("OverruleWest"); 
                    symboltag=attributeFinder.result("SymbolTag"); 
                    symboltagdx=attributeFinder.result("SymbolTagDx"); 
                    symboltagdy=attributeFinder.result("SymbolTagDy"); 

                }
            }
        } 
 
        RichtingString richtingsRegel = new RichtingString(overrulenorth,overrulenortheast,overruleeast,overrulesoutheast,overrulesouth,overrulesouthwest,overrulewest,overrulenorthwest);
        if (showScreenDump){
            System.out.println("Joystick overrules \t: "+richtingsRegel.regel); 
        }
    }
}

    public void analyse(){
        
    //=================================== Write tags to the tag-tracker database
    
    if (check.isTag(tagSW)) { VocamAnalyser.reportTags.addTag
                            ("Button","SW", tagSW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagCW)) { VocamAnalyser.reportTags.addTag
                            ("Button","CW", tagCW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Button","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagDisabled))  { VocamAnalyser.reportTags.addTag
                            ("Button","DIS", tagDisabled, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagBlink))  { VocamAnalyser.reportTags.addTag
                            ("Button","BLK", tagBlink, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagFault))  { VocamAnalyser.reportTags.addTag
                            ("Button","FLT", tagFault, "(Pos "+xPos+","+yPos+")"); }
    
 
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
        
    if (activeColor.equals(inactiveColor)){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Active and inactive state have the same color","(Pos "+xPos+","+yPos+")");}

    //================================== Write messages to the pagelink database
 
    if (!onReleaseAction.isEmpty()){ VocamAnalyser.reportLink.addTag
                            ("ButtonComp.","(Pos "+xPos+","+yPos+")",onReleaseAction);
    }

    }
}
    

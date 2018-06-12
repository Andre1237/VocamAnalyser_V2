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
public class Component_Indicator {

    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;

    String joystickOverrules;
        
    String description="";
    String enable="";
    String height="";
    String joyStick="";
    String onReleaseAction="";
    String visible="";
    String width="";
    String show="";
    String xPos="";
    String yPos="";
    String xPosM="";
    String yPosM="";
    String referenceTable="";    
    
    String colorActive=""; 
    String colorInActive="";
    String labelColor=""; 
    String labelFont="";
    String mode=""; 
    String overruledDescription=""; 
    String rotation=""; 
    String shape="";     
    String showDescription=""; 
    String tagCW="";     
    String tagSW="";
    String tagSWBitSplit=""; 
    
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

    Check check;

public Component_Indicator() {
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
              
        tabel.addCell(cell.writeGrayB(6, "INDICATOR"));

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

        tabel.addCell(cell.write(1, "Rotation"));
        tabel.addCell(cell.write(2, rotation));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.writeGray(3, "Layout"));
        
        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Color False/True"));
        tabel.addCell(cell.write(2, colorInActive+" / "+colorActive));
        
        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.write(1, "Label font"));
        tabel.addCell(cell.write(2, labelFont));

        tabel.addCell(cell.write(1, "Mode"));
        tabel.addCell(cell.write(2, mode));
        tabel.addCell(cell.write(1, "Label color"));
        tabel.addCell(cell.write(2, labelColor));

        tabel.addCell(cell.write(1, "Show Description"));
        tabel.addCell(cell.write(2, showDescription));
        tabel.addCell(cell.write(1, "Shape"));
        tabel.addCell(cell.write(2, shape));
                        
        tabel.addCell(cell.writeGray(6, "Actions"));

        tabel.addCell(cell.write(1, "ReferenceTable"));
        tabel.addCell(cell.write(5, referenceTable));

        tabel.addCell(cell.write(1, "Help popup"));
        tabel.addCell(cell.write(5, helppopup));

        tabel.addCell(cell.write(1, "OnReleaseAction"));
        tabel.addCell(cell.write(5, onReleaseAction));
        
        RichtingString richtingsRegel = new RichtingString(overrulenorth,overrulenortheast,overruleeast,overrulesoutheast,overrulesouth,overrulesouthwest,overrulewest,overrulenorthwest);
        tabel.addCell(cell.write(1, "Joystick"));
        tabel.addCell(cell.write(5, joyStick + "   " + richtingsRegel.regel));

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

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    description=attributeFinder.result("Description");
                    enable=attributeFinder.result("Enable");
                    height=attributeFinder.result("Height");
                    joyStick=attributeFinder.result("JoyStick");
                    onReleaseAction=attributeFinder.result("OnReleaseAction");
                    visible=attributeFinder.result("Visible");
                    width=attributeFinder.result("Width");
                    show=attributeFinder.result("Show");
                    xPos=attributeFinder.result("X-pos");
                    yPos=attributeFinder.result("Y-pos");
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    colorActive=attributeFinder.result("ColorActive"); 
                    colorInActive=attributeFinder.result("ColorInActive");
                    labelColor=attributeFinder.result("LabelColor"); 
                    labelFont=attributeFinder.result("LabelFont");
                    mode=attributeFinder.result("Mode"); 
                    overruledDescription=attributeFinder.result("OverruledDescription"); 
                    rotation=attributeFinder.result("Rotation"); 
                    shape=attributeFinder.result("Shape");     
                    showDescription=attributeFinder.result("ShowDescription"); 
                    tagCW=attributeFinder.result("TagCW");     
                    tagSW=attributeFinder.result("TagSW");
                    tagSWBitSplit=attributeFinder.result("TagSWBitSplit"); 
                }

                if(childNode.getNodeName().equals("ComponentProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
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
                            ("Indicator","SW", tagSW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagCW)) { VocamAnalyser.reportTags.addTag
                            ("Indicator","CW", tagCW, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Indicator","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
        
    if (colorActive.equals(colorInActive)){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Active and inactive state have the same color","(Pos "+xPos+","+yPos+")");}
            
    }
}
    

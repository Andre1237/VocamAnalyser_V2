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
public class Component_Line {

    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;
    Check check;

    String description="";
    String enable="";
    String height="";
    String joystick="";
    String onReleaseAction="";
    String visible="";
    String width="";
    String show="";
    String xPos="";
    String yPos="";
    String referenceTable="";    

    String colorActive="";
    String conditional="";
    String direction="";
    String lineSize="";
    String mirror="";
    String mode="";
    String offset="";
    String showArrow="";
    String tagSW="";

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

        
public Component_Line() {
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
              
        tabel.addCell(cell.writeGrayB(6, "LINE"));

        tabel.addCell(cell.write(1, "Description"));
        tabel.addCell(cell.write(5, description));
        
        tabel.addCell(cell.writeGray(3, "Geometry"));
        tabel.addCell(cell.writeGray(3, "Software Tags"));

        tabel.addCell(cell.write(1, "Size"));
        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));
        tabel.addCell(cell.write(1, "TagSW"));
        tabel.addCell(cell.write(2, tagSW));

        tabel.addCell(cell.write(1, "Position"));
        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.writeGray(3, "Layout"));
        
        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Color"));
        tabel.addCell(cell.write(2, colorActive));
        
        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.write(1, "LineSize"));
        tabel.addCell(cell.write(2, lineSize));

        tabel.addCell(cell.write(1, "Mode"));
        tabel.addCell(cell.write(2, mode));
        tabel.addCell(cell.write(1, "Mirror"));
        tabel.addCell(cell.write(2, mirror));

        tabel.addCell(cell.write(1, "Offset"));
        tabel.addCell(cell.write(2, offset));
        tabel.addCell(cell.write(1, "Show Arrow"));
        tabel.addCell(cell.write(2, showArrow));

        tabel.addCell(cell.writeGray(6, "Actions"));

        tabel.addCell(cell.write(1, "ReferenceTable"));
        tabel.addCell(cell.write(5, referenceTable));

        tabel.addCell(cell.write(1, "Help popup"));
        tabel.addCell(cell.write(5, helppopup));

        tabel.addCell(cell.write(1, "Conditional colors"));
        tabel.addCell(cell.write(5, conditional));
 
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

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    description=attributeFinder.result("Description"); 
                    enable=attributeFinder.result("Enable"); 
                    height=attributeFinder.result("Height"); 
                    joystick=attributeFinder.result("JoyStick"); 
                    onReleaseAction=attributeFinder.result("OnReleaseAction"); 
                    visible=attributeFinder.result("Visible"); 
                    width=attributeFinder.result("Width"); 
                    xPos=attributeFinder.result("X-pos"); 
                    yPos=attributeFinder.result("Y-pos"); 
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");

                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    colorActive=attributeFinder.result("ColorActive"); 
                    conditional=attributeFinder.result("Conditional"); 
                    direction=attributeFinder.result("Direction"); 
                    lineSize=attributeFinder.result("LineSize"); 
                    mirror=attributeFinder.result("Mirror"); 
                    mode=attributeFinder.result("Mode"); 
                    offset=attributeFinder.result("Offset"); 
                    showArrow=attributeFinder.result("ShowArrow"); 
                    tagSW=attributeFinder.result("TagSW"); 

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
                            ("Line","SW", tagSW, "(Pos "+xPos+","+yPos+")"); }
    
    
    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Line","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    //=================================== Write messages to the message database
 
    if (description.isEmpty()){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Component has no description text added","(Pos "+xPos+","+yPos+")");}
        
            
    }
}
    
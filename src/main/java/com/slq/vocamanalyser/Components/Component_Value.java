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
public class Component_Value {

    Node childNode;
    NodeList verzamelingElementen;
    PfdCell cell;

//    String exportRegel="";

    String description="";
    String enable="";
    String height="";
    String joystick="";
    String onReleaseAction="";
    String visible="";
    String width="";
    String xPos="";
    String yPos="";
    String show="";
    String referenceTable="";    
    
    String alignment="";
    String colortable="";
    String descriptionwidth="";
    String dimensionwidth="";
    String drawbottomborder="";
    String inactivetag="";
    String indexnumber="";
    String indextag="";
    String labelcolor="";
    String labelfont="";
    String maxexpecteddecimals="";
    String overruledDescription="";
    String overruleddimension="";
    String overruledformatter="";
    String showDescription="";
    String showdimension="";
    String tag="";
    String tagcolor="";
    String valuecolor="";
    String valuefont="";
    String valuehoralignment="";
    String valuerefreshperiod="";
    String valuetype="";
    String vertalign="";

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

    
public Component_Value() {
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
              
        tabel.addCell(cell.writeGrayB(6, "VALUE"));

        tabel.addCell(cell.write(1, "Description"));
        tabel.addCell(cell.write(5, description));

        tabel.addCell(cell.write(1, "Overruled discr."));
        tabel.addCell(cell.write(5, overruledDescription));

        tabel.addCell(cell.write(1, "Overruled format"));
        tabel.addCell(cell.write(5, overruledformatter + " " + overruleddimension));


        tabel.addCell(cell.writeGray(3, "Geometry"));
        tabel.addCell(cell.writeGray(3, "Software Tags"));

        tabel.addCell(cell.write(1, "Size"));
        tabel.addCell(cell.write(2, width+" x "+height+"  (Width x Height)"));
        tabel.addCell(cell.write(1, "Value"));
        tabel.addCell(cell.write(2, tag));

        tabel.addCell(cell.write(1, "Position"));
        tabel.addCell(cell.write(2, xPos+" , "+yPos+"  (X,Y)"));
        tabel.addCell(cell.write(1, "Inactive tag"));
        tabel.addCell(cell.write(2, inactivetag));

        tabel.addCell(cell.write(1, "Description width"));
        tabel.addCell(cell.write(2, descriptionwidth));
        tabel.addCell(cell.write(1, "Show"));
        tabel.addCell(cell.write(2, show));

        tabel.addCell(cell.write(1, "Dimension width"));
        tabel.addCell(cell.write(2, dimensionwidth));
        tabel.addCell(cell.write(1, "Color Index"));
        tabel.addCell(cell.write(2, tagcolor));

        
        tabel.addCell(cell.writeGray(3, "Access/Miscilanious"));
        tabel.addCell(cell.write(1, "Index Number"));
        tabel.addCell(cell.write(2, indexnumber));
        

        tabel.addCell(cell.write(1, "Enable"));
        tabel.addCell(cell.write(2, enable));
        tabel.addCell(cell.write(1, "Index tag"));
        tabel.addCell(cell.write(2, indextag));
        
        tabel.addCell(cell.write(1, "Visible"));
        tabel.addCell(cell.write(2, visible));
        tabel.addCell(cell.writeGray(3, "Value lay out"));


        tabel.addCell(cell.write(1, "Show Description"));
        tabel.addCell(cell.write(2, showDescription));
        tabel.addCell(cell.write(1, "Font size"));
        tabel.addCell(cell.write(2, valuefont));

        tabel.addCell(cell.write(1, "Show dimension"));
        tabel.addCell(cell.write(2, showdimension));
        tabel.addCell(cell.write(1, "Value color"));
        tabel.addCell(cell.write(2, valuecolor));

        tabel.addCell(cell.write(1, "Value type"));
        tabel.addCell(cell.write(2, valuetype));
        tabel.addCell(cell.write(1, "Alignment"));
        tabel.addCell(cell.write(2, alignment));

        tabel.addCell(cell.write(1, "Bottom border"));
        tabel.addCell(cell.write(2, drawbottomborder));
        tabel.addCell(cell.write(1, "Alignment H"));
        tabel.addCell(cell.write(2, valuehoralignment));
    
        tabel.addCell(cell.write(1, "Max decimals"));
        tabel.addCell(cell.write(2, maxexpecteddecimals));
        tabel.addCell(cell.write(1, "Alignment V"));
        tabel.addCell(cell.write(2, vertalign));

        tabel.addCell(cell.write(1, "Refresh rate"));
        tabel.addCell(cell.write(2, valuerefreshperiod));
        tabel.addCell(cell.write(1, " "));
        tabel.addCell(cell.write(2, " "));

        tabel.addCell(cell.writeGray(6, "Actions"));

        tabel.addCell(cell.write(1, "ReferenceTable"));
        tabel.addCell(cell.write(5, referenceTable));

        tabel.addCell(cell.write(1, "Help popup"));
        tabel.addCell(cell.write(5, helppopup));

        tabel.addCell(cell.write(1, "OnReleaseAction"));
        tabel.addCell(cell.write(5, onReleaseAction));

        tabel.addCell(cell.write(1, "Color table"));
        tabel.addCell(cell.write(5, colortable));

        
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
                    show=attributeFinder.result("Show"); 
                    xPos=attributeFinder.result("X-pos"); 
                    yPos=attributeFinder.result("Y-pos"); 
                    referenceTable=attributeFinder.result("ReferenceTable").replaceAll("[\\r\\n\\t]", "");
                    
                }

                if(childNode.getNodeName().equals("CustomProperties")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode,false); 
                    //attributeFinder.setShow(true);

                    alignment=attributeFinder.result("Alignment"); 
                    colortable=attributeFinder.result("ColorTable").replaceAll("[\\r\\n\\t]", ""); 
                    descriptionwidth=attributeFinder.result("DescriptionWidth"); 
                    dimensionwidth=attributeFinder.result("DimensionWidth"); 
                    drawbottomborder=attributeFinder.result("DrawBottomBorder"); 
                    inactivetag=attributeFinder.result("InActiveTag"); 
                    indexnumber=attributeFinder.result("IndexNumber"); 
                    indextag=attributeFinder.result("IndexTag"); 
                    labelcolor=attributeFinder.result("LabelColor"); 
                    labelfont=attributeFinder.result("LabelFont"); 
                    maxexpecteddecimals=attributeFinder.result("MaxExpectedDecimals"); 
                    overruledDescription=attributeFinder.result("OverruledDescription"); 
                    overruleddimension=attributeFinder.result("OverruledDimension"); 
                    overruledformatter=attributeFinder.result("OverruledFormatter"); 
                    showDescription=attributeFinder.result("ShowDescription"); 
                    showdimension=attributeFinder.result("ShowDimension"); 
                    tag=attributeFinder.result("Tag"); 
                    tagcolor=attributeFinder.result("TagColor"); 
                    valuecolor=attributeFinder.result("ValueColor"); 
                    valuefont=attributeFinder.result("ValueFont"); 
                    valuehoralignment=attributeFinder.result("ValueHorAlignment"); 
                    valuerefreshperiod=attributeFinder.result("ValueRefreshPeriod"); 
                    valuetype=attributeFinder.result("ValueType"); 
                    vertalign=attributeFinder.result("VertAlign"); 

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
    
    if (check.isTag(tag)) { VocamAnalyser.reportTags.addTag
                            ("Value","SW",tag , "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(inactivetag)) { VocamAnalyser.reportTags.addTag
                            ("Value","MISC", inactivetag, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(show))  { VocamAnalyser.reportTags.addTag
                            ("Value","SHOW", show, "(Pos "+xPos+","+yPos+")"); }
    
    if (check.isTag(tagcolor))  { VocamAnalyser.reportTags.addTag
                            ("Value","SHOW", tagcolor, "(Pos "+xPos+","+yPos+")"); }
   
    if (check.isTag(indexnumber))  { VocamAnalyser.reportTags.addTag
                            ("Value","INDX", indexnumber, "(Pos "+xPos+","+yPos+")"); }
   
    if (check.isTag(indextag))  { VocamAnalyser.reportTags.addTag
                            ("Value","INDX", indextag, "(Pos "+xPos+","+yPos+")"); }
        
     //=================================== Write messages to the message database
 
    if ((showDescription.equals("true"))&&(description.isEmpty())){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Description is selecteed but no text is entered","(Pos "+xPos+","+yPos+")");}
        
    if ((showdimension.equals("true"))&&(overruleddimension.isEmpty())){ VocamAnalyser.reportMessages.addMsg
                            ("Warning","Description is selecteed but no text is entered","(Pos "+xPos+","+yPos+")");}
      
        
    }
    
}


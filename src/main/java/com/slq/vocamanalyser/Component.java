/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;

import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author SLQ
 */
public class Component {
    //================================================================ constants
   
    //=================================================================== fields
    // Standard variables
    public static boolean showOutputToScreen;

    public String componentType="";
    public String componentID="";
    public String directionOverule;
    
    // General properties
    public String visible="";
    public String enable="";
    public String description="";
    public String yPos="";
    public String xPos="";
    public String width="";
    public String show="";
    public String referenceTable="";
    public String onReleaseAction="";
    public String joyStick="";
    public String height="";
    
    //Custom Properties
    public String action="";
    public String activeColor="";
    public String alignment="";
    public String autoLineWrap="";
    public String bordercolor="";
    public String borderlinewidth="";
    public String color="";
    public String colorActive="";
    public String colorInActive="";
    public String colortable="";
    public String colorTable="";
    public String conditional="";
    public String descriptionwidth="";
    public String descriptionWidth="";
    public String dimensionwidth="";
    public String dimensionWidth="";
    public String direction="";
    public String doFill="";
    public String drawbottomborder="";
    public String drawBottomBorder="";
    public String fillcolor="";
    public String font="";
    public String fontSize="";
    public String hideOnDisable="";
    public String horAlign="";
    public String inactiveColor="";
    public String inactivetag="";
    public String indexnumber="";
    public String indextag="";
    public String inputFont="";
    public String label="";
    public String labelcolor="";
    public String labelColor="";
    public String labelfont="";
    public String labelFont="";
    public String lineSize="";
    public String link="";
    public String maxexpecteddecimals="";
    public String maxExpectedDecimals="";
    public String maxInput="";
    public String minInput="";
    public String mirror="";
    public String mode="";
    public String normalImage="";
    public String numberOfSteps="";
    public String offset="";
    public String overrideLabel="";
    public String overruledDescription="";
    public String overruleddimension="";
    public String overruledDimension="";
    public String overruledformatter="";
    public String overruledFormatter="";
    public String rotation="";
    public String shape="";     
    public String showArrow="";
    public String showDescription="";
    public String showdimension="";
    public String showDimension="";
    public String smallestStep="";
    public String smallestStepJoystick="";
    public String states="";
    public String tag="";
    public String tagBlink="";
    public String tagcolor="";
    public String tagColor="";
    public String tagCW="";
    public String tagDisable="";
    public String tagDisabled="";
    public String tagFault="";
    public String tagRead="";
    public String tagStates="";
    public String tagSW="";
    public String tagValue="";
    public String tagWrite="";
    public String type="";
    public String useImage="";
    public String useStates="";
    public String valuecolor="";
    public String valueColor="";
    public String valuefont="";
    public String valueFont="";
    public String valuehoralignment="";
    public String valuerefreshperiod="";
    public String valueRefreshPeriod="";
    public String valuetype="";
    public String vertalign="";
    public String vertAlign="";
    
    //Component Properties 
    public String symbolTagDy="";
    public String symbolTagDx="";
    public String symbolTag="";
    public String overruleWest="";
    public String overruleSouthWest="";
    public String overruleSouthEast="";
    public String overruleSouth="";
    public String overruleNorthWest="";
    public String overruleNorthEast="";
    public String overruleNorth="";
    public String overruleEast="";
    public String helpPopup="";
    
    //============================================================== constructor 
public Component(){
    showOutputToScreen = false;
}

/**
 * Method to display the raw values (read in from the XML file) to the 
 * standard output screen.
 * 
 * @param showRawValues true show the values 
 */
public void seeRawValues(boolean showRawValues){
    showOutputToScreen = showRawValues;
}

/**
 * Method that read in the XML file
 * @param component
 * @throws IOException 
 */
public void get(Node component) throws IOException{
    
    AttributeFinder attributeFinder;
    
    // find the component type mentioned in the attribute 'Title'
    attributeFinder = new AttributeFinder(component); 

    this.componentType = attributeFinder.result("Title");
    
    if (component.hasChildNodes()){

        NodeList verzamelingElementen = component.getChildNodes();

        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            Node childNode = verzamelingElementen.item(j);
            
            if (childNode.getNodeType()==childNode.ELEMENT_NODE){
            
                if(childNode.getNodeName().equals("GeneralProperties")){

                    attributeFinder = new AttributeFinder(childNode); 
                    
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

                    attributeFinder = new AttributeFinder(childNode); 
 //                   attributeFinder.setShow(true);
                    
                    action=attributeFinder.result("Action"); 
                    activeColor=attributeFinder.result("ActiveColor"); 
                    alignment=attributeFinder.result("Alignment"); 
                    autoLineWrap=attributeFinder.result("AutoLineWrap"); 
                    bordercolor=attributeFinder.result("BorderColor"); 
                    borderlinewidth=attributeFinder.result("BorderLineWidth"); 
                    color=attributeFinder.result("Color"); 
                    colorActive=attributeFinder.result("ColorActive");
                    colorInActive=attributeFinder.result("ColorInActive");
                    colortable=attributeFinder.result("ColorTable").replaceAll("[\\r\\n\\t]", ""); 
                    colorTable=attributeFinder.result("ColorTable").replaceAll("[\\r\\n\\t]", ""); 
                    conditional=attributeFinder.result("Conditional").replaceAll("[\\r\\n\\t]", ""); 
                    descriptionWidth=attributeFinder.result("DescriptionWidth");
                    descriptionwidth=attributeFinder.result("DescriptionWidth"); 
                    dimensionWidth=attributeFinder.result("DimensionWidth");
                    dimensionwidth=attributeFinder.result("DimensionWidth"); 
                    direction=attributeFinder.result("Direction"); 
                    doFill=attributeFinder.result("DoFill"); 
                    drawbottomborder=attributeFinder.result("DrawBottomBorder"); 
                    drawBottomBorder=attributeFinder.result("DrawBottomBorder"); 
                    fillcolor=attributeFinder.result("FillColor"); 
                    font=attributeFinder.result("Font"); 
                    fontSize=attributeFinder.result("FontSize");
                    hideOnDisable=attributeFinder.result("HideOnDisable");
                    horAlign=attributeFinder.result("HorAlign"); 
                    inactiveColor=attributeFinder.result("InactiveColor"); 
                    inactivetag=attributeFinder.result("InActiveTag"); 
                    indexnumber=attributeFinder.result("IndexNumber"); 
                    indextag=attributeFinder.result("IndexTag"); 
                    inputFont=attributeFinder.result("InputFont");
                    label=attributeFinder.result("Label");
                    labelColor=attributeFinder.result("LabelColor");
                    labelcolor=attributeFinder.result("LabelColor"); 
                    labelFont=attributeFinder.result("LabelFont");
                    labelfont=attributeFinder.result("LabelFont"); 
                    lineSize=attributeFinder.result("LineSize"); 
                    link=attributeFinder.result("Link"); 
                    maxExpectedDecimals=attributeFinder.result("MaxExpectedDecimals");
                    maxexpecteddecimals=attributeFinder.result("MaxExpectedDecimals"); 
                    maxInput=attributeFinder.result("MaxInput");
                    minInput=attributeFinder.result("MinInput");
                    mirror=attributeFinder.result("Mirror"); 
                    mode=attributeFinder.result("Mode"); 
                    normalImage=attributeFinder.result("NormalImage");
                    numberOfSteps=attributeFinder.result("NumberOfSteps");
                    offset=attributeFinder.result("Offset"); 
                    overrideLabel=attributeFinder.result("OverrideLabel"); 
                    overruledDescription=attributeFinder.result("OverruledDescription"); 
                    overruledDimension=attributeFinder.result("OverruledDimension");
                    overruleddimension=attributeFinder.result("OverruledDimension"); 
                    overruledFormatter=attributeFinder.result("OverruledFormatter");
                    overruledformatter=attributeFinder.result("OverruledFormatter"); 
                    rotation=attributeFinder.result("Rotation"); 
                    shape=attributeFinder.result("Shape");   
                    showArrow=attributeFinder.result("ShowArrow"); 
                    showDescription=attributeFinder.result("ShowDescription"); 
                    showDimension=attributeFinder.result("ShowDimension");
                    showdimension=attributeFinder.result("ShowDimension"); 
                    smallestStep=attributeFinder.result("SmallestStep");
                    smallestStepJoystick=attributeFinder.result("SmallestStepJoystick");
                    states=attributeFinder.result("States").replaceAll("[\\r\\n\\t]", "");
                    tag=attributeFinder.result("Tag"); 
                    tagBlink=attributeFinder.result("TagBlink"); 
                    tagcolor=attributeFinder.result("TagColor"); 
                    tagColor=attributeFinder.result("TagColor"); 
                    tagCW=attributeFinder.result("TagCW");
                    tagDisable=attributeFinder.result("TagDisable");
                    tagDisabled=attributeFinder.result("TagDisabled");
                    tagFault=attributeFinder.result("TagFault"); 
                    tagRead=attributeFinder.result("TagRead");
                    tagStates=attributeFinder.result("TagStates");
                    tagSW=attributeFinder.result("TagSW");
                    tagValue=attributeFinder.result("TagValue");
                    tagWrite=attributeFinder.result("TagWrite");
                    type=attributeFinder.result("Type"); 
                    useImage=attributeFinder.result("UseImage");
                    useStates=attributeFinder.result("UseStates").replaceAll("[\\r\\n\\t]", "");
                    valueColor=attributeFinder.result("ValueColor");
                    valuecolor=attributeFinder.result("ValueColor"); 
                    valueFont=attributeFinder.result("ValueFont");
                    valuefont=attributeFinder.result("ValueFont"); 
                    valuehoralignment=attributeFinder.result("ValueHorAlignment"); 
                    valueRefreshPeriod=attributeFinder.result("ValueRefreshPeriod");
                    valuerefreshperiod=attributeFinder.result("ValueRefreshPeriod"); 
                    valuetype=attributeFinder.result("ValueType"); 
                    vertalign=attributeFinder.result("VertAlign"); 
                    vertAlign=attributeFinder.result("VertAlign"); 
                    

                }

                if(childNode.getNodeName().equals("ComponentProperties")){

                    attributeFinder = new AttributeFinder(childNode); 
                    
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
                
                componentID ="01."+xPos.toString()+"."+yPos+"."+"01";

                directionOverule = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest).regel;
            }
        } 
 
//        RichtingString richtingsRegel = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest);
    }    
}


}

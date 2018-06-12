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
    public String useStates="";
    public String useImage="";
    public String tagValue="";
    public String tagStates="";
    public String tagDisabled="";
    public String tagCW="";
    public String states="";
    public String normalImage="";
    public String label="";
    public String fontSize="";
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
            }
        } 
 
        RichtingString richtingsRegel = new RichtingString(overruleNorth,overruleNorthEast,overruleEast,overruleSouthEast,overruleSouth,overruleSouthWest,overruleWest,overruleNorthWest);
    }    
}


}

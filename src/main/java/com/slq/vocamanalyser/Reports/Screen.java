/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.slq.vocamanalyser.AttributeFinder;
import java.io.IOException;
import org.w3c.dom.Node;

/**
 *
 * @author SLQ
 */
public class Screen {

    //================================================================ constants

    //=================================================================== fields
    public String filename="";
    public String path="";   
    public String vocamScreenType="";    
    public String visible="";
    public String enable="";
    public String description="";
    public String template="";
    public String substitutionTable="";
    public String shortcut1=""; 
    public String shortcut2=""; 
    public String shortcut3=""; 
 
    public String editMode="";  
    public String height="";  
    public String width="";  
    public String dialogCloseAction="";  
    public String dialogHeaderTitle="";  
    public String popupOpaque="";  
    public String onReleaseAction="";  
    public String dialogCloseButtonSize="";  
    public String dialogHeaderSize="";  
    public String dialogHeaderUserPixels="";  
    public String dialogTestHeader="";  
    public String templateType="";  
    public String bigFont="";  
    public String joyStick="";  

    
    //============================================================== constructor 
    
public Screen() {

}

public void get(Node screenNode) throws IOException{
    
    AttributeFinder attributeFinder;
    
    attributeFinder = new AttributeFinder(screenNode); 
    
    visible=attributeFinder.result("Visible");
    enable=attributeFinder.result("Enable");
    description=attributeFinder.result("Description");
    template=attributeFinder.result("Template");
    substitutionTable=attributeFinder.result("SubstitutionTable");
    shortcut1=attributeFinder.result("Shortcut1"); 
    shortcut2=attributeFinder.result("Shortcut2"); 
    shortcut3=attributeFinder.result("Shortcut3"); 
    editMode=attributeFinder.result("EditMode");  
    height=attributeFinder.result("Height"); 
    width=attributeFinder.result("Width"); 
    dialogCloseAction=attributeFinder.result("DialogCloseAction");  
    dialogHeaderTitle=attributeFinder.result("DialogHeaderTitle"); 
    popupOpaque=attributeFinder.result("PopupOpaque"); 
    onReleaseAction=attributeFinder.result("OnReleaseAction"); 
    dialogCloseButtonSize=attributeFinder.result("DialogCloseButtonSize"); 
    dialogHeaderSize=attributeFinder.result("DialogHeaderSize"); 
    dialogHeaderUserPixels=attributeFinder.result("DialogHeaderUserPixels"); 
    templateType=attributeFinder.result("TemplateType");  
    bigFont=attributeFinder.result("BigFont"); 
    joyStick=attributeFinder.result("JoyStick"); 
                    
    }

public void setFilename(String filename){
    this.filename = filename;
}
public void setPath(String path){
    this.path = path;
}
public void setVocamScreenType(String vocamScreenType){
    this.vocamScreenType = vocamScreenType;
}    

}    


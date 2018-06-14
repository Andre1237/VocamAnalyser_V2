/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.layout.Document;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Components.*;
import java.io.IOException;

/**
 *
 * @author SLQ
 */
public class ComponentPdf {

    //================================================================ constants

    //=================================================================== fields
    Document pdfDoc;

    //============================================================== constructor 
public ComponentPdf(Document pdfDoc){
    this.pdfDoc = pdfDoc;
}


public void create(Component component) throws IOException {
    System.out.println("-->Component type = "+ component.componentType);
    
    
                    switch(component.componentType){
                        case "Indicator"      : Component comp_Indicator            = new Component_Indicator().create(pdfDoc, component); break;
                        case "Value"          : Component comp_Value                = new Component_Value().create(pdfDoc, component); break;  
                        case "Button"         : Component comp_Button               = new Component_Button().create(pdfDoc, component); break;
                        case "ButtonComponent": Component component_ButtonComponent = new Component_ButtonComponent().create(pdfDoc, component);break;
                        case "ButtonToggle"   : Component comp_ButtonToggle         = new Component_ButtonToggle().create(pdfDoc, component);break;
                        case "Rectangle"      : Component Component_Rectangle       = new Component_Button().create(pdfDoc, component); break;
                        case "Label"          : Component comp_Label                = new Component_Label().create(pdfDoc, component); break;
                        case "InputField"     : Component comp_InputField           = new Component_InputField().create(pdfDoc, component); break;                                               
                        case "CheckBox"       : Component component_CheckBox        = new Component_CheckBox().create(pdfDoc, component);break;
                        case "Line"           : Component comp_Line                 = new Component_Line().create(pdfDoc, component);break;
                        
                        default:                System.out.println("#####Component type"+component.componentType+" not yet defined");                    

                    }
}
}

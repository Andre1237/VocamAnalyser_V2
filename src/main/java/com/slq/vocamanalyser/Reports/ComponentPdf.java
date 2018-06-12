/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser.Reports;

import com.itextpdf.layout.Document;
import com.slq.vocamanalyser.Component;
import com.slq.vocamanalyser.Components.Component_Button;
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
                        case "Indicator":       System.out.println("To de defined Indicator");
                                                break;   
                        case "Rectangle":       System.out.println("To de defined Rectangle");
                                                break;    
                        case "Value":           System.out.println("To de defined Value"); 
                                                break;   
                        case "ButtonComponent": System.out.println("To de defined ButtonComponent");


                        case "Button":          Component comp_Button = new Component_Button().create(pdfDoc, component);
                                                //comp_Button.create(pdfDoc, component);

                                                break;
                        case "Label":           System.out.println("To de defined Label");
                                                break;
                        case "Line":            System.out.println("To de defined Line");
                                                break;
                        case "InputField":      System.out.println("To de defined InputField"); 
                                                break;                                                
                        case "CheckBox":        System.out.println("To de defined CheckBox"); 
                                                break;                                                
                        default:                System.out.println("#####Component type"+component.componentType+" not yet defined");                    

                    }
}
}

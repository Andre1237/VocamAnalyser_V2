/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;
import com.slq.vocamanalyser.Reports.RapportageScreen;

import com.itextpdf.layout.Document;
import com.slq.vocamanalyser.Components.Component_Button;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author SLQ
 */
public class AnalyseScreen {
     //================================================================ constants
    private static final boolean SCREENOUTPUT = true;
    private static final boolean NOSCREENOUTPUT = false;

    //=================================================================== fields
    public static Component_Button          comp_Button = new Component_Button();
    
     
    Node                childNode;
    NodeList            verzamelingElementen;
    String              attribuut;
    String              screenDescription;
    Document            pdfDoc;

    public RapportageScreen   rapportage;  
    ArrayList<String>   componentenLijst;
    ArrayList<Integer>  compTeller;   

   //============================================================== constructors
    
public AnalyseScreen() {
    pdfDoc              = null;
    screenDescription   = "";
    componentenLijst    = new ArrayList<>();
    compTeller          = new ArrayList<>();   
}
public AnalyseScreen(Document pdfDoc) {
    pdfDoc              = null;
    screenDescription   = "";
    componentenLijst    = new ArrayList<>();
    compTeller          = new ArrayList<>();   
    rapportage          = new RapportageScreen(pdfDoc);  

}

public void update_ScreenComponents(Document pdfDoc,Node ScreenNode) throws IOException, InterruptedException{
 
    this.pdfDoc = pdfDoc;   
    if (ScreenNode.hasChildNodes()){

        verzamelingElementen = ScreenNode.getChildNodes();

        //System.out.println("lengte:" + verzamelingElementen.getLength());

        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        // Eerste loop voor algemene zaken 
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        
        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            childNode = verzamelingElementen.item(j);

            if(childNode.getNodeType()==childNode.ELEMENT_NODE){
            //System.out.println("Element number: "+j+"  "+ childNode.getNodeName());

                if(childNode.getNodeName().equals("GeneralProperties")){
                    AttributeFinder ScreenAttributeFinder = new AttributeFinder(childNode); 
                    //Write date for the header
                    rapportage.reportScreenHeader.setScreenHeaderTitle(ScreenAttributeFinder.result("ScreenHeaderTitle"));
                    //Write date for the general section
                    rapportage.reportScreenGeneral.setFileName(VocamAnalyser.filenaam );
                    rapportage.reportScreenGeneral.setFilePath(VocamAnalyser.pad);
                    
                    screenDescription = ScreenAttributeFinder.result("Description");
                    rapportage.reportScreenGeneral.setScreenDescription(screenDescription);
                    rapportage.reportScreenGeneral.setScreentemplate(ScreenAttributeFinder.result("Template"));
                    
                    rapportage.reportScreenGeneral.setScreenEnable(ScreenAttributeFinder.result("Enable"));
                    rapportage.reportScreenGeneral.setdScreenVisible(ScreenAttributeFinder.result("Visible"));

                    rapportage.reportScreenGeneral.setscreenSubstitutionTable(ScreenAttributeFinder.result("SubstitutionTable"));
           
                    rapportage.reportScreenGeneral.setShortcut1(ScreenAttributeFinder.result("Shortcut1"));
                    rapportage.reportScreenGeneral.setShortcut2(ScreenAttributeFinder.result("Shortcut2"));
                    rapportage.reportScreenGeneral.setShortcut3(ScreenAttributeFinder.result("Shortcut3"));
                }
                
                //Select components on the summation page    
                if(childNode.getNodeName().equals("Component")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode); 
                    //Write date for the summation paragraph
                    attribuut = attributeFinder.result("Title");
                    rapportage.reportScreenSummary.addComponentToSummary(attribuut);

                }
            }
        }

        if(screenDescription.isEmpty()){
            VocamAnalyser.reportMessages.addMsg(
                "Warning",
                "Screen screen has no description text added",
                " ");
        }

        rapportage.createFrontPage(pdfDoc);
     //   rapportage.reportScreenMessages.create(pdfDoc);
        

        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        // Tweede loop voor individuele componenten
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            childNode = verzamelingElementen.item(j);

            if(childNode.getNodeType()==childNode.ELEMENT_NODE){
            
                if(childNode.getNodeName().equals("Component")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode); 

                    attribuut = attributeFinder.result("Title");

                    switch(attribuut){
                        case "Indicator":       VocamAnalyser.comp_Indicator.readComponentData(childNode, NOSCREENOUTPUT);
                                                VocamAnalyser.comp_Indicator.analyse();
                                                VocamAnalyser.comp_Indicator.create(pdfDoc); 
                                                break;   
                        case "Rectangle":       VocamAnalyser.comp_Rectangle.readComponentData(childNode, NOSCREENOUTPUT);
                                                VocamAnalyser.comp_Rectangle.analyse();
                                                VocamAnalyser.comp_Rectangle.create(pdfDoc); 
                                                break;    
                        case "Value":           VocamAnalyser.comp_Value.readComponentData(childNode, NOSCREENOUTPUT); 
                                                VocamAnalyser.comp_Value.analyse();
                                                VocamAnalyser.comp_Value.create(pdfDoc); 
                                                break;   
                        case "ButtonComponent": VocamAnalyser.comp_ButtonComponent.readComponentData(childNode, NOSCREENOUTPUT);
                                                VocamAnalyser.comp_ButtonComponent.analyse();
                                                VocamAnalyser.comp_ButtonComponent.create(pdfDoc);
                                                break;
//                        case "Button":          VocamAnalyser.comp_Button.readComponentData(childNode, NOSCREENOUTPUT);
//                                                VocamAnalyser.comp_Button.analyse();
//                                                VocamAnalyser.comp_Button.create(pdfDoc);
//                                                break;
                        case "Button":          comp_Button.readComponentData(childNode, NOSCREENOUTPUT);
                                                comp_Button.analyse();
                                                comp_Button.create(pdfDoc);
                                                break;
//                        case "Button":       component[1] = getComponent(attribuut);//   comp_Button.readComponentData(childNode, NOSCREENOUTPUT);
                        case "Label":           VocamAnalyser.comp_Label.readComponentData(childNode, NOSCREENOUTPUT);
                                                VocamAnalyser.comp_Label.analyse();
                                                VocamAnalyser.comp_Label.create(pdfDoc);
                                                break;
                        case "Line":            VocamAnalyser.comp_Line.readComponentData(childNode, NOSCREENOUTPUT); 
                                                VocamAnalyser.comp_Line.analyse();
                                                VocamAnalyser.comp_Line.create(pdfDoc);
                                                break;
                        case "InputField":      VocamAnalyser.comp_InputField.readComponentData(childNode, NOSCREENOUTPUT); 
                                                VocamAnalyser.comp_InputField.analyse();
                                                VocamAnalyser.comp_InputField.create(pdfDoc);
                                                break;                                                
                        case "CheckBox":        VocamAnalyser.comp_CheckBox.readComponentData(childNode, NOSCREENOUTPUT); 
                                                VocamAnalyser.comp_CheckBox.analyse();
                                                VocamAnalyser.comp_CheckBox.create(pdfDoc);
                                                break;                                                
                        default:                System.out.println("#####Component type "+attribuut+" not yet defined");                    

                    }
                }
            }
        }
        rapportage.createMessages(pdfDoc);  
        
    }
}
}


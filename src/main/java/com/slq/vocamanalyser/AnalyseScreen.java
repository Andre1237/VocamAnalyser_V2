/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;
import com.slq.vocamanalyser.Reports.RapportageScreen;

import com.itextpdf.layout.Document;
import com.slq.vocamanalyser.Components.*;
import com.slq.vocamanalyser.Reports.ComponentPdf;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -non public fields
    Node                    childNode;
    NodeList                verzamelingElementen;
    String                  attribuut;
    String                  screenDescription;
    Document                pdfDoc;
    Component               component;
    ComponentPdf            componentPdf;            
    
    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -public fields
//    public List<Component>  components = new ArrayList<>();
    
    //public List<Component>  components;
    public List<Component>  components;
    public RapportageScreen rapportage;  

    //- - - - - - - - - - - - - - - - - - - - - - - - - - - - -Components fields
    public static Component_Button          comp_Button = new Component_Button();
    public static Component_CheckBox        comp_CheckBox = new Component_CheckBox();
    public static Component_Indicator       comp_Indicator = new Component_Indicator();
    public static Component_Rectangle       comp_Rectangle = new Component_Rectangle();
    public static Component_Value           comp_Value = new Component_Value();
    public static Component_ButtonComponent comp_ButtonComponent = new Component_ButtonComponent();
    public static Component_Line            comp_Line = new Component_Line();
    public static Component_Label           comp_Label = new Component_Label();
    public static Component_InputField      comp_InputField = new Component_InputField();
   // ArrayList<Object> arrayList = new ArrayList<Object>();
     
   //============================================================== constructors
    
public AnalyseScreen() {
    pdfDoc              = null;
    screenDescription   = "";
    component           = new Component();
    components          = new ArrayList<Component>();
}
public AnalyseScreen(Document pdfDoc) {
    pdfDoc              = null;
    screenDescription   = "";
    rapportage          = new RapportageScreen(pdfDoc);  
    component           = new Component();
    components          = new ArrayList<Component>();

}

public void update_ScreenComponents(Document pdfDoc,Node ScreenNode) throws IOException, InterruptedException{
 
    components = new ArrayList<Component>();
    
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

                    //create the Pdf component
                    componentPdf        = new ComponentPdf(pdfDoc);
                    component           = new Component();                

                    //read the component and place it into the component array
                    component.get(childNode);

                    // Add the component to the arraylist
                    components.add(component);
                    
                    //create the Pdf tabels from every component
                    componentPdf.create(component);
                }
            }
        }
        
        for(int i=0; i < components.size();i++){
            //componentPdf.create(components.get(i));
            System.out.println("Component"+i+" "+components.get(i).componentID);
            
        }    
    }
}
}


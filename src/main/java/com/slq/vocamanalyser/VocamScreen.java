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
import com.slq.vocamanalyser.Reports.Screen;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author SLQ
 */
public class VocamScreen {
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
    public Screen screen;
    public String vocamScreenType;
    
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
    
public VocamScreen() {
    screen               = new Screen();
    pdfDoc               = null;
    screenDescription    = "";
    component            = new Component();
    components           = new ArrayList<Component>();
}
public VocamScreen(Document pdfDoc, String vocamScreenType) {
    screen               = new Screen();
    this.vocamScreenType = vocamScreenType;
    pdfDoc               = null;
    screenDescription    = "";
    rapportage           = new RapportageScreen(pdfDoc,screen);  
    component            = new Component();
    components           = new ArrayList<Component>();

}

public void analyse(Document pdfDoc,Node ScreenNode) throws IOException, InterruptedException{
 
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
                    
                    screen.get(childNode);
                    screen.setFilename(VocamAnalyser.filenaam );
                    screen.setPath(VocamAnalyser.pad);
                    screen.setVocamScreenType(vocamScreenType); 
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
                    
                }
            }
        }

        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        // Create PDF document
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        
        rapportage.createFrontPage(pdfDoc);
        
        //print individual component sheets to pdf.
        for(int i=0; i < components.size();i++){
            componentPdf.create(components.get(i));
        } 
        
        rapportage.createMessages(pdfDoc);
    }
}
}


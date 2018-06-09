/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;


import com.slq.vocamanalyser.Reports.RapportageDialog;
import com.itextpdf.layout.Document;
import java.io.IOException;
import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author SLQ
 */
public class AnalyseDialog {
    //================================================================ constants
    private static final boolean SCREENOUTPUT = true;
    private static final boolean NOSCREENOUTPUT = false;

    //=================================================================== fields
    
    //============================================================== constructor
    
    Node                childNode;
    NodeList            verzamelingElementen;
    String              attribuut;
    String              dialogDescription;
    Document            pdfDoc;

    public RapportageDialog   rapportage;  
    ArrayList<String>   componentenLijst;
    ArrayList<Integer>  compTeller;   

public AnalyseDialog() {
    pdfDoc              = null;
    dialogDescription   = "";
    componentenLijst    = new ArrayList<>();
    compTeller          = new ArrayList<>();   
}
public AnalyseDialog(Document pdfDoc) {
    pdfDoc              = null;
    dialogDescription   = "";
    componentenLijst    = new ArrayList<>();
    compTeller          = new ArrayList<>();   
    rapportage          = new RapportageDialog(pdfDoc);  

}

public void update_DialogComponents(Document pdfDoc,Node DialogNode) throws IOException, InterruptedException{
 
    this.pdfDoc = pdfDoc;   
    if (DialogNode.hasChildNodes()){

        verzamelingElementen = DialogNode.getChildNodes();

        //System.out.println("lengte:" + verzamelingElementen.getLength());

        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        // Eerste loop voor algemene zaken 
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        
        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            childNode = verzamelingElementen.item(j);

            if(childNode.getNodeType()==childNode.ELEMENT_NODE){
            //System.out.println("Element number: "+j+"  "+ childNode.getNodeName());

                if(childNode.getNodeName().equals("GeneralProperties")){
                    AttributeFinder DialogAttributeFinder = new AttributeFinder(childNode); 
                    //Write date for the header
                    rapportage.reportDialogHeader.setDialogHeaderTitle(DialogAttributeFinder.result("DialogHeaderTitle"));
                    //Write date for the general section
                    rapportage.reportDialogGeneral.setFileName(VocamAnalyser.filenaam );
                    rapportage.reportDialogGeneral.setFilePath(VocamAnalyser.pad);
                    dialogDescription = DialogAttributeFinder.result("Description");
                    rapportage.reportDialogGeneral.setDialogDescription(dialogDescription);
                    rapportage.reportDialogGeneral.setDialogHeaderTitle(DialogAttributeFinder.result("DialogHeaderTitle"));
                    rapportage.reportDialogGeneral.setDialogtemplate(DialogAttributeFinder.result("Template"));
                    rapportage.reportDialogGeneral.setDdalogCloseAction(DialogAttributeFinder.result("DialogCloseAction"));
                    rapportage.reportDialogGeneral.setDialogEditMode(DialogAttributeFinder.result("EditMode"));
                    rapportage.reportDialogGeneral.setDialogEnable(DialogAttributeFinder.result("Enable"));
                    rapportage.reportDialogGeneral.setDialogHeight(DialogAttributeFinder.result("Height"));
                    rapportage.reportDialogGeneral.setdialogSubstitutionTable(DialogAttributeFinder.result("SubstitutionTable"));
                    rapportage.reportDialogGeneral.setdDialogVisible(DialogAttributeFinder.result("Visible"));
                    rapportage.reportDialogGeneral.setDialogWidth(DialogAttributeFinder.result("Width"));
                }
                
                //Select components on the summation page    
                if(childNode.getNodeName().equals("Component")){

                    AttributeFinder attributeFinder = new AttributeFinder(childNode); 
                    //Write date for the summation paragraph
                    attribuut = attributeFinder.result("Title");
                    rapportage.reportDialogSummary.addComponentToSummary(attribuut);
                    

                }
            }
        }

        if(dialogDescription.isEmpty()){
            VocamAnalyser.reportMessages.addMsg(
                "Warning",
                "Dialog screen has no description text added",
                " ");
        }

        rapportage.createFrontPage(pdfDoc);
     //   rapportage.reportMessages.create(pdfDoc);
        

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
                        case "Button":          VocamAnalyser.comp_Button.readComponentData(childNode, NOSCREENOUTPUT);
                                                VocamAnalyser.comp_Button.analyse();
                                                VocamAnalyser.comp_Button.create(pdfDoc);
                                                break;
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

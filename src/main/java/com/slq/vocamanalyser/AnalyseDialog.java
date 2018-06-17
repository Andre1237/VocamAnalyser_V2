/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;


import com.slq.vocamanalyser.Reports.RapportageDialog;
import com.itextpdf.layout.Document;
import com.slq.vocamanalyser.Reports.ComponentPdf;
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
     //   rapportage.reportScreenMessages.create(pdfDoc);
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        // Tweede loop voor individuele componenten
        //= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = =
        for (int j = 0; j < verzamelingElementen.getLength(); j++) {

            childNode = verzamelingElementen.item(j);

//            if(childNode.getNodeType()==childNode.ELEMENT_NODE){
//            
//                if(childNode.getNodeName().equals("Component")){
//
//                    AttributeFinder attributeFinder = new AttributeFinder(childNode); 
//
//                    attribuut = attributeFinder.result("Title");
//
//                    //create the Pdf component
//                    componentPdf        = new ComponentPdf(pdfDoc);
//                    
//                    //read the component and place it into the component array
//                    component.get(childNode);
//
//                    System.out.println("component.componentID =  "+component.componentID);
//                    
//                    components.add(component);
//                    
//                    //create the Pdf tabels from every component
//                    componentPdf.create(component);
//                }
//            }
//        }
//        
//        
//        for(int i=0; i < components.size();i++){
//            //componentPdf.create(components.get(i));
//            System.out.println("Component"+i+" "+components.get(i).componentID);
//            
//        }    
//        rapportage.createMessages(pdfDoc);  
//            Component c;
//            c = components.get(1); System.out.println("nummer 1 = "+c.componentID);
//            c = components.get(2); System.out.println("nummer 1 = "+c.componentID);
//            c = components.get(3); System.out.println("nummer 1 = "+c.componentID);
//            c = components.get(4); System.out.println("nummer 1 = "+c.componentID);
        
        }
    }
}
}


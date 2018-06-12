/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;

import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author SLQ
 */
public class AttributeFinder {

    //================================================================ constants

    //=================================================================== fields
    Node node;
    boolean showAttribContent ;
    //============================================================= constructors 
public AttributeFinder(Node node, Boolean showAttribContent){ 
    this.node = node;
    this.showAttribContent = showAttribContent;
}

public AttributeFinder(Node node){ 
    this.node = node;
    showAttribContent = false;   // by default no output to screen
}

    public void setShow(Boolean showAttribContent){ 
        this.showAttribContent = showAttribContent;
    }

    public String result(String AttribName){

        String antwoord = "";

        NamedNodeMap verzamelingAttributen = node.getAttributes();                   

         // get the number of nodes in this map
        int aantalAtributen = verzamelingAttributen.getLength();

        for (int i = 0; i < aantalAtributen; i++) {
            Attr attribuut = (Attr) verzamelingAttributen.item(i);
            if(attribuut.getNodeName().equals(AttribName)){
                String attrName = attribuut.getNodeName();
                antwoord = attribuut.getNodeValue();

                // if requested ik can be put on the screen.
                if (showAttribContent){
                    String str = AttribName+"                                     ";
                    System.out.println(str.substring(0, 24)+"\t"+antwoord);
                }    
                break;
            }
        }

        return antwoord;
        }   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slq.vocamanalyser;

/**
 *
 * @author SLQ
 */
public class RichtingString {
    public String regel;
    
public RichtingString(){
    regel ="";
}

public RichtingString(String noord, String noordoost, String oost, String zuidoost,
                             String zuid,  String zuidwest,  String west, String noordwest){

regel = "N => "+ richingsTeken(noord);
regel = regel + "    NE => "+ richingsTeken(noordoost);
regel = regel + "    E => "+ richingsTeken(oost);
regel = regel + "    SE => "+ richingsTeken(zuidoost);
regel = regel + "    S => "+ richingsTeken(zuid);
regel = regel + "    SW => "+ richingsTeken(zuidwest);
regel = regel + "    W => "+ richingsTeken(west);
regel = regel + "    NW => "+ richingsTeken(noordwest);

if (regel.equals("N =>      NE =>      E =>      SE =>      S =>      SW =>      W =>      NW =>  ")){
    regel = "No overrules";
}

}

public static String richingsTeken(String Richting){
    
    switch(Richting){
        case "NORTH"        : return "N";
        case "NORTH_EAST"   : return "NE";
        case "EAST"         : return "E";    
        case "SOUTH_EAST"   : return "SE";    
        case "SOUTH"        : return "S";
        case "SOUTH_WEST"   : return "SW";
        case "WEST"         : return "W";    
        case "NORTH_WEST"   : return "NW";    
        default             : return " ";
    }
    
}
        
}

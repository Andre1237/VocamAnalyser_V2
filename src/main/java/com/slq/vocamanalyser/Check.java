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
public class Check {
    
    //================================================================ constants
    
    //=================================================================== fields
    
    //============================================================== constructor
    public Check() {
    //Nothing to construct
    }
    
    /**  isTag checks if a string is a tag
     * It's a tag when:
     * 1) it has a "."somewhere in the sting
     * 2) Has no " " in the string
     * 3) Is all capitals
     * 
     * @param testTag : String to be tested if it is a string
     * @return True = string / False = NO string
     */
    public boolean isTag(String testTag){
        boolean verifiedTag;
        String upper = testTag.toUpperCase();
        if (!testTag.isEmpty()){
            if (testTag.contains(".")){
                if (!testTag.contains(" ")){
                    if (testTag.equals(upper)){
                        return true;
                    }
                }
            }
        }
        return false;
    }    
}

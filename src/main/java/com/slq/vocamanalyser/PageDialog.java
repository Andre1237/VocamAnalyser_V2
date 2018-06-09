package com.slq.vocamanalyser;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PageDialog implements Printable {
    public static final int KOLOMBREEDTE = 150;
    public static String dialogDescription=""; 
    public static String dialogCloseAction=""; 
    public static String dialogHeaderTitle=""; 
    public static String dialogEditMode=""; 
    public static String dialogEnable=""; 
    public static String dialogHeight=""; 
    public static String dialogSubstitutionTable=""; 
    public static String dialogtemplate=""; 
    public static String dialogVisible=""; 
    public static String dialogWidth="";
    
    public static int indicatorTeller = 0;
    public static int rectangleTeller = 0;
    public static int valueTeller = 0;
    public static int ButtonComponentTeller = 0;
    public static int labelTeller = 0;
    public static int lineTeller = 0;
    
    ArrayList<String> berichtenSoort = new ArrayList<>();
    ArrayList<String> berichtenRegels = new ArrayList<>();
    ArrayList<String> berichtenPos = new ArrayList<>();
    
    //--- Private instances declarations
    public final static int POINTS_PER_INCH = 72;
    public static int regelAfstand = 12;    
    
public PageDialog(){
     
}     
public void berichtToevoegen(String soortBericht,String berichtRegel,String positie){
    berichtenSoort.add(soortBericht);  
    berichtenRegels.add(berichtRegel);  
    berichtenPos.add(positie);
}
public void setDialogHeaderTitle(String dialogHeaderTitle){this.dialogHeaderTitle = dialogHeaderTitle;}
public void setDialogDescription(String dialogDescription){this.dialogDescription = dialogDescription;}
public void setDialogtemplate(String dialogtemplate){this.dialogtemplate = dialogtemplate;}
public void setDialogHeight(String dialogHeight){this.dialogHeight = dialogHeight;}
public void setDialogWidth(String dialogWidth){this.dialogWidth = dialogWidth;}
public void setdDialogVisible(String dialogVisible){this.dialogVisible = dialogVisible;}
public void setDialogEnable(String dialogEnable){this.dialogEnable = dialogEnable;}
public void setDialogEditMode(String dialogEditMode){this.dialogEditMode = dialogEditMode;}
public void setDdalogCloseAction(String dialogCloseAction){this.dialogCloseAction = dialogCloseAction;}
public void setdialogSubstitutionTable(String dialogSubstitutionTable){this.dialogSubstitutionTable = dialogSubstitutionTable;}
public void incIndicator(){indicatorTeller++;}
public void incRectangle(){rectangleTeller++;}
public void incValue(){valueTeller++;}
public void incButton(){ButtonComponentTeller++;}
public void incLabel(){labelTeller++;}
public void incLine(){lineTeller++;}

public void resetDialogData(){
    indicatorTeller = 0;
    rectangleTeller = 0;
    valueTeller = 0;
    ButtonComponentTeller = 0;
    labelTeller = 0;
    lineTeller = 0;
    berichtenSoort.clear();
    berichtenRegels.clear();
    berichtenPos.clear();
}

public void showData(){
    System.out.println("dialogDescription\t:"+dialogDescription);
    System.out.println("dialogCloseAction\t:"+dialogCloseAction);
    System.out.println("dialogHeaderTitle\t:"+dialogHeaderTitle);
    System.out.println("dialogEditMode\t\t:"+dialogEditMode);
    System.out.println("dialogEnable\t\t:"+dialogEnable);
    System.out.println("dialogHeight\t\t:"+dialogHeight);
    System.out.println("dialogSubstitutionTable\t:"+dialogSubstitutionTable);
    System.out.println("dialogtemplate\t\t:"+dialogtemplate);
    System.out.println("dialogVisible\t\t:"+dialogVisible);
    System.out.println("dialogWidth\t\t:"+dialogWidth);
    
    System.out.println("indicatorTeller\t\t:"+ indicatorTeller);
    System.out.println("rectangleTeller\t\t:"+ rectangleTeller);
    System.out.println("valueTeller\t\t:"+ valueTeller);
    System.out.println("ButtonComponentTeller\t\t:"+ ButtonComponentTeller);
    System.out.println("labelTeller\t\t:"+ labelTeller);
    System.out.println("lineTeller\t\t:"+ indicatorTeller);
    
    
}

@Override
public int print(Graphics g, PageFormat pageFormat, int page) {
    String hulpString;
    int kolomOffset = 0;
    
    
    dialogDescription="Moet nog ingevuld worden classe DialogPage in this";

    double breed =  pageFormat.getImageableWidth();
    double hoog =  pageFormat.getImageableHeight();

    //System.out.println("breed "+breed+" hoog "+hoog);
    int tmpRegelnr = 0;

    Graphics2D g2d = (Graphics2D) g;
    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

    //--- Set the default drawing color and tekst font
    Font titleFont = new Font("arial", Font.PLAIN, 14);
    Font normalFont = new Font("arial", Font.PLAIN, 10);
    Font smallFont = new Font("arial", Font.PLAIN, 8);

    g2d.setFont(titleFont);
    g2d.setPaint(Color.black);


    //--- Draw a border arround the page
    Rectangle2D.Double border = new Rectangle2D.Double(0, 0, breed, hoog);g2d.draw(border);
    // --- Draw a line under the title
    Line2D.Double lijn01 = new Line2D.Double(0,20,breed,20);              g2d.draw(lijn01);
    //--- Draw the title
    g2d.drawString("Vocam Analysis Dialog ", 5, 15);
    //--- Draw time and date in the to right corner
    g2d.setFont(smallFont);

    Date dNow = new Date( );
    SimpleDateFormat ft1 = new SimpleDateFormat ("hh:mm:ss");
    SimpleDateFormat ft2 = new SimpleDateFormat ("dd.MM.yy");
    String tijd  = ft1.format(dNow);
    String datum = ft2.format(dNow);

    FontMetrics fontMetrics = g2d.getFontMetrics();
    g2d.drawString(tijd,  (int) (pageFormat.getImageableWidth()-7-fontMetrics.stringWidth(tijd)),9);
    g2d.drawString(datum, (int) (pageFormat.getImageableWidth()-7-fontMetrics.stringWidth(datum)),17);

    //--- draw the page name
    g2d.setFont(normalFont);
    tmpRegelnr = 35;
    g2d.drawString("Dialog title", 5, tmpRegelnr);g2d.drawString(":", 55, tmpRegelnr);
    g2d.drawString(dialogHeaderTitle, 60, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("Description", 5, tmpRegelnr);g2d.drawString(":", 55, tmpRegelnr);
    g2d.drawString(dialogDescription, 60, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("Template", 5, tmpRegelnr);g2d.drawString(":", 55, tmpRegelnr);
    g2d.drawString(dialogtemplate, 60, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("Size", 5, tmpRegelnr);g2d.drawString(":", 55, tmpRegelnr);
    g2d.drawString(dialogWidth+" x "+dialogHeight+"  (Width x Height)", 60, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    // --- Draw a line under the title
    Line2D.Double lijn02 = new Line2D.Double(0,tmpRegelnr,breed,tmpRegelnr);  g2d.draw(lijn02);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("Edit Mode", 5, tmpRegelnr);g2d.drawString(":", 95, tmpRegelnr);
    hulpString ="YES";
    if (dialogEditMode.contentEquals("true")) {hulpString ="NO";}
    g2d.drawString(hulpString, 100, tmpRegelnr);

    g2d.drawString("Visible", 160, tmpRegelnr);g2d.drawString(":", 200, tmpRegelnr);
    hulpString ="YES";
    if (dialogVisible.contentEquals("true")) {hulpString ="NO";}
    g2d.drawString(hulpString, 205, tmpRegelnr);

    g2d.drawString("Enable", 260, tmpRegelnr);g2d.drawString(":", 300, tmpRegelnr);
    hulpString ="YES";
    if (dialogEnable.contentEquals("true")) {hulpString ="NO";}
    g2d.drawString(hulpString, 305, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("SubstitutionTable", 5, tmpRegelnr);g2d.drawString(":", 100, tmpRegelnr);
    g2d.drawString(dialogSubstitutionTable, 105, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    g2d.drawString("Close Action", 5, tmpRegelnr);g2d.drawString(":", 100, tmpRegelnr);
    g2d.drawString(dialogCloseAction, 105, tmpRegelnr);

    tmpRegelnr = tmpRegelnr + regelAfstand;
    // --- Draw a line under the title
    Line2D.Double lijn03 = new Line2D.Double(0,tmpRegelnr,breed,tmpRegelnr);  g2d.draw(lijn03);

    tmpRegelnr = tmpRegelnr + regelAfstand+ regelAfstand;
    g2d.drawString("Summary", 5, tmpRegelnr);
    
    tmpRegelnr = tmpRegelnr + regelAfstand;
    if (indicatorTeller!=0){
        g2d.drawString("# Indicators", 5+kolomOffset%450, tmpRegelnr);g2d.drawString(":", 95+kolomOffset%450, tmpRegelnr);
        g2d.drawString(Integer.toString(indicatorTeller)  , 100+kolomOffset%450, tmpRegelnr);    
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if ((kolomOffset%450)==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }
    

    if (valueTeller!=0){
        g2d.drawString("# Values", 5+kolomOffset%450, tmpRegelnr);g2d.drawString(":", 95+kolomOffset%450, tmpRegelnr);
        g2d.drawString(Integer.toString(valueTeller)  , 100+kolomOffset%450, tmpRegelnr);    
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if (kolomOffset%450==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }
 

    if (ButtonComponentTeller!=0){
        g2d.drawString("# Buttons", 5+kolomOffset%450, tmpRegelnr);g2d.drawString(":", 95+kolomOffset%450, tmpRegelnr);
        g2d.drawString(Integer.toString(ButtonComponentTeller)  , 100+kolomOffset%450, tmpRegelnr);    
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if ((kolomOffset%450)==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }


    if (labelTeller!=0){
        g2d.drawString("# Labels", 5+kolomOffset%450, tmpRegelnr);g2d.drawString(":", 95+kolomOffset%450, tmpRegelnr);
        g2d.drawString(Integer.toString(labelTeller)  , 100+kolomOffset%450, tmpRegelnr);    
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if ((kolomOffset%450)==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }
    
        if (rectangleTeller!=0){
        g2d.drawString("# Rectangles", kolomOffset, tmpRegelnr);g2d.drawString(":", kolomOffset+95, tmpRegelnr);
        g2d.drawString(Integer.toString(rectangleTeller)  , kolomOffset+100, tmpRegelnr);      
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if ((kolomOffset%450)==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }
        
        if (lineTeller!=0){
        g2d.drawString("# Lijnen", kolomOffset, tmpRegelnr);g2d.drawString(":", kolomOffset+95, tmpRegelnr);
        g2d.drawString(Integer.toString(lineTeller)  , kolomOffset+100, tmpRegelnr);      
        kolomOffset =kolomOffset+KOLOMBREEDTE;
        if ((kolomOffset%450)==0){tmpRegelnr = tmpRegelnr + regelAfstand;}
    }
        
    
       
    // --- Draw a line under the title
    Line2D.Double lijn04 = new Line2D.Double(0,400,breed,400);              g2d.draw(lijn04);
    tmpRegelnr = 515;    
      
    //for(String berichtRegel: berichtenRegels){
     
    for (int i = 0; i < berichtenRegels.size(); i++){
        tmpRegelnr = tmpRegelnr + regelAfstand;
        g2d.drawString(berichtenSoort.get(i), 5, tmpRegelnr);
        g2d.drawString(berichtenPos.get(i), 390, tmpRegelnr);
        g2d.drawString(berichtenRegels.get(i), 55, tmpRegelnr);   

    }
 
    return (PAGE_EXISTS);
    }
}

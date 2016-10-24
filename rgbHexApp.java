/*  
 * ======================== *
 * Small App that converts  *
 * rgb value to HEX         *
 * Author: Finn O'Connor    *
 * ======================== *
 */     
 import javax.swing.*;
 import java.awt.*;
 import java.util.*;
 import java.awt.event.*;
 import javax.swing.border.EtchedBorder;
 import javax.swing.border.TitledBorder;
 
 public class rgbHexApp extends JFrame {
     
     public rgbHexApp() {
        super("rgbHexApp");
        
        // Create Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Adds in panels
        setUpToolsPanel();
        
        // Display Window
        pack();
        setVisible(true);
     }
     private void setUpToolsPanel() {
        // Setup Output Text Area
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        
        // Setup rgbText Area for input
        JTextField rgbText = new JTextField("000 000 000");
        rgbText.setPreferredSize(new Dimension(100,20));
        rgbText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField tf = (JTextField)e.getSource();
                try {
                    String newValue = tf.getText();
                    textArea.setText(null);
                    textArea.append(rgbConvertHex(newValue));
                } catch (Exception ex){
                    textArea.append(rgbConvertHex("000000000"));
                }
            }
        });
        // Setup hexText Field for input
        JTextField hexText = new JTextField("#00 00 00");
        hexText.setPreferredSize(new Dimension(100,20));
        hexText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField tf = (JTextField)e.getSource();
                try {
                    String newValue = tf.getText();
                    textArea.setText(null);
                    textArea.append(hexConvertRGB(newValue));
                } catch (Exception ex){
                    textArea.append(hexConvertRGB("#000000"));
                }
            }
        });
        
        JPanel toolsPanel = new JPanel();
        toolsPanel.add(new JLabel(" Convert RGB: ", JLabel.RIGHT), BorderLayout.NORTH);
        toolsPanel.add(rgbText);
        toolsPanel.add(new JLabel(" Convert Hex: ", JLabel.RIGHT), BorderLayout.CENTER);
        toolsPanel.add(hexText);
        
        // OUTPUT PANEL
        JPanel outputPanel = new JPanel();
        outputPanel.add(textArea, BorderLayout.SOUTH);
        outputPanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
        
        // ADD PANELS
        add(toolsPanel,BorderLayout.NORTH);
        add(outputPanel,BorderLayout.SOUTH);
     }
     
     public String rgbConvertHex(String rgbLine){
         String pureRGB = rgbLine.replaceAll("\\s+","").replaceAll(",","");
         String outHex = "";
         for (int i=0;i<3;i++){
             int currInt = Integer.parseInt(pureRGB.substring(i*3,i*3+3));
             String currHex = Integer.toString(currInt,16);
             if (currHex.length() < 2){
                 currHex = "0" + currHex;
             }
             outHex = outHex + currHex;
         }
         return outHex;
     }
     public String hexConvertRGB(String hexLine) {
         String pureHEX = hexLine.replaceAll("\\s+","").replaceAll("#","");
         String outRGB = "";
        for (int i=0;i<3;i++){
             int currRGB = Integer.parseInt(pureHEX.substring(i*2,i*2+2), 16);
             if (9 < currRGB && currRGB< 100){
                 outRGB += "0" + currRGB;
                 System.out.println(outRGB);
             } else if (currRGB < 10) {
                 outRGB += "00" + currRGB;
                 System.out.println(outRGB);
             } else {
                 outRGB = outRGB + currRGB;
                 System.out.println(outRGB);
             }
         }
         return outRGB;
     }
     public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new rgbHexApp();
            }
        });
     }   
 }
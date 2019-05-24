/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.despegar.visio.tagger;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author claudio.gauna
 */
public class ImageTagger extends javax.swing.JFrame implements KeyListener {
    
    
    private File imageFolder;
    private File tagFile;
    private File counterFile;
    private int nextImageIndex;
    private  File[] imageFiles;
    private String[] tags = {"bar", "bathroom", "bedroom", "breakfast", "city_view", "dinning",
       "hotel_Front", "hotel_exterior_view", "hotel_interior_view",
       "kitchen", "living_room", "lobby", "natural_view", "pool",
       "recreation", "sports","undefined"};
    private Integer to;
    private Integer from;

    /**
     * Creates new form ImageTagger
     */
    public ImageTagger(File imageFolder, File tagFile, File counterFile, Integer from, Integer to) {
                this.imageFolder = imageFolder;
        this.tagFile = tagFile;
        this.counterFile = counterFile;
        this.nextImageIndex = loadNextImageIndex();
        this.imageFiles = this.imageFolder.listFiles();
        initComponents();
        loadImage();
        this.tagOptionList.setListData(tags);
        this.to = to;
        this.from = from;
        addKeyListener(this);        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
    }
    
    private int loadNextImageIndex() {
        try {
            Scanner scanner = new Scanner(this.counterFile);
            int lastIndex = scanner.nextInt();
            return lastIndex;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ImageTagger.class.getName()).log(Level.SEVERE, String.format("Counter file =  doesn't exist.", this.counterFile.getAbsolutePath()), ex);
        }
        return 0;
    }
    
    private boolean loadImage() {
        if(nextImageIndex > this.imageFiles.length) {
            return false;
        } else {
            File imageFile = this.imageFiles[nextImageIndex];
            this.imageLabel.setText(imageFile.getName());
            try {
                Logger.getLogger(ImageTagger.class.getName()).log(Level.INFO, String.format("Loading image %s ...", imageFile.getName()));
                BufferedImage image = ImageIO.read(imageFile);
                image = resizeImage(image);
                ImageIcon icon = new ImageIcon(image);
                this.imagePanel.getViewport().add(new JLabel(icon));
            } catch (IOException ex) {
                Logger.getLogger(ImageTagger.class.getName()).log(Level.SEVERE, String.format("Can't load image %s = ", imageFile.getName()));
            } catch (Exception e) {
                Logger.getLogger(ImageTagger.class.getName()).log(Level.SEVERE, String.format("Can't load image %s = ", imageFile.getName()));
            }
        }
        return false;
    }
    
    private BufferedImage resizeImage(BufferedImage image) {
        int width = this.imagePanel.getWidth()-20;
        int height = this.imagePanel.getHeight()-20;
        Logger.getLogger(ImageTagger.class.getName()).log(Level.INFO, String.format("Width = %s, height = %s", width, height));
        Image resized =  image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage buffered = new BufferedImage(width, height, Image.SCALE_REPLICATE);
        buffered.getGraphics().drawImage(resized, 0, 0 , null);
        return buffered;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JScrollPane();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tagOptionList = new javax.swing.JList<>();
        tagButton = new javax.swing.JButton();
        imageLabel = new javax.swing.JLabel();
        logLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        previousButton.setText("Previous");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        tagOptionList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(tagOptionList);

        tagButton.setText("Tag");
        tagButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(previousButton)
                        .addGap(78, 78, 78)
                        .addComponent(nextButton)
                        .addGap(136, 136, 136)
                        .addComponent(tagButton)
                        .addGap(381, 381, 381))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(imagePanel))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(previousButton)
                    .addComponent(nextButton)
                    .addComponent(tagButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        previous();
    }//GEN-LAST:event_previousButtonActionPerformed

    private void previous() {
        // TODO add your handling code here:
        if (this.nextImageIndex > this.from) {
            this.nextImageIndex--;
            loadImage();
        }
    }

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        next();
    }//GEN-LAST:event_nextButtonActionPerformed

    private void next() {
        // TODO add your handling code here:
        if (this.nextImageIndex < to){
            this.nextImageIndex++;
            loadImage();
        }
    }

    private void tagButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagButtonActionPerformed
        tag();
    }//GEN-LAST:event_tagButtonActionPerformed

    private void tag() {
        // TODO add your handling code here:
        File imageFile = this.imageFiles[this.nextImageIndex];
        this.logLabel.setText(String.format("Tagging %s as %s", imageFile.getName(), this.tagOptionList.getSelectedValue()));
        try {
            FileWriter writer = new FileWriter(this.tagFile, true);
            writer.append(imageFile.getName());
            writer.append(",");
            writer.append(this.tagOptionList.getSelectedValue());
            writer.append("\n");
            writer.flush();
            writer.close();
            writer = new FileWriter(this.counterFile);
            writer.append(this.nextImageIndex + "");
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(ImageTagger.class.getName()).log(Level.SEVERE, "Can't open tagFile", ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageTagger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageTagger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageTagger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageTagger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        String imageFolderArg = args[0];
        String tagFileArg = args[1];
        String counterFileArg = args[2];
        Integer from = Integer.valueOf(args[3]);
        Integer to = Integer.valueOf(args[4]);
        
        System.out.println(String.format("Image folder = %s", imageFolderArg));
        System.out.println(String.format("Tag file = %s", tagFileArg));
        System.out.println(String.format("Counter file = %s", counterFileArg));
        System.out.println(String.format("from = %s", from));
        System.out.println(String.format("to = %s", to));
        
        File imageFolder = new File(imageFolderArg);
        File tagFile = new File(tagFileArg);
        File counterFile = new File(counterFileArg);
        
        try {
            tagFile.createNewFile();
            boolean newFile = counterFile.createNewFile();
            if (newFile) {
                try (FileWriter writer = new FileWriter(counterFile)) {
                    writer.append(from + "");
                    writer.flush();
                }
            }
        } catch(IOException e) {
            System.out.println(String.format("Error when creating file %s or file %s error + %s", tagFile.getName(), counterFile.getName(), e.getMessage()));
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageTagger(imageFolder, tagFile, counterFile, from, to).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imageLabel;
    private javax.swing.JScrollPane imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton tagButton;
    private javax.swing.JList<String> tagOptionList;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                next();
                break;
            case KeyEvent.VK_LEFT:
                previous();
                break;
            case KeyEvent.VK_DOWN:
                selectDown();
                break;
            case KeyEvent.VK_UP:
                selectUp();
                break;
            case KeyEvent.VK_ENTER:
                tag();
                break;
            default:
                if(Character.isAlphabetic(e.getKeyChar())) {
                    for (int i = 0; i < this.tagOptionList.getModel().getSize(); i++) {
                        if (this.tagOptionList.getModel().getElementAt(i).charAt(0) == e.getKeyChar())
                            this.tagOptionList.setSelectedIndex(i);
                    }
                }
                break;
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    private void selectUp() {
        int actual = this.tagOptionList.getSelectedIndex();
        if (actual == -1)
            this.tagOptionList.setSelectedIndex(0);
        else if (actual > 0)
            this.tagOptionList.setSelectedIndex(actual - 1);
     
    }
    
    private void selectDown() {
        int actual = this.tagOptionList.getSelectedIndex();
        if (actual == -1)
            this.tagOptionList.setSelectedIndex(0);
        else if (actual < this.tagOptionList.getModel().getSize() - 1)
            this.tagOptionList.setSelectedIndex(actual + 1);
    }
}

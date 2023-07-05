/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

/**
 *
 * @author ntn19
 */
public class UI {
    GamePanel gp;
    Graphics2D g2;
    public Font font;
    BufferedImage heart_blank, heart_full, heart_half, crystal_full, crystal_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue= "";
    public int commandNum = 0;
    public int titleScreenState = 0;
    public int slotCol = 0;
    public int slotRow = 0;
    
    public UI(GamePanel gp){
        this.gp = gp;
        
        try {
            InputStream is = getClass().getResourceAsStream("/font/Cucho.otf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Entity heart = new OBJ_Heart(gp);
        heart_blank = heart.image;
        heart_full = heart.image2;
        heart_half = heart.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_blank = crystal.image;
        crystal_full = crystal.image2;
    }
    
    //NHẬN VÀO Ô TEXT
    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    
    // VỄ Ô TEXT RA MÀN HÌNH
    public void draw(Graphics2D g2){
        
        this.g2 = g2;
        
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }
    
    public void  drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;
        
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y,null);
            i++;
            x += gp.tileSize;
        }
        
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;
        
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y,null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y,null);
            }
            i++;
            x += gp.tileSize;
        }
        
        x = (gp.tileSize/2) -5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        
        while (i < gp.player.maxMana) {            
            g2.drawImage(crystal_blank, x, y,null);
            i++;
            x += 35;
        }
        
        x = (gp.tileSize/2) -5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        
        while (i < gp.player.mana) {            
            g2.drawImage(crystal_full, x, y,null);
            i++;
            x += 35;
        }
    }
    
    public void drawTitleScreen(){
        
        if(titleScreenState == 0){
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));

        String text = "Cậu bé da xanh";
        
        int x = getXForCenterText(text);
        int y = gp.tileSize * 3;
        
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        
        x = gp.screenWidth/2 - gp.tileSize;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        
        g2.setFont(gp.getFont().deriveFont(Font.BOLD, 38F));
        
        text = "Game mới";
        x = getXForCenterText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x - gp.tileSize, y);
        }
        
        text = "Vào game";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x- gp.tileSize, y);
        }
        
        text = "Thoát";
        x = getXForCenterText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x- gp.tileSize, y);
        }      
        } 
        else if(titleScreenState == 1){
            
            g2.setFont(g2.getFont().deriveFont(38F));
            
            String text = "Xanh";
            g2.setColor(Color.green);
            int x = getXForCenterText(text);
            int y = gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }
         
            text = "Đỏ";
            g2.setColor(Color.red);
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Vàng";
            g2.setColor(Color.yellow);
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Hồng";
            g2.setColor(Color.pink);
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Lam";
            g2.setColor(Color.blue);
            x = getXForCenterText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 4){
                g2.drawString(">", x-gp.tileSize, y);
            }
            
            text = "Qoay lại";
            g2.setColor(Color.white);
            x = getXForCenterText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if(commandNum == 5){
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }
    
    public void drawDialogueScreen(){
        
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - gp.tileSize*4;
        int height = gp.tileSize*4; 
        
        drawSubWindown(x, y, width, height);
        
        x += gp.tileSize;
        y += gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,26F));
        for(String line : currentDialogue.split("/n")){
            g2.drawString(line, x, y);
            y += 40; 
        }
    }
    
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        
        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);
                
                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i,counter);
                messageY += 50;
                
                if(messageCounter.get(i) > 120){
                    messageCounter.remove(i);
                    message.remove(i);
                }
            }
        }
    }
    
    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";
        int x = getXForCenterText(text);
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - length/2;
        
        int y = gp.screenHeight/2;
        
        g2.drawString(text, x, y);
    }
    
    public void drawCharacterScreen(){
        final int frameX = 0;
        final int frameY = 0;
        final int frameWidth = gp.tileSize *8;
        final int frameHeight = gp.tileSize*12;
        drawSubWindown(frameX, frameY, frameWidth, frameHeight);
        
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));
        
        int textX = frameX + 20;
        int textY = frameY +gp.tileSize;
        final int lineHeight = 42;
        
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
         g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
         g2.drawString("Coin", textX, textY);
        textY += lineHeight;
         g2.drawString("Weapon", textX, textY + 10);
        textY += lineHeight;
         g2.drawString("Shield", textX, textY + 10);
        textY += lineHeight;
        
        //tham so
        int tailX = (frameX + frameWidth) - 30;
        textY = frameY + gp.tileSize;
        
        String value;
        
        value = String.valueOf(gp.player.level);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;       
        value = String.valueOf(gp.player.life+"/"+gp.player.maxLife);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.mana+"/"+gp.player.maxMana);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
                value = String.valueOf(gp.player.exp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
                value = String.valueOf(gp.player.nextLevelExp);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
                value = String.valueOf(gp.player.coin);
        textX = getXForAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 16, null);
        textY += lineHeight;
        g2.drawImage(gp.player.currenShield.down1, tailX - gp.tileSize, textY -16, null);
        textY += lineHeight;
    }
    
    public void drawGameOverScreen(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        int x;
        int y;
        String text = "Game over";
        
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
        g2.setColor(Color.black);
        x = getXForCenterText(text);
        y = gp.tileSize*5;
        g2.drawString(text, x, y);
        
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        
        text = "Chơi lại";
        g2.setFont(g2.getFont().deriveFont( 38f));
        x = getXForCenterText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x- gp.tileSize, y);
        }
        
        text = "Thoát";
        x = getXForCenterText(text);
        y += 55;
        g2.drawString(text, x, y);     
        if(commandNum == 1){
            g2.drawString(">", x- gp.tileSize, y);
        }
    }
    
    public void drawInventory(){
        
        int frameX = gp.tileSize * 12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 7;
        int frameHeight = gp.tileSize * 5;
        drawSubWindown(frameX, frameY, frameWidth, frameHeight);
        
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+2;
        
        for(int i = 0 ; i < gp.player.inventory.size(); i++){
            
            if(gp.player.inventory.get(i) == gp.player.currentWeapon || 
                    gp.player.inventory.get(i) == gp.player.currenShield){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            
            g2.drawImage(gp.player.inventory.get(i).down1, slotX+1, slotY+1,null);
            slotX += slotSize;
            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = slotSize;
        int cursorHeight = slotSize;
        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
        
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;
        
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(24F));
        
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawSubWindown(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            for(String line : gp.player.inventory.get(itemIndex).description.split("/n")){
            g2.drawString(line, textX, textY);
            textY += 32;
            }
        }
        
    }
    
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow *5);
        return itemIndex;
    }
    
    public void drawSubWindown(int x, int y, int width, int height){
        
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    
    public  int getXForCenterText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    
    public  int getXForAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}

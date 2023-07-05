/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author ntn19
 */
//LỚP THỰC THỂ. LỚP CHUNG CỦA CÁC THỰC THỂ
public class Entity {
    
    GamePanel gp;
    
    //KHỞI TẠO 2 BIẾN CÔNG KHAI LƯU TRỮ VỊ TRÍ CỦA PLAYER SO VỚI BẢN ĐỒ THẾ GIỚI
    //         2 BIẾN SPEED LƯU TRỮ TỐC ĐỘ CỦA PLAYER 
    public int worldX, worldY;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int value;
    public Entity currentWeapon;
    public Entity currenShield;
    public Projectile projectile;
    
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    
    //KHỞI TẠO BIẾN BufferedImage ĐỂ LƯU LINK HÌNH ẢNH.
    //KHỞI TẠO BIẾT directioN ĐỂ ĐỌC CHUYỂN ĐỘNG: LÊN, XUỐNG, TRÁI, PHẢI.
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackLeft1, attackLeft2, 
            attackRight1, attackRight2, attackDown1, attackDown2;
    public String direction = "down";
    
    //KHỞI TẠO BIẾN ĐẾM CHO HÀNH ĐỘNG DI CHUYỂN
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //KHỞI TẠO Rectangle CÓ TÁC DỤNG TẠO 1 HÌNH CHỮ NHẬT TRONG PLAYER ĐỂ BẮT SỰ KIỆN VA CHẠM CỦA PLAYER
    //KHỞI TẠO 2 BIẾN solidAreaDefaultX, solidAreaDefaultY; ĐỂ LƯU TRỮ TỌA ĐỘ X, Y CỦA HCN TRONG PLAYER
    public Rectangle solidArea = new Rectangle(0,0 , 48, 48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    
    //KHỞI TẠO BIẾN CHO PHÉP VA CHẠM
    public  boolean collisionOn = false;
    public int actionLookCounter = 0;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    
    public int shotAvailableCounter = 0;
    public int invincibleCenter = 0; 
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    
    public int type; // 0 = player, 1= npc, 2 = monster 
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_mon = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public void speak(){
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void setAction(){}
    
    public void damageReaction(){}
    
    public void use(Entity entity){}
    
    public void checkDrop(){}
    
    public void dropItem(Entity droppedItem){
        for(int i =0; i < gp.obj.length; i++){
            if(gp.obj[i] == null){
                gp.obj[i] = droppedItem;
                gp.obj[i].worldX = worldX;
                gp.obj[i].worldY = worldY;
                break;
            }
        }
    }
    
    public Color setParticleColor(){
        Color color = null;
        return color;
    }
        
    public int setParticleSpeed(){
        int speed = 0;
        return speed;
    }
        
    public int setParticleSize(){
        int size = 0;
        return size;
    }
        
    public int setPartileMaxLife(){
        int maxLife = 0;
        return maxLife;
    }
    
    public void generateParticle( Entity target, Entity generate){
        Color color = target.setParticleColor();
        int speed = target.setParticleSpeed();
        int size = target.setParticleSize();
        int maxLife = target.setPartileMaxLife();
        
        Particle p1 = new Particle(gp, generate, color, speed, size, maxLife, -2, -1);
        Particle p2 = new Particle(gp, generate, color, speed, size, maxLife, 2, -1);
        Particle p3 = new Particle(gp, generate, color, speed, size, maxLife, 2, 1);
        Particle p4 = new Particle(gp, generate, color, speed, size, maxLife, -2, 1);
        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    
    public void update(){
        if(actionLookCounter == 120){
            setAction();
            actionLookCounter = 0;
        }
        actionLookCounter++;
        
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.mon);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if(this.type == type_mon && contactPlayer == true){
            damagePlayer(attack);
        }
        
         //NẾU CHO PHÉP VA CHẠM THÌ ĐI XUYÊN QUA
            if(collisionOn == false){
                
                switch (direction) {   
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;   
                }
            }
            
            //QUA MỖI 10 KHUNG HÌNH(10 LẦN UPDATE VÀ DRAW) THÌ CHUYỂN HÌNH ẢNH
            spriteCounter++;
            if(spriteCounter >= 10){
            
                if(spriteNum == 1){
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
            spriteCounter = 0;
            }
            
            if(invincible == true){
                invincibleCenter++;
            if(invincibleCenter >= 40){
                invincible = false;
                invincibleCenter = 0;
            }
        }
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
    }
    
    public void draw(Graphics2D g2){
            
        BufferedImage image = null;
                    
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
           worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
           worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ){              
        
        switch (direction) {
            case "up":
                if(spriteNum == 1){image = up1;}
                if(spriteNum == 2){image = up2;}
                break;
            case "down":
                if(spriteNum == 1){image = down1;}
                if(spriteNum == 2){image = down2;}
                break;
            case "left":
                if(spriteNum == 1){image = left1;}
                if(spriteNum == 2){image = left2;}
                break;
            case "right":
                if(spriteNum == 1){image = right1;}
                if(spriteNum == 2){image = right2;}
                break;
            }
        
        if(type == type_mon && hpBarOn == true){
            
            double oneScale = (double)gp.tileSize/maxLife;
            double hpBarValue = oneScale*life;
            
            g2.setColor(new Color(35,35,35));
            g2.fillRect(screenX - 1, screenY - 6, gp.tileSize + 2, 12);
            
            g2.setColor(new Color(255,0,30));
            g2.fillRect(screenX, screenY - 5, (int) hpBarValue, 10);
            
            hpBarCounter++;
            if(hpBarCounter > 300){
                hpBarCounter = 0;
                hpBarOn = false;
            }
            
            g2.setFont(gp.ui.font);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        
        for(int i = 0; i < gp.player.damageMon.size(); i++){
            if(gp.player.damageMon.get(i) != null){
                
                
                g2.setColor(new Color(240,90,80));
                g2.drawString(gp.player.damageMon.get(i), screenX, screenY -15);
                
                int counter = gp.player.damageMonCounter.get(i) + 1;
                gp.player.damageMonCounter.set(i,counter);
                
                if(gp.player.damageMonCounter.get(i) > 30){
                    gp.player.damageMonCounter.remove(i);
                    gp.player.damageMon.remove(i);
                }
            }
        }
        }
        
        if(invincible == true){
            hpBarOn = true;
            hpBarCounter = 0;
            changeAlpha(g2, 0.4f);
        }
        if(dying == true){
            dyingAnimation(g2);
        }
        g2.drawImage(image, screenX, screenY, null);
            changeAlpha(g2, 1f);
        }
    }
    
    public void damagePlayer(int attack){
        if(gp.player.invincible == false){
                gp.playSE(6);
            int damage = attack - gp.player.defense;
            if(damage < 0){
                    damage = 0;
                }
                gp.player.life -= damage;
                gp.player.invincible = true;
            }
    }
    
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i = 5;
        if(dyingCounter <= i){ changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <= i*2){ changeAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){ changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){ changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){ changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){ changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){ changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){ changeAlpha(g2,1f);}
        if(dyingCounter > i*8){
            alive = false;
        }
    }
    
    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    
    //CÀI ĐẶT hÌNH ẢNH HÀNH ĐỘNG CỦA NHÂN VẬT. KHỞI TẠO SẴN CHIỀU NGANG CHIỀU DỌC CỦA HÌNH
    public BufferedImage setup(String imagePath, int width, int height){
        
        UtilityTool uTool = new UtilityTool(); //ĐỂ KHỞI TẠO KÍCH THƯỚC CHO ẢNH
        BufferedImage image = null; // ĐỂ LƯU LINK ẢNH
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));// LẤY LINK CỦA HÌNH
            image = uTool.scaledImage(image, width, height); // LẤY KÍCH THƯỚC CỦA HÌNH
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}

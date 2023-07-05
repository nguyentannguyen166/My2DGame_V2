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
import main.KeyHandler;
import main.UtilityTool;
import monsters.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

/**
 *
 * @author ntn19
 */
public class Player extends Entity{
    
    KeyHandler keyH;
    
    //KHỞI TẠO 2 BIẾT ĐỂ LƯU TRỮ VỊ TRÍ GIỮA MÀN HÌNH CỦA PLAYER
    public final int screenX;
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public ArrayList<String> damageMon = new ArrayList<>();
    public ArrayList<Integer> damageMonCounter = new ArrayList<>();
    
    public ArrayList<String> expPlayer = new ArrayList<>();
    public ArrayList<Integer> expPlayerCounter = new ArrayList<>();
    
    public final int maxInventorySize = 20;
    
    //HÀM TẠO CÓ THAM SỐ. PLAER PHẢI IN RA MÀN HÌNH VÀ PHẢI DI CHUYỂN QUA CÁC PHÍM
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        
        //TÌM VỊ TRÍ GIỮA MÀN HÌNH CHO PLAYER
        screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        
        //KHỞI TẠO HÌNH CHỮ NHẬT
        solidArea = new Rectangle();
        
        //TỌA ĐỘ X,Y CỦA HÌNH CHỮ NHẬT TRONG PLAYER SO VỚI PLAYER
        solidArea.x = 8;
        solidArea.y = 16;
        
        //LƯU LẠI
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        //KHỞI TẠO CHIỀU NGANH VÀ CHIỀU DỌC HCN
        solidArea.width = 32;
        solidArea.height = 32;
        
        //GỌI 2 HÀM ĐỂ CÓ THỂ CHẠY KHI GAME KHỞI ĐỘNG
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItem();
    }
    
    //VỊ TRÍ TỐC ĐỘ CỦA NGƯỜI CHƠI KHI GAME VỪA VÀO
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 17;
        worldY = gp.tileSize * 20;
        speed = 4;
        direction = "right";
        
        //PLAYER STATUS
        level = 1;
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentWeapon = new OBJ_Axe(gp);
        currenShield = new OBJ_Shield_Wood(gp);
        projectile = new OBJ_Fireball(gp);
//        projectile = new OBJ_Rock(gp);
        attack = getAttack();
        defense = getDefense();
        invincible = false;
    }
    
    public void setDefaultPositions(){
        worldX = gp.tileSize * 17;
        worldY = gp.tileSize * 20;
        invincible = false;
        direction = "right";
    }
    
    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
    } 
    
    public void setItem(){
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currenShield);
    }
    
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        return attack = strength + currentWeapon.attackValue;
    }
    
    public int getDefense(){
        return defense = dexterity + currenShield.defenseValue;
    }
    
    //CÀI ĐẶT hÌNH ẢNH HÀNH ĐỘNG CỦA PLAYER. GỌI HÀM SETUP VÀ CHUYỀN THAM SỐ
    public void getPlayerImage(){
          
        down1 = setup("/player/boy_down_1.png",gp.tileSize,gp.tileSize);
        down2 = setup("/player/boy_down_2.png",gp.tileSize,gp.tileSize);
        left1 = setup("/player/boy_left_1.png",gp.tileSize,gp.tileSize);
        left2 = setup("/player/boy_left_2.png",gp.tileSize,gp.tileSize);
        right1 = setup("/player/boy_right_1.png",gp.tileSize,gp.tileSize);
        right2 = setup("/player/boy_right_2.png",gp.tileSize,gp.tileSize);
        up1 = setup("/player/boy_up_1.png",gp.tileSize,gp.tileSize);
        up2 = setup("/player/boy_up_2.png",gp.tileSize,gp.tileSize);
        
    }
    
    public void getPlayerAttackImage(){
        
        if(currentWeapon.type == type_sword){
        attackDown1 = setup("/player/boy_attack_down_1.png",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("/player/boy_attack_down_2.png",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("/player/boy_attack_left_1.png",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("/player/boy_attack_left_2.png",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("/player/boy_attack_right_1.png",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("/player/boy_attack_right_2.png",gp.tileSize*2,gp.tileSize);
        attackUp1 = setup("/player/boy_attack_up_1.png",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("/player/boy_attack_up_2.png",gp.tileSize,gp.tileSize*2);
        }
        if(currentWeapon.type == type_axe){
        attackDown1 = setup("/player/boy_axe_down_1.png",gp.tileSize,gp.tileSize*2);
        attackDown2 = setup("/player/boy_axe_down_2.png",gp.tileSize,gp.tileSize*2);
        attackLeft1 = setup("/player/boy_axe_left_1.png",gp.tileSize*2,gp.tileSize);
        attackLeft2 = setup("/player/boy_axe_left_2.png",gp.tileSize*2,gp.tileSize);
        attackRight1 = setup("/player/boy_axe_right_1.png",gp.tileSize*2,gp.tileSize);
        attackRight2 = setup("/player/boy_axe_right_2.png",gp.tileSize*2,gp.tileSize);
        attackUp1 = setup("/player/boy_axe_up_1.png",gp.tileSize,gp.tileSize*2);
        attackUp2 = setup("/player/boy_axe_up_2.png",gp.tileSize,gp.tileSize*2);
        }
    }
    
    public void update(){
        
        if(attacking == true){
            attacking();
        }
        //BẮT SỰ KIỆN NHẤN NÚT
        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){
            
            if(keyH.upPressed == true){
                direction = "up";
            }else if(keyH.downPressed == true){
                direction = "down";
            }else if(keyH.leftPressed == true){
                direction = "left";
            }else if(keyH.rightPressed == true){
                direction = "right";
            } 
            
            collisionOn = false;//CHO PHÉP VA CHẠM
            gp.cChecker.checkTile(this);// CHECK VA CHẠM CỦA PLAYER VÀ ĐỊA HÌNH
            
            int objIndex = gp.cChecker.checkObject(this, true); //CHECK VA CHẠM CỦA PLAYER VỚI VẬT PHẨM 
            pickUpObject(objIndex);//GỌI HÀM
            
            //check va cham npc
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);
            
            int monIndex = gp.cChecker.checkEntity(this, gp.mon);
            contactMON(monIndex);
            
            int iTile = gp.cChecker.checkEntity(this, gp.iTile);
            
            gp.eHandler.checkEven();          
            
            //NẾU CHO PHÉP VA CHẠM THÌ ĐI XUYÊN QUA
            if(collisionOn == false && keyH.enterPressed == false){
                
                switch (direction) {   
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;   
                }
            }
            
            if(keyH.enterPressed == true && attackCanceled == false){
                gp.playSE(7);
                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            
            gp.keyH.enterPressed = false;
            
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
        }
        
        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true){
            projectile.set(worldX, worldY, direction, true, this);            
            gp.projectileList.add(projectile);            
            gp.playSE(10);
            projectile.subtractResource(this);
            shotAvailableCounter = 0;           
        }
        
            if(invincible == true){
                invincibleCenter++;
            if(invincibleCenter >= 60){
                invincible = false;
                invincibleCenter = 0;
            }
        }
            if(shotAvailableCounter < 30){
                shotAvailableCounter++;
            }
            
        if(life > maxLife){
            life = maxLife;
        }
        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
        }
    }
    
    public void attacking(){
        spriteCounter++;
        
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;
            
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int soliAreaWidth = solidArea.width;
            int soliAreaHeight = solidArea.height;
            
            switch (direction) {   
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;   
            }
            
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            
            int monIndex = gp.cChecker.checkEntity(this, gp.mon);            
            damageMonster(monIndex, attack);
            
            int iTileIndext = gp.cChecker.checkEntity(this, gp.iTile);
            damageInteractiveTile(iTileIndext);
            
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = soliAreaWidth;
            solidArea.height = soliAreaHeight;
        }
        if(spriteCounter >25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }    
    }
    
    public void damageMonster(int i, int attack){
        if(i != 999){
            if(gp.mon[i].invincible == false){
                
                gp.playSE(5);
                
                int damage = attack - gp.mon[i].defense;
                if(damage < 0){
                    damage = 0;
                }
                gp.mon[i].life -= damage;
                gp.ui.addMessage("-"+damage);
                //
                addMess("-"+damage);
                //
                gp.mon[i].invincible = true;
                gp.mon[i].damageReaction();                
                
                if(gp.mon[i].life <= 0){
                    gp.mon[i].dying = true;
                    gp.ui.addMessage("Killed the "+ gp.mon[i].name);
                    gp.ui.addMessage("Exp +"+ gp.mon[i].exp);
                    //
                    addExp("+"+gp.mon[i].exp);
                    //
                    exp += gp.mon[i].exp;
                    checkLevelUp();
                }
            }
        }
    }
    
    public void damageInteractiveTile(int i){
        if( i != 999 && gp.iTile[i].destrructible == true 
                && gp.iTile[i].invincible == false && gp.iTile[i].isCorrectItem(this) == true){
            gp.iTile[i].playSE();
            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;
            generateParticle(gp.iTile[i], gp.iTile[i]);
            if(gp.iTile[i].life == 0){
                gp.iTile[i] = gp.iTile[i].getDextroyedForm();
            }
        }
    }
    
    public void addMess(String text){
        damageMon.add(text);
        damageMonCounter.add(0);
    }
    
    public void addExp(String text){
        expPlayer.add(text);
        expPlayerCounter.add(0);
    }
    
    public void checkLevelUp(){
        if(exp >= nextLevelExp){
        gp.ui.addMessage("Level Up");
        //
        addExp("Level Up");
        //
        level++;
        nextLevelExp = nextLevelExp*2;
        maxLife += 2;
        life += 2;
        strength++;
        dexterity++;
        attack = getAttack();
        defense = getDefense();
        
        gp.playSE(8);
        }
    }
    
    public void selectItem(){
        int itemIndext = gp.ui.getItemIndexOnSlot();
        if(itemIndext < inventory.size()){
            Entity selectedItem = inventory.get(itemIndext);
            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if(selectedItem.type == type_shield){
                currenShield = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndext);
            }
        }
    }
    
    // SỬ LÝ SAU VA CHẠM VỚI VẬT PHẨM
    public void pickUpObject(int i){
        if(i != 999){           
            if(gp.obj[i].type == type_pickupOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            else{
            String text;
            if(inventory.size() != maxInventorySize){
                inventory.add(gp.obj[i]);
                gp.playSE(1);
                text = "Bạn nhặt được x1 "+gp.obj[i].name;
                gp.obj[i] = null;
            }
            else{
                text = "Rương đã đầy";
            }
            gp.ui.addMessage(text);
            }
        }
    }
    
    //SỬ LÝ VA CHẠM VỚI NPC
    public void interactNPC(int i){
        
        if(gp.keyH.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }
    
    public void contactMON(int i){
        if(i != 999){
            if(invincible == false && gp.mon[i].dying == false){
            gp.playSE(6);
                
            int damage = gp.mon[i].attack - defense;
            if(damage < 0){
                    damage = 0;
                }
            life -= damage;
            invincible = true;
            }
        }
    }
    
    // VẼ PLAYER VÀ HÌNH ẢNH HOẠT ĐỘNG CỦA PLAYER
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        
        switch (direction) {
            case "up":
                if(attacking == false){
                if(spriteNum == 1){image = up1;}
                if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
                tempScreenY = screenY - gp.tileSize;
                if(spriteNum == 1){image = attackUp1;}
                if(spriteNum == 2){image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false){
                if(spriteNum == 1){image = down1;}
                if(spriteNum == 2){image = down2;}
                }                
                if(attacking == true){
                if(spriteNum == 1){image = attackDown1;}
                if(spriteNum == 2){image = attackDown2;}
                }
                break;
            case "left":
                if(attacking == false){
                if(spriteNum == 1){image = left1;}
                if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
                tempScreenX = screenX - gp.tileSize;
                if(spriteNum == 1){image = attackLeft1;}
                if(spriteNum == 2){image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking == false){
                if(spriteNum == 1){image = right1;}
                if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                if(spriteNum == 1){image = attackRight1;}
                if(spriteNum == 2){image = attackRight2;}
                }
                break;
        }
        
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        
            g2.setFont(gp.ui.font);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));
        
        for(int i = 0; i < expPlayer.size(); i++){
            if(expPlayer.get(i) != null){
                int expY = screenY;
                if(expPlayer.get(i) == "Level Up"){
                    g2.setColor(Color.red);
                    g2.drawString(expPlayer.get(i), screenX - 25, expY -50);
                                    int counter = expPlayerCounter.get(i) + 1;
                expPlayerCounter.set(i,counter);
                expY -= 30;
                
                if(expPlayerCounter.get(i) > 120){
                    expPlayerCounter.remove(i);
                    expPlayer.remove(i);
                }
                }
                else{
                g2.setColor(Color.green);
                g2.drawString(expPlayer.get(i), screenX, expY -15);
                                int counter = expPlayerCounter.get(i) + 1;
                expPlayerCounter.set(i,counter);
                expY -= 30;
                
                if(expPlayerCounter.get(i) > 30){
                    expPlayerCounter.remove(i);
                    expPlayer.remove(i);
                }
                }
               
            }
        }
    }
}

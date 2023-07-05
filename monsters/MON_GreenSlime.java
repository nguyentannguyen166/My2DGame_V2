/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monsters;

import entity.Entity;
import java.util.Random;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Fireball;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;
import object.OBJ_Shield_Blue;

/**
 *
 * @author ntn19
 */
public class MON_GreenSlime extends Entity{
    
    GamePanel gp;
    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
        name = "Green Slime";
        speed = 1;
        type = type_mon;
        maxLife = 10;
        life = maxLife;
        exp = 2;
        attack = 4;
        defense = 0;
        projectile = new OBJ_Rock(gp);
        
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/greenslime_down_1.png",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/greenslime_down_2.png",gp.tileSize,gp.tileSize);
    }
    
    public void setAction(){
        Random random = new Random();
        int i = random.nextInt(100)+1;
        
        if(i <= 25){
            direction = "up";
        }
        else if(i > 25 && i <= 50){
            direction = "down";
        }
        else if(i > 50 && i <= 75){
            direction = "left";
        }
        else if(i > 75 && i <= 100){
            direction = "right";
        }
        
        i = random.nextInt(100)+1;
        if(i > 50 && projectile.alive == false && shotAvailableCounter == 30){
            projectile.set(worldX, worldY, direction, true, this);
            gp.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    
    public void damageReaction(){
        actionLookCounter = 0;
        direction = gp.player.direction;
    }
    
    public void checkDrop(){
        int i = new Random().nextInt(150)+1;
        
        if(i <= 50){
        }
        if(i > 50 && i <= 75){
            dropItem(new OBJ_Heart(gp));
        }
        if(i > 75 && i <= 100){
            dropItem(new OBJ_ManaCrystal(gp));
        }
        if(i > 100 && i <= 125){
            dropItem(new OBJ_Shield_Blue(gp));
        }
        if(i > 125 && i< 150){
            dropItem(new OBJ_Coin_Bronze(gp));
        }
    }
}

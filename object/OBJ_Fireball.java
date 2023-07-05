/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import entity.Projectile;
import java.awt.Color;
import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class OBJ_Fireball extends Projectile{
    
    GamePanel gp;
    
    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;
        
        name = "Fire Ball";
        speed = 7;
        maxLife = 60;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    
    public void getImage(){
        up1 = setup("/projectiles/fireball_up_1.png",gp.tileSize,gp.tileSize);
        up2 = setup("/projectiles/fireball_up_2.png",gp.tileSize,gp.tileSize);
        down1 = setup("/projectiles/fireball_down_1.png",gp.tileSize,gp.tileSize);
        down2 = setup("/projectiles/fireball_down_2.png",gp.tileSize,gp.tileSize);
        left1 = setup("/projectiles/fireball_left_1.png",gp.tileSize,gp.tileSize);
        left2 = setup("/projectiles/fireball_left_2.png",gp.tileSize,gp.tileSize);
        right1 = setup("/projectiles/fireball_right_1.png",gp.tileSize,gp.tileSize);
        right2 = setup("/projectiles/fireball_right_2.png",gp.tileSize,gp.tileSize);   
    }
    
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        if(user.mana >= useCost ){
            haveResource = true;
        }
        return haveResource;
    }
    
    public void subtractResource(Entity user){
        user.mana -= useCost;
    }
    
        public Color setParticleColor(){
            Color color = new Color(240,50,0);
            return color;
        }
        
        public int setParticleSpeed(){
            int speed = 1;
            return speed;
        }
        
        public int setParticleSize(){
            int size = 10;
            return size;
        }
        
        public int setPartileMaxLife(){
            int maxLife = 20;
            return maxLife;
        }   
}

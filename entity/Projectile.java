/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class Projectile extends Entity{
    
    Entity user;
    
    public Projectile(GamePanel gp) {
        super(gp);
    }
    
    public void set(int wordX, int wordY, String direction, boolean alive, Entity user){
        this.worldX = wordX;
        this.worldY = wordY;
        this.alive = alive;
        this.direction = direction;
        this.user = user;
        this.life = this.maxLife;
    }
    
    public void update(){
        
        if(user == gp.player){
            int monIndext = gp.cChecker.checkEntity(this, gp.mon);
            if(monIndext != 999){
                int attack = gp.player.strength + this.attack;
                gp.player.damageMonster(monIndext, attack);
                alive = false;
                generateParticle(user.projectile, gp.mon[monIndext]);
            }
        }
        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(contactPlayer == true && gp.player.invincible == false){
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);
                alive = false;
            }
        }
        
        switch (direction) {
            case "up": worldY -= speed; break;
            case "left": worldX -= speed; break;
            case "down": worldY += speed; break;
            case "right": worldX += speed; break;
        }
        
        life--;
        if(life <= 0){
            alive = false;
        }
        
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
    
    public boolean haveResource(Entity user){
        boolean haveResource = false;
        return haveResource;
    }
    
    public void subtractResource(Entity user){}
    
}

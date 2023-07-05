/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile_interactive;

import entity.Entity;
import java.awt.Color;
import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class IT_DryTree extends InteractiveTile{
    
    GamePanel gp;
    
    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp,col,row);
        
        this.gp = gp;
        this.worldX = col * gp.tileSize;
        this.worldY = row * gp.tileSize;
        
        down1 = setup("/tile_interactives/drytree.png",gp.tileSize,gp.tileSize);
        destrructible = true;       
        life = 3;
    }
    
        public boolean isCorrectItem(Entity entity){
        boolean isCorrwctItem = false;
            if(entity.currentWeapon.type == type_axe){
                isCorrwctItem = true;
            }
        return isCorrwctItem;
        }
        
        public void playSE(){
        gp.playSE(11);
        }
    
        public InteractiveTile getDextroyedForm(){
        InteractiveTile tile = new IT_Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
        return tile;
        }
        
        public Color setParticleColor(){
            Color color = new Color(65,50,30);
            return color;
        }
        
        public int setParticleSpeed(){
            int speed = 1;
            return speed;
        }
        
        public int setParticleSize(){
            int size = 6;
            return size;
        }
        
        public int setPartileMaxLife(){
            int maxLife = 20;
            return maxLife;
        }
}

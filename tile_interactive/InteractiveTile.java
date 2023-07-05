/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile_interactive;

import entity.Entity;
import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class InteractiveTile extends Entity{
    
    GamePanel gp;
    public boolean destrructible = false;
    
    public InteractiveTile(GamePanel gp,int col, int row) {
        super(gp);
    }
    
    public boolean isCorrectItem(Entity entity){
        boolean isCorrwctItem = false;
        return isCorrwctItem;
    }
    
    public void playSE(){}
    
    public InteractiveTile getDextroyedForm(){
        InteractiveTile tile = null;
        return tile;
    }
    
    public void update() {
        
        if(invincible == true){
                invincibleCenter++;
            if(invincibleCenter >= 20){
                invincible = false;
                invincibleCenter = 0;
            }
        }
    }
}

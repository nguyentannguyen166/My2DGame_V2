/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class OBJ_Coin_Bronze extends Entity{
    
    GamePanel gp;
    
    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_pickupOnly;
        name = "Coin";
        down1 = setup("/objects/coin_bronze.png",gp.tileSize,gp.tileSize);
        value = 10;
    }
    
        public void use(Entity entity){
            gp.playSE(1);
            gp.ui.addMessage("coin +"+value);
            gp.player.coin += value;
    }
}

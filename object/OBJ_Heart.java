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
public class OBJ_Heart extends Entity{
    
    GamePanel gp;
    
    public OBJ_Heart(GamePanel gp){
        
        super(gp);
        this.gp = gp;
        
        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image = setup("/objects/heart_blank.png",gp.tileSize,gp.tileSize);
        image2 = setup("/objects/heart_full.png",gp.tileSize,gp.tileSize);
        image3 = setup("/objects/heart_half.png",gp.tileSize,gp.tileSize);
    }
    
    public void use(Entity user){
        gp.playSE(2);
        gp.ui.addMessage("Life +"+value);
        user.life += value;
    }
}

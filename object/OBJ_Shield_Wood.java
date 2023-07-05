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
public class OBJ_Shield_Wood extends Entity{
    
    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);
        
        type = type_shield;
        name = "Wood Shield";
        down1 = setup("/objects/shield_wood.png",gp.tileSize,gp.tileSize);
        defenseValue = 1;
        description = "["+ name +"]"+"/nTăng phòng thủ"; 
    }
    
}

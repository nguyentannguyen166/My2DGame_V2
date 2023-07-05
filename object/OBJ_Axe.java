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
public class OBJ_Axe extends Entity{
    
    public OBJ_Axe(GamePanel gp) {
        super(gp);
        
        type = type_axe;
        name = "Axe";
        down1 = setup("/objects/axe.png",gp.tileSize,gp.tileSize);
        attackValue = 3;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "["+ name +"]"+"/nTăng tấn công"; 
    }
    
}

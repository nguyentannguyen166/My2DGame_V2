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
public class OBJ_Potion_Red extends Entity{
    
    GamePanel gp;
    
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;
        
        type = type_consumable;
        name = "Red Potion";
        down1 = setup("/objects/potion_red.png",gp.tileSize,gp.tileSize);
        value = 5;
        description = "Hồi phục ngay "+value+" máu";
    }
    
    public void use(Entity entity){
        gp.gameState = gp.dialogueState;
        gp.ui.currentDialogue = "Bạn đã uống "+name+"\nvà được hồi "+value+" máu";
        entity.life += value;
        gp.playSE(2);
    }
    
}

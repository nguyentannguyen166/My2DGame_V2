/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tile_interactive;

import main.GamePanel;

/**
 *
 * @author ntn19
 */
public class IT_Trunk extends InteractiveTile{
    
    public IT_Trunk(GamePanel gp, int col, int row) {
        super(gp, col, row);
        
        this.gp = gp;
        this.worldX = col * gp.tileSize;
        this.worldY = row * gp.tileSize;
        
        down1 = setup("/tile_interactives/trunk.png",gp.tileSize,gp.tileSize);
        
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 0;
        solidArea.height = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultX = solidArea.y;
    }
    
}

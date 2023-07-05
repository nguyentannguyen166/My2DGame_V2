/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.NPC_OldMan;
import main.GamePanel;
import monsters.MON_GreenSlime;
import tile_interactive.IT_DryTree;

/**
 *
 * @author ntn19
 */
public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Heart(gp);
        gp.obj[0].worldX = gp.tileSize * 24;
        gp.obj[0].worldY = gp.tileSize * 21;
        
        gp.obj[1] = new OBJ_ManaCrystal(gp);
        gp.obj[1].worldX = gp.tileSize * 24;
        gp.obj[1].worldY = gp.tileSize * 24;
        
        gp.obj[2] = new OBJ_Coin_Bronze(gp);
        gp.obj[2].worldX = gp.tileSize * 24;
        gp.obj[2].worldY = gp.tileSize * 26;
        
        gp.obj[3] = new OBJ_Axe(gp);
        gp.obj[3].worldX = gp.tileSize * 24;
        gp.obj[3].worldY = gp.tileSize * 28;
        
        gp.obj[4] = new OBJ_Potion_Red(gp);
        gp.obj[4].worldX = gp.tileSize * 24;
        gp.obj[4].worldY = gp.tileSize * 30;
        
        gp.obj[5] = new OBJ_Shield_Blue(gp);
        gp.obj[5].worldX = gp.tileSize * 24;
        gp.obj[5].worldY = gp.tileSize * 33;
    }
    
    public void setNPC(){
        
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;
    }
    
    public void setMonster(){
        gp.mon[0] = new MON_GreenSlime(gp);
        gp.mon[0].worldX = gp.tileSize * 23;
        gp.mon[0].worldY = gp.tileSize * 39;
        
        gp.mon[1] = new MON_GreenSlime(gp);
        gp.mon[1].worldX = gp.tileSize *24;
        gp.mon[1].worldY = gp.tileSize * 39;
        
        gp.mon[2] = new MON_GreenSlime(gp);
        gp.mon[2].worldX = gp.tileSize * 25;
        gp.mon[2].worldY = gp.tileSize * 39;
        
        gp.mon[3] = new MON_GreenSlime(gp);
        gp.mon[3].worldX = gp.tileSize * 23;
        gp.mon[3].worldY = gp.tileSize * 40;
        
        gp.mon[4] = new MON_GreenSlime(gp);
        gp.mon[4].worldX = gp.tileSize * 24;
        gp.mon[4].worldY = gp.tileSize * 40;
        
        gp.mon[5] = new MON_GreenSlime(gp);
        gp.mon[5].worldX = gp.tileSize * 25;
        gp.mon[5].worldY = gp.tileSize * 40;
    }
    
    public void setInteractiveTile(){
        int i=0;
        gp.iTile[i] = new IT_DryTree(gp,22,23);i++;
        gp.iTile[i] = new IT_DryTree(gp,22,24);i++;
        gp.iTile[i] = new IT_DryTree(gp,22,25);i++;
        gp.iTile[i] = new IT_DryTree(gp,22,26);i++;

    }    
}

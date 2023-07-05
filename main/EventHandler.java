/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author ntn19
 */
public class EventHandler {
    GamePanel gp;
    
    EventRect eventRect[][];
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    public EventHandler(GamePanel gp){
        this.gp = gp;
        
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        
        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {            
                    
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;   
            
            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }
    
    public void checkEven(){
        
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(yDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        if(canTouchEvent == true){
            if(hit(23,36,"down") == true){
                damagePit(23,36,gp.dialogueState);
            }
            if(hit(21,19,"up") == true){
                healingPool(21,19,gp.dialogueState);
            }
        }
        
        if(hit(23,12,"up") == true){
            dichChuyen(gp.dialogueState);
        }
    }
    
    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
        
        if(gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false){
            if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")){
                hit = true;
                
                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x =  eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y =  eventRect[col][row].eventRectDefaultY;
        
        return hit;
    }
    
    public void damagePit(int col, int row, int gameState){
        gp.gameState = gameState;
        gp.ui.currentDialogue = "Bạn đã bị hố";
        gp.player.life--;
 //       eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    
    public void healingPool(int col, int row, int gameState){
        if(gp.keyH.enterPressed == true){
            gp.player.attackCanceled = true;
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Bạn đã uống nước thánh /nbạn được hồi đầy máu và mana";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;
            gp.aSetter.setMonster();
        }
    }
    
    public void dichChuyen(int gameState){
        if(gp.keyH.enterPressed == true){
            gp.player.attackCanceled = true;
            gp.player.worldX = 36*gp.tileSize;
            gp.player.worldY = 40*gp.tileSize;
            gp.gameState = gameState;
            gp.ui.currentDialogue = "Dịch chuyển đến địa điểm mới thành công";
        }
    }
}

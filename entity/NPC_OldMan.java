/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Random;
import main.GamePanel;
/**
 *
 * @author ntn19
 */
public class NPC_OldMan extends Entity{
    
    public NPC_OldMan(GamePanel gp){
        super(gp);
        
        //TẠO ĐỘ HCN TRONG CPC
        solidArea.x = 8;
        solidArea.y = 16;
        
        //LƯU LẠI
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        //KHỞI TẠO CHIỀU NGANH VÀ CHIỀU DỌC HCN
        solidArea.width = 32;
        solidArea.height = 32;
        
        direction = "down";
        speed = 1;
        
        getImage();
        setDialogues();
    }
    
    //CÀI ĐẶT hÌNH ẢNH HÀNH ĐỘNG CỦA NPC. GỌI HÀM SETUP VÀ CHUYỀN THAM SỐ
    public void getImage(){
          
        down1 = setup("/npc/oldman_down_1.png",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/oldman_down_2.png",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/oldman_left_1.png",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/oldman_left_2.png",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/oldman_right_1.png",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/oldman_right_2.png",gp.tileSize,gp.tileSize);
        up1 = setup("/npc/oldman_up_1.png",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/oldman_up_2.png",gp.tileSize,gp.tileSize);
        
    }
    
    public void setDialogues(){
        dialogues[0] = "Chào cháu";
        dialogues[1] = "Sao cháu lại đến mảnh đất island này";
        dialogues[2] = "Cháu muốn tìm kho báu ư. /nƯm...vậy cháu hãy đi tới hang động phía /nđông bắc hòn đảo";
        dialogues[3] = "Chúc cháu may mắn";
    }
    
    public void setAction(){
        Random random = new Random();
        int i = random.nextInt(100)+1;
        
        if(i <= 25){
            direction = "up";
        }
        else if(i > 25 && i <= 50){
            direction = "down";
        }
        else if(i > 50 && i <= 75){
            direction = "left";
        }
        else if(i > 75 && i <= 100){
            direction = "right";
        }
    }
    
    public void speak(){
        super.speak();
    }
}

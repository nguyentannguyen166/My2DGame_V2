/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;

/**
 *
 * @author ntn19
 */
public class CollisionChecker {
    
    GamePanel gp;
    
    //HÀM TẠO CÓ THAM SỐ. CHECKS VA CHẠM SẢY RA Ở MÀN HÌNH GAME NÊN CHUYỀN MÀN HÌNH GAME
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
    //CHECKS VA CHẠM CỦA PLAYER VÀ ĐỊA HÌNH. SỬ DỤNG VA CHẠM GIỮA 4 GÓC CỦA HCN TRONG PLAYER
    public void checkTile(Entity entity){
        
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        /*
            entity.worldX, entity.worldY LÀ TỌA ĐỘ CỦA PLAYER ĐẾN BẢN ĐỒ THẾ GIỚI
            entity.solidArea.x, entity.solidArea.Y LÀ TỌA ĐỘ CỦA HCN TRONG PLAYER ĐẾN CẠNH CỦA PLAYER
            entity.solidArea.width, entity.solidArea.height LÀ ĐỘ DÀI 2 CẠNH HCN TRONG PLAYER
        =>  entityLeftWorldX LÀ KHOẢNG CÁCH CẠNH BÊN TRÁI HCN ĐẾN CẠNH TRÁI BẢN ĐỒ THẾ GIỚI
            entityRightWorldX LÀ KHOẢNG CÁCH CẠNH BÊN PHẢI HCN ĐẾN CẠNH TRÁI BẢN ĐỒ THẾ GIỚI
            entityTopWorldY LÀ KHOẢNG CÁCH CẠNH BÊN TRÊN HCN ĐẾN BÊN TRÊN BẢN ĐỒ THẾ GIỚI
            entityBottomWorldY LÀ KHOẢNG CÁCH CẠNH BÊN DƯỚI HCN ĐẾN CẠNH BÊN TRÊN BẢN ĐỒ THẾ GIỚI
        */
        
        int entityLeftCol = entityLeftWorldX / gp.tileSize; // SỐ CỘT TỪ BÊN TRÁI HCN ĐẾN CẠNH TRÁI BẢN ĐỒ. LẤY PHẦN NGUYÊN
        int entityRightCol = entityRightWorldX / gp.tileSize;//SỐ CỘT TỪ BÊN PHẢI HCN ĐẾN CẠNH TRÁI BẢN ĐỒ. LẤY PHẦN NGUYÊN
        int entityTopRow = entityTopWorldY / gp.tileSize;//SỐ HÀNG TỪ BÊN TRÊN HCN ĐẾN CẠNH TRÊN BẢN ĐỒ. LẤY PHẦN NGUYÊN
        int entityBottomRow = entityBottomWorldY / gp.tileSize;//SỐ HÀNG TỪ BÊN DƯỚI HCN ĐẾN CẠNH TRÊN BẢN ĐỒ. LẤY PHẦN NGUYÊN        
        
        int tileNum1, tileNum2; // 2 ĐIỂM VA CHẠM TRÊN CÁC CẠNH HCN
        
        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
                /*
                    TÌM VỊ TRÍ VẬT BỊ VA TRẠM KHI PLAYER ĐI LÊN
                    KHI VA CHẠM VỚI VẬT THỂ THEO HƯỚNG LÊN. KHOẢNG CÁCH TỪ CẠNH TRÊN BẢN ĐỒ ĐẾN VẬT THỂ VÀ ĐẾN HCN TRONG PLAYER CÁCH NHAU 1 CỘT
                    NÊN KHI (entityBottomWorldY - entity.speed) / gp.tileSize KHOẢNG CÁCH PLAYER - 1 LƯỢNG NHỎ KHÔNG NHẤT THIẾT entity.speed.
                    THÌ KHI CHIA CHO gp.tileSize SẼ RA SỐ CỘT TỪ CẠNH TRÊN BẢN ĐỒ ĐẾN VẬT THỂ VÀ DƯ 1 ÍT KHOẢN CÁCH ĐẾN PLAYER. TA LẤY PHẦN NGUYÊN RA SỐ CỘT CỦA VẬT THỂ
                    CÒN SỐ HÀNG CỦA PLAYER SẼ BẰNG SỐ HÀNG CỦA VẬT THỂ
                */
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;    
        }
    }
    
    //HÀM CHÉCK VA CHẠM VỚI VẬT PHẨM
    public int checkObject(Entity entity, boolean player){
        
        int index = 999;
        
        for(int i = 0; i < gp.obj.length; i++){
            
            if(gp.obj[i] != null){
                
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
                
                 switch (entity.direction) {   
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;   
                }
                 
                if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                    if(gp.obj[i].collision == true){
                        entity.collisionOn = true;
                    }
                    if(player == true){
                        index = i;
                    }
                }
                                        
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }           
        }
        return index;       
    }
    
    //NPC HOẶC QOÁI VA CHẠM
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        
        for(int i = 0; i < target.length; i++){
            
            if(target[i] != null){
                
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
                
                 switch (entity.direction) {   
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;   
                }
                 
                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                    entity.collisionOn = true;
                    index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }           
        }
        return index;       
    }
    
    public boolean checkPlayer(Entity entity){
        
        boolean contactPlayer = false;
        
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
                
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
                
        switch (entity.direction) {   
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;   
        }
                 
        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.collisionOn = true;
            contactPlayer = true;
        }
                
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
    }
}

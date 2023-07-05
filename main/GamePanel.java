/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import object.AssetSetter;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import tile.TileManager;
import tile_interactive.InteractiveTile;

/**
 *
 * @author ntn19
 */
public class GamePanel extends JPanel implements Runnable{ 
        // CÀI ĐẶT MÀN HÌNH GAME
        final int originalTileSize = 16; //16x16 tile.
        final int  scale = 3;
        
        public final int tileSize = originalTileSize * scale; // 48x48
        public final int maxScreenCol = 20;
        public final int maxScreenRow = 12;
        public final int screenWidth = tileSize * maxScreenCol;
        public final int screenHeight = tileSize * maxScreenRow;
        
        //CÀI ĐẶT MÀN HÌNH THẾ GIỚI
        public final int maxWorldCol = 50;
        public final int maxWorldRow = 50;
        
        //FPS
        int FPS = 60;
        
        // KHỞI TẠO CLASS TẠO BẢN ĐỒ THẾ GIỚI
        // KHỞI TẠO CLASS BẮT SỰ KIỆN NHẤN PHÍM
        TileManager tileM = new TileManager(this);
        public KeyHandler keyH = new KeyHandler(this);
        
        //KHỞI TẠO CLASS PHÁT NHẠC
        Sound music = new Sound();
        Sound se = new Sound();
        
        // KHỞI TẠO CLASS CÔNG KHAI 1 CÁI CHECK VA CHẠM
        // KHỞI TẠO CLASS LƯU ĐƯỜNG LINK VẬT PHẨM
        public CollisionChecker cChecker = new CollisionChecker(this);
        public AssetSetter aSetter = new AssetSetter(this);
        
        // KHỞI TẠO CALSS CHUYÊN IN THÔNG BÁO RA MÀN HÌNH
        public UI ui = new UI(this);
        public EventHandler eHandler = new EventHandler(this);
        
        // TẠO LUỒNG CHẠY LIÊN TỤC KHI CHƯƠNG TRÌNH ĐƯỢC KHỞI ĐỘNG 
        Thread gameThread;
        
        // KHỞI TẠO CLASS NGƯỜI CHƠI VÀ CLASS VẬT PHẨM
        public Player player = new Player(this, keyH);
        public Entity obj[] = new Entity[50];
        public Entity npc[] = new Entity[10];
        public Entity mon[] = new Entity[10];
        public InteractiveTile iTile[] = new InteractiveTile[100];
        ArrayList<Entity> entityList = new ArrayList<>();
        public ArrayList<Entity> projectileList = new ArrayList<>();
        public ArrayList<Entity> particleList = new ArrayList<>();
        
        //GAME STATE
        public int gameState;
        public final int titleState = 0;
        public final int playState = 1;
        public final int pauseState = 2;
        public final int dialogueState = 3;
        public final int characterState = 4;
        public final int gameOverState = 6;
        
        //HÀM TẠO
        public GamePanel(){
            this.setPreferredSize(new Dimension(screenWidth,screenHeight));
            this.setBackground(Color.black);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH);
            this.setFocusable(true);
        }
        
        // HÀM ĐƯỢC TẠO RA ĐỂ CÀI ĐẶT TRƯỚC KHI GAME CHẠY. ĐƯỢC GỌI TỪ MAIN
        public void setupGame(){
            aSetter.setMonster();
            aSetter.setObject();
            aSetter.setNPC();
            aSetter.setInteractiveTile();
            gameState = titleState;
        }
        
        public void retry(){
            player.setDefaultPositions();
            player.restoreLifeAndMana();
            aSetter.setMonster();
            aSetter.setNPC();
        }
        
        public void restart(){
            player.setDefaultValues();
            aSetter.setMonster();
            aSetter.setObject();
            aSetter.setNPC();
            aSetter.setInteractiveTile();           
        }
        // HÀM ĐƯỢC TẠO RA ĐỂ KHỞI ĐỘNG THREAD, GIÚP GAME HOẠT ĐỘNG
        public void startGameThread(){
            gameThread = new Thread(this);
            gameThread.start();
        }
    
    // KHI startGameThread() ĐƯỢC BẬT SẼ TỰ ĐỘNG CHẠY HÀM NÀY
    @Override
    public void run() {
                    
            double drawInterval = 1000000000/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            
            
        while(gameThread != null){
            
            // CÂU LỆNH GIÚP GAME CHẠY 60 LẦN 1S. FPS 60
            currentTime = System.nanoTime();           
            delta += (currentTime - lastTime) / drawInterval;            
            lastTime = currentTime;
            
            if(delta >= 1){
            
            //GỌI HÀM update
            update();
            
            //GỌI HÀM paintComponent
            repaint();
            
            delta--;
            }
        }
        /*
            GIẢI THÍCH:
            currentTime LÀ THỜI GIAN BẮT ĐẦU VÒNG LẶP.
            lastTime LÀ THỜI GIAN TRƯỚC ĐÓ CỦA VÒNG LẶP TRƯỚC. currentTime luôn > lastTime
            TRONG 1 VONG LẶP LUÔN CÓ 1 KHOẢNG THỜI GIAN CHẠY TỪ lastTime -> currentTime LÀ currentTime - lastTime
            CHÚNG TA CỘNG TẤT CẢ KHOẢNG THỜI GIAN NÀY VỚI NHAU  delta += (currentTime - lastTime)
            NẾU TỔNG TẤT CẢ THỜI GIAN NÀY BẰNG 1/60GIÂY. TỨC LÀ 1 KHUNG HÌNH CỦA FPS:60. CHÚNG TA CHẠY TẠO 1 KHUNG HÌNH
            (delta += (currentTime - lastTime)) / drawInterval = 1 => delta += (currentTime - lastTime) = drawInterval(1/60GIÂY)
        */
    }
    
    //HÀM GIÚP THAY ĐỔI LIÊN TỤC
    public void update(){   
        
        //TRẠNG THÁI KHỞI ĐỘNG
        if(gameState == playState){
        //player
        player.update();
        //NPC
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                npc[i].update();
            }
        }    
        
        for(int i = 0; i < mon.length; i++){
            if(mon[i] != null){
                if(mon[i].alive == true && mon[i].dying == false){
                mon[i].update();
                }
                else if(mon[i].alive == false){
                mon[i].checkDrop();
                mon[i] = null;
                }
            }
        } 
        
        for(int i = 0; i < projectileList.size(); i++){
            if(projectileList.get(i) != null){
                if(projectileList.get(i).alive == true){
                projectileList.get(i).update();
                }
                else if(projectileList.get(i).alive == false){
                projectileList.remove(i);
                }
            }
        } 
        
        for(int i = 0; i < particleList.size(); i++){
            if(particleList.get(i) != null){
                if(particleList.get(i).alive == true){
                particleList.get(i).update();
                }
                else if(particleList.get(i).alive == false){
                particleList.remove(i);
                }
            }
        } 
        
        for(int i = 0; i < iTile.length; i++){
            if(iTile[i] != null){
                iTile[i].update();
            }
        }
                
        }
        //TRẠNG THAI TẠM DỪNG
        if(gameState == pauseState){
            //null
        }
    }
    
    //HÀM VẼ RA MÀN HÌNH
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;       
        
        if(gameState == titleState){
            ui.draw(g2);
        }
        else{
        //VẼ BẢN ĐỒ THẾ GIỚI
        tileM.draw(g2);
        
        for(int i = 0; i < iTile.length ; i++){
            if(iTile[i] != null){
                iTile[i].draw(g2);
            }      
        }
        
        entityList.add(player);
        for(int i = 0; i < npc.length; i++){
            if(npc[i] != null){
                entityList.add(npc[i]);
            }
        } 
        
        for(int i = 0; i < mon.length; i++){
            if(mon[i] != null){
                entityList.add(mon[i]);
            }
        }
        
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                entityList.add(obj[i]);
            }
        } 
        
        for(int i = 0; i < projectileList.size(); i++){
            if(projectileList.get(i) != null){
                entityList.add(projectileList.get(i));
            }
        }
        
        //SẮP XẾP CÁC THỰC THỂ TRONG DANH SÁCH THEO WORDY
        //PHƯƠNG THỨC SO SÁNH CÁC THỰC THỂ. SO SÁNH WORDY
        Collections.sort(entityList, new Comparator<Entity>() {
            @Override
            public int compare(Entity e1, Entity e2) {
                
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });
        
        for(int i = 0; i < entityList.size(); i++){
            entityList.get(i).draw(g2);
        }
        
        for(int i = 0; i < particleList.size(); i++){
            particleList.get(i).draw(g2);
        }
        
        //XÓA ĐI ĐỂ LẦN SAU ADD VÀO
        entityList.clear();
        
        //VẼ THÔNG BÁO
        ui.draw(g2);      
        }
        //ĐÓNG
        g2.dispose();
    }
    
    //PHÁT NHẠC NỀN
    public void playMusic(int i){
        
        music.setFile(i);
        music.play();
        music.loop();
    }
    
    //ĐÓNG PHÁT NHẠC 
    public void stopMusic(){
        music.stop();
    }
    
    //PHÁT NHẠC HIỆU ỨNG
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}

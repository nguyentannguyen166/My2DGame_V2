/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ntn19
 */
public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    GamePanel gp;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) { // khi nhấn hoặc nhấn giữ
        int code = e.getKeyCode(); // Lấy mã phím bằng cách bắt sự kiện keyPressed là e
        
        if(gp.gameState == gp.titleState){titleState(code);}
        else if(gp.gameState == gp.playState){playState(code);}
        else if(gp.gameState == gp.pauseState){pauseState(code);}
        else if(gp.gameState == gp.dialogueState){dialogueState(code);}
        else if(gp.gameState == gp.characterState){characterState(code);}
        else if(gp.gameState == gp.gameOverState){gameOverState(code);}
    }

    @Override
    public void keyReleased(KeyEvent e) { // phím phát hành chắc là khi không ấn hoặc giữ
        int code = e.getKeyCode();
        
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
    }
    
    public void titleState(int code){
                    
        if(gp.ui.titleScreenState == 0){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                }
                if(gp.ui.commandNum == 1){
                    
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1){

            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 5;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 5){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 5){
                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 0;
                }
            }
        }
    }
    
    public void gameOverState(int code){
            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 1){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.retry();
                    gp.gameState = gp.playState;                    
                }
                if(gp.ui.commandNum == 1){
                    gp.restart();
                    gp.gameState = gp.titleState;
                    gp.ui.titleScreenState = 0;
                    gp.ui.commandNum = 0;
                }
            }
    }
    
    public void playState(int code){
            if(code == KeyEvent.VK_W){
                upPressed = true;
            }
            if(code == KeyEvent.VK_S){
                downPressed = true;
            }
            if(code == KeyEvent.VK_A){
                leftPressed = true;
            }
            if(code == KeyEvent.VK_D){
                rightPressed = true;
            }
            if(code == KeyEvent.VK_C){
                gp.gameState = gp.characterState;
            }
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.pauseState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }
            if(code == KeyEvent.VK_F){
                shotKeyPressed = true;
            }
    }
        
    public void pauseState(int code){
            if(code == KeyEvent.VK_P){
                gp.gameState = gp.playState;
            }
    }
    
    public void dialogueState(int code){
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
    }
            
    public void characterState(int code){
            if(code == KeyEvent.VK_C){
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_W){
                if(gp.ui.slotRow != 0){
                    gp.ui.slotRow--;
                    gp.playSE(9);
                }
            }
            if(code == KeyEvent.VK_A){
                if(gp.ui.slotCol != 0){
                    gp.ui.slotCol--;
                    gp.playSE(9);
                }
            }
            if(code == KeyEvent.VK_S){
                if(gp.ui.slotRow != 3){
                    gp.ui.slotRow++;
                    gp.playSE(9);
                }
            }
            if(code == KeyEvent.VK_D){
                if(gp.ui.slotCol != 5){
                    gp.ui.slotCol++;
                    gp.playSE(9);
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.player.selectItem();
            }
    }
}

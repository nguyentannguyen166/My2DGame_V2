/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author ntn19
 */
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game 2D");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack(); // Đóng gói
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //GỌI 2 HÀM NÀY ĐỂ SETTING TRƯỚC KHI GAME KHỞI ĐỘNG
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }   
}

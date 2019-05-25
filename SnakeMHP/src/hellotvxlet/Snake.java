package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;

public class Snake {
    private int SX, SY, width, height;
    
    public Snake(int X, int Y, int tileSize) {
        this.SX = X;
        this.SY = Y;
        width = tileSize;
        height = tileSize;
    }
    
    public void tick() {
        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(SX * width, SY * width, width, height);
    }
    
    public int getSX() {
        return SX;
    }
    
    public void setSX(int SX) {
        this.SX = SX;
    }
    
    public int getSY() {
        return SY;
    }
    
    public void setSY(int SY) {
        this.SY = SY;
    }
}

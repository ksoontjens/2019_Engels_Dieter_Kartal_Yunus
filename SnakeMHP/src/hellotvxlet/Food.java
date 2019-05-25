package hellotvxlet;



import java.awt.Color;
import java.awt.Graphics;

public class Food {
    private int FX, FY, width, height;
    
    public Food(int X, int Y, int tileSize) {
        this.FX = X;
        this.FY = Y;
        width = tileSize;
        height = tileSize;
    }
    
    public void tick() {
        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(FX * width, FY * width, width, height);
    }
    
    public int getFX() {
        return FX;
    }
    
    public void setSX(int FX) {
        this.FX = FX;
    }
    
    public int getFY() {
        return FY;
    }
    
    public void setSY(int FY) {
        this.FY = FY;
    }
}

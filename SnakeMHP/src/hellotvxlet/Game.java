package hellotvxlet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.swing.JPanel;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HStaticText;
import org.havi.ui.HText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;

public class Game extends HComponent implements Runnable, KeyListener , HActionListener {
    
    public static final int WIDTH = 720, HEIGHT = 576;
    
    private Thread thread;
    
    private boolean running;
    
    private boolean right = false, left = false, up = false, down = true;
    
    private Snake snake;
    private ArrayList fullSnake;
    
    private Food food;
    private ArrayList allFood;
    HStaticText hst;
    private Random r;
    
    private int SX = 10, SY = 10, size = 5;
    
    private int ticks = 0;
    HScene scene;
    public Game(HScene sc){
        
        scene=sc;
        setFocusable(true);
        this.setBounds(0, 0, 720, 576);
        //setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addKeyListener(this);
        
        fullSnake = new ArrayList();
        allFood = new ArrayList();
        
        r = new Random();
        hst=new HStaticText("SCORE:",620,0,100,100);
        scene.add(hst);
        scene.popToFront(hst);
        scene.repaint();
      
        start();
        
    }
    void updatescore(int score)
    {
        hst.setTextContent("SCORE:"+score,HVisible.NORMAL_STATE);
        hst.repaint();
    }
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
     public void actionPerformed(ActionEvent arg0) {
      if (arg0.getActionCommand().equals("startgame"))
      {        
          scene.removeAll();
          Game game = new Game(scene);
          scene.add(game);
          game.requestFocus();
      }
      if (arg0.getActionCommand().equals("quitgame"))
      {
          Runtime.getRuntime().exit(1);        
      }
     }
     
    public void stop() throws InterruptedException{
        running = false;
        //thread.stop(); //join();

        scene.remove(this); // remove game object

        HTextButton button = new HTextButton("START GAME",238,150,200,100);
        HTextButton button2 = new HTextButton("QUIT GAME",238,276,200,100);
        
        HStaticText gameovertext = new HStaticText("GAME OVER",238,50,200,100);
        
        this.scene.setBackgroundMode(1);
        this.scene.setBackground(Color.BLACK);
        
        button.setBackgroundMode(1);
        button.setActionCommand("startgame");
        button.setBackground(Color.GREEN);
        
        button2.setBackgroundMode(1);
        button2.setActionCommand("quitgame");
        button2.setBackground(Color.RED);
        
        button.addHActionListener(this);
        button2.addHActionListener(this);
        
        this.scene.add(gameovertext);
        this.scene.add(button);
        this.scene.add(button2);
        this.scene.popToFront(gameovertext);
        this.scene.validate();
        this.scene.setVisible(true);
        
        button.setFocusTraversal(null, button2, null, null);
        button2.setFocusTraversal(button, null, null, null);

        button.requestFocus();
    }
    
    public void tick() throws InterruptedException{
        if(fullSnake.size() == 0) {
            snake = new Snake(SX, SY, 10);
            fullSnake.add(snake);
        }
        ticks++;
        if(ticks > 300000) {
            if(right){ SX++; }
            if(left){ SX--; }
            if(up){ SY--; }
            if(down){ SY++; }
            
            ticks = 0;
            
            snake = new Snake( SX, SY, 10);
            fullSnake.add(snake);
            
            if(fullSnake.size() > size) {
                fullSnake.remove(0);
            }
        }
        
        if(allFood.size() == 0) {
            int FX = r.nextInt(49);
            int FY = r.nextInt(49);
            
            food = new Food( FX, FY, 10);
            allFood.add(food);
        }
        
        for(int i = 0; i < allFood.size(); i++) {
            if(SX == ((Food)allFood.get(i)).getFX() && SY == ((Food)allFood.get(i)).getFY()) {
                size++;
                updatescore(size-5);
                allFood.remove(i);
                i++;
            }
        }
        
        for(int i = 0; i < fullSnake.size(); i++) {
            if(SX == ((Snake)fullSnake.get(i)).getSX() && SY == ((Snake)fullSnake.get(i)).getSY()) {
                if(i !=  fullSnake.size() - 1) {
                    System.out.println("Game Over");
                    stop();
                }
            }
        }        
        if (SX < 0 || SX > 49 || SY < 0 || SY > 49) {
            System.out.println("Game Over");
            stop();
        }   
    }
    
    public void paint(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        for(int i = 0; i < WIDTH/10; i++) {
            g.drawLine(i * 10, 0, i * 10, HEIGHT);
        }
        for(int i = 0; i < WIDTH/10; i++) {
            g.drawLine(0, i * 10, WIDTH, i * 10);
        }
        for(int i = 0; i < fullSnake.size(); i++) {
            ((Snake)fullSnake.get(i)).draw(g);
        }
        for(int i = 0; i < allFood.size(); i++) {
            ((Food)allFood.get(i)).draw(g);
        }
    }

    public void run() {
        while(running) {
            try {
                tick();
            } catch (InterruptedException ex) {
         ex.printStackTrace();
              //  Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            repaint();
        }
    }

    public void keyTyped(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        
        if(key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        
        if(key == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
        }
        
        if(key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
        }
    }

    public void keyReleased(KeyEvent arg0) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}

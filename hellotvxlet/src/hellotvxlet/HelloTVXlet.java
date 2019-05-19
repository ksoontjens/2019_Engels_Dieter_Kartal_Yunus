package hellotvxlet;

import hellotvxlet.XY;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.event.HActionListener;

public class HelloTVXlet
  implements Xlet, HActionListener, UserEventListener
{
  HScene scene;
  HStaticText hst2 = null;
  int kopx = 20;
  int kopy = 20;
  int richtingx = 0;
  int richtingy = 15;
  GameComponent gc = new GameComponent();
  MijnTimerTask mtt = new MijnTimerTask(this);
  Timer t = new Timer();
  


  
  public void initXlet(XletContext ctx) throws XletStateChangeException {
    this.scene = HSceneFactory.getInstance().getDefaultHScene();
    

    
    HStaticText hst = new HStaticText("SNAKE", 280, 80, 150, 100);
    hst.setBackgroundMode(1);
    hst.setBackground(Color.BLUE);
    this.scene.add(hst);
    
    HTextButton button1 = new HTextButton("Play Game", 200, 290, 310, 50);
    button1.setBackgroundMode(1);
    button1.setBackground(Color.GREEN);
    this.scene.add(button1);
    
    HTextButton button2 = new HTextButton("Exit Game", 200, 380, 310, 50);
    button2.setBackgroundMode(1);
    button2.setBackground(Color.RED);
    this.scene.add(button2);

    
    button1.setFocusTraversal(null, button2, null, null);
    button2.setFocusTraversal(button1, null, null, null);
    
    button1.setActionCommand("button1");
    button1.addHActionListener(this);
    button2.setActionCommand("button2");
    button2.addHActionListener(this);

    
    this.scene.add(button1);
    this.scene.add(button2);
    this.scene.add(hst);
    button1.requestFocus();
    this.scene.validate();
    this.scene.setVisible(true);
  }




  
  public void run() {
    System.out.println(".");
    this.kopx += this.richtingx;
    this.kopy += this.richtingy;
    
    for (int i = 0; i < this.gc.slang.size(); i++) {
      
      if (((XY)this.gc.slang.get(i)).x == this.kopx && 
        ((XY)this.gc.slang.get(i)).y == this.kopy) {
        System.out.println("game over");
      }
    } 
    this.gc.slang.add(new XY(this.kopx, this.kopy));
    this.gc.repaint();
    if (this.gc.slang.size() > 8) this.gc.slang.remove(0);
  
  }


  
  public void startXlet() {}


  
  public void pauseXlet() {}


  
  public void destroyXlet(boolean unconditional) {}

  
  public void actionPerformed(ActionEvent arg0) {
    if (this.hst2 != null) {
      this.scene.remove(this.hst2);
    }
    
    if (arg0.getActionCommand().equals("button1")) {
      this.scene.removeAll();

      
      this.scene.add(this.gc);

      
      this.scene.repaint();
      this.t.scheduleAtFixedRate(this.mtt, 0L, 200L);
      UserEventRepository rep = new UserEventRepository("naam");
      rep.addAllArrowKeys();
      EventManager.getInstance().addUserEventListener(this, rep);
    } else {
      
      Runtime.getRuntime().exit(1);
    } 
  }



  
  public void userEventReceived(UserEvent arg0) {
    if (arg0.getType() == 401) {
      
      if (arg0.getCode() == 40) {
        
        this.richtingx = 0;
        this.richtingy = 15;
      } 
      
      if (arg0.getCode() == 38) {
        
        this.richtingx = 0;
        this.richtingy = -15;
      } 
      
      if (arg0.getCode() == 37) {
        
        this.richtingx = -15;
        this.richtingy = 0;
      } 
      
      if (arg0.getCode() == 39) {
        
        this.richtingx = 15;
        this.richtingy = 0;
      } 
    } 
  }
}

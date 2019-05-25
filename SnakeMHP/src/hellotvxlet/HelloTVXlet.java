package hellotvxlet;

//import hellotvxlet.XY;
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
import org.havi.ui.HText;
import org.havi.ui.HTextButton;
import org.havi.ui.event.HActionListener;
//import hellotvxlet.GameComponent;

public class HelloTVXlet
  implements Xlet, HActionListener, UserEventListener
{
    HScene scene;  
    MijnTimerTask mtt = new MijnTimerTask(this);
    Timer t = new Timer();
  
  public void initXlet(XletContext ctx) throws XletStateChangeException {
       
        this.scene = HSceneFactory.getInstance().getDefaultHScene();
        HTextButton button = new HTextButton("START GAME",238,150,200,100);
        HTextButton button2 = new HTextButton("QUIT GAME",238,276,200,100);
        
        this.scene.setBackgroundMode(1);
        this.scene.setBackground(Color.BLACK);
        
        button.setBackgroundMode(1);
        button.setActionCommand("startgame");
        button.setBackground(Color.GREEN);
        button.addHActionListener(this);
        
        button2.setBackgroundMode(1);
        button2.setActionCommand("quitgame");
        button2.setBackground(Color.RED);
        button2.addHActionListener(this);
        
        this.scene.add(button);
        this.scene.add(button2);
        this.scene.validate();
        this.scene.setVisible(true);
        
        button.setFocusTraversal(null, button2, null, null);
        button2.setFocusTraversal(button, null, null, null);
        
        button.requestFocus();   
    
 //   game.requestFocus();
  }
  
  public void run() {
 /*   System.out.println(".");
    this.kopx += this.richtingx;
    this.kopy += this.richtingy;
    for (int i = 0; i < this.gc.slang.size(); i++) {
      
      if (((XY)this.gc.slang.get(i)).x == this.kopx && 
        ((XY)this.gc.slang.get(i)).y == this.kopy) {
        System.out.println("game over");
      }
      if (((XY)this.gc.slang.get(i)).x == ((XY)this.gc.food.get(0)).x && ((XY)this.gc.slang.get(i)).y == ((XY)this.gc.food.get(0)).y) {
    }      
      
    }
    
    
    this.gc.slang.add(new XY(this.kopx, this.kopy));
    this.gc.repaint();
    this.gc.food.add(new XY(500, 200));
    this.gc.repaint();
    if (this.gc.slang.size() > 8) this.gc.slang.remove(0);
  */
  }
  public void startXlet() {} 
  public void pauseXlet() {}  
  public void destroyXlet(boolean unconditional) {}
  
  public void actionPerformed(ActionEvent arg0) {
      if (arg0.getActionCommand().equals("startgame"))
      {
        scene.removeAll();
        Game game=new Game(scene);
        scene.add(game);
        game.requestFocus();
      }
      if (arg0.getActionCommand().equals("quitgame"))
      {
          Runtime.getRuntime().exit(1);
      }
  /*  if (this.hst2 != null) {
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
      
      Runtime.getRuntime().exit(1);*/
 //   } 
  }
  public void userEventReceived(UserEvent arg0) {
/*    if (arg0.getType() == 401) {
      
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
    } */
  }
}

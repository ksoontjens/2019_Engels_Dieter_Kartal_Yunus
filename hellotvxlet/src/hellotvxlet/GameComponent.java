package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import org.havi.ui.HComponent;

public class GameComponent extends HComponent {
  ArrayList slang = new ArrayList();
  
  public GameComponent() { setBounds(0, 0, 720, 576); }

  public void paint(Graphics g) {
    for (int i = 0; i < this.slang.size(); i++) {
      
      g.setColor(Color.GREEN);
      
      g.fillOval(((XY)this.slang.get(i)).x, ((XY)this.slang.get(i)).y, 20, 20);
      
      g.setColor(Color.YELLOW);
      
      g.fillOval(((XY)this.slang.get(i)).x + 5, ((XY)this.slang.get(i)).y + 5, 10, 10);
    } 
  }
}

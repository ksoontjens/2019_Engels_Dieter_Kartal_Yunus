package hellotvxlet;

import java.awt.Color;
import javax.tv.xlet.*;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HVisible;


public class HelloTVXlet implements Xlet {

  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
      HScene scene=HSceneFactory.getInstance().getDefaultHScene();
      
      HStaticText hst=new HStaticText("He llo World",100,100,200,200);
      hst.setBackgroundMode(HVisible.BACKGROUND_FILL);
      hst.setBackground(Color.BLUE);
      
     
      scene.add(hst);
      scene.setVisible(true);
         
      scene.validate();
 
      
     
    }

    public void startXlet() {
    
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
}

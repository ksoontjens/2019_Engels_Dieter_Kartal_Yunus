package hellotvxlet;
import java.util.TimerTask;










public class MijnTimerTask
  extends TimerTask
{
  HelloTVXlet ht;
  
  public MijnTimerTask(HelloTVXlet ht) { this.ht = ht; }

  
  public void run() { this.ht.run(); }
}

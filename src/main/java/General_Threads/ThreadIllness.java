package General_Threads;

import Events.Illnesses;
import Events.Storm;


/**
 *
 * @author Brian Ramirez
 */


public class ThreadIllness extends Thread{
    int checker;//check that we have changed day 
    boolean isRunning=true;
    boolean isPaused=false;
    Illnesses illness;
    ThreadDays thread_days;

    public ThreadIllness( Illnesses illness, ThreadDays thread_days) {
        this.illness = illness;
        this.thread_days = thread_days;
    }
    
    
   
    @Override
    public void run() {
        checker=thread_days.getCont();
        while (isRunning) {
            try {

                if(checker!=thread_days.getCont()){
                    illness.affectCharacter();
                    checker=thread_days.getCont();  
                }
                
                sleep(2);
            } catch (Exception e) {
            }
            
            while (isPaused) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) { }
             
                
            }
            if(!illness.getActive())break; 
        }
    }
    
   public void  stopTh(){
       this.isRunning=false;
   }
   public boolean  isRunnig(){
       return isRunning;
   }
    public boolean isPause() {
        return isPaused;
    }

    public void resumePause() {
        this.isPaused = false;
    }
    public void pause() {
        this.isPaused = true;
    }

    
    
}

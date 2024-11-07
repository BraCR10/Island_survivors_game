package General_Threads;

import Events.Storm;

public class ThreadStorm extends Thread{
    int lastDay;
    int checker;//check that we have changed day 
    boolean isRunning=true;
    boolean isPaused=false;
    Storm storm;
    ThreadDays thread_days;
    
    
    public ThreadStorm(Storm storm, ThreadDays thread_days) {
       this.lastDay=thread_days.getCont()+storm.getDays();
       this.storm=storm;
       this.thread_days=thread_days;
    }
    @Override
    public void run() {
        checker=thread_days.getCont();
        while (isRunning) {
            try {
                if(lastDay==thread_days.getCont()){
                    break;
                }else{
                    if(checker!=thread_days.getCont()){
                        storm.provokeStormEffects();
                        checker=thread_days.getCont();  
                    }
                }
                sleep(2);
            } catch (Exception e) {
            }
            
            while (isPaused) {
                try {
                    sleep(1000);
                } catch (InterruptedException ex) { }
                
            }
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

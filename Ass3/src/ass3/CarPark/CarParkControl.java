/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass3.CarPark;


import java.util.concurrent.Semaphore;


class CarParkControl {

    protected int spaces;
    protected int capacity;
    NumberCanvas cont_;
    Counter counter;
    

    CarParkControl(int n) {
        capacity = spaces = n;
        
    }
    
    CarParkControl(int n,NumberCanvas cont) {
        cont_ = cont;
        capacity = spaces = n;
        counter = new Counter(cont_);
        counter.show(spaces);
    }
/*
    void arrive() throws InterruptedException {

        try{
            full.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
  
        --spaces;
        counter.show(spaces); 
        full.release();

        
       
    }

    void leaves() throws InterruptedException{
        try{
            empty.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        
        ++spaces;
        counter.show(spaces);
        empty.release();
     }
}
*/

synchronized void arrive() throws InterruptedException {
        while(spaces == 0){
            wait();
        }
        
        --spaces;
        counter.show(spaces); 
        notifyAll();
    }

    synchronized void leaves() throws InterruptedException{
        while(capacity == spaces){
            wait();
        }
        
        ++spaces;
        counter.show(spaces);
        notifyAll();
     }

}


/********************LLEGADAS*******************************/
class Arriving implements Runnable {

    CarParkControl carpark;
    NumberCanvas display;

    Arriving(CarParkControl c) {
        carpark = c;
    }
    Arriving(CarParkControl c, NumberCanvas n) {
        carpark = c;
        display = n;
    }
    public void run() {
      try {
        while(true) {
          ThreadPanel.rotate(340); 
           carpark.arrive();
          ThreadPanel.rotate(20);           
        }
      } catch (InterruptedException e){}
    }
}

/********************SALIDAS*******************************/

class Leaving implements Runnable {

    CarParkControl carpark;
    NumberCanvas display;

    Leaving(CarParkControl c) {
        carpark = c;
    }
    
    public void run() {
      try {
        while(true) {
          ThreadPanel.rotate(20);
          carpark.leaves();
          ThreadPanel.rotate(340);        
        }
      } catch (InterruptedException e){}
    }
}
/********************COUNTER*******************************/

 class Counter {

    volatile int value=0;
    NumberCanvas display;

   
    Counter(NumberCanvas n) {
        display=n;
    }
    
    void show() {
       display.setvalue(value);   

    }
    
    void show(int n) {
        value = n;
        display.setvalue(value);   
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass3.bridge;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficController {

    Semaphore enter = new Semaphore(1);
    /*Semaphore blue = new Semaphore(1); for part 1.B
    Semaphore red = new Semaphore(0);*/
    Semaphore blue = new Semaphore(1);
    Semaphore red = new Semaphore(1);
    volatile int counterBlue = 0, counterRed = 0, counter = 0;
    boolean wantBlue = false, wantRed = false, want = false;
// ******************PART A****************
    public void redEnters() {
        try {
            enter.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void blueEnters() {
        try {
            enter.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void blueExits() {
        enter.release();

    }

    public void redExits() {
        enter.release();

    }

    //****************PART B******************
    /*synchronized public void redEnters(){
     try {
     if(want)
     wait();
            
     } catch (InterruptedException ex) {
     Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
     }
     want = true;
     }

     synchronized public void blueEnters(){
     try {
     if(want)
     wait();
           
     } catch (InterruptedException ex) {
     Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
     } 
     want = true;
     }

     synchronized public void blueExits() {
     want = false;
     notify();
     }

     synchronized public void redExits() {
     want = false;
     notify();
     }
     */
    /*2 part of b part*/
//    public void redEnters() throws InterruptedException {
//        try {
//            if (counterRed == 0) {
//                enter.acquire();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        red.acquire();
//        counterRed++;
//        red.release();
//
//    }
//
//    public void blueEnters() throws InterruptedException {
//        try {
//            if (counterBlue == 0) {
//                enter.acquire();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        blue.acquire();
//        counterBlue++;
//        blue.release();
//    }
//
//    public void blueExits() throws InterruptedException {
//
//        if (counterBlue == 1) {
//            enter.release();
//        }
//        blue.acquire();
//        counterBlue--;
//        blue.release();
//    }
//
//    public void redExits() throws InterruptedException {
//        if (counterRed == 1) {
//            enter.release();
//
//        }
//        red.acquire();
//        counterRed--;
//        red.release();
//    }

}

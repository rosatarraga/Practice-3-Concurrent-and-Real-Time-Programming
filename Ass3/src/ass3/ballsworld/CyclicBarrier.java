/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ballsworld;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class CyclicBarrier {

    volatile int cont = 0;
    int aux;
    Semaphore cb = new Semaphore(0);
    Semaphore protect = new Semaphore(1);
    public CyclicBarrier(int num) {

        aux = num;

    }

    public void await() {
        try {
            protect.acquire();
            cont++;
        } catch (InterruptedException ex) {}
        
        if(aux >cont){
            try{
                protect.release();
                cb.acquire();
            }catch(InterruptedException ex){}
        }else{
            cont=0;
            protect.release();
            cb.release(aux-1);
        }
        
    }
}

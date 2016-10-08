/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ass3.CarPark;


import java.util.concurrent.Semaphore;


class DisplayCarPark extends CarParkControl {

    CarParkCanvas disp;
    boolean occupied[];

    DisplayCarPark(CarParkCanvas disp,int size,NumberCanvas cont) {
        super(size, cont);
        this.disp = disp;
        occupied = new boolean[size];
        for (int i=0; i<size; i++) occupied[i]=false;
    }

    private void display() {
        disp.setvalue(spaces,occupied);
    }

    public void arrive() throws InterruptedException {
        super.arrive();
        occupied[place(false)]=true;
        display();
        //Thread.sleep(400);
    }

    public void leaves() throws InterruptedException {
        super.leaves();
        occupied[place(true)]=false;
        display();
    }

    private int place(boolean v) {
        int start = ((int)(Math.random() * 1000))% capacity;
        for (int i =0; i<capacity; i++) {
            int j = (start + i) % capacity;
             if(occupied[j] == v) return j;
        }
        return 0; //should never happen
    }

 }

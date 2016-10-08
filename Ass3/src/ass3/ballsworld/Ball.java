/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3.ballsworld;

import java.awt.*;

import ass3.ballsworld.Balls;

public class Ball extends Thread {

    BallsWorld world;

    private int xpos, ypos, vel, dirc;

    private final Color col;

    private final static int bolaw = 20;
    private final static int bolah = 20;
    //private int cont = 0;
    
    private CyclicBarrier barrier;


    public Ball(BallsWorld world, int xpos, int ypos,
            int vel, int dirc, Color col, CyclicBarrier barrier) {

        super("Ball :" + col);

        this.world = world;
        this.xpos = xpos;
        this.ypos = ypos;
        this.vel = vel;
        this.dirc = dirc;
        this.col = col;
        this.barrier = barrier;
        world.addBall(this);
    }

    public void run() {
        while (true) {
            move();
        }
    }

    public void move() {
        if (xpos >= world.getWidth() - bolaw || xpos <= 0) {
            vel = -vel;
        }

        if (ypos >= world.getHeight() - bolah || ypos <= 0) {
            dirc = -dirc;
        }

        Balls.nap(30);
        doMove();
        world.repaint();

        /**
         * *******************************
         */
        /*Barrier must be used in here
         if (xpos >= ypos-2 && xpos <= ypos+2) 
         we stop ball
         short nap Balls.nap(1000);
	    
         */
        if (xpos >= ypos - 2 && xpos <= ypos + 2) {
            try {

                barrier.await();
                Balls.nap(1000);
              

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void doMove() {
        xpos += vel;
        ypos += dirc;
    }

    public synchronized void draw(Graphics g) {
        g.setColor(col);
        g.fillOval(xpos, ypos, bolaw, bolah);
    }
}

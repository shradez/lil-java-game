package com.liljavagame.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serializable;
import java.util.Random;

public class Game extends Canvas implements Runnable, Serializable {

    private static final long serialVersionUID = 4857461307867123759L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r;
    private Handler handler;

    public Game(){
        new Window(WIDTH, HEIGHT, "Let's Build a Game", this);

        handler = new Handler();
        r = new Random();

        for (int i = 0; i < 50; i++){
            handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
        }

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        handler.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy(); // Since this will be null by default, the following is how we
        // will want to actually create our buffer strategy
        if(bs == null){
            this.createBufferStrategy(3); // This means along with the current buffer, there will be two additional
            // buffers being loaded at any time.
            return;
        }

        Graphics g = bs.getDrawGraphics(); // Anything after this line and before disposal of buffer strategy(g
        // .dispose) is where we can draw graphics, then bs.show will "show" them

        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();;
        bs.show();
    }

    public static void main (String args[]){
        new Game();
    }
}

package Projects.gravity;

import Projects.gravity.TestSystem;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * @since 8 Mar, 2016
 * @author Abhishek
 */
public class StarGame {
    public static Thread renderer, input;
    public static World system;
    public static boolean gaming;
    
    public static void main(String[] args) {
        TestSystem t = new TestSystem();
        init(t);
    }
    
    public static void init(World s){
        system = s;
        gaming = true;
        initThreads();
        renderer.start();
    }
    public static void loadSystem(World s){
        gaming = false;
        while(renderer.isAlive() || input.isAlive());
        initThreads();
        gaming = true;
        renderer.start();
    }
    private static void initThreads(){
        renderer = new Thread(null, () -> {
            try {
                if(!Display.isCreated()) {
                    Display.setDisplayMode(new DisplayMode(config.WIDTH, config.HEIGHT));
                    Display.setTitle("Macro Particle Simulator");
                    Display.create();
                    Keyboard.create();
                    Mouse.create();
                }
            } catch (LWJGLException ex) {
                System.out.println("FATAL ERROR!");
                System.out.println(ex);
            }
            glDisable(GL_DEPTH_TEST);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(-config.WIDTH, config.WIDTH, -config.HEIGHT, config.HEIGHT, 1, -1);
            glMatrixMode(GL_MODELVIEW);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            gameLoop();
        }, "Renderer", 8);
        input = new Thread(null, () -> {
            while(gaming){
                system.inputTick();
                try{
                    Thread.sleep(1000/config.fps);
                }catch(InterruptedException e){}
            }
        });
    }
    private static void gameLoop() {
        input.start();
        long l = System.nanoTime(), c = 0;
        while(!Display.isCloseRequested() && gaming){
            c++;
            glClear(GL_COLOR_BUFFER_BIT);
            system.draw();
            system.updatePos();
            Display.sync(config.fps);
            Display.update();
        }
        System.out.println((System.nanoTime() - l)/c);
        gaming = false;
    }
}
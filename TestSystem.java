package Projects.gravity;

import Projects.gravity.uitl.Vector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

/**
 * @since 8 Mar, 2016
 * @author Abhishek
 */
public class TestSystem extends GravityWorld{
    
    GravityBody sun;
    Orbiter p3, p4, p5, p6;
    Orbiter p1, p2;
    Comet Halley;
    
    public TestSystem(){
        super();
        Halley = new Comet(this);
        Halley.pos.y = 650;
        Halley.vel.x = 53;
        sun = new GravityBody(1000000, 50f, this){
            @Override
            public void draw() {
                super.draw();
                GL11.glBegin(GL11.GL_QUADS);
                GL11.glColor4f(r, g, b, 0.4f);
                GL11.glVertex2d(config.cam.transToWinCoodX(pos.x + size/1.3),
                        config.cam.transToWinCoodY(pos.y));
                GL11.glVertex2d(config.cam.transToWinCoodX(pos.x),
                        config.cam.transToWinCoodY(pos.y + size/1.3));
                GL11.glVertex2d(config.cam.transToWinCoodX(pos.x - size/1.3),
                        config.cam.transToWinCoodY(pos.y));
                GL11.glVertex2d(config.cam.transToWinCoodX(pos.x),
                        config.cam.transToWinCoodY(pos.y - size/1.3));
                GL11.glEnd();
            }
        };
        sun.r = 0.99f;
        sun.g = 0.75f;
        sun.b = 0.01f;
        p1 = new Orbiter(sun, new Vector(0, -71), new Vector(306.502, 0), 00.1, 2f, this);
        p1.r = 1f;
        p1.g = 0.4f;
        p2 = new Orbiter(sun, new Vector(0, -121), new Vector(234.785, 0), 00.1, 2f, this);
        p2.r = 0.87f;
        p2.g = 0.6f;
        p3 = new Orbiter(sun, new Vector(0, -171), new Vector(197.499, 0), 00.1, 4f, this);
        p3.r = 0.1f;
        p3.g = 1.0f;
        p3.b = 0.1f;
        p4 = new Orbiter(sun, new Vector(0, -307.6), new Vector(147.255, 0), 00.1, 8f, this);
        p4.g = 0.4f;
        p4.b = 0.5f;
        p5 = new Orbiter(sun, new Vector(0, -467.85),  new Vector(119.401, 0),00.1, 7f, this);
        p5.g = 0.2f;
        p5.b = 0.5f;
        p6 = new Orbiter(sun, new Vector(100, -410), new Vector(154.568, 0), 00.1, 12f, this);
//        p6 = new Orbiter(sun, new Vector(0, -610), new Vector(104.568, 0), 00.1, 12f, this);
        p6.r = 0.2f;
        p6.b = 1.0f;
        p6.selected = true;
        selected = p6;
    }
    
    @Override
    public void draw() {
        GL11.glClearColor(0.1f, 0.1f, 0.1f, 0.02f);
        sun.draw();
        p2.draw();
        p2.drawOrbit();
        p1.draw();
        p1.drawOrbit();
        p3.draw();
        p3.drawOrbit();
        p4.draw();
        p4.drawOrbit();
        p5.draw();
        p5.drawOrbit();
        p6.draw();
        p6.drawOrbit();
        Halley.draw();
    }

    @Override
    public Vector getNetFieldFor(Body g) {
        Vector v = super.getNetFieldFor(g);
        double x = v.x, y = v.y;
        if(!g.equals(sun)){
            v = sun.getFieldAt(g);
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p1)){
            v = (p1.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p2)){
            v =(p2.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p3)){
            v = (p3.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p4)){
            v = (p4.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p5)){
            v = (p5.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        if(!g.equals(p5)){
            v = (p5.getFieldAt(g));
            x += v.x;
            y += v.y;
        }
        return new Vector(x, y);
    }

    @Override
    public void updatePos() {
        for (int i = 0; i < 1 + config.TIME_WARP*config.PLANCK_TIME; i++) {
//            sun.updatePos();
            p1.updatePos();
            p2.updatePos();
            p3.updatePos();
            p4.updatePos();
            p5.updatePos();
            p6.updatePos();
            Halley.updatePos();
        }
    }

    @Override
    public void inputTick() {
        super.inputTick();
        if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
            if(selected != null) selected.selected = false;
            p1.selected = true;
            selected = p1;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            p2.accelerate(new Vector(0f, .1f));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_K)){
            p2.accelerate(new Vector(0f, -.1f));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
            p2.accelerate(new Vector(.1f, 0f));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_J)){
            p2.accelerate(new Vector(-.1f, 0f));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_2)) {
            if(selected != null) selected.selected = false;
            p2.selected = true;
            selected = p2;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_3)) {
            p3.selected = true;
            selected = p3;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_4)) {
            if(selected != null) selected.selected = false;
            p4.selected = true;
            selected = p4;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_5)) {
            if(selected != null) selected.selected = false;
            p5.selected = true;
            selected = p5;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_6)) {
            if(selected != null) selected.selected = false;
            p6.selected = true;
            selected = p6;
        }
    }
}
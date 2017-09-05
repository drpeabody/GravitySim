package Projects.gravity;

import Projects.gravity.uitl.Vector;
import org.lwjgl.opengl.GL11;

/**
 * @since 6 Apr, 2016
 * @author Abhishek
 */
public class Comet extends Body{

    public Comet(World t) {
        super();
        parentSystem = t;
        size = 2.0f;
        acc = new Vector();
        vel = new Vector();
        pos = new Vector();
        r = g = b = a = 1.0f;
    }

    @Override
    public void updatePos() {
        acc = parentSystem.getNetFieldFor(this);
        pos.x += (vel.x * config.UNIT_TIME);
        pos.y += (vel.y * config.UNIT_TIME);
        vel.x += (acc.x * config.UNIT_TIME);
        vel.y += (acc.y * config.UNIT_TIME);
    }
    
    @Override
    public void draw() {
        GL11.glColor4f(r, g, b, a);
        GL11.glBegin(GL11.GL_QUADS);
        double x = config.cam.transToWinCoodX(pos.x);
        double y = config.cam.transToWinCoodY(pos.y);
        double d = config.cam.transToWinDist(size/2);
        GL11.glVertex2d(x - d,y - d);
        GL11.glVertex2d(x - d,y + d);
        GL11.glVertex2d(x + d,y + d);
        GL11.glVertex2d(x + d,y - d);
        GL11.glEnd();
        acc.eval();
        acc = acc.multiply(0.01);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x - config.cam.transToWinDist(acc.x), y - config.cam.transToWinDist(acc.y));
        acc = acc.rotate(0.1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x - config.cam.transToWinDist(acc.x), y - config.cam.transToWinDist(acc.y));
        acc = acc.rotate(-0.2);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x - config.cam.transToWinDist(acc.x), y - config.cam.transToWinDist(acc.y));
        GL11.glEnd();
    }

    @Override
    public Vector getFieldAt(Body b) {
        return new Vector();
    }
}
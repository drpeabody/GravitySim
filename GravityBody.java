package Projects.gravity;

import Projects.gravity.uitl.Arrow;
import Projects.gravity.uitl.Scalar;
import Projects.gravity.uitl.Vector;
import org.lwjgl.opengl.GL11;

public class GravityBody extends Body{
    public boolean selected;
    public double Gparam;
    
    public GravityBody(World s){
     this(0.1, 1.0f, s);   
    }
    public GravityBody(double m, World s){
        this(m, 1.0f, s);
    }
    public GravityBody(double m, double s, World t){
        super();
        if(m < 0) throw new IllegalArgumentException("Mass Cannot be negative!");
        if(t == null) throw new IllegalArgumentException("Star System cannot be null!!");
        Gparam = config.UNIVERSAL_GRAVITY_CONSTANT * m;
        parentSystem = t;
        size = s;
        r = g = b = 0.0f;
        a = 0.8f;
        selected = false;
    }
    
    @Override
    public void updatePos() {
        super.updatePos();
        if(selected) config.cam.pos = new Scalar(pos.x, pos.y);
    }
    @Override
    public Vector getFieldAt(Body b){
        Vector t =  new Vector(pos.x - b.pos.x, pos.y - b.pos.y, true);
        double d;
        if(t.mod > (b.size+size)/2) d = Gparam / t.sqMod;
        else d = - config.UNIVERSAL_STRONG_CONSTANT / t.sqMod;
        return t.getUnitVec().multiply(d);
    }
    
    @Override
    public void draw(){
        if(selected){
            GL11.glColor3f(1.0f, 0.0f, 0.0f);
        }
        else GL11.glColor4f(r, g, b, a);
        GL11.glBegin(GL11.GL_QUADS);
        double x = config.cam.transToWinCoodX(pos.x);
        double y = config.cam.transToWinCoodY(pos.y);
        double d = config.cam.transToWinDist(size/2);
        if(d < 2) d = 2;
        GL11.glVertex2d(x - d, y - d);
        GL11.glVertex2d(x - d, y + d);
        GL11.glVertex2d(x + d, y + d);
        GL11.glVertex2d(x + d, y - d);
        GL11.glEnd();
    }
    
    public void drawVectors() {
        GL11.glColor3f(0.0f, 0.6f, 1.0f);
        Arrow v = new Arrow(0.0, 0.0, config.cam.transToWinDist(vel.x), config.cam.transToWinDist(vel.y));
        v.draw();
        GL11.glColor3f(0.0f, 1.0f, 0.0f);
        v = new Arrow(0.0, 0.0, config.cam.transToWinDist(acc.x), config.cam.transToWinDist(acc.y));
        v.draw();
    }
}
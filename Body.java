package Projects.gravity;

import Projects.gravity.uitl.Vector;

public abstract class Body{
    public Vector pos, vel, acc;
    public float r, g, b, a;
    public double size;
    public World parentSystem;
    
    public Body(){
        pos = new Vector();
        vel = new Vector();
        acc = new Vector();
        r = g = b = a = 0.0f;
    }
    
    public abstract void draw();
    public abstract Vector getFieldAt(Body b);
    
    public void updatePos() {
        acc = parentSystem.getNetFieldFor(this);
        pos.x += (vel.x * config.UNIT_TIME);
        pos.y += (vel.y * config.UNIT_TIME);
        vel.x += (acc.x * config.UNIT_TIME);
        vel.y += (acc.y * config.UNIT_TIME);
    }
}
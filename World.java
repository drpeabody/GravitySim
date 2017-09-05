package Projects.gravity;

import Projects.gravity.uitl.Vector;

/**
 * @since 8 Mar, 2016
 * @author Abhishek
 */
public abstract class World {
    protected MouseBody mouse;
    protected boolean nBodySim;
    
    public abstract void draw();
    
    public abstract Vector getNetFieldFor(Body b) ;
    public abstract void updatePos();
    
    public abstract void inputTick();
    
    public boolean isNBodyOn(){ 
        return nBodySim;
    }
}
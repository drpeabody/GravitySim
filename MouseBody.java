package Projects.gravity;

import Projects.gravity.uitl.Vector;

/**
 * @since 8 Mar, 2016
 * @author Abhishek
 */
public abstract class MouseBody extends Body{
    
    public MouseBody(){
        pos = new Vector();
    }
    
    public abstract Vector getPrimaryFieldAt(Body b);
    public abstract Vector getSecondaryFieldAt(Body b);
    
    @Override
    public Vector getFieldAt(Body b){
        return new Vector();
    }
    
    @Override
    public void draw(){}
}
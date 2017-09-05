package Projects.gravity;

import Projects.gravity.uitl.Vector;

/**
 * @since 5 Apr, 2016
 * @author Abhishek
 */
public class GravityMouse extends MouseBody{
    
    public GravityMouse(){
        pos = new Vector();
    }
    @Override
    public Vector getPrimaryFieldAt(Body b){
        Vector t =  new Vector(pos.x - b.pos.x, pos.y - b.pos.y);
        return t.getUnitVec().multiply(config.UNIVERSAL_GRAVITY_CONSTANT);
    }
    @Override
    public Vector getSecondaryFieldAt(Body b){
        Vector t =  new Vector(2* - b.vel.x,2* - b.vel.y);
        return t;
    }
}
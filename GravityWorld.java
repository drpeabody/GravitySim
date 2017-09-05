package Projects.gravity;

import Projects.gravity.uitl.Scalar;
import Projects.gravity.uitl.Vector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * @since 5 Apr, 2016
 * @author Abhishek
 */
public abstract class GravityWorld extends World{

    public GravityBody selected;
    
    public GravityWorld(){
        super();
        nBodySim = false;
        mouse = new GravityMouse();
        selected = null;
    }
    
    @Override
    public Vector getNetFieldFor(Body b) {
        if(b instanceof Comet) return new Vector();
        GravityBody g = (GravityBody)b;
        if (g.selected) {
            mouse.pos.x = config.cam.transToWorldCoodX(Mouse.getX());
            mouse.pos.y = config.cam.transToWorldCoodY(Mouse.getY());
            if (Mouse.isButtonDown(0)) {
                return mouse.getPrimaryFieldAt(b);
            }
            if (Mouse.isButtonDown(1)) {
                return mouse.getSecondaryFieldAt(b);
            }
        }
        return new Vector();
    }
    @Override
    public void inputTick() {
        int i;
        if((i = Mouse.getDWheel()) != 0){
            if(i > 0) config.cam.zoomIn();
            else config.cam.zoomOut();
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            if(selected != null){
                selected.selected = false;
                selected = null;
            }
                config.cam.pos = new Scalar();
        }
        if(Mouse.isButtonDown(1) && selected == null){
            config.cam.pos.x -= (Mouse.getDX())*config.cam.scale;
            config.cam.pos.y -= (Mouse.getDY())*config.cam.scale;
        }
    }
}
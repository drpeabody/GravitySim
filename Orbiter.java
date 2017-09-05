package Projects.gravity;

import Projects.gravity.uitl.Scalar;
import Projects.gravity.uitl.Vector;
import org.lwjgl.opengl.GL11;

/**
 * @since 14 Mar, 2017
 * @author Abhishek
 */
public class Orbiter extends GravityBody{
    /**
     * Uses Patched Conic definitions and solutions to Newton's gravitational equation to model orbits.
     * Any accelerations are calculated as new orbits on spot.
     * Polar solution: r = 1/(u/H^2 + Acos(arccos(B/A) + O)
     * Here u is the parent body's standard Gravitational Parameter
     * H is the Specific Angular Momentum
     * B = 1/r - u/H^2 at some initial r
     * A = sqrt(B^2 + ((dr/dt)^2)/H^2) and dr/dt measure radial velocity
     * O = Orbital Parameter or Polar Angle
     * P = Initial Anomaly    
     */
    
    double u, H, B, A, O, P, R;
    static final double Tau = 2 * Math.PI;
    volatile boolean accelerating;
            
    public Orbiter(GravityBody parent, Vector pos, Vector vel, double mass, double size, World w){
        super(mass, size, w);
        pos = pos.subtract(parent.pos);
        vel = vel.subtract(parent.vel);
        pos.eval();
        u = parent.Gparam;
        Vector r = pos.getUnitVec();
        H = pos.cross(vel);
        B = 1/pos.mod - u/(H*H);
        A = Math.sqrt(B*B + (vel.dot(r)*vel.dot(r))/(H*H));
        P = Math.acos(B/A);
        O = Tau + Math.atan(pos.y/pos.x) - P;
        R = pos.mod;
        accelerating = false;
    }
        
    public void reCalcOrbit(Vector pos, Vector vel, double u){
//        System.out.println(H+", "+B+", "+A+", "+O+", "+P+", "+R);
        pos.eval();
        vel.eval();
        this.vel = vel;
        this.u = u;
        Vector r = pos.getUnitVec();
        r.eval();
        H = pos.cross(vel);
        B = 1/pos.mod - u/(H*H);
        A = Math.sqrt(B*B + (vel.dot(r)*vel.dot(r))/(H*H));
        P = Math.acos(B/A);
//        O = Tau + Math.atan(pos.y/pos.x) - P;
        R = pos.mod;
    }
    
    public Vector getCurrentRadial(){
        return new Vector(Math.cos(O + P), Math.sin(O + P));
    }
    public Vector getCurrentNorRadial(){
        return new Vector(-Math.sin(O + P), Math.cos(O + P));
    }
    
    public void accelerate(Vector deltaV){
        accelerating = true;
        reCalcOrbit(pos, vel.add(deltaV), u);
        accelerating = false;
    }
    
    @Override
    public void updatePos() {
        if (!accelerating) {
            O += (H / (R * R)) * config.UNIT_TIME;
            R = 1 / (u / (H * H) + A * Math.cos(O + P));
        acc.x = -vel.x;
        vel.x = -pos.x;
        pos.x = R * Math.cos(O);
        vel.x = (vel.x + pos.x)/config.UNIT_TIME;
        acc.x = (acc.x + vel.x)/config.UNIT_TIME;
        acc.y = -vel.y;
        vel.y = -pos.y;
        pos.y = R * Math.sin(O);
        vel.y = (vel.y + pos.y)/config.UNIT_TIME;
        acc.y = (acc.y + vel.y)/config.UNIT_TIME;
        }
        if(selected){
            config.cam.pos = new Scalar(pos.x, pos.y);
            drawVectors();
        }
    }
    
    public void updatePosNBody(){
        super.updatePos();
    }
    
    public void drawOrbit(){
        double R, o = O%Tau;
        double res = config.ORBIT_RESOLUTION/config.cam.scale;
        GL11.glBegin(GL11.GL_LINE_LOOP);
        for (double i = o; i < o + Tau; i+=Tau/res) {
            GL11.glColor4d(r, g, b, (a/Tau)*(i - o + 1));
            R = 1 / (u/(H*H) + A * Math.cos(P + i));
            GL11.glVertex2d(config.cam.transToWinCoodX(R*Math.cos(i)), config.cam.transToWinCoodY(R*Math.sin(i)));
        }
        GL11.glEnd();
    }

}
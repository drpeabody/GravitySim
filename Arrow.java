package Projects.gravity.uitl;

import org.lwjgl.opengl.GL11;

/**
 * @since 12 Mar, 2016
 * @author Abhishek
 */
public class Arrow {
    
    double l = 1/5f;
    double Ix, Iy, Tx, Ty;
    Scalar s1, s2;
    
    public Arrow(double Ix, double Iy, double Tx, double Ty){
        this.Ix = Ix;
        this.Iy = Iy;
        this.Tx = Tx;
        this.Ty = Ty;
        s1 = new Scalar(((Tx - Ix)*Math.cos(Math.PI/2)- (Ty - Iy)*Math.sin(Math.PI/2)) + Ix, 
                ((Tx - Ix)*Math.sin(Math.PI/2) + (Ty - Iy)*Math.cos(Math.PI/2)) + Iy);
        s2 = new Scalar(((Tx - Ix)*Math.cos(-Math.PI/2) - (Ty - Iy)*Math.sin(-Math.PI/2)) + Ix, 
                ((Tx - Ix)*Math.sin(-Math.PI/2) + (Ty - Iy)*Math.cos(-Math.PI/2)) + Iy);
        s1.x = (s1.x - Tx)*l + Tx;
        s1.y = (s1.y - Ty)*l + Ty;
        s2.x = (s2.x - Tx)*l + Tx;
        s2.y = (s2.y - Ty)*l + Ty;
    }
    
    public void setInitial(Scalar s){
        Ix = s.x;
        Iy = s.y;
    }
    public void setTerminal(Scalar s){
        Tx = s.x;
        Ty = s.y;
    }
    
    public void updateHead(){
        s1.x = ((Tx - Ix)*Math.cos(Math.PI/2)- (Ty - Iy)*Math.sin(Math.PI/2)) + Ix; 
        s1.y = ((Tx - Ix)*Math.sin(Math.PI/2) + (Ty - Iy)*Math.cos(Math.PI/2)) + Iy;
        s2.x = ((Tx - Ix)*Math.cos(-Math.PI/2) - (Ty - Iy)*Math.sin(-Math.PI/2)) + Ix;
        s2.y = ((Tx - Ix)*Math.sin(-Math.PI/2) + (Ty - Iy)*Math.cos(-Math.PI/2)) + Iy;
        s1.x = (s1.x - Tx)*l + Tx;
        s1.y = (s1.y - Ty)*l + Ty;
        s2.x = (s2.x - Tx)*l + Tx;
        s2.y = (s2.y - Ty)*l + Ty;
    }
    
    public void draw(){
        GL11.glBegin(GL11.GL_LINE_STRIP);
        GL11.glVertex2d((Ix), (Iy));
        GL11.glVertex2d((Tx), (Ty));
        GL11.glVertex2d((s1.x), (s1.y));
        GL11.glVertex2d((s2.x), (s2.y));
        GL11.glVertex2d((Tx), (Ty));
        GL11.glEnd();
    }
}
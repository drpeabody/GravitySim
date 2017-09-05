package Projects.gravity;

import Projects.gravity.uitl.Vector;

public final class config{
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1280;
    public static final int fps = 60;
    public static final int sensitivity = 50;
    public static final double ZOOM_IN_LIMIT = 0.02;
    public static final double ZOOM_OUT_LIMIT = 800000;
    public static final double UNIT_TIME = 0.000001;//0.00000005;
    public static final int PLANCK_TIME = 2000;//20000;
    //public static final double UNIVERSAL_GRAVITY_CONSTANT = 0.0000000000667;
    public static final double UNIVERSAL_GRAVITY_CONSTANT = 6.67;
    public static final double UNIVERSAL_STRONG_CONSTANT = 1e7;
    public static final double UNIVERSAL_PLANCK_CONSTANT = 6.626e-4;
    // 1 mass = 10 ^ 25 Kg
    // 1 time = 10 ^ 5 s
    // 1 length = 10 ^ 8 Km
    
    public static int ORBIT_RESOLUTION = 150;
    public static int TIME_WARP = 1;
    
    public static Camera cam = new Camera();
    
    public static boolean checkIsOnScreen(Vector v){
        if(v.x > 0 && v.y > 0){
            if(v.x < HEIGHT && v.y < HEIGHT){
                return true;
            }
        }
        return false;
    }
}
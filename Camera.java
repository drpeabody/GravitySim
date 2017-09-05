package Projects.gravity;

import Projects.gravity.uitl.Scalar;

public class Camera{
    public Scalar pos;
    public double scale;
    //scale is length per pixel
    
    public Camera(){
        pos = new Scalar(0.0, 0.0);
        scale = 1.0;
    }
    
    public double transToWinCoodX(double d){
        return (d - pos.x)/scale;
    }
    public double transToWinCoodY(double d){
        return (d - pos.y)/scale;
    }
    
    public double transToWorldCoodX(double d){
        return ((d - config.WIDTH / 2)*scale + config.cam.pos.x);
    }
    public double transToWorldCoodY(double d){
        return ((d - config.HEIGHT / 2)*scale + config.cam.pos.y);
    }
    
    public double transToWorldDist(double d){
        return d * scale;
    }
    public double transToWinDist(double d){
        return d / scale;
    }
    
    public void zoomOut(){
        scale *= 1.5;
        if(scale > config.ZOOM_OUT_LIMIT) scale = config.ZOOM_OUT_LIMIT;
    }
    public void zoomIn(){
        scale /= 1.5;
        if(scale < config.ZOOM_IN_LIMIT) scale = config.ZOOM_IN_LIMIT;
    }
}
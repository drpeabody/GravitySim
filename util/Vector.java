package Projects.gravity.uitl;

public class Vector{
    public double x, y;
    public double mod, sqMod;
    
    public Vector(){
        mod = sqMod = x = y = 0.0;
    }
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
        sqMod = mod = 0.0;
    }
    public Vector(double magnitude, Vector direction) {
        this(magnitude * direction.getUnitVec().x,
                magnitude * direction.getUnitVec().y);
    }
    public Vector(double x, double y, boolean evaluateNow){
        this.x = x;
        this.y = y;
        if(evaluateNow){
            sqMod = x * x + y * y;
            mod = Math.sqrt(sqMod);
        }
        else{
            mod = sqMod = 0.0;
        }
    }
    
    public void eval(){
        sqMod = x*x + y*y;
        mod = Math.sqrt(sqMod);
    }
    
    public double dot(Vector v){
        return x*v.x + y*v.y;
    }
    public double cross(Vector v){
        return x*v.y - y*v.x;
    }
    
    public Vector getUnitVec(){
        return new Vector(x/mod, y/mod);
    }
    public Vector add(Vector v){
        return new Vector(x += v.x,y += v.y);
    }
    public Vector subtract(Vector v){
        return new Vector(x - v.x, y - v.y);
    }
    public Vector multiply(double magnitude) {
        return new Vector(x * magnitude, y * magnitude);
    }
    public Vector rotate(double angle){
        return new Vector(x*Math.cos(angle) - y*Math.sin(angle), 
                        x*Math.sin(angle) + y*Math.cos(angle));
    }
    
    @Override
    public String toString(){
        return "Vector:(" + x + ", " + y + ")";
    }
        
}

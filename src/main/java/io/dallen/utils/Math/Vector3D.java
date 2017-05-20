package io.dallen.utils.Math;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.util.Vector;

/**
 *
 * @author donoa_000
 */
public class Vector3D {
    @Getter @Setter
    private double X;
    
    @Getter @Setter
    private double Y;
    
    @Getter @Setter
    private double Z;
    
    public Vector3D(double X, double Y, double Z){
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
    
    public Vector3D(double[] v){
        this.X = v[0];
        this.Y = v[1];
        this.Z = v[2];
    }
    
    public double[] toArray(){
        return new double[] { X, Y, Z };
    }
    
    public double length(){
        return Math.sqrt(X*X+Y*Y+Z*Z);
    }
    
    public void normalize(){
        X /= this.length();
        Y /= this.length();
        Z /= this.length();
    }
    
    @Override
    public String toString(){
        return "<"+X+", "+Y+", "+Z+">";
    }
    
    public Vector toBukkitVector(){
        return new Vector(X, Y, Z);
    }
    
    public static Vector3D cross(Vector3D u, Vector3D v){
        return new Vector3D(Vector3D.cross(u.toArray(), v.toArray()));
    }
    
    public static double[] cross(double[] u, double[] v){
        return new double[] {
            u[1]*v[2]-u[2]*v[1],
            u[2]*v[0]-u[0]*v[2],
            u[0]*v[1]-u[1]*v[0]
        };
    }
    
    public static double dot(Vector3D u, Vector3D v){
        return Vector3D.dot(u.toArray(), v.toArray());
    }
    
    public static double dot(double[] u, double[] v){
        return u[0]*v[0]+u[1]*v[1]+u[2]*v[2];
    }
    
    public static Vector3D dot(double s, Vector3D v){
        return new Vector3D(Vector3D.dot(s, v.toArray()));
    }
    
    public static double[] dot(double s, double[] v){
        return new double[] {
            s*v[0],
            s*v[1],
            s*v[2]
        };
    }
}

package first;

import java.util.HashMap;

public class Energy {
    private double energy;
    private double LossEnergy;
    private double ExpirationTime;
    HashMap WhoSteal = new HashMap();
    public Energy(){
        energy = 0;
        LossEnergy = 0;
        ExpirationTime = 0;
    }
    public void setExpirationTime(double extime){
        ExpirationTime = extime;
    }
    public void setEnergy(double total){
        energy = total;
    }
    public void setLossEnergy(double loss){
        LossEnergy = loss;
    }
    public double getEnergy(){
        return energy;
    }
    public double getLossEnergy(){
        return LossEnergy;
    }
    public double getExpirationTime(){
        return ExpirationTime;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_assignment1;

/**
 *
 * @author thapanan
 */
public class Perceptron {
    private double Weight[][] = new double[3][2];            // Weight[][0] = Present weight , Weight[][1] = Past weight 
    private double Input[] = new double[3];
    private double Output;
    private double NetValue;                                 // NetValue = Net activity value
    private double Ramp;                                     
    private int level;                                       // level = lv. of Perceptron
    private int Snum;                                        // Snum = Serial Number of Perceptron

    public Perceptron(int Snum,int level)
    {
        this.Input[0] = -1;                                 // Set input x[0] = -1
        for(int i=0 ; i<3 ; i++)
        {
            this.Weight[i][0] = -1 + Math.random()*2;       // Random initial weight for all connection 
        }
        this.level = level;                                 // Set level of Perceptron
        this.Snum = Snum;                                   // Set Serial Number of Perceptron
    }
    
    public void GetOutput()                                // Get output value
    {
        this.NetValue = 0;
        for(int i=0 ; i<3 ; i++)
        {
            this.NetValue = this.NetValue + (this.Weight[i][0]*this.Input[i]);     // find Sum of Net activity value
        }
        this.Output = 1 / (1 + Math.exp(-this.NetValue));   // find output
    }
    
    public double GetNetValue()
    {
        return this.NetValue;
    }
    
    public double GetOutputValue()
    {
        return this.Output;
    }
    
    public void SetInput(double x1,double x2)                // Set input x1,x2
    {
        this.Input[1] = x1;
        this.Input[2] = x2;
    }
    
    public double GetInput(int address)                      // Get input for specific address
    {
        return this.Input[address];                 
    }
            
    public void SetWeight(double[] w)                        // Adjust Weight (both present and past)
    {
        for(int i=0 ; i<3 ; i++)
        {
            this.Weight[i][1] = this.Weight[i][0];          // Save Past Weight = Present Weight
            this.Weight[i][0] = w[i];                       // Save Present Weight = Adjust Weight
        }
    }
    
    public double GetPresentWeight(int NumberOfWeight)
    {
        double temp;
        temp = this.Weight[NumberOfWeight][0];
        return temp;
    }
    
    public double GetPastWeight(int NumberOfWeight)
    {
        double temp;
        temp = this.Weight[NumberOfWeight][1];
        return temp;
    }
    
    public void SetRamp(double temp)
    {
        this.Ramp = temp;
    }
    
    public double GetRamp()
    {
        return this.Ramp;
    }
    
    public int Getlevel()
    {
        return this.level;
    }
    
    public int GetSnum()                                     // Get Serial number of Perceptron 
    {
        return this.Snum;
    }
}

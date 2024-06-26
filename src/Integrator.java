import parcs.*;
public class Integrator implements AM{
    public double integrate(double a,double b){
        double sum=0;
        int t=0;
        double step=0.0001;
        sum=0;
        double x1=a;
        while(x1<b){
            sum+=((function(x1)+function(x1+step))*step)/2;
            x1+=step;
        }
        return sum;
    }
    public double function(double x){
        return 4.0/(1+Math.pow(x,2));
    }

    @Override
    public void run(AMInfo info) {
        double a=info.parent.readDouble();
        double b=info.parent.readDouble();
        double r=integrate(a,b);
        info.parent.write(r);
    }
}
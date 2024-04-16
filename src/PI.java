import parcs.*;
public class PI {
    public static void main(String[] args)
    {
        task curtask = new task();
        curtask.addJarFile("Pi.jar");

        AMInfo info = new AMInfo(curtask, null);
        int n=100;
        point[] p = new point[n];
        channel[] c = new channel[n];
        double step=1.0/n;
        double x=0;
        for (int i=0; i<n; i++) {
            p[i] = info.createPoint();
            c[i] = p[i].createChannel();
            p[i].execute("Integrator");
            c[i].write(x);
            if(x+step<=1){
                c[i].write(x+step);
                x+=step;
            }
            else{
                c[i].write(x);
            }
        }
        System.out.println("Waiting for result...");
        double result =0;
        for (int i=0; i<n; i++) {
            result+=c[i].readDouble();
        }
        System.out.println("Result: "+result);
        curtask.end();
    }
}
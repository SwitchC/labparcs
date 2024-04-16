import parcs.*;
public class PI {
    public static void main(String[] args)
    {
        task curtask = new task();
        curtask.addJarFile("Pi.jar");

        AMInfo info = new AMInfo(curtask, null);
        point[] p = new point[8];
        channel[] c = new channel[8];
        int n=8;
        double step=1.0/8;
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
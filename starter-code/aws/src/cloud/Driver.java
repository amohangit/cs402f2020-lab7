/*
    Code written for CMSPC 402 class 
    by Professor. Mohan using the Amazon EC2 SDK.
    This class provides the methods such as create instances, display running 
    instances, terminate instances, start instances, and stop instances.
    The code expects you to copy the credentials to ~/.aws/credentials file 
    for correct working.
      
*/
package cloud;
import java.util.ArrayList;
public class Driver {
    public static void main(String[] args) throws Exception, InterruptedException{
        String ACCESS_KEY = "";
        String SECRET_KEY = "";
        String TOKEN = "";
        String KEY = "";
        String GROUP = "";
        String TYPE = "t2.micro";
        String IMAGE = "ami-0f82752aa17ff8f5d";
        VMProvisioner obj = new VMProvisioner();
        obj.settings(ACCESS_KEY, SECRET_KEY, TOKEN, KEY, GROUP, TYPE, IMAGE);
        obj.init();
        
        obj.createInstances(1);
        System.out.println("#waiting for machines to be ssh friendly...");
        Thread.sleep(30000);
        
        ArrayList<String> ips = obj.displayRunningInstances();
        String pemFileLocation = "..\\" + KEY + ".pem";
        obj.cloud_execute(pemFileLocation, "..\\logs\\", ips);
        

        //obj.terminateInstances();
        //obj.startInstances();
        //obj.stopInstances();
        

        /* for debugging purposes
        ArrayList<String> ips = new ArrayList<String>();
        ips.add("3.86.183.228");
        String pemFileLocation = "/Users/amohan/Downloads/Auto-Cloud/EC2/amohan_ec2.pem";
        obj.cloudStart(pemFileLocation, ips);
        */
      
        
    }
}


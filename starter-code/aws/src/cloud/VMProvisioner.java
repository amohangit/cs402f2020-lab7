/*
    Code written for CMSPC 402 class 
    by Professor. Mohan using the Amazon EC2 SDK.
    This class provides the methods such as create instances, display running 
    instances, terminate instances, start instances, and stop instances.
    The code expects you to copy the credentials to ~/.aws/credentials file 
    for correct working.
      
*/
package cloud;
import java.util.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.InstanceState;

public class VMProvisioner {
    private AmazonEC2 ec2;
    private Set<Instance> instances = new HashSet<Instance>();
    private String ACCESS_KEY = "";
    private String SECRET_KEY = "";
    private String TOKEN = "";
    private String KEY = "";
    private String GROUP = "";
    private String TYPE = "";
    private String IMAGE = "";
    public void settings(String access_key, String secret_key, 
                        String token, String key, String group, 
                                String type, String image){
        this.ACCESS_KEY = access_key;
        this.SECRET_KEY = secret_key;
        this.TOKEN = token;
        this.KEY = key;
        this.GROUP = group;
        this.TYPE = type;
        this.IMAGE = image;
    }
    public void init() throws Exception {
        ec2 = AmazonEC2ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(ACCESS_KEY, SECRET_KEY, TOKEN)))
            .withRegion(Regions.US_EAST_1)
            .build();
    }
    public void createInstances(int noOfInstances) throws InterruptedException {
        RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
        .withInstanceType(TYPE)
        .withImageId(IMAGE)
        .withMinCount(noOfInstances)
        .withMaxCount(noOfInstances)
        .withKeyName(KEY)
        .withSecurityGroupIds(GROUP);
        RunInstancesResult runInstances = ec2.runInstances(runInstancesRequest);
        System.out.println("#provisioning machines in the Cloud...");
        Thread.sleep(20000);
    }
    public void describeInstances(){
        System.out.println("#describe current instances");
        DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
        List<Reservation> reservations = describeInstancesRequest.getReservations();
        for (Reservation reservation : reservations) 
            instances.addAll(reservation.getInstances());
    }
    public ArrayList<String> displayRunningInstances(){
        ArrayList<String> ips = new  ArrayList<String>();
        describeInstances();
        System.out.println("#display running instances");
        for (Instance ins : instances){
             String instanceId = ins.getInstanceId();
             String instanceIPs = ins.getPublicIpAddress();
             InstanceState is = ins.getState();
             if (is.getName().equalsIgnoreCase("running")){
                System.out.println(instanceId + " " + instanceIPs + " " + is.getName());
                ips.add(instanceIPs);
            }
        }
        return ips;
    }
    public void terminate(String instanceID){
        describeInstances();
        System.out.println("#terminating running instances");
        ArrayList<String> instanceIdsToTerminate = new ArrayList<String>();
        for (Instance ins : instances){
            String instanceId = ins.getInstanceId();
            InstanceState is = ins.getState();
            if (is.getName().equalsIgnoreCase("running"))
                instanceIdsToTerminate.add(instanceId);
        }
        if (instanceIdsToTerminate.size() > 0){
            TerminateInstancesRequest terminateRequest = new TerminateInstancesRequest(instanceIdsToTerminate);
            ec2.terminateInstances(terminateRequest);
        }
        else
            System.out.println("No instances to terminate!");
    }
    public void terminateInstances(){
        describeInstances();
        System.out.println("#terminating running instances");
        ArrayList<String> instanceIdsToTerminate = new ArrayList<String>();
        for (Instance ins : instances){
            String instanceId = ins.getInstanceId();
            InstanceState is = ins.getState();
            if (is.getName().equalsIgnoreCase("running"))
                instanceIdsToTerminate.add(instanceId);
        }
        if (instanceIdsToTerminate.size() > 0){
            TerminateInstancesRequest terminateRequest = new TerminateInstancesRequest(instanceIdsToTerminate);
            ec2.terminateInstances(terminateRequest);
        }
        else
            System.out.println("No instances to terminate!");
    }
    public void startInstances() throws InterruptedException{
        describeInstances();
        System.out.println("#starting stopped instances");
        ArrayList<String> instanceIdsToStart = new ArrayList<String>();
        for (Instance ins : instances){
            String instanceId = ins.getInstanceId();
            InstanceState is = ins.getState();
            if (is.getName().equalsIgnoreCase("stopped"))
                instanceIdsToStart.add(instanceId);
        }
        if (instanceIdsToStart.size() > 0){
            StartInstancesRequest startRequest = new StartInstancesRequest(instanceIdsToStart);
            ec2.startInstances(startRequest);
        }
        else
            System.out.println("No instances to start!");
        Thread.sleep(20000);
        displayRunningInstances();
    }

    public void stopInstances(){
        describeInstances();
        System.out.println("#stopping running instances");
        ArrayList<String> instanceIdsToStop = new ArrayList<String>();
        for (Instance ins : instances){
            String instanceId = ins.getInstanceId();
            InstanceState is = ins.getState();
            if (is.getName().equalsIgnoreCase("running"))
                instanceIdsToStop.add(instanceId);
        }
        if (instanceIdsToStop.size() > 0){
            StopInstancesRequest stopRequest = new StopInstancesRequest(instanceIdsToStop);
            ec2.stopInstances(stopRequest);
        }
        else
            System.out.println("No instances to stop!");
    }
    public void cloud_execute(String pemFileLocation, String logFile, ArrayList<String> ips) throws Exception{
        Shell sh = new Shell();
        for (String ipAddress : ips) {
            System.out.println("executing commands on :" + ipAddress);
            ArrayList<String> commands = new ArrayList<String>();
            commands.add("sudo apt update");
            commands.add("yes | sudo apt install default-jdk");
            commands.add("wget https://www.cs.allegheny.edu/sites/amohan/402/Hello.java");
            commands.add("javac Hello.java");
            commands.add("java Hello");
            commands.add("exit");
            String logs = sh.execute(ipAddress.trim(), "ubuntu", pemFileLocation, 22, commands);
            System.out.println(logs);
            FileUtility _file = new FileUtility(logFile + ipAddress + ".txt");
            _file.write(logs);
            _file.reset();
        }
        System.out.println("All set to go...");
    }
   
}


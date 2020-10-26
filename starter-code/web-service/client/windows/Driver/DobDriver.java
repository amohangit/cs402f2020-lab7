package com.web;

import java.rmi.RemoteException;
import org.apache.axis2.AxisFault;
import java.util.Scanner;

public class DobDriver
{
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Enter your age:");
            Scanner scan = new Scanner(System.in);
            int year = scan.nextInt();
     
            // Create the Stub object
	        DobServiceStub stub = new DobServiceStub();
            // Create the request
            DobServiceStub.GetYear request = new DobServiceStub.GetYear();
            // Set the parameters
            request.setArgs0(year);
            
            // Invoke the service
            DobServiceStub.GetYearResponse response = stub.getYear(request);
            // method
            System.out.println(response.get_return());

        }
        catch (AxisFault e)
        {
            e.printStackTrace();
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
    }
}

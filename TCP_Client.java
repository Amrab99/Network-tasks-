import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class TCP_Client
{
    private static InetAddress host;
    private static  final  int PORT= 1234;
    public static void main(String[] args){
        try{
            host=InetAddress.getLocalHost(); 
        }
        catch 
        (Exception ioex){
            System.out.println("Host ID not found!");
            System.exit(1); 
        }
        AccessServer();
    }
    private static void AccessServer()
    {
        Socket link=null;
        try 
        {
            link= new Socket(host,PORT);
            Scanner input=new Scanner(link.getInputStream());
            PrintWriter output=new PrintWriter(link.getOutputStream(),true);
            Scanner inputUser=new Scanner(System.in);
            String UserName, Password, respons;
            do{
                System.out.print("Enter The UserName: ");
                UserName= inputUser.nextLine();
                output.println(UserName);
                respons= input.nextLine();
                if(!respons.equals("Quit"))
                    System.out.println("\nSERVER--> "+respons);
            }
            while (respons.equals("Wrong Username, plz try again!.."));
            do {
                
                System.out.print("Enter Password: ");
                Password= inputUser.nextLine();
                output.println(Password);
                respons=input.nextLine();
                System.out.println("\nSERVER--> "+respons);
            }
            while (respons.equals("Wrong Password, plz try again!.."));
            String msg;
            do {
                System.out.println(respons);
                System.out.print("Enter The Message: ");
                msg = inputUser.nextLine();
                output.println(msg);
                respons = input.nextLine();
                System.out.println("Server> " + respons);
            }
            while(!msg.equals("CLOSE"));
        }
        catch (Exception ex)
        {
            System.out.println("Error");
        }
        finally 
        {
            try 
            {
                System.out.println("CLOSING CONNECTION DONE..");
                link.close();
            } catch (IOException e) 
            {
                System.out.println("Unable To Disconnect");
                System.exit(1);
            }

        }

    }
}

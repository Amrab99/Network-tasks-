import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class TCP_Server
{
    private static ServerSocket serverSocket;
    private static final int PORT = 1234;

    public static void main(String[] args) {
        System.out.println("Opening port!.." );
        try
        {
            serverSocket=new ServerSocket(PORT);
        }
        catch (Exception ex)
        {
            System.out.println("Failed to connect to the number port: " + PORT);
            System.out.println("Please Try Another Port!");
            System.exit(1);
        }
        while (true)
        {
            handel_Connection();
        }
    }

    private static void handel_Connection()
    {
        Socket link=null;
        try{
            link=serverSocket.accept();
            Scanner input=new Scanner(link.getInputStream());
            Scanner userInput = new Scanner(System.in);
            PrintWriter output=new PrintWriter(link.getOutputStream(),true);
            String userName="Ereh",passWord="#2310#";
            String user=input.nextLine();
            while(!user.equals(userName))
            {
                output.println("Wrong Username, Plz try again....");
                user=input.nextLine();
            }
            output.println("Connection is DONE!..");
            String pass=null;
            pass=input.nextLine();

            while(!pass.equals(passWord))
            {
                output.println("Wrong Password, Plz try again....");
                pass = input.nextLine();
            }
            output.println("Welcome To You Ereh!");
            String msg= input.nextLine();
            int MsgNum=0;
            while(!msg.equals("CLOSE"))
            {
                System.out.println("Message Is Received");
                MsgNum++;
                output.println("Message "+MsgNum+": "+msg);
                msg=input.nextLine();
            }
            output.println("Messages received: "+MsgNum);

        }
        catch ( IOException ioex)
        {
            ioex.printStackTrace();
        }
        finally
        {
            try
            {
                System.out.println("CLOSING CONNECTION DONE..");
                link.close();
            } catch (IOException ioex) {
                System.out.println("Unable To Disconnect");
                System.exit(1);
            }
        }



    }
}

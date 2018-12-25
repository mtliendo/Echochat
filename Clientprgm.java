import java.io.*;
import java.net.*;

public class Clientprgm {

public static void main(String[] args)
{
    Socket socket;
    try
    {
        socket = new Socket("127.0.0.1", 9999);            
        if(!socket.isConnected())
            System.out.println("Socket Connection Not established");
        else
            System.out.println("Socket Connection established : "+socket.getInetAddress());

        File myfile = new File("/Users/rachanaadusumilli/Desktop/file.txt");       //local file path.


        if(!myfile.exists())
            System.out.println("File Not Existing.");
        else
            System.out.println("File Existing.");

        byte[] byteArray = new byte[1024];

        FileInputStream fis = new FileInputStream(myfile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        OutputStream os = socket.getOutputStream();
        int trxBytes =0;
        while((trxBytes = bis.read(byteArray, 0, byteArray.length)) !=-1)
        {           
        os.write(byteArray, 0, byteArray.length);
        System.out.println("Transfering bytes : "+trxBytes );
        }
        os.flush();
        bis.close();
        socket.close();

        System.out.println("File Transfered...");
    }
    catch(Exception e)
    {
        System.out.println("Client Exception : "+e.getMessage());
    }       
}
}

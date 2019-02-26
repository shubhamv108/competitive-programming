package input;

// Working program using Reader Class
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


public class ReaderMain
{
    public static void main(String[] args) throws IOException
    {
        Reader s=new Reader();
        int n = s.nextInt();
        int k = s.nextInt();
        int count=0;
        while (n-- > 0)
        {
            int x = s.nextInt();
            if (x%k == 0)
                count++;
        }
        System.out.println(count);
    }
}
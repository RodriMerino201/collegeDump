package Final;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author rodri
 */
public class IOReadDataStream{
    
    private File file;
    
    public IOReadDataStream(){}

    public IOReadDataStream(File file){
      this.file = file;
    }
    
    public String read() throws IOException{
        String content="";
        try{
            try (DataInputStream input = new DataInputStream(new FileInputStream(file));){
                while (input.available() != 0){
                    content = content + " " + input.readUTF();
                }
            }
        } catch (EOFException e){
            System.out.print("EOF(End of file): You have to check if you already reach the end before Reading..." + e.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
        
        return content;
    }
}

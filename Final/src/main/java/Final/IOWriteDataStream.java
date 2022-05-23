package Final;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author rodri
 */
public class IOWriteDataStream {
    private File file;
    public IOWriteDataStream(){}
    
    public IOWriteDataStream(File file){
      this.file = file;
    }
  
    public void write(String line) throws IOException{
        try(DataOutputStream output = new DataOutputStream(new FileOutputStream(file, false))){
            output.writeUTF(line);
        }
    }catch (EOFException e){
    System.out.print("EOF(End of file): You have to check if you already reach the end before reading... " + e.toString());
} catch (IOException e){
    
}
}
package Final;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerV2 {//without Scan class working
    private static PageGenerator pageGenerator = new PageGenerator();
    public static Object lock = new Object();
    public static String text  = "<html><head></head><body><input type='text' value='hi'/><inputtype='button' value='Click me' onclick='alert(\"Hi\")'/></body></html>";
    
    public static void main(String[] args) {
        // Create a thread pool with two threads
        ExecutorService executor = Executors.newFixedThreadPool(1);
        ListenTask listening = new ListenTask();
        executor.execute(listening);
        executor.shutdown();
    }    
    
    
    public static class ListenTask implements Runnable {
        boolean flag = true;
        ArrayList<String> alStrCommands = new ArrayList<String>(Arrays.asList("exit","write", "read", "read"));
                 
        @Override//Keep adding an amount to the account
        public void run() {
            ExecutorService executor = Executors.newFixedThreadPool(3);
            try{//Purposely delay it to let the withdraw method proceed
                System.out.println("ListenTask: Server running at localhost...");
                while(!alStrCommands.isEmpty() && flag) {
                    System.out.println("ListenTask: Please enter a command... ");
                    String strCommand = alStrCommands.get(alStrCommands.size()-1);
                    alStrCommands.remove(alStrCommands.size()-1);

                    System.out.println("ListenTask: \t\t\tThe command entered is " + strCommand);
                    Thread.sleep(2000);
                    switch(strCommand) {
                        case "exit":
                            flag = false;
                            System.out.println("ListenTask: Server shutdown...");
                            break;
                        case "write":
                            WriteTask writeTask = new WriteTask(text);
                            writeTask.run();
                            break;
                        case "read":
                            ReadTask readTask = new ReadTask();
                            readTask.run();
                            break;
                    }
                    
                }
                executor.shutdown();
            }catch (InterruptedException ex) {
              ex.printStackTrace();
            }
            executor.shutdown();
        }
    }

    public static class WriteTask implements Runnable{
        String text;
        public WriteTask(String text){
            this.text = text;
        }
        
        @Override//Keep adding an amount to the account
        public void run() {
            synchronized (lock){
            System.out.println("\t\t\tWriteTask: Writing");
            try {
                pageGenerator.write(text);
            } catch (Exception ex) {
                Logger.getLogger(ServerV2.class.getName()).log(Level.SEVERE, null, ex);
            }}
        }
    }

    public static class ReadTask implements Runnable{
        private static String path = "C:\\Users\\rodri\\Documents\\NetBeansProjects\\Lab8\\";
        private static String name = "page.html";
        @Override//Keep adding an amount to the account
        public void run() {
            File file = new File(path + name);
            if (file.exists()){
            
            System.out.println("\t\t\tReadTask: Reading");
            System.out.println("\t\t\tReadTask:" + pageGenerator.read());                
        }
            else {
                synchronized (lock){
                    System.out.println("\t\t\tReadTask: Reading");
                    System.out.println("\t\t\tReadTask:" + pageGenerator.read());
                }
            }
        }
    }
    
    
    private static class PageGenerator {
        private static String path = "C:\\Users\\rodri\\Documents\\NetBeansProjects\\Lab8\\";
        private static String name = "page.html";
        
        protected File file = new File(path + name);
        private IOReadDataStream read = new IOReadDataStream(file);
        private IOWriteDataStream write = new IOWriteDataStream(file);

        public String read(){
            String text="";
      
            return text;
        }

        public void write(String text) throws Exception{
            write.write(text);
        }
    }
}
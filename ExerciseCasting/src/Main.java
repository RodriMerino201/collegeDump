
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 *
 * @author rodri
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        ArrayList list = new ArrayList<>();
        Object circle = new Circle(5.6);
        Object date = new Date();
        Object rec = new Rectangle(8.9, 5.6);
        Object string = "Hello";
        
        list.add(circle);
        list.add(date);
        list.add(string);
        list.add(rec);
        
        for (int i = 0; i < 4; i++){
            if (list.get(i) instanceof Circle){
                System.out.println("The circle radius in the list is: " + 
                        ((Circle)list.get(i)).getRadius());
            }
            if (list.get(i) instanceof Date){
                System.out.println("The date is: " + ((Date)list.get(i))); 
            }
            if (list.get(i) instanceof String){
                System.out.println("The string is: " + ((String)list.get(i)));
            }
            if (list.get(i) instanceof Rectangle){
                System.out.println("The rectangle height in the list is: " + 
                        ((Rectangle)list.get(i)).getHeight());
            }
        }
        
    }
}

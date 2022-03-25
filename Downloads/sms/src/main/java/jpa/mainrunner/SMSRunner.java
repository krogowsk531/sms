package jpa.mainrunner;
import java.util.Scanner;
import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static CourseService courseService = new CourseService();

    public static void main(String[] args) {
        SMSRunner sms = new SMSRunner();
        sms.menu1();
    }


    private int menu1() {
        System.out.println("Are you a \n1.Student \n2. Quit Application\nPlease Enter 1 or 2: ");
        return scanner.nextInt();
    }

}
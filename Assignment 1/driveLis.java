import java.util.Scanner;

public class driveLis {
    public static void main(String[] args) {
        RTO r = new RTO();
        r.init();
    }
}
class App{
    int age;
    public void input(){
        System.out.println("Please input age");
        Scanner s = new Scanner(System.in);
        age = s.nextInt();
        s.close();
    }
    public void verify() throws ageCheck
    {
        if(age < 18 || age < 70)
        {
            ageCheck check = new ageCheck(); 
            System.out.println(check.getMessage() + " from app side");
            throw check;
        }
        else{
            System.out.println("Collect L");
        }
    }
}
class RTO{
    void init()
    {
        try{
            App a = new App();
            a.input();
            a.verify();
        }
        catch(ageCheck e)
        {
            System.out.println("Invalid age from RTO");
        }

    }
}
class ageCheck extends Exception
{
    public String getMessage()
    {
        return "Age is invalid";
    }
}

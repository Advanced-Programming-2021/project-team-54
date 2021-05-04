import java.util.Arrays;
import java.util.Scanner;

public class quera {
   public static Scanner  scanner = new Scanner(System.in);

   public static void print(int a , int b , int c){
       int []q = {a,b,c};
       Arrays.sort(q);
       System.out.println(q[0] +" "+ q[1] +" "+ q[2]);
   }


   public  static boolean check(int a , int b , int c){
       if(a*a == c*c + b*b|| b*b== c*c + a*a || c*c == a*a+b*b)
           return true;
       return false;
   }

    public static void main(String[] args) {
        int n = scanner.nextInt();
        for(int a= 1 ; a < n/4+1 ; a++){
            for(int b = n/2 ; b > n/4+1 ; b--){
//                if(a==b) {
//                    System.out.println("Impossible");
//                    System.exit(0);
//                }
             if(check(a,b,n-b-a)){
                 print(n-a-b,b,a);
                 System.exit(0);
             }
            }
        }
        System.out.println("Impossible");
    }


}

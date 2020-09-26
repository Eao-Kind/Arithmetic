import org.junit.Test;

import java.util.Random;

public class EquationTest {

    @Test
    public void getRglEquation() throws Exception {
        Random random = new Random();
        int num;
        int start = 1;
        int end = 9;
        for (int i = 0; i < 1000000; i++) {
            num = random.nextInt(5) + 2;
            Equation equation = new Equation(num, start, end, true, true);
//            System.out.println(equation.getEquz() + " == " + equation.getAnswer());
        }
    }

    @Test
    public  void test(){
        StringBuilder temp = new StringBuilder("7+5*8÷41÷10");
        System.out.println(temp.length());
        for (int i= 0; i<temp.length();i++){
            if (temp.charAt(i)=='÷'){
                temp.setCharAt(i,'/');
            }
        }
        System.out.println(temp.toString());

    }
}

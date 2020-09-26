import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class UtilsTest {
    public Utils utils = new Utils();

    @Test
    public void getRglEquation() {
        int num; // 随机2-7个数字生成
        int start = 1;
        int end = 9;
        String str;
        for (int i = 0; i < 1000000; i++) {
            num = (int) (Math.random() * 5) + 2;
            str = utils.getRglEquation(num, start, end);
//           System.out.println(str);
        }
    }

    @Test
    public void getBrcEquation() {
        int num; // 随机2-7个数字生成
        int start = 1;
        int end = 19;
        String str;
        for (int i = 0; i < 1000000; i++) {
            num = (int) (Math.random() * 5) + 2;
            str = utils.getBrcEquation(num, start, end);
//            System.out.println(str + " ");
        }
    }

    @Test
    public void epuToList() {
        int num; // 随机2-7个数字生成
        int start = 1;
        int end = 9;
        String str;
        for (int i = 0; i < 1000000; i++) {
            num = (int) (Math.random() * 5) + 2;
            str = utils.getBrcEquation(num, start, end);
            List list = utils.epuToList(str);
//            System.out.println(list.toString());
        }
    }

    @Test
    public void priority() {
        for (int i = 0; i < 10; i++) {
            int a = Utils.priority("+");
            int b = Utils.priority("-");
            int c = Utils.priority("*");
            int d = Utils.priority("÷");
//            System.out.println(a + "" + b + c + d);
        }
    }

    @Test
    public void parseSuffixExpressionList() {
        int num; // 随机2-7个数字生成
        int start = 1;
        int end = 9;
        String str;
        for (int i = 0; i < 1000000; i++) {
            num = (int) (Math.random() * 5) + 2;
            str = utils.getBrcEquation(num, start, end);
            List list1 = utils.epuToList(str);
            List list2 = utils.parseSuffixExpressionList(list1);
//            System.out.println("中缀表达式：" + "\t" + str + "后缀表达式" + list2.toString());
        }
    }

    @Test
    public void calculate() {
        int num; // 随机2-7个数字生成
        int start = 1;
        int end = 9;
        String str;
        for (int i = 0; i < 1000000; i++) {
            num = (int) (Math.random() * 5) + 2;
            str = utils.getBrcEquation(num, start, end);
            List list1 = utils.epuToList(str);
            List list2 = utils.parseSuffixExpressionList(list1);
            String answer = utils.calculate(list2);
//            System.out.println("中缀表达式：" + str +"\t" + answer);
        }
    }
}
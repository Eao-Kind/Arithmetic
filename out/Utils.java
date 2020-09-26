import com.github.kiprobinson.bigfraction.BigFraction;

import java.util.*;

public class Utils {
    public ArrayList<String> op;

    public Utils() {
        op = new ArrayList<String>(Arrays.asList("+", "-", "*"));
    }

    /**
     * 生成num个数字(范围是start, end)的无括号表达式
     *
     * @param num
     * @param start
     * @param end
     * @return
     */
    public String getRglEquation(int num, int start, int end) {
        StringBuffer equ = new StringBuffer();
        for (int i = 0; i < num; i++) {
            equ.append((int) (Math.random() * (end - start + 1)) + start); //随机获取数字
            equ.append(op.get(new Random().nextInt(op.size()))); // 随机获取符号
        }
        equ.deleteCharAt(equ.length() - 1);
        equ.append("="); // 最后一个符号变为等号
        return equ.toString();
    }

    /**
     * 为表达式插入括号
     *
     * @param st
     * @return
     */
    public String insertBracke(String st, int length) {
        int equLength = st.length();
        int brackerNum;
        if (length > 4) { // 括号数量控制在2个
            brackerNum = (int) Math.random() * 2 + 1; // 1-2个
        } else if (length > 2) {
            brackerNum = (int) Math.random() + 1; // 1个括号
        } else {
            brackerNum = 0;
        }
        brackerNum = 1;
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();
        for (int i = 0; i < brackerNum; i++) {
            int left = (int) (Math.random() * (length - 1)); // 左括号位置
            int right = (int) (Math.random() * (length - left)) + left; //右括号位置
            /*
             * 判断括号有无意义
             * 1.+（5+6）*7 有意义 2. 1/(1/2） 有意义
             * 2. +(5 + (6 + 7 ))
             */
//            st = "4-5+6*7";
//            left = 1;
//            right = 2;
            String judgeSt = st.substring(2 * left, 2 * right + 1);
            String judgeLf, judgeRg;
            if (left != 0) // 解决第0个位置添加左括号导致-1小于0
                judgeLf = st.substring(2 * left - 1, 2 * left); // 括号左边算术符
            else
                judgeLf = " ";
            if (st.length() > 2 * right + 1)
                judgeRg = st.substring(2 * right + 1, 2 * right + 2); // 括号右边算术符
            else
                judgeRg = "";
            if ((!judgeSt.contains("+") && !judgeSt.contains("-") || judgeLf.equals("/") || judgeRg.equals("/"))) {
                // 括号里面 不是 + -  或者  1/(1+2)/ 这种分数格式则跳出本次循环
                continue;
            }
            if ((leftList.contains(left) && rightList.contains(right)) || rightList.contains(left) || leftList.contains(right)) {
                // 重复括号  !!
                continue;
            }
            leftList.add(left);
            rightList.add(right);
        }
        Collections.sort(leftList); // 排序一下括号
        Collections.sort(rightList); // 排序一下括号

        StringBuffer temp = new StringBuffer(st);
        // 插入左括号
        for (int i = 0; i < leftList.size(); i++) {
            temp.insert(2 * leftList.get(i) + i, "(");
        }
        // 插入右边符号
        for (int i = 0; i < rightList.size(); i++) {
            if (rightList.get(i) < leftList.get(leftList.size() - 1)) {
                temp.insert(2 * rightList.get(i) + i + leftList.size(), ")");
            } else {
                temp.insert(2 * rightList.get(i) + i + leftList.size() + 1, ")");
            }
        }
        return temp.toString();
    }

    /**
     * 获取有符号表达式
     *
     * @param num
     * @param start
     * @param end
     * @return
     */
    public String getBrcEquation(int num, int start, int end) {
        String equ = this.getRglEquation(num, start, end); // 获取无符号表达式
        return this.insertBracke(equ, num); // 返回有括号表达式
    }

    /**
     * 将表达式转为 list
     *
     * @param expression
     * @return
     */
    public  List<String> expressionToList(String expression) {
        int index = 0;
        List<String> list = new ArrayList<>();
        do {
            char ch = expression.charAt(index);
            if (ch < 48 || ch > 57) {
                //是操作符，直接添加至list中
                index++;
                list.add(ch + "");
            } else if (ch >= 48 && ch <= 57) {
                //是数字,判断多位数的情况
                String str = "";
                while (index < expression.length() && expression.charAt(index) >= 48 && expression.charAt(index) <= 57) {
                    str += expression.charAt(index);
                    index++;
                }
                list.add(str);
            }
        } while (index < expression.length());
        list.remove(list.size() - 1);
        return list;
    }

    /**
     * 获取操作符的优先级
     *
     * @param op
     * @return
     */
    public static int priority(String op) {
        if (op.equals("*") || op.equals("÷")) {
            return 1;
        } else if (op.equals("+") || op.equals("-")) {
            return 0;
        }
        return -1;
    }

    /**
     * 中缀转后缀
     *
     * @param ls
     * @return
     */
    public  List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();  //符号栈
        List<String> s2 = new ArrayList<String>();  //结果

        for (String item : ls) {
            //如果是一个数
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                while (s1.size() != 0 && priority(s1.peek()) >= priority(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 完成对逆波兰表达式的计算
     * @param ls
     * @return
     */
    public  int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            BigFraction a;
            //使用正则表达式
            if (item.matches("\\d+")) {  //匹配多位数
                //入栈
                stack.push(item);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("÷")) {
                    try {
                        res = num1 / num2;
                    }catch (Exception e){
                    }
                } else {
                    throw new RuntimeException("运算符有问题");
                }
                //把结果入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

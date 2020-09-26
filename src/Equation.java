import java.util.List;

public class Equation {
    public String equz; // 中缀
    public String equh; // 后缀
    public String answer;  // 答案

    public Equation() {
    }

    /**
     * 根据 数字个数， 起始数字，结束数字， 是否出现括号，是否出现分数
     *
     * @param num
     * @param start
     * @param end
     * @param brc
     * @param fraction
     */
    public Equation(int num, int start, int end, boolean brc, boolean fraction) {
        Utils utils = new Utils();
        if (brc == true) { // 使用括号
            this.equz = utils.getBrcEquation(num, start, end);
        } else {
            this.equz = utils.getRglEquation(num, start, end);
        }
        List<String> ls = utils.epuToList(equz); // 中缀转换为列表
        List equhlist = utils.parseSuffixExpressionList(ls); // 中缀转后缀
        this.equh = equhlist.toString();
        String answer = utils.calculate(equhlist);
        this.answer = answer;

        if (fraction == true) { // 真分数
            this.equz = utils.trFraction(this.equz);
        }
    }

    public String getEquz() {
        return equz;
    }

    public void setEquz(String equz) {
        this.equz = equz;
    }

    public String getEquh() {
        return equh;
    }

    public void setEquh(String equh) {
        this.equh = equh;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

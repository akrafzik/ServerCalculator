
package ServerCalculator;

import java.io.Serializable;

public class Requisicao implements Serializable{
    float num1;
    float num2;
    char op;

    public float getNum1() {
        return num1;
    }

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public char getOp() {
        return op;
    }

    public void setOp(char op) {
        this.op = op;
    }
}

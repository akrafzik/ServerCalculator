
package ServerCalculator;

import java.io.Serializable;

public class Resposta implements Serializable{
    int Status;
    double result;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}

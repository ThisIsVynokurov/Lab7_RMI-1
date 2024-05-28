package client;

import java.math.BigDecimal;

import compute.Task;

public class e implements Task<BigDecimal>{

    private static final BigDecimal ONE = BigDecimal.ONE;
    private final int numOfDigits;

    public e(int numOfDigits){
        this.numOfDigits = numOfDigits;
    }

    public BigDecimal execute(){
        return ComputeE (numOfDigits);
    }

    public static BigDecimal ComputeE (int numOfDigits){
        int i = 1;
        int scale = numOfDigits + 5;
        BigDecimal res = ONE;
        BigDecimal term = ONE;

        while (term.compareTo(BigDecimal.ZERO) != 0){
            BigDecimal factorial = factorial (i);
            term = ONE.divide(factorial, scale, BigDecimal.ROUND_HALF_UP);
            res = res.add(term);
            i++;
        }
        return res.setScale(numOfDigits, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal factorial (int a){
        BigDecimal res = BigDecimal.ONE;
        for (int i = 2; i <= a; i++){
            res = res.multiply(BigDecimal.valueOf(i));
        }
        return res;
    }
}
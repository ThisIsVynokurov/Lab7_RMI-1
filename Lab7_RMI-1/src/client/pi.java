package client;

import java.math.BigDecimal;

import compute.Task;

public class pi implements Task<BigDecimal> {

    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
    private final int numOfDigits;

    public pi(int numOfDigits){
        this.numOfDigits = numOfDigits;
    }

    public BigDecimal execute(){
        return ComputePi (numOfDigits);
    }

    public static BigDecimal ComputePi (int numOfDigits){
        int scale = numOfDigits + 5;
        BigDecimal arctan1_5 = arctan (5, scale).divide(BigDecimal.valueOf(5), scale, roundingMode);
        BigDecimal arctan1_239 = arctan (239, scale).divide(BigDecimal.valueOf(239), scale, roundingMode);
        BigDecimal pi = arctan1_5.multiply (FOUR).subtract(arctan1_239).multiply(FOUR);
        return pi.setScale (numOfDigits, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal arctan(int X, int scale){
        int i = 0;
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal num = BigDecimal.valueOf(X).setScale(scale + 5);
        BigDecimal term;

        while (true){
            BigDecimal divider = BigDecimal.valueOf(2 * i + 1);
            BigDecimal dividend = num.divide(divider, scale, roundingMode);
            term = dividend.divide(BigDecimal.valueOf(X).pow(2 * i + 1), scale, roundingMode);

            if (i % 2 == 0){
                result = result.add(term);
            } else{
                result = result.subtract(term);
            }

            if (term.abs().compareTo(BigDecimal.ZERO) == 0){
                break;
            }
            i++;
        }
        return result;
    }
}
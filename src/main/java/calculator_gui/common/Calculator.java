package calculator_gui.common;

import com.udojava.evalex.AbstractFunction;
import com.udojava.evalex.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by zengxf on 2019/11/5.
 */
public class Calculator {

    private static final Logger log = LoggerFactory.getLogger(Calculator.class);
    private static final int DEF_SCALE = 2;

    private final int scale;
    private final RoundingMode mode = RoundingMode.HALF_UP;

    Calculator(int scale) {
        this.scale = scale;
    }

    public static Calculator of() {
        return ofScale(DEF_SCALE);
    }

    public static Calculator ofScale(int scale) {
        return new Calculator(scale);
    }

    public BigDecimal calculate(String expression) {
        Expression ex = new Expression(expression)
                .setRoundingMode(this.mode)
                .setVariable("k", "1000")
                .setVariable("w", "10000")
                .setVariable("m", "1000000")
                .setVariable("y", "100000000");
        this.setLe(ex)
                .setLg(ex)
                .setLog(ex)
                .setSqrt(ex);
        BigDecimal result = ex.eval()
                .setScale(this.scale, this.mode);
        log.debug("{} = {}", expression, result);
        return result;
    }

    /*** log_10{v} */
    private Calculator setLg(Expression ex) {
        ex.addFunction(new AbstractFunction("lg", 1) {
            @Override
            public BigDecimal eval(List<BigDecimal> parameters) {
                double v1 = parameters.get(0).doubleValue();
                return Calculator.this.valueOf(Math.log10(v1));
            }
        });
        return this;
    }

    /*** log_e{v} */
    private Calculator setLe(Expression ex) {
        ex.addFunction(new AbstractFunction("le", 1) {
            @Override
            public BigDecimal eval(List<BigDecimal> parameters) {
                double v1 = parameters.get(0).doubleValue();
                return Calculator.this.valueOf(Math.log(v1));
            }
        });
        return this;
    }

    /*** log_v1{v2} */
    private Calculator setLog(Expression ex) {
        ex.addFunction(new AbstractFunction("log", 2) {
            @Override
            public BigDecimal eval(List<BigDecimal> parameters) {
                double v1 = parameters.get(0).doubleValue();
                double v2 = parameters.get(1).doubleValue();
                return Calculator.this.valueOf(Math.log(v2) / Math.log(v1));
            }
        });
        return this;
    }

    /*** sqrt(4) = sqrt_2{4}; sqrt(4, 64) = sqrt_4{64};  */
    private Calculator setSqrt(Expression ex) {
        ex.addFunction(new AbstractFunction("sqrt", -1) {
            @Override
            public BigDecimal eval(List<BigDecimal> parameters) {
                int size = parameters.size();
                if (size == 0 || size > 2)
                    throw new RuntimeException("只支持一个或两个参数");
                double value;
                double v1 = parameters.get(0).doubleValue();
                if (size == 1)
                    value = Math.sqrt(v1);
                else {
                    double v2 = parameters.get(1).doubleValue();
                    value = Math.pow(v2, 1D / v1);
                }
                return Calculator.this.valueOf(value);
            }
        });
        return this;
    }

    private BigDecimal valueOf(double value) {
        return BigDecimal.valueOf(value).setScale(scale, mode);
    }

}

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
                .setRoundingMode(this.mode);
        this.setLe(ex)
                .setLg(ex)
                .setLog(ex);
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

    private BigDecimal valueOf(double value) {
        return new BigDecimal(value).setScale(scale, mode);
    }

}

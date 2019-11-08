package calculator_gui.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zengxf on 2019/11/5.
 */
public class CalculatorTest {

    static Logger log = LoggerFactory.getLogger(CalculatorTest.class);

    @Test
    public void test() {
        List<String> expressions = List.of(
                "220 / 3",
                "2 * (3 + 2)",
                "lg(100)",
                "log(2, 8)",
                "le(e)",
                "le(e)",
                "sqrt(4)",
                "sqrt(3, 64)",
                "2 ^ 5",
                "2 / 3"
        );
        expressions.forEach(expression -> {
            BigDecimal res = Calculator.of().calculate(expression);
            log.info("{} = {}", expression, res.doubleValue());
        });
    }

    @Test
    public void testPrecision() {
        String expression = "220 / 3";
        BigDecimal res = Calculator.of().calculate(expression);
        log.info("{} = {}", expression, res.doubleValue());
    }

    @Test
    public void testVariable() {
        List<String> expressions = List.of(
                "2 * PI",
                "2 * K",
                "2 * W",
                "2 * M",
                "2 * Y"
        );
        expressions.forEach(expression -> {
            BigDecimal res = Calculator.of().calculate(expression);
            log.info("{} = {}", expression, res.doubleValue());
        });
    }

}

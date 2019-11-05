package calculator_gui.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by zengxf on 2019/11/5.
 */
public class BigDecimalTest {

    static Logger log = LoggerFactory.getLogger(BigDecimalTest.class);

    @Test
    public void test() {
        BigDecimal v1 = new BigDecimal("12.3232");
        log.info("{} # scale: {}, precision: {}", v1, v1.scale(), v1.precision());
    }

}

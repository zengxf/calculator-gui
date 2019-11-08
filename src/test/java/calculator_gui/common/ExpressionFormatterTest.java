package calculator_gui.common;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zengxf on 2019/11/5.
 */
public class ExpressionFormatterTest {

    static Logger log = LoggerFactory.getLogger("UT");

    @Test
    public void format() {
        String ex = "23  /23 +32-32*43  %  23  /log(  20  )";
        log.info("{} #### {}", ex, ExpressionFormatter.format(ex));

        ex = "23*k/43*w";
        log.info("{} #### {}", ex, ExpressionFormatter.format(ex));
    }

}
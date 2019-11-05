package calculator_gui;

import calculator_gui.common.Calculator;
import calculator_gui.common.ExpressionFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by zengxf on 2019/11/5.
 */
public class CalculatorController {

    private static final Logger log = LoggerFactory.getLogger(CalculatorController.class);

    @FXML
    private Button calculateBtn;
    @FXML
    private TextField expressionText;
    @FXML
    private TextArea historyArea;

    @FXML
    public void onInitialize() {
        log.info("Initialize");
    }

    public void onTextKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            this.calculate();
        } else {
            calculateBtn.setDisable(false);
        }
    }

    public void onCalculate() {
        this.calculate();
    }

    private void calculate() {
        String expression = expressionText.getText();
        if (expression.isBlank()) {
            this.showMessage("请输入要计算的表达式");
            return;
        }

        log.info("Calculate");
        try {
            BigDecimal res = Calculator.of().calculate(expression);
            double value = res.doubleValue();

            log.info("{} = {}", expression, res.doubleValue());
            this.appendHistory(expression, value);
            expressionText.setText(value + "");
            calculateBtn.setDisable(true);
        } catch (Exception e) {
            this.showMessage("表达式有误！error: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("消息提示");
        alert.setHeaderText("表达式不对");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void appendHistory(String expression, double value) {
        log.info("Append history record");
        String text = ExpressionFormatter.format(expression) + " = " + value;
        String record = String.format("[%tT] %s", System.currentTimeMillis(), text);
        String history = historyArea.getText();
        if (history.isBlank()) {
            history = record;
        } else {
            history = record + "\n" + history;
        }
        historyArea.setText(history);
    }

    public void onClearHistory(ActionEvent event) {
        log.info("Clear history record");
        historyArea.setText("");
    }

    public void onClose(ActionEvent event) {
        log.info("Exit");
        System.exit(0);
    }

}

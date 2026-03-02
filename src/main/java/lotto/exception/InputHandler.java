package lotto.exception;

import lotto.view.InputView;
import lotto.view.LottoViewCode;

import java.util.function.Consumer;

public class InputHandler {
    private final InputView inputView = new InputView();

    public void retryInput(LottoViewCode code, Consumer<String> action) {
        boolean isValid = false;
        while (!isValid) {
            try {
                String input = inputView.price(code);
                action.accept(input);
                isValid = true;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
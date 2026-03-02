package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String price(LottoViewCode code) {
        System.out.println(code.getMessage());
        return Console.readLine();
    }
}
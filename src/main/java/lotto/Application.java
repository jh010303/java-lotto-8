package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();
        lottoController.purhcaseLotto();
        lottoController.getLottos();
        lottoController.setWinningNumbers();
        lottoController.setBonusNumber();
        lottoController.getWinningStatics();
        lottoController.getRate();
    }
}

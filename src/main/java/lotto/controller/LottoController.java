package lotto.controller;

import lotto.domain.Lotto;
import lotto.exception.InputHandler;
import lotto.service.LottoCommandService;
import lotto.service.LottoQueryService;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;

import static lotto.view.LottoViewCode.*;

public class LottoController {
    private final InputHandler inputHandler = new InputHandler();
    private final OutputView outputView = new OutputView();
    private final LottoCommandService lottoCommandService = new LottoCommandService();
    private final LottoQueryService lottoQueryService = new LottoQueryService();

    public void purhcaseLotto() {
        inputHandler.retryInput(PURCHASE,lottoCommandService::setLottoCount);
        int lottoCount = lottoQueryService.getLottoCount();
        outputView.purchased(PURCHASE_STATE,lottoCount);
    }

    public void getLottos() {
        lottoCommandService.purchaseLotto();
        List<Lotto> lottos = lottoQueryService.getLottos();
        outputView.Lottos(lottos);
    }

    public void setWinningNumbers() {
        inputHandler.retryInput(WINNER_NUMBER,lottoCommandService::setWinningNumbers);
    }

    public void setBonusNumber(){
        inputHandler.retryInput(BONUS_NUMBER,lottoCommandService::setBonusNumber);
    }

    public void getWinningStatics() {
        lottoCommandService.setWinningStatics();
        Map<Integer, Integer> winningStatics = lottoQueryService.getWinningStatics();
        outputView.winStatistics(winningStatics);
    }

    public void getRate() {
        lottoCommandService.setRate();
        double rate = lottoQueryService.getRate();
        outputView.rate(rate);
    }
}
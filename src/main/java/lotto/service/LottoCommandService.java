package lotto.service;

import lotto.domain.*;


public class LottoCommandService {
    public void setLottoCount(String price) {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        lottoMachine.setLottoPurchasePrice(price);
    }

    public void purchaseLotto() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        Lottos lottos = Lottos.getInstance();
        int lottoCount = lottoMachine.getLottoAmount();
        for(int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(lottoMachine.extractLottoNumbers());
            lottos.addLotto(lotto);
        }
    }

    public void setWinningNumbers(String winningNumbers) {
        WinningLotto winningLotto = WinningLotto.getInstance();
        winningLotto.setWinnerNumbers(winningNumbers);
    }

    public void setBonusNumber(String bonusNumber) {
        WinningLotto winningLotto = WinningLotto.getInstance();
        winningLotto.setBonusNumber(bonusNumber);
    }

    public void setWinningStatics() {
        Lottos lottos = Lottos.getInstance();
        WinningLotto winningLotto = WinningLotto.getInstance();
        WinningRecord winningRecord = WinningRecord.getInstance();
        for(Lotto lotto : lottos.getLottos()) {
            int correctCount = lotto.prepareLotto(winningLotto.getWinnerNumbers());
            boolean isBonus = lotto.checkBonus(winningLotto.getBonusNumber());
            winningRecord.addRecord(correctCount, isBonus);
        }
    }

    public void setRate() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        WinningRecord winningRecord = WinningRecord.getInstance();
        Store store = Store.getInstance();
        long profit = winningRecord.getTotalReward();
        int lottoPurchasePrice = lottoMachine.getLottoPurchasePrice();
        store.setProfit(profit,lottoPurchasePrice);
    }
}
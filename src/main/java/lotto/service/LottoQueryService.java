package lotto.service;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

public class LottoQueryService {
    public int getLottoCount() {
        LottoMachine lottoMachine = LottoMachine.getInstance();
        return lottoMachine.getLottoAmount();
    }

    public List<Lotto> getLottos(){
        Lottos lottos = Lottos.getInstance();
        return lottos.getLottos();
    }

    public Map<Integer, Integer> getWinningStatics() {
        WinningRecord winningRecord = WinningRecord.getInstance();
        return winningRecord.getRecords();
    }

    public double getRate() {
        Store store = Store.getInstance();
        return store.getRate();
    }
}
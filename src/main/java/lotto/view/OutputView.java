package lotto.view;

import lotto.domain.Lotto;

import java.util.List;
import java.util.Map;

import static lotto.constants.LottoPrice.*;
import static lotto.constants.LottoVariable.*;
import static lotto.constants.LottoWinner.*;
import static lotto.view.LottoViewCode.*;

public class OutputView {
    public void purchased(LottoViewCode code, int count) {
        System.out.println(code.getMessage(count));
    }

    public void Lottos(List<Lotto> lottos) {
        for(Lotto lotto : lottos){
            System.out.println(lotto.getNumbers());
        }
    }

    public void winStatistics(Map<Integer,Integer> winners) {
        System.out.println(WINNER_STATISTICS.getMessage());
        System.out.println(FIFTH_STATISTICS.getMessage(FIFTH_LOTTO_PRICE,winners.getOrDefault(FIFTH, LOTTO_NUMBER_COUNT_NONE)));
        System.out.println(FOURTH_STATISTICS.getMessage(FOURTH_LOTTO_PRICE ,winners.getOrDefault(FOURTH, LOTTO_NUMBER_COUNT_NONE)));
        System.out.println(THIRD_STATISTICS.getMessage(THIRD_LOTTO_PRICE, winners.getOrDefault(THIRD, LOTTO_NUMBER_COUNT_NONE)));
        System.out.println(SECOND_STATISTICS.getMessage(SECOND_LOTTO_PRICE, winners.getOrDefault(SECOND, LOTTO_NUMBER_COUNT_NONE)));
        System.out.println(FIRST_STATISTICS.getMessage(FIRST_LOTTO_PRICE, winners.getOrDefault(FIRST, LOTTO_NUMBER_COUNT_NONE)));
    }

    public void rate(double rate) {
        System.out.println(EARN_RATE.getMessage(rate));
    }
}
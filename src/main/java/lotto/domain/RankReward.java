package lotto.domain;

import lotto.constants.LottoWinner;
import lotto.exception.LottoErrorCode;

import static lotto.constants.LottoPrice.*;

public enum RankReward {
    FIRST(LottoWinner.FIRST, FIRST_LOTTO_PRICE),
    SECOND(LottoWinner.SECOND, SECOND_LOTTO_PRICE),
    THIRD(LottoWinner.THIRD, THIRD_LOTTO_PRICE),
    FOURTH(LottoWinner.FOURTH, FOURTH_LOTTO_PRICE),
    FIFTH(LottoWinner.FIFTH, FIFTH_LOTTO_PRICE),;

    private final int rank;
    private final int reward;

    RankReward(int rank, int reward) {
        this.rank = rank;
        this.reward = reward;
    }

    public static int getReward(int rank) {
        for (RankReward r : values()) {
            if (r.rank == rank) {
                return r.reward;
            }
        }
        throw new IllegalArgumentException(LottoErrorCode.RANK_OUT_OF_RANGE.getMessage());
    }
}
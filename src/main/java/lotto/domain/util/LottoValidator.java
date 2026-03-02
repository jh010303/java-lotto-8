package lotto.domain.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.constants.LottoVariable.LOTTO_NUMBER_COUNT;
import static lotto.exception.LottoErrorCode.WINNER_NUMBER_DUPLICATE;

public class LottoValidator {
    public static void checkDuplicate(List<Integer> winnerNumbers) {
        Set<Integer> set = new HashSet<>(winnerNumbers);
        if (set.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(WINNER_NUMBER_DUPLICATE.getMessage());
        }
    }
}
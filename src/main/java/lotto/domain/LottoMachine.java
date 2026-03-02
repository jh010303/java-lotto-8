package lotto.domain;

import lotto.domain.util.Converter;

import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

import static lotto.constants.LottoPrice.LOTTO_PRICE;
import static lotto.constants.LottoRegex.NUMBER_REGEX;
import static lotto.exception.LottoErrorCode.*;
import static lotto.constants.LottoVariable.*;

public class LottoMachine {
    private int lottoPurchasePrice;

    private static LottoMachine instance;

    private LottoMachine() {

    }

    public static LottoMachine getInstance() {
        if (instance == null) {
            instance = new LottoMachine();
        }
        return instance;
    }

    private void checkEmpty(String lottoPurchasePrice) {
        if(lottoPurchasePrice.isEmpty()) {
            throw new IllegalArgumentException(PURCHASE_EMPTY.getMessage());
        }
    }

    private void checkMatches(String lottoPurchasePrice) {
        if(!lottoPurchasePrice.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException(PURCHASE_STYLE_NOT_MATCH.getMessage());
        }
    }

    private void checkThousand(int lottoPurchasePrice) {
        if(lottoPurchasePrice % LOTTO_PRICE != REMAINDER_ZERO) {
            throw new IllegalArgumentException(PURCHASE_NOT_THOUSAND.getMessage());
        }
    }

    public int getLottoPurchasePrice() {
        return lottoPurchasePrice;
    }

    public void setLottoPurchasePrice(String lottoPurchasePrice) {
        this.lottoPurchasePrice = validate(lottoPurchasePrice);
    }

    private int validate(String lottoPurchasePrice) {
        checkEmpty(lottoPurchasePrice);
        checkMatches(lottoPurchasePrice);
        int convertedPrice = Converter.ToInt(lottoPurchasePrice);
        checkThousand(convertedPrice);
        return convertedPrice;
    }

    public List<Integer> extractLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_NUMBER_RANGE_MIN,LOTTO_NUMBER_RANGE_MAX,LOTTO_NUMBER_COUNT);
    }

    public int getLottoAmount() {
        return lottoPurchasePrice / LOTTO_PRICE;
    }
}
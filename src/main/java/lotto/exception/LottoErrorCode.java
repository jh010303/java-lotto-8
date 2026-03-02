package lotto.exception;

import static lotto.view.LottoViewCode.*;

public enum LottoErrorCode {
    PURCHASE_EMPTY("구입 금액이 비어있습니다."),
    PURCHASE_STYLE_NOT_MATCH("구입 금액이 올바른 형태가 아닙니다."),
    PURCHASE_NOT_THOUSAND("구입 금액이 1000단위가 아닙니다."),
    WINNER_NUMBER_STYLE_NOT_MATCH("당첨 번호가 올바른 형태가 아닙니다."),
    WINNER_NUMBER_OUT_OF_SIZE("당첨 번호가 6개가 아닙니다."),
    WINNER_NUMBER_DUPLICATE("당첨 번호가 중복됩니다."),
    BONUS_NUMBER_STYLE_NOT_MATCH("보너스 번호가 올바른 형태가 아닙니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호가 당첨 번호와 중복됩니다."),
    NUMBER_OUT_OF_RANGE("1~45가 아닌 숫자가 있습니다."),
    RANK_OUT_OF_RANGE("존재하지 않는 등수입니다."),
    OUT_OF_LOTTO_NUMBER("당첨 개수가 유효하지 않는 값 입니다."),
    LOTTO_COUNT_OUT_OF_RANGE("로또 번호는 6개여야 합니다.");

    private final String message;

    LottoErrorCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX.getMessage() +message;
    }
}
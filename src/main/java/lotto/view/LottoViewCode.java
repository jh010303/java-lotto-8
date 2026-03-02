package lotto.view;


public enum LottoViewCode {
    ERROR_PREFIX("[ERROR] "),
    PURCHASE("구입금액을 입력해 주세요."),
    PURCHASE_STATE("%d개를 구매했습니다."),
    WINNER_NUMBER("당첨 번호를 입력해 주세요."),
    BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    WINNER_STATISTICS("당첨 통계\n---"),
    FIFTH_STATISTICS("3개 일치 (%,d원) - %d개"),
    FOURTH_STATISTICS("4개 일치 (%,d원) - %d개"),
    THIRD_STATISTICS("5개 일치 (%,d원) - %d개"),
    SECOND_STATISTICS("5개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    FIRST_STATISTICS("6개 일치 (%,d원) - %d개"),
    EARN_RATE("총 수익률은 %.1f%%입니다.");

    private final String message;

    LottoViewCode(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(final Object... args) {
        return String.format(message, args);
    }
}
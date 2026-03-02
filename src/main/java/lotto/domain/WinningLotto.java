package lotto.domain;

import lotto.domain.util.Converter;
import lotto.domain.util.LottoValidator;

import java.util.Arrays;
import java.util.List;

import static lotto.constants.LottoRegex.*;
import static lotto.constants.LottoVariable.*;
import static lotto.exception.LottoErrorCode.*;

public class WinningLotto {
    private List<Integer> winnerNumbers;
    private int bonusNumber;

    private static WinningLotto instance;

    public static WinningLotto getInstance() {
        if (instance == null) {
            instance = new WinningLotto();
        }
        return instance;
    }

    public int getBonusNumber(){
        return bonusNumber;
    }

    public void setBonusNumber(String bonusNumber) {
        this.bonusNumber = validateBonusNumber(bonusNumber);
    }

    public List<Integer> getWinnerNumbers() {
        return winnerNumbers;
    }

    public void setWinnerNumbers(String winnerNumbers) {
        this.winnerNumbers = validateWinnerNumbers(winnerNumbers);
    }

    private List<Integer> validateWinnerNumbers(String winnerNumbers) {
        List<Integer> convertedWinnerNumbers = convertIntegerList(winnerNumbers);
        checkWinnerNumber(convertedWinnerNumbers);
        return convertedWinnerNumbers;
    }

    // convertIntegerList에 변환, 검증이 다 들어있음
    private List<Integer> convertIntegerList(String winnerNumbers) {
        try {
            return Arrays.stream(winnerNumbers.split(SPLIT_PATTERN)).map(Converter::ToInt).toList();
        }
        catch (Exception e) {
            throw new IllegalArgumentException(WINNER_NUMBER_STYLE_NOT_MATCH.getMessage());
        }
    }

    private void checkWinnerNumber(List<Integer> winnerNumbers) {
        checkWinnerNumberSize(winnerNumbers);
        LottoValidator.checkDuplicate(winnerNumbers);
        winnerNumbers.forEach(this::checkNumber);
    }

    private void checkWinnerNumberSize(List<Integer> winnerNumbers) {
        if(winnerNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(WINNER_NUMBER_OUT_OF_SIZE.getMessage());
        }
    }

    private int validateBonusNumber(String bonusNumber) {
        int convertedBonusNumber = convertBonusNumber(bonusNumber);
        checkNumber(convertedBonusNumber);
        checkBonusDuplicate(convertedBonusNumber);
        return convertedBonusNumber;
    }

    // convertBonusNumber에 변환, 검증이 다 들어있음
    private int convertBonusNumber(String bounsNumber) {
        try {
            return Converter.ToInt(bounsNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BONUS_NUMBER_STYLE_NOT_MATCH.getMessage());
        }
    }

    private void checkNumber(int number) {
        if(number< LOTTO_NUMBER_RANGE_MIN || number> LOTTO_NUMBER_RANGE_MAX) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private void checkBonusDuplicate(int bonusNumber) {
        if (winnerNumbers != null && winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE.getMessage());
        }
    }
}
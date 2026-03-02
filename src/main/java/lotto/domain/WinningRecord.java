package lotto.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static lotto.constants.LottoVariable.*;
import static lotto.constants.LottoWinner.*;
import static lotto.exception.LottoErrorCode.OUT_OF_LOTTO_NUMBER;

public class WinningRecord {
    private final Map<Integer, Integer> records = new HashMap<>();

    private static WinningRecord instance;

    private WinningRecord() {

    }

    public static WinningRecord getInstance() {
        if (instance == null) {
            instance = new WinningRecord();
        }
        return instance;
    }

    public void addRecord(int correctCount,boolean bonus) {
        validateRecord(correctCount);
        if(correctCount == FIFTH_COUNT) {
            records.merge(FIFTH,RECORD_INCREASE,Integer::sum);
        }
        if(correctCount==FOURTH_COUNT) {
            records.merge(FOURTH,RECORD_INCREASE,Integer::sum);
        }
        if(correctCount==THIRD_COUNT && !bonus) {
            records.merge(THIRD,RECORD_INCREASE,Integer::sum);
        }
        if(correctCount==SECOND_COUNT && bonus) {
            records.merge(SECOND,RECORD_INCREASE,Integer::sum);
        }
        if(correctCount==FIRST_COUNT) {
            records.merge(FIRST,RECORD_INCREASE,Integer::sum);
        }
    }

    private void validateRecord(int number) {
        if(number < LOTTO_NUMBER_COUNT_NONE || number > LOTTO_NUMBER_COUNT) {
            throw new IllegalStateException(OUT_OF_LOTTO_NUMBER.getMessage());
        }
    }

    public Map<Integer,Integer> getRecords() {
        return Collections.unmodifiableMap(records);
    }

    public long getTotalReward() {
        long total = 0;
        for (Map.Entry<Integer, Integer> entry : records.entrySet()) {
            int rank = entry.getKey();
            int count = entry.getValue();
            int reward = RankReward.getReward(rank);
            total += (long) reward * count;
        }
        return total;
    }

    public void clearRecords() {
        records.clear();
    }
}
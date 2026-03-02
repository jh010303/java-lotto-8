package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static lotto.constants.LottoWinner.*;

class WinningRecordTest {
    private WinningRecord winningRecord;

    @BeforeEach
    void setUp() {
        winningRecord = WinningRecord.getInstance();
        winningRecord.clearRecords();
    }

    @DisplayName("3개 일치하는 경우 5등으로 기록된다.")
    @Test
    void 세개_일치하는_경우_5등으로_기록된다() {
        // when
        winningRecord.addRecord(3, false);
        
        // then
        Map<Integer, Integer> records = winningRecord.getRecords();
        assertThat(records.get(FIFTH)).isEqualTo(1);
    }

    @DisplayName("4개 일치하는 경우 4등으로 기록된다.")
    @Test
    void 네개_일치하는_경우_4등으로_기록된다() {
        // when
        winningRecord.addRecord(4, false);
        
        // then
        Map<Integer, Integer> records = winningRecord.getRecords();
        assertThat(records.get(FOURTH)).isEqualTo(1);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하지 않는 경우 3등으로 기록된다.")
    @Test
    void 다섯개_일치하고_보너스_불일치_경우_3등으로_기록된다() {
        // when
        winningRecord.addRecord(5, false);
        
        // then
        Map<Integer, Integer> records = winningRecord.getRecords();
        assertThat(records.get(THIRD)).isEqualTo(1);
    }

    @DisplayName("5개 일치하고 보너스 번호가 일치하는 경우 2등으로 기록된다.")
    @Test
    void 다섯개_일치하고_보너스_일치_경우_2등으로_기록된다() {
        // when
        winningRecord.addRecord(5, true);
        
        // then
        Map<Integer, Integer> records = winningRecord.getRecords();
        assertThat(records.get(SECOND)).isEqualTo(1);
    }

    @DisplayName("6개 일치하는 경우 1등으로 기록된다.")
    @Test
    void 여섯개_일치하는_경우_1등으로_기록된다() {
        // when
        winningRecord.addRecord(6, false);
        
        // then
        Map<Integer, Integer> records = winningRecord.getRecords();
        assertThat(records.get(FIRST)).isEqualTo(1);
    }

    @DisplayName("유효하지 않은 일치 개수인 경우 예외가 발생한다.")
    @Test
    void 유효하지_않은_일치_개수인_경우_예외가_발생한다() {
        assertThatThrownBy(() -> winningRecord.addRecord(-1, false))
                .isInstanceOf(IllegalStateException.class);
                
        assertThatThrownBy(() -> winningRecord.addRecord(7, false))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("총 상금이 정확하게 계산된다.")
    @Test
    void 총_상금이_정확하게_계산된다() {
        // given
        winningRecord.addRecord(3, false);
        winningRecord.addRecord(4, false);
        winningRecord.addRecord(5, false);
        
        // when
        long totalReward = winningRecord.getTotalReward();
        
        // then
        assertThat(totalReward).isEqualTo(1_555_000);
    }
}
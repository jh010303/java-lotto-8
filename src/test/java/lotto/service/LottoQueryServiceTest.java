package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.WinningRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoQueryServiceTest {
    private LottoQueryService lottoQueryService;
    private LottoCommandService lottoCommandService;

    @BeforeEach
    void setUp() {
        lottoQueryService = new LottoQueryService();
        lottoCommandService = new LottoCommandService();
        Lottos.getInstance().clear();
        WinningRecord.getInstance().clearRecords();
    }

    @DisplayName("로또 구매 개수를 조회한다.")
    @Test
    void 로또_구매_개수를_조회한다() {
        // given
        lottoCommandService.setLottoCount("5000");
        
        // when
        int lottoCount = lottoQueryService.getLottoCount();
        
        // then
        assertThat(lottoCount).isEqualTo(5);
    }

    @DisplayName("구매한 로또 목록을 조회한다.")
    @Test
    void 구매한_로또_목록을_조회한다() {
        // given
        lottoCommandService.setLottoCount("3000");
        lottoCommandService.purchaseLotto();
        
        // when
        List<Lotto> lottos = lottoQueryService.getLottos();
        
        // then
        assertThat(lottos).hasSize(3);
    }

    @DisplayName("당첨 통계를 조회한다.")
    @Test
    void 당첨_통계를_조회한다() {
        // given
        lottoCommandService.setLottoCount("1000");

        Lottos lottos = Lottos.getInstance();
        lottos.addLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        lottoCommandService.setBonusNumber("7");

        lottoCommandService.setWinningStatics();
        
        // when
        Map<Integer, Integer> winningStatics = lottoQueryService.getWinningStatics();
        
        // then
        assertThat(winningStatics.get(1)).isEqualTo(1); // 1등 1개
    }

    @DisplayName("수익률을 조회한다.")
    @Test
    void 수익률을_조회한다() {
        // given
        lottoCommandService.setLottoCount("1000");

        Lottos lottos = Lottos.getInstance();
        lottos.addLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        lottoCommandService.setBonusNumber("7");

        lottoCommandService.setWinningStatics();
        lottoCommandService.setRate();
        
        // when
        double rate = lottoQueryService.getRate();
        
        // then
        assertThat(rate).isEqualTo(200000000.0);
    }
}
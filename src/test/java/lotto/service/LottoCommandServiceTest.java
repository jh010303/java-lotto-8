package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.domain.WinningRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCommandServiceTest {
    private LottoCommandService lottoCommandService;
    private LottoQueryService lottoQueryService;

    @BeforeEach
    void setUp() {
        lottoCommandService = new LottoCommandService();
        lottoQueryService = new LottoQueryService();
        Lottos.getInstance().clear();
        WinningRecord.getInstance().clearRecords();
    }

    @DisplayName("로또 구입 금액을 설정한다.")
    @Test
    void 로또_구입_금액을_설정한다() {
        // given, when
        lottoCommandService.setLottoCount("5000");
        
        // then
        LottoMachine lottoMachine = LottoMachine.getInstance();
        assertThat(lottoMachine.getLottoPurchasePrice()).isEqualTo(5000);
    }

    @DisplayName("로또를 구매한다.")
    @Test
    void 로또를_구매한다() {
        // given
        lottoCommandService.setLottoCount("3000");
        
        // when
        lottoCommandService.purchaseLotto();
        
        // then
        List<Lotto> lottos = lottoQueryService.getLottos();
        assertThat(lottos).hasSize(3);
    }

    @DisplayName("당첨 번호를 설정한다.")
    @Test
    void 당첨_번호를_설정한다() {
        // given, when
        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        
        // then
        WinningLotto winningLotto = WinningLotto.getInstance();
        assertThat(winningLotto.getWinnerNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("보너스 번호를 설정한다.")
    @Test
    void 보너스_번호를_설정한다() {
        // given
        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        
        // when
        lottoCommandService.setBonusNumber("7");
        
        // then
        WinningLotto winningLotto = WinningLotto.getInstance();
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @DisplayName("당첨 통계를 설정한다.")
    @Test
    void 당첨_통계를_설정한다() {
        // given
        lottoCommandService.setLottoCount("1000");

        Lottos lottos = Lottos.getInstance();
        lottos.addLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        lottoCommandService.setBonusNumber("7");
        
        // when
        lottoCommandService.setWinningStatics();
        
        // then
        Map<Integer, Integer> winningStatics = lottoQueryService.getWinningStatics();
        assertThat(winningStatics.get(1)).isEqualTo(1);
    }

    @DisplayName("수익률을 설정한다.")
    @Test
    void 수익률을_설정한다() {
        // given
        // 로또 구매
        lottoCommandService.setLottoCount("1000");

        Lottos lottos = Lottos.getInstance();
        lottos.addLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)));

        lottoCommandService.setWinningNumbers("1,2,3,4,5,6");
        lottoCommandService.setBonusNumber("7");

        lottoCommandService.setWinningStatics();
        
        // when
        lottoCommandService.setRate();
        
        // then
        double rate = lottoQueryService.getRate();
        assertThat(rate).isEqualTo(200000000.0);
    }
}
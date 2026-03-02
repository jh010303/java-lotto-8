package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = LottoMachine.getInstance();
    }

    @DisplayName("구입 금액이 정상적으로 설정되는지 확인한다.")
    @Test
    void 구입_금액이_정상적으로_설정된다() {
        // when
        lottoMachine.setLottoPurchasePrice("8000");
        
        // then
        assertThat(lottoMachine.getLottoPurchasePrice()).isEqualTo(8000);
    }

    @DisplayName("구입 금액이 비어있으면 예외가 발생한다.")
    @Test
    void 구입_금액이_비어있으면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoMachine.setLottoPurchasePrice(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_숫자가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoMachine.setLottoPurchasePrice("abc"))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> lottoMachine.setLottoPurchasePrice("1000원"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 구입_금액이_천원_단위가_아니면_예외가_발생한다() {
        assertThatThrownBy(() -> lottoMachine.setLottoPurchasePrice("1500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 구매 개수가 정확하게 계산된다.")
    @Test
    void 로또_구매_개수가_정확하게_계산된다() {
        // given
        lottoMachine.setLottoPurchasePrice("8000");
        
        // when
        int lottoAmount = lottoMachine.getLottoAmount();
        
        // then
        assertThat(lottoAmount).isEqualTo(8);
    }

    @DisplayName("로또 번호가 정상적으로 생성된다.")
    @Test
    void 로또_번호가_정상적으로_생성된다() {
        // when
        List<Integer> lottoNumbers = lottoMachine.extractLottoNumbers();
        
        // then
        assertThat(lottoNumbers).hasSize(6);
        assertThat(lottoNumbers).allMatch(n -> n >= 1 && n <= 45);
        assertThat(lottoNumbers).doesNotHaveDuplicates();
    }

    @DisplayName("수익률이 정확하게 계산된다.")
    @Test
    void 수익률이_정확하게_계산된다() {
        // given
        lottoMachine.setLottoPurchasePrice("10000");
        lottoMachine.setProfit(5000);
        
        // when
        double rate = lottoMachine.getRate();
        
        // then
        assertThat(rate).isEqualTo(50.0);
    }
}
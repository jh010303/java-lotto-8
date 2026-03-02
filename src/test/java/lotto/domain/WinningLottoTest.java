package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {
    private WinningLotto winningLotto;

    @BeforeEach
    void setUp() {
        winningLotto = WinningLotto.getInstance();
    }

    @DisplayName("당첨 번호가 정상적으로 설정되는지 확인한다.")
    @Test
    void 당첨_번호가_정상적으로_설정된다() {
        winningLotto.setWinnerNumbers("1,2,3,4,5,6");
        assertThat(winningLotto.getWinnerNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("당첨 번호의 형식이 올바르지 않으면 예외가 발생한다.")
    @Test
    void 당첨_번호의_형식이_올바르지_않으면_예외가_발생한다() {
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);
        
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 당첨_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("1,2,3,4,5,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호의 범위가 1~45를 벗어나면 예외가 발생한다.")
    @Test
    void 당첨_번호의_범위가_벗어나면_예외가_발생한다() {
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("0,2,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> winningLotto.setWinnerNumbers("1,2,3,4,5,46"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 정상적으로 설정되는지 확인한다.")
    @Test
    void 보너스_번호가_정상적으로_설정된다() {
        winningLotto.setWinnerNumbers("1,2,3,4,5,6");
        winningLotto.setBonusNumber("7");
        assertThat(winningLotto.getBonusNumber()).isEqualTo(7);
    }

    @DisplayName("보너스 번호의 형식이 올바르지 않으면 예외가 발생한다.")
    @Test
    void 보너스_번호의_형식이_올바르지_않으면_예외가_발생한다() {
        winningLotto.setWinnerNumbers("1,2,3,4,5,6");
        
        assertThatThrownBy(() -> winningLotto.setBonusNumber("a"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스_번호가_당첨_번호와_중복되면_예외가_발생한다() {
        winningLotto.setWinnerNumbers("1,2,3,4,5,6");
        
        assertThatThrownBy(() -> winningLotto.setBonusNumber("1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호의 범위가 1~45를 벗어나면 예외가 발생한다.")
    @Test
    void 보너스_번호의_범위가_벗어나면_예외가_발생한다() {
        winningLotto.setWinnerNumbers("1,2,3,4,5,6");
        
        assertThatThrownBy(() -> winningLotto.setBonusNumber("0"))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> winningLotto.setBonusNumber("46"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
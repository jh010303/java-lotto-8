package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 정상적으로 생성되는지 확인한다.")
    @Test
    void 로또_번호가_정상적으로_생성된다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("로또 번호와 당첨 번호를 비교하여 일치하는 개수를 반환한다.")
    @Test
    void 로또_번호와_당첨_번호를_비교한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int matchCount = lotto.prepareLotto(List.of(1, 2, 3, 10, 11, 12));
        assertThat(matchCount).isEqualTo(3);
    }

    @DisplayName("로또 번호에 보너스 번호가 포함되어 있는지 확인한다.")
    @Test
    void 로또_번호에_보너스_번호가_포함되어_있는지_확인한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThat(lotto.checkBonus(3)).isTrue();
        assertThat(lotto.checkBonus(7)).isFalse();
    }
}

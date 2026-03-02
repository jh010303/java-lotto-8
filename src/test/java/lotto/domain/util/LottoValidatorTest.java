package lotto.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {
    
    @DisplayName("중복되지 않은 6개의 번호는 검증을 통과한다.")
    @Test
    void 중복되지_않은_6개의_번호는_검증을_통과한다() {
        // when, then
        assertThatCode(() -> LottoValidator.checkDuplicate(List.of(1, 2, 3, 4, 5, 6)))
                .doesNotThrowAnyException();
    }
    
    @DisplayName("중복된 번호가 있으면 예외가 발생한다.")
    @Test
    void 중복된_번호가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> LottoValidator.checkDuplicate(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
                
        assertThatThrownBy(() -> LottoValidator.checkDuplicate(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
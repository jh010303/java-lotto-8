package lotto.domain.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConverterTest {
    
    @DisplayName("문자열을 정수로 변환한다.")
    @Test
    void 문자열을_정수로_변환한다() {
        // when, then
        assertThat(Converter.ToInt("123")).isEqualTo(123);
        assertThat(Converter.ToInt("0")).isEqualTo(0);
        assertThat(Converter.ToInt("-10")).isEqualTo(-10);
    }
    
    @DisplayName("숫자가 아닌 문자열을 변환하면 예외가 발생한다.")
    @Test
    void 숫자가_아닌_문자열을_변환하면_예외가_발생한다() {
        assertThatThrownBy(() -> Converter.ToInt("abc"))
                .isInstanceOf(NumberFormatException.class);
                
        assertThatThrownBy(() -> Converter.ToInt("123a"))
                .isInstanceOf(NumberFormatException.class);
                
        assertThatThrownBy(() -> Converter.ToInt(""))
                .isInstanceOf(NumberFormatException.class);
    }
}
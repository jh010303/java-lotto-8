package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottosTest {
    private Lottos lottos;

    @BeforeEach
    void setUp() {
        lottos = Lottos.getInstance();
    }

    @DisplayName("로또를 추가하면 리스트에 정상적으로 저장된다.")
    @Test
    void 로또를_추가하면_리스트에_정상적으로_저장된다() {
        // given
        int initialSize = lottos.getLottos().size();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        
        // when
        lottos.addLotto(lotto);
        
        // then
        List<Lotto> lottoList = lottos.getLottos();
        assertThat(lottoList).hasSize(initialSize + 1);
        assertThat(lottoList.get(lottoList.size() - 1).getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("getLottos 메서드는 불변 리스트를 반환한다.")
    @Test
    void getLottos_메서드는_불변_리스트를_반환한다() {
        // when
        List<Lotto> lottoList = lottos.getLottos();
        
        // then
        assertThatThrownBy(() -> lottoList.add(new Lotto(List.of(1, 2, 3, 4, 5, 6))))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
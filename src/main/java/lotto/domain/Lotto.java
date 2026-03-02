package lotto.domain;

import java.util.Collections;
import java.util.List;


public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    // TODO: 추가 기능 구현
    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }

    public boolean checkBonus(int bonus) {
        return this.numbers.contains(bonus);
    }

    public int prepareLotto(List<Integer> winningNumbers) {
        return toInt(this.numbers.stream().filter(winningNumbers::contains).count());
    }

    private int toInt(long number) {
        return Math.toIntExact(number);
    }
}
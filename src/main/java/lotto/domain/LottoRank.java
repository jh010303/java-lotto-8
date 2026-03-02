package lotto.domain;

import java.util.List;

public class LottoRank {

    // TODO 준형 : 위치 이동하기 (현재 package -> lotto.domain.util)을
    // TODO 준형 : LottoCalculator 아닌 다른 이름(원하는대로) 변경하고 다른 패키지로 이동
    public enum Rank {
        FIRST(6, false, "1등"),
        SECOND(5, true, "2등"),
        THIRD(5, false, "3등"),
        FOURTH(4, false, "4등"),
        FIFTH(3, false, "5등"),
        NONE(0, false, "꽝")
        ;

        private final int matchCount;
        private final boolean bonus;
        private final String description;

        Rank(int matchCount, boolean bonus, String description) {
            this.matchCount = matchCount;
            this.bonus = bonus;
            this.description = description;
        }

        public Rank calculateRank(WinningLotto winningLotto, int bonusNumber, Lotto lotto) {
            List<Integer> userNumbers = lotto.getNumbers();
            List<Integer> winningNumbers = winningLotto.getWinnerNumbers();
            // TODO -> 준형 : stream으로 고치기
            // TODO -> 현빈 : for문을 유지하되, 더 깔끔하게 + List<Integer>가 아닌 만들어놓은 객체 사용하기
            long matchCount = 0;
            matchCount = userNumbers.stream().filter(num->winningNumbers.contains(num)).count();

            boolean bonusMatch = userNumbers.contains(bonusNumber);

            // TODO -> 준형 : Rank enum에 findByCount() 만들어서 호출하는 쪽으로 변경
            // TODO -> 현빈 : Rank enum 구조 변경(description 추가) 및 기존 정의한 matchCount 활용으로 변경
            return findByCount(matchCount,bonusMatch);
        }

        private LottoRank.Rank findByCount(long matchCount, boolean bonusMatch){
            if (matchCount == FIRST.matchCount) return Rank.FIRST;
            if (matchCount == SECOND.matchCount && bonusMatch) return Rank.SECOND;
            if (matchCount == THIRD.matchCount) return Rank.THIRD;
            if (matchCount == FOURTH.matchCount) return Rank.FOURTH;
            if (matchCount == FIFTH.matchCount) return Rank.FIFTH;
            return Rank.NONE;
        }
    }
}

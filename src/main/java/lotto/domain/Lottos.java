package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private List<Lotto> lottos = new ArrayList<>();

    private static Lottos instance;

    private Lottos() {

    }

    public static Lottos getInstance() {
        if (instance == null) {
            instance = new Lottos();
        }
        return instance;
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    public void addLotto(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public void clear(){
        this.lottos.clear();
    }
}
package game.components;

import game.Type;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

public class AiMove {

    public void moveWolf(Type wolf) {
        assert wolf != null;
        getGameWorld().getSingleton(wolf).setAnchoredPosition((wolf.getX()+1)*50, (wolf.getY()+1)*50);
    }
}

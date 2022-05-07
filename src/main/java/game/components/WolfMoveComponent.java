package game.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.Type;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static game.Type.*;

public class WolfMoveComponent extends Component {
    private static AStarMoveComponent astar;

    public void moveDWright(int wolfNumber) {
        Type wolf = whichWolf(wolfNumber);
        assert wolf != null;
        if (!getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).isMoving()) {
            getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).moveToCell(
                    (int) (getGameWorld().getSingleton(wolf).getX() / 50 + 1),
                    (int) (getGameWorld().getSingleton(wolf).getY() / 50 + 1));
        }

    }

    public void moveDWLeft(int wolfNumber) {
        Type wolf = whichWolf(wolfNumber);
        assert wolf != null;
        if (!getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).isMoving()) {
            getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).moveToCell(
                    (int) (getGameWorld().getSingleton(wolf).getX() / 50 - 1),
                    (int) (getGameWorld().getSingleton(wolf).getY() / 50 + 1));
        }

    }

    public Type whichWolf(int i) {
        switch (i) {
            case 4 -> {
                return WOLF4;
            }
            case 3 -> {
                return WOLF3;
            }
            case 2 -> {
                return WOLF2;
            }
            case 1 -> {
                return WOLF1;
            }
        }
        return null;
    }
}

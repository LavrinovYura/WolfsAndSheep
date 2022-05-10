package game.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.Pair;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static game.Type.*;
import static game.components.GlobalVars.turn;

public class SheepMoveComponent extends Component {

    private static AStarMoveComponent astar;

    //private static final Entity component = getGameWorld().getSingleton(SHEEP);
    //private static final AStarMoveComponent move = getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class);

    public void moveDWright() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 + 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 + 1));
        }
        SHEEP.setCoordinate(new Pair<>(SHEEP.getY() + 1, SHEEP.getX() + 1));
    }

    public void moveDWLeft() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 - 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 + 1));
        }
        SHEEP.setCoordinate(new Pair<>(SHEEP.getY() + 1, SHEEP.getX() - 1));

    }

    public void moveUPDRight() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 + 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 - 1));
        }
        SHEEP.setCoordinate(new Pair<>(SHEEP.getY() - 1, SHEEP.getX() + 1));
    }

    public void moveUPDLeft() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 - 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 - 1));
        }
        SHEEP.setCoordinate(new Pair<>(SHEEP.getY() - 1, SHEEP.getX() - 1));
    }
}

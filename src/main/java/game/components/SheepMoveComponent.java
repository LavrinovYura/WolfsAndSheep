package game.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.Pair;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static game.FieldParameters.*;
import static game.Type.*;
import static game.components.GlobalVars.sheepCoordinate;
import static game.components.GlobalVars.turn;
import static game.components.GlobalVars.array;

public class SheepMoveComponent extends Component {
    private WolfAlgorithm wolfAlgorithm = new WolfAlgorithm();
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
        array[(int) sheepCoordinate.getFirst()][(int) sheepCoordinate.getSecond()] = "1";
        array[(int) sheepCoordinate.getFirst() + 1][(int) sheepCoordinate.getSecond() + 1] = "S";
        sheepCoordinate = new Pair((int) sheepCoordinate.getFirst() + 1, (int) sheepCoordinate.getSecond() + 1);
        wolfAlgorithm.wolfMove();
    }

    public void moveDWLeft() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 - 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 + 1));
        }
        array[(int) sheepCoordinate.getFirst()][(int) sheepCoordinate.getSecond()] = "1";
        array[(int) sheepCoordinate.getFirst() + 1][(int) sheepCoordinate.getSecond() - 1] = "S";
        sheepCoordinate = new Pair<>((int) sheepCoordinate.getFirst() + 1, (int) sheepCoordinate.getSecond() - 1);
        wolfAlgorithm.wolfMove();
    }

    public void moveUPDRight() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 + 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 - 1));
            //astar.moveToRightCell();
        }
        array[(int) sheepCoordinate.getFirst()][(int) sheepCoordinate.getSecond()] = "1";
        array[(int) sheepCoordinate.getFirst() - 1][(int) sheepCoordinate.getSecond() + 1] = "S";
        sheepCoordinate = new Pair((int) sheepCoordinate.getFirst() - 1, (int) sheepCoordinate.getSecond() + 1);
        wolfAlgorithm.wolfMove();
    }

    public void moveUPDLeft() {
        if (!getGameWorld().getSingleton(SHEEP).getComponent(AStarMoveComponent.class).isMoving()) {
            turn = true;
            astar.moveToCell(
                    (int) (getGameWorld().getSingleton(SHEEP).getX() / 50 - 1),
                    (int) (getGameWorld().getSingleton(SHEEP).getY() / 50 - 1));
        }
        array[(int) sheepCoordinate.getFirst()][(int) sheepCoordinate.getSecond()] = "1";
        array[(int) sheepCoordinate.getFirst() - 1][(int) sheepCoordinate.getSecond() - 1] = "S";
        sheepCoordinate = new Pair((int) sheepCoordinate.getFirst() - 1, (int) sheepCoordinate.getSecond() - 1);
        System.out.println(sheepCoordinate.getFirst());
        System.out.println(sheepCoordinate.getSecond());
        wolfAlgorithm.wolfMove();
    }
}

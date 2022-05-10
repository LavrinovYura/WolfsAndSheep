package game.components;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.Type;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static game.components.GlobalVars.turn;

public class WolfMoveComponent extends Component {

    private static AStarMoveComponent astar;

    public void moveWolf(Type wolf) {
        assert wolf != null;
        System.out.println(getGameWorld().getSingleton(wolf).getY());
        System.out.println(getGameWorld().getSingleton(wolf).getX());
        if (!getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).isMoving()) {
            getGameWorld().getSingleton(wolf).getComponent(AStarMoveComponent.class).moveToCell( (wolf.getX()+1), (wolf.getY())+1);
        }
        System.out.println(getGameWorld().getSingleton(wolf).getY());
        System.out.println(getGameWorld().getSingleton(wolf).getX());
        turn = true;
    }
}

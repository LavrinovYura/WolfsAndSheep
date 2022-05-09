package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.components.SheepMoveComponent;
import game.components.WolfAlgorithm;
import game.components.WolfMoveComponent;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import static com.almasb.fxgl.dsl.FXGL.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;
import static game.FieldParameters.*;
import static game.Type.*;
import static game.components.GlobalVars.*;

public class Main extends GameApplication {


    public WolfAlgorithm wolfAlgorithm = new WolfAlgorithm();

    public WolfMoveComponent wolfMove1;
    public WolfMoveComponent wolfMove2;
    public WolfMoveComponent wolfMove3;
    public WolfMoveComponent wolfMove4;
    public Entity wolf1;
    public Entity wolf2;
    public Entity wolf3;
    public Entity wolf4;
    public Factory factory = new Factory();
    public SheepMoveComponent sheepMove;
    public Entity sheep;

    public static AStarGrid grid;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(500);
        settings.setHeight(500);
        settings.setTitle("Basic Game App");
        settings.setVersion("0.1");
        settings.setManualResizeEnabled(true);
        settings.setMainMenuEnabled(true);
        settings.setGameMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @NotNull
            @Override
            public FXGLMenu newMainMenu() {
                return new MenuMine();
            }
        });
    }

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(factory);
        setGame();
    }

    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Up Right") {
            @Override
            protected void onAction() {
                if (!sheep.getComponent(AStarMoveComponent.class).isMoving() && isWolfMoving() && canSheepMoveInThatDirection(1)) {
                    sheepMove.moveUPDRight();

                }
            }
        }, KeyCode.E);

        getInput().addAction(new UserAction("UP Left") {
            @Override
            protected void onAction() {
                if (!sheep.getComponent(AStarMoveComponent.class).isMoving() && isWolfMoving() && canSheepMoveInThatDirection(2))
                    sheepMove.moveUPDLeft();

            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down Right") {
            @Override
            protected void onAction() {
                if (!sheep.getComponent(AStarMoveComponent.class).isMoving() && isWolfMoving() && canSheepMoveInThatDirection(3))
                    sheepMove.moveDWright();

            }
        }, KeyCode.D);
        getInput().addAction(new UserAction("Down Left") {
            @Override
            protected void onAction() {
                if (!sheep.getComponent(AStarMoveComponent.class).isMoving() && isWolfMoving() && canSheepMoveInThatDirection(4))
                    sheepMove.moveDWLeft();

            }
        }, KeyCode.S);
        getInput().addAction(new UserAction("Reset") {
            @Override
            protected void onAction() {
                resetGame();
            }
        }, KeyCode.O);
    }

    public void resetGame() {
        getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
        setGame();
    }

    // по диагонила вверх 1 - вправо 2 - влево
//              вниз  3 - вправо 4 - влево
    public boolean canSheepMoveInThatDirection(int direction) {
        int y = SHEEP.getY();
        int x = SHEEP.getX();
        switch (direction) {
            case 1 -> {
                return array[y - 1][x + 1].equals("1");
            }
            case 2 -> {
                return array[y - 1][x - 1].equals("1");
            }
            case 3 -> {
                return array[y + 1][x + 1].equals("1");
            }
            case 4 -> {
                return array[y + 1][x - 1].equals("1");
            }
        }
        return false;
    }

    public boolean isWolfMoving() {
        return !wolf1.getComponent(AStarMoveComponent.class).isMoving()
                & !wolf2.getComponent(AStarMoveComponent.class).isMoving()
                & !wolf3.getComponent(AStarMoveComponent.class).isMoving()
                & !wolf4.getComponent(AStarMoveComponent.class).isMoving();
    }

    public void setGame() {
        spawn("BG");

        sheep = spawn("S", 50, 400);
        SHEEP.setCoordinate(new Pair<>(8, 1));
        for (int i = 0; i < 10; i++) System.arraycopy(arr[i], 0,array[i] , 0, 10);
        System.out.println(arr[1][2]);
        int cellX = 0;
        int cellY = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                switch (array[i][j]) {
                    //   case "0" -> spawn("WC", cellX, cellY);

                    //   case "1" -> spawn("BC", cellX, cellY);

                    case "WA" -> spawn("WA", cellX, cellY);

                    case "W1" -> {
                        //      spawn("BC", cellX, cellY);
                        WOLF1.setCoordinate(new Pair<>(1, 2));
                        wolf1 = spawn("W1", cellX, cellY);
                        wolfMove1 = wolf1.getComponent(WolfMoveComponent.class);
                    }
                    case "W2" -> {
                        //    spawn("BC", cellX, cellY);
                        WOLF2.setCoordinate(new Pair<>(1, 4));
                        wolf2 = spawn("W2", cellX, cellY);
                        wolfMove2 = wolf2.getComponent(WolfMoveComponent.class);
                    }
                    case "W3" -> {
                        //        spawn("BC", cellX, cellY);
                        WOLF3.setCoordinate(new Pair<>(1, 6));
                        wolf3 = spawn("W3", cellX, cellY);
                        wolfMove3 = wolf3.getComponent(WolfMoveComponent.class);
                    }
                    case "W4" -> {
                        //        spawn("BC", cellX, cellY);
                        WOLF4.setCoordinate(new Pair<>(1, 8));
                        wolf4 = spawn("W4", cellX, cellY);
                        wolfMove4 = wolf4.getComponent(WolfMoveComponent.class);
                    }
                }
                cellX += CELL_SIZE;
            }
            cellY += CELL_SIZE;
            cellX = 0;
        }

        sheepMove = sheep.getComponent(SheepMoveComponent.class);

        grid = AStarGrid.fromWorld(getGameWorld(), FIELD_SIZE, FIELD_SIZE, CELL_SIZE, CELL_SIZE, type -> {
            if (type.equals(WALL))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        set("grid", grid);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
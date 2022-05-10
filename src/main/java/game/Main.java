package game;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.pathfinding.CellState;
import com.almasb.fxgl.pathfinding.astar.AStarGrid;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import game.components.Algorithm;
import game.components.SheepMoveComponent;
import game.components.WolfMoveComponent;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

import static com.almasb.fxgl.dsl.FXGL.*;

import static com.almasb.fxgl.dsl.FXGLForKtKt.getInput;
import static game.FieldParameters.*;
import static game.Type.*;
import static game.components.GlobalVars.*;

public class Main extends GameApplication {


    public Algorithm algorithm = new Algorithm();

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
                if (turn && isWolfMoving()) {
                    for (Type entity : Type.values()) {
                        System.out.print(entity.getName() + " " + entity.getY() + "." + entity.getX() + " ");
                    }
                    System.out.println("\n");
                    sheepMove.moveUPDRight();
                    turn = false;
                    algorithm.minMax(2, 0, -500, +500);
                    for (Type entity : Type.values()) {
                        System.out.print(entity.getName() + " " + entity.getY() + "." + entity.getX() + " ");
                    }
                    System.out.println("\n");
                    for (int k = 0; k < 8; k++) System.out.println(Arrays.toString(array[k]));
                    System.out.println("\n");

                }
            }
        }, KeyCode.E);

        getInput().addAction(new UserAction("UP Left") {
            @Override
            protected void onAction() {
                if (turn && isWolfMoving()) {
                    sheepMove.moveUPDLeft();
                    turn = false;
                    algorithm.minMax(2, 0, -500, +500);
                    for (int k = 0; k < 8; k++) System.out.println(Arrays.toString(array[k]));
                    System.out.println("\n");

                }
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Down Right") {
            @Override
            protected void onAction() {
                if (turn) {
                    turn = false;
                    algorithm.minMax(2, 0, -500, +500);
                    algorithm.prepareField();
                    for (int k = 0; k < 8; k++) System.out.println(Arrays.toString(array[k]));
                    System.out.println("\n");
                 // for (Type entity : Type.values()) {
                 //     System.out.print(entity.getName() + " " + entity.getY() + "." + entity.getX() + " " + "\n");
                 // }
                }
            }
        }, KeyCode.D);
        getInput().addAction(new UserAction("Down Left") {
            @Override
            protected void onAction() {
                if (turn) {

                    turn = false;
                    algorithm.minMax(1, 0, -500, +500);
                    algorithm.prepareField();
                    for (int k = 0; k < 8; k++) System.out.println(Arrays.toString(array[k]));
                    System.out.println("\n");
                  // for (Type entity : Type.values()) {
                  //     System.out.print(entity.getName() + " " + entity.getY() + "." + entity.getX() + " " + "\n");
                  // }
                }
            }
        }, KeyCode.S);
        getInput().addAction(new UserAction("Reset") {
            @Override
            protected void onAction() {
                //resetGame();
                turn = true;
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
                if (y != 0 && x != 7)
                    return array[y - 1][x + 1] == 0;
            }
            case 2 -> {
                if (y != 0 && x != 0)
                    return array[y - 1][x - 1] == 0;
            }
            case 3 -> {
                if (y != 7 && x != 7)
                    return array[y + 1][x + 1] == 0;
            }
            case 4 -> {
                if (y != 7 && x != 0)
                    return array[y + 1][x - 1] == 0;
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

        sheep = spawn("S", 400, 250);
        SHEEP.setCoordinate(new Pair<>(7, 1));
        sheepMove = sheep.getComponent(SheepMoveComponent.class);

        WOLF1.setCoordinate(new Pair<>(0, 0));
        wolf1 = spawn("W1", 50, 100);
        wolfMove1 = wolf1.getComponent(WolfMoveComponent.class);

        WOLF2.setCoordinate(new Pair<>(0, 2));
        wolf2 = spawn("W2", 50, 200);
        wolfMove2 = wolf2.getComponent(WolfMoveComponent.class);

        WOLF3.setCoordinate(new Pair<>(0, 4));
        wolf3 = spawn("W3", 50, 300);
        wolfMove3 = wolf3.getComponent(WolfMoveComponent.class);

        WOLF4.setCoordinate(new Pair<>(0, 6));
        wolf4 = spawn("W4", 50, 400);
        wolfMove4 = wolf4.getComponent(WolfMoveComponent.class);

        int cellX = 0;
        int cellY = 0;

        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (i == 0 || j == 0 || j == 9 || i == 9) {
                    spawn("WA", cellX, cellY);
                }
                cellX += CELL_SIZE;
            }
            cellY += CELL_SIZE;
            cellX = 0;
        }

        grid = AStarGrid.fromWorld(getGameWorld(), FIELD_SIZE, FIELD_SIZE, CELL_SIZE, CELL_SIZE, type -> {
            if (type.equals(WALL))
                return CellState.NOT_WALKABLE;

            return CellState.WALKABLE;
        });
        set("grid", grid);
    }

    public static void gameOver() {
        String gameOver = "Sheep Winner!\n\n";

        FXGL.getDialogService().showMessageBox(gameOver, () -> FXGL.getGameController().gotoMainMenu());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package game;

import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.HealthIntComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.pathfinding.CellMoveComponent;
import com.almasb.fxgl.pathfinding.astar.AStarMoveComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.ui.ProgressBar;
import game.components.SheepMoveComponent;
import game.components.WolfMoveComponent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import static com.almasb.fxgl.dsl.FXGL.geto;
import static com.almasb.fxgl.dsl.FXGL.texture;
import static com.almasb.fxgl.dsl.FXGLForKtKt.entityBuilder;
import static game.Textures.*;
import static game.FieldParameters.*;
import static game.Type.*;

public class Factory implements EntityFactory {
    @Spawns("BG")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .view(BG_TEXTURE)
                .zIndex(-1)
                .build();
    }
    @Spawns("WA")
    public Entity newWall(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(WALL)
                .with(new CollidableComponent(true))
                .viewWithBBox(texture(WALL_TEXTURE, CELL_SIZE, CELL_SIZE))
                .build();
    }
    @Spawns("BC")
    public Entity blackCell(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(BLACK)
                .with(new CollidableComponent(true))
                .viewWithBBox(texture(BLACK_CELL, CELL_SIZE, CELL_SIZE))
                .build();
    }
    @Spawns("WC")
    public Entity whiteCell(SpawnData data) {

        return FXGL.entityBuilder(data)
                .type(WHITE)
                .with(new CollidableComponent(true))
                .viewWithBBox(texture(WHITE_CELL, CELL_SIZE, CELL_SIZE))
                .build();
    }

    @Spawns("S")
    public Entity newSheep(SpawnData data) {

        var e =  entityBuilder(data)
                .type(SHEEP)
                .viewWithBBox(texture(SHEEP_TEXTURE, CELL_SIZE, CELL_SIZE))
                .with(new CellMoveComponent(CELL_SIZE, CELL_SIZE, 200))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new SheepMoveComponent())
                .build();
        e.setLocalAnchorFromCenter();
        return e;
    }
    @Spawns("W1")
    public Entity newWolf1(SpawnData data) {

        var e =  entityBuilder(data)
                .type(WOLF1)
                .viewWithBBox(texture(WOLF_TEXTURE, CELL_SIZE, CELL_SIZE))
                .with(new CellMoveComponent(CELL_SIZE, CELL_SIZE, 300))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new WolfMoveComponent())
                .build();
        e.setLocalAnchorFromCenter();
        return e;
    }
    @Spawns("W2")
    public Entity newWolf2(SpawnData data) {

        var e =  entityBuilder(data)
                .type(WOLF2)
                .viewWithBBox(texture(WOLF_TEXTURE, CELL_SIZE, CELL_SIZE))
                .with(new CellMoveComponent(CELL_SIZE, CELL_SIZE, 300))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new WolfMoveComponent())
                .build();
        e.setLocalAnchorFromCenter();
        return e;
    }
    @Spawns("W3")
    public Entity newWolf3(SpawnData data) {

        var e =  entityBuilder(data)
                .type(WOLF3)
                .viewWithBBox(texture(WOLF_TEXTURE, CELL_SIZE, CELL_SIZE))
                .with(new CellMoveComponent(CELL_SIZE, CELL_SIZE, 300))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new WolfMoveComponent())
                .build();
        e.setLocalAnchorFromCenter();
        return e;
    }
    @Spawns("W4")
    public Entity newWolf4(SpawnData data) {

        var e =  entityBuilder(data)
                .type(WOLF4)
                .viewWithBBox(texture(WOLF_TEXTURE, CELL_SIZE, CELL_SIZE))
                .with(new CellMoveComponent(CELL_SIZE, CELL_SIZE, 300))
                .with(new AStarMoveComponent(new LazyValue<>(() -> geto("grid"))))
                .with(new WolfMoveComponent())
                .build();
        e.setLocalAnchorFromCenter();
        return e;
    }
}

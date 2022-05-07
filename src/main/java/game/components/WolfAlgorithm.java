package game.components;

import game.Pair;
import game.Type;

import static game.Type.*;
import static game.Type.WOLF1;
import static game.components.GlobalVars.*;


public class WolfAlgorithm {

    public WolfMoveComponent wolfComponent = new WolfMoveComponent();
    Pair<Integer, Integer> wolf1Last = new Pair<>(1, 2);
    Pair<Integer, Integer> wolf2Last = new Pair<>(1, 4);
    Pair<Integer, Integer> wolf3Last = new Pair<>(1, 6);
    Pair<Integer, Integer> wolf4Last = new Pair<>(1, 8);

    private Pair<Integer, Integer>[] wolfs = new Pair[]{wolf1Last, wolf2Last, wolf3Last, wolf4Last};
    private int init = 0;
    private int iteration = 0;
    boolean bestMove = true;

    public void wolfMove() {
        if (turn) {
            if (init < 4) {
                for (int i = 0; i < 4; i++) {
                    Type wolf = wolfComponent.whichWolf(i+1);
                    int first = wolfs[i].getFirst();
                    int second = wolfs[i].getSecond();
                    if (first == 1) {
                        wolfComponent.moveDWLeft(i + 1);
                        ++first;
                        --second;
                        wolf1Last = new Pair<>(first, second);
                        System.out.println(wolfs[i].getFirst());
                        System.out.println(wolf1Last.getFirst());
                        array[first - 1][second + 1] = "1";
                        array[first][second] = "W" + i;
                        turn = false;
                        break;
                    }
                }
                init++;
            } else {
                int iteration =0;
                String[][] testArray = array;
                int wolf1x = wolf1Last.getSecond();
                int wolf1y = wolf1Last.getFirst();
                int wolf2x = wolf1Last.getSecond();
                int wolf2y = wolf1Last.getFirst();
                int wolf3x = wolf1Last.getSecond();
                int wolf3y = wolf1Last.getFirst();
                int wolf4x = wolf1Last.getSecond();
                int wolf4y = wolf1Last.getFirst();
                int sheepX = (int) sheepCoordinate.getSecond();
                int sheepY = (int) sheepCoordinate.getFirst();
                while (bestMove) {
                    for (int i = 0; i < 20; i++) {
                        System.out.println("44444444444444");
                        if (wolf1y == wolf2y && wolf1y == wolf3y && wolf1y == wolf4y && iteration ==0) {
                            if (testArray[wolf1y + 1][wolf1x + 1].equals("1") && wolf1y ==1){
                                wolfComponent.moveDWright(1);
                                array[wolf1y][wolf1x] = "1";
                                array[wolf1y+1][wolf1x+1] = "W" + i;
                                wolf1Last = new Pair<>(++wolf1y, ++wolf1x);
                                bestMove =false;
                                turn = false;
                                break;
                            } else if (testArray[wolf4y + 1][wolf1x - 1].equals("1") && wolf4x == 8) {
                                wolfComponent.moveDWLeft(4);
                                array[wolf4y][wolf4y] = "1";
                                array[wolf4y+1][wolf4x-1] = "W" + i;
                                wolf4Last = new Pair<>(++wolf4y, ++wolf4x);
                                bestMove =false;
                                turn = false;
                                break;
                            } else if (wolf1x == 1){
                                wolfComponent.moveDWLeft(4);
                                array[wolf4y][wolf4y] = "1";
                                array[wolf4y+1][wolf4x-1] = "W" + i;
                                wolf4Last = new Pair<>(++wolf4y, ++wolf4x);
                                bestMove =false;
                                turn = false;
                                break;
                            } else  if (wolf4x ==8){
                                wolfComponent.moveDWright(1);
                                array[wolf1y][wolf1x] = "1";
                                array[wolf1y+1][wolf1x+1] = "W" + i;
                                wolf1Last = new Pair<>(++wolf1y, ++wolf1x);
                                bestMove =false;
                                turn = false;
                                break;
                            }
                        }
                        iteration++;
                    }
                    bestMove =false;
                }
            }
        }
    }
}

package game.components;

import game.Pair;
import game.Type;

import java.util.Arrays;

import static game.Type.*;
import static game.Type.WOLF1;
import static game.components.GlobalVars.*;


public class WolfAlgorithm {

    public WolfMoveComponent wolfComponent = new WolfMoveComponent();

    private int init = 0;
    private int iteration = 0;
    boolean bestMove = true;
    String[][] testArray = new String[10][10];
    Type wolf;

    public void wolfMove() {
        if (turn) {
            if (init < 4) {
                for (int i = 0; i < 4; i++) {
                    wolf = wolfComponent.whichWolf(i + 1);
                    int y = wolf.getY();
                    int x = wolf.getX();
                    System.out.print(x);
                    if (y == 1) {
                        wolfComponent.moveDWLeft(i + 1);
                        array[y][x] = "1";
                        wolf.setCoordinate(new Pair<>(++y, --x));
                        array[y][x] = "W" + i;
                        turn = false;
                        break;
                    }
                }
                init++;
            } else {
                for (Type type : Arrays.stream(values()).toList()) {
                    System.out.println(type + " " + type.getX() + " " + type.getY());
                }
                int iteration = 0;
                for (int i = 0; i < 10; i++) System.arraycopy(arr[i], 0, testArray[i], 0, 10);
                int wolfX = WOLF1.getX();
                int wolf1x = WOLF1.getX();
                int wolf2x = WOLF2.getX();
                int wolf3x = WOLF3.getX();
                int wolf4x = WOLF4.getX();
                int wolf1y = WOLF1.getY();
                int wolf2y = WOLF2.getY();
                int wolf3y = WOLF3.getY();
                int wolf4y = WOLF4.getY();
                int sheepX = SHEEP.getX();
                int sheepY = SHEEP.getY();
                Pair<Integer,Pair<Type, Integer>> firstMove = null;
                if (wolf1y == wolf2y && wolf1y == wolf3y && wolf1y == wolf4y) {
                    if (testArray[wolf1y + 1][wolf1x + 1].equals("1") && wolf1x == 1) {
                        System.out.println("here");
                        move4(wolf1x, wolf1x, wolf1y, wolf1y);
                        return;
                    } else if (testArray[wolf4y + 1][wolf1x - 1].equals("1") && wolf4x == 8) {
                        move1(wolf4x, wolf4y);
                        return;
                    } else if (wolf1x == 1) {
                        move1(wolf4x, wolf4y);
                        return;
                    } else if (wolf4x == 8) {
                        move4(wolf1x, wolf4x, wolf1y, wolf4y);
                        return;
                    }
                }
                int cof = 0;
                while (bestMove) {
                    System.out.println("stack");
                    for (int i = 0; i < 10; i++) System.arraycopy(arr[i], 0, testArray[i], 0, 10);
                    Pair[] testAr = new Pair[]{new Pair<>(wolf1y, wolf1x), new Pair<>(wolf2y, wolf2x), new Pair<>(wolf2y, wolf2x), new Pair<>(wolf2y, wolf2x)};
                    for (int i = 0; i < 5; i++) {
                        do {
                            int r = (int) (Math.random() * 3) + 1;

                            if (canWolfMoveRight((int) testAr[r].getFirst(), (int) testAr[r].getSecond())) {
                                if (iteration == 0) {
                                    wolf = wolfComponent.whichWolf(r);
                                    firstMove = new Pair<>(r, new Pair<>(wolf, 0));
                                } //0 = ход вправо

                                testArray[(int) testAr[r].getFirst()][(int) testAr[r].getSecond()] = "1";
                                testArray[(int) testAr[r].getFirst() + 1][(int) testAr[r].getSecond() + 1] = "W";

                                break;
                            } else if (canWolfMoveLeft((int) testAr[r].getFirst(), (int) testAr[r].getSecond())) {
                                if (iteration == 0) {
                                    wolf = wolfComponent.whichWolf(r);
                                    firstMove =  new Pair<>(r, new Pair<>(wolf, 1));
                                }//1=ход влево

                                testArray[(int) testAr[r].getFirst()][(int) testAr[r].getSecond()] = "1";
                                testArray[(int) testAr[r].getFirst() + 1][(int) testAr[r].getSecond() - 1] = "W";

                                break;
                            }
                        } while (true);
                        if (canSheepMoveURight(sheepY, sheepX)) {
                            testArray[sheepY][sheepX] = "1";
                            testArray[sheepY - 1][sheepX + 1] = "S";
                        } else if (canSheepMoveULeft(sheepY, sheepX)) {
                            testArray[sheepY][sheepX] = "1";
                            testArray[sheepY - 1][sheepX - 1] = "S";
                        } else if (canSheepMoveDRight(sheepY, sheepX)) {
                            testArray[sheepY][sheepX] = "1";
                            testArray[sheepY + 1][sheepX + 1] = "S";
                        } else if (canSheepMoveDLeft(sheepY, sheepX)) {
                            testArray[sheepY][sheepX] = "1";
                            testArray[sheepY + 1][sheepX - 1] = "S";
                        }
                        iteration++;
                    }
                    for (Pair wolf : testAr) {
                        if (wolf.getFirst().equals(sheepY)) {
                            cof++;
                        }
                    }
                    if (cof < 4) bestMove = true;
                }
                if (firstMove.getSecond().getSecond() == 0) {
                    wolfComponent.moveDWright(firstMove.getFirst());
                    array[firstMove.getSecond().getFirst().getY()][firstMove.getSecond().getFirst().getX()] = "1";
                    array[firstMove.getSecond().getFirst().getY() + 1][firstMove.getSecond().getFirst().getX() + 1] = "W" + 1;
                    firstMove.getSecond().getFirst().setCoordinate(new Pair<>(firstMove.getSecond().getFirst().getY() + 1, firstMove.getSecond().getFirst().getX() + 1));
                    turn = false;
                } else {
                    wolfComponent.moveDWright(firstMove.getFirst());
                    array[firstMove.getSecond().getFirst().getY()][firstMove.getSecond().getFirst().getX()] = "1";
                    array[firstMove.getSecond().getFirst().getY() + 1][firstMove.getSecond().getFirst().getX() - 1] = "W" + 1;
                    firstMove.getSecond().getFirst().setCoordinate(new Pair<>(firstMove.getSecond().getFirst().getY() + 1, firstMove.getSecond().getFirst().getX() + 1));
                    turn = false;
                }
            }
        }
    }

    private void move1(int wolf4x, int wolf4y) {
        wolfComponent.moveDWLeft(4);
        array[wolf4y][wolf4y] = "1";
        array[wolf4y + 1][wolf4x - 1] = "W" + 4;
        WOLF4.setCoordinate(new Pair<>(++wolf4y, ++wolf4x));
        turn = false;
    }

    private void move4(int wolf1x, int wolf4x, int wolf1y, int wolf4y) {
        wolfComponent.moveDWright(1);
        array[wolf1y][wolf1x] = "1";
        array[wolf1y + 1][wolf1x + 1] = "W" + 1;
        WOLF1.setCoordinate(new Pair<>(++wolf4y, ++wolf4x));
        turn = false;
    }

    public boolean canWolfMoveRight(int y, int x) {
        return testArray[y + 1][x + 1].equals("1");
    }

    public boolean canWolfMoveLeft(int y, int x) {
        return testArray[y + 1][x - 1].equals("1");
    }

    public boolean canSheepMoveDRight(int y, int x) {
        return testArray[y + 1][x + 1].equals("1");
    }

    public boolean canSheepMoveDLeft(int y, int x) {
        return testArray[y + 1][x - 1].equals("1");
    }

    public boolean canSheepMoveURight(int y, int x) {
        return testArray[y - 1][x + 1].equals("1");
    }

    public boolean canSheepMoveULeft(int y, int x) {
        return testArray[y - 1][x - 1].equals("1");
    }
}

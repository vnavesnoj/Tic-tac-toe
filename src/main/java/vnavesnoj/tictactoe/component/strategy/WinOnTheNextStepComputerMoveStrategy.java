/*
 * Copyright (c) 2022. vnavesnoj <vnavesnoj@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vnavesnoj.tictactoe.component.strategy;

import vnavesnoj.tictactoe.component.ComputerMoveStrategy;
import vnavesnoj.tictactoe.model.game.Cell;
import vnavesnoj.tictactoe.model.game.GameTable;
import vnavesnoj.tictactoe.model.game.Player;
import vnavesnoj.tictactoe.model.game.Sign;

import java.util.Random;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class WinOnTheNextStepComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Player player) {
        final Cell targetCell = findRandomCellWithSign(gameTable, player.getSign());
        if (targetCell != null) {
            int[] randomNumbers = massiveWithRandomUniqueNumbersFrom1(4);
            for (final int randomNumber : randomNumbers) {
                if (randomNumber == 1) {
                    if (tryToMoveSecondSignByTheRow(gameTable, player, targetCell)) return true;
                } else if (randomNumber == 2) {
                    if (tryToMoveSecondSignByTheColumn(gameTable, player, targetCell)) return true;
                } else if (randomNumber == 3) {
                    if (tryToMoveSecondSignByTheFirstDiagonal(gameTable, player, targetCell)) return true;
                } else if (randomNumber == 4) {
                    if (tryToMoveSecondSignByTheSecondDiagonal(gameTable, player, targetCell)) return true;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("SameParameterValue")
    private int[] massiveWithRandomUniqueNumbersFrom1(final int length) {
        int[] numbers = new int[length];
        Random random = new Random();
        int count = 0;
        while (count < length) {
            boolean unique = true;
            int randomNumber = random.nextInt(length) + 1;
            for (int i = 0; i < count; i++) {
                if (numbers[i] == randomNumber) {
                    unique = false;
                    break;
                }
            }
            if (unique) {
                numbers[count] = randomNumber;
                count++;
            }
        }
        return numbers;
    }

    private Cell findRandomCellWithSign(final GameTable gameTable, final Sign sign) {
        Cell[] cellsWithPlayerSigns = gameTable.getAllCellsWithTargetSign(sign);
        int amountOfPlayerSigns = cellsWithPlayerSigns.length;
        if (amountOfPlayerSigns > 0) {
            return cellsWithPlayerSigns[new Random().nextInt(amountOfPlayerSigns)];
        }
        return null;
    }

    private boolean tryToMoveSecondSignByTheRow(final GameTable gameTable,
                                                final Player player,
                                                final Cell targetCell) {
        return tryToMoveSecondSign(
                gameTable,
                player,
                i -> new Cell(targetCell.getRow(), (targetCell.getCol() + i + 1) % 3)
        );
    }

    private boolean tryToMoveSecondSignByTheColumn(final GameTable gameTable,
                                                   final Player player,
                                                   final Cell targetCell) {
        return tryToMoveSecondSign(
                gameTable,
                player,
                i -> new Cell(((targetCell.getRow() + i + 1) % 3), targetCell.getCol())
        );
    }

    private boolean tryToMoveSecondSignByTheFirstDiagonal(final GameTable gameTable,
                                                          final Player player,
                                                          final Cell targetCell) {
        boolean isTargetCellOnFirstDiagonal = isTargetCellOnDiagonal(targetCell, i -> i);
        if (isTargetCellOnFirstDiagonal) {
            return tryToMoveSecondSign(
                    gameTable,
                    player,
                    i -> new Cell((targetCell.getRow() + i + 1) % 3, (targetCell.getCol() + i + 1) % 3)
            );
        }
        return false;
    }

    private boolean tryToMoveSecondSignByTheSecondDiagonal(final GameTable gameTable,
                                                           final Player player,
                                                           final Cell targetCell) {
        boolean isTargetCellOnSecondDiagonal = isTargetCellOnDiagonal(targetCell, i -> 2 - i);
        if (isTargetCellOnSecondDiagonal) {
            return tryToMoveSecondSign(
                    gameTable,
                    player,
                    i -> new Cell((targetCell.getRow() + i + 1) % 3, 2 - (targetCell.getCol() + i + 1) % 3)
            );
        }
        return false;
    }


    private boolean isTargetCellOnDiagonal(final Cell targetCell, LambdaConversionForInt lambdaInt) {
        for (int i = 0; i < 3; i++) {
            if (targetCell.getRow() == i && targetCell.getCol() == lambdaInt.getValue(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean tryToMoveSecondSign(final GameTable gameTable,
                                        final Player player,
                                        LambdaConversionForCell lambdaCell) {
        Cell[] cells = new Cell[2];
        for (int i = 0; i < 2; i++) {
            cells[i] = lambdaCell.getValue(i);
        }
        if (gameTable.isEmpty(cells[0]) && gameTable.isEmpty(cells[1])) {
            gameTable.setSign(cells[new Random().nextInt(2)], player.getSign());
            return true;
        }
        return false;
    }

    @FunctionalInterface
    private interface LambdaConversionForInt {

        int getValue(int i);
    }

    @FunctionalInterface
    private interface LambdaConversionForCell {

        Cell getValue(int i);
    }
}

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

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Player player) {
        final Sign findSign = getFindSign(player.getSign());
        return tryToMakeMoveByRows(gameTable, player, findSign) ||
                tryToMakeMoveByCols(gameTable, player, findSign) ||
                tryToMakeMoveByMainDiagonal(gameTable, player, findSign) ||
                tryToMakeMoveBySecondaryDiagonal(gameTable, player, findSign);
    }

    protected abstract Sign getFindSign(Sign moveSign);

    private boolean tryToMakeMoveByRows(final GameTable gameTable, final Player player, final Sign findSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, player, findSign, false, (i, k) -> new Cell(k, i));
    }

    private boolean tryToMakeMoveByCols(final GameTable gameTable, final Player player, final Sign findSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, player, findSign, false, Cell::new);
    }

    private boolean tryToMakeMoveByMainDiagonal(final GameTable gameTable, final Player player, final Sign findSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, player, findSign, true, (i, k) -> new Cell(k, k));
    }

    private boolean tryToMakeMoveBySecondaryDiagonal(final GameTable gameTable, final Player player, final Sign findSign) {
        return tryToMakeMoveUsingLambdaConversion(gameTable, player, findSign, true, (i, k) -> new Cell(k, 2 - k));
    }

    private boolean tryToMakeMoveUsingLambdaConversion(final GameTable gameTable, final Player player, final Sign findSign, boolean tryToMoveByDiagonal, LambdaNewCell lambdaNewCell) {
        for (int i = 0; i < 3; i++) {
            int count = 0;
            Cell currentCell;
            Cell emptyCell = null;
            for (int k = 0; k < 3; k++) {
                currentCell = lambdaNewCell.getValue(i, k);
                if (gameTable.getSign(currentCell) == Sign.EMPTY) {
                    emptyCell = currentCell;
                } else if (gameTable.getSign(currentCell) == findSign) {
                    count++;
                } else {
                    break;
                }
            }
            if (emptyCell != null && count == 2) {
                gameTable.setSign(emptyCell, player.getSign());
                return true;
            }
            if (tryToMoveByDiagonal) {
                break;
            }
        }
        return false;
    }

    @FunctionalInterface
    private interface LambdaNewCell {

        Cell getValue(int row, int col);
    }
}

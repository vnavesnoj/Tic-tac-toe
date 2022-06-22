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

package vnavesnoj.tictactoe.component;

import vnavesnoj.tictactoe.model.Cell;
import vnavesnoj.tictactoe.model.GameTable;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class WinnerVerifier {

    public boolean isUserWinner(final GameTable gameTable) {
        return isWinner(gameTable, 'X');
    }

    public boolean isComputerWinner(final GameTable gameTable) {
        return isWinner(gameTable, 'O');
    }

    private boolean isWinner(final GameTable gameTable, final char sign) {
        return isWinHorizontally(gameTable, sign) ||
                isWinVertically(gameTable, sign) ||
                isWinDiagonally(gameTable, sign);
    }

    private boolean isWinHorizontally(final GameTable gameTable, final char sign) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(i, 0)) == gameTable.getSign(new Cell(i, 1))
                    && gameTable.getSign(new Cell(i, 0)) == gameTable.getSign(new Cell(i, 2))
                    && gameTable.getSign(new Cell(i, 0)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinVertically(final GameTable gameTable, final char sign) {
        for (int i = 0; i < 3; i++) {
            if (gameTable.getSign(new Cell(0, i)) == gameTable.getSign(new Cell(1, i))
                    && gameTable.getSign(new Cell(0, i)) == gameTable.getSign(new Cell(2, i))
                    && gameTable.getSign(new Cell(0, i)) == sign) {
                return true;
            }
        }
        return false;
    }

    private boolean isWinDiagonally(final GameTable gameTable, final char sign) {
        if (gameTable.getSign(new Cell(0, 0)) == gameTable.getSign(new Cell(1, 1))
                && gameTable.getSign(new Cell(0, 0)) == gameTable.getSign(new Cell(2, 2))
                && gameTable.getSign(new Cell(0, 0)) == sign) {
            return true;
        }
        if (gameTable.getSign(new Cell(0, 2)) == gameTable.getSign(new Cell(1, 1))
                && gameTable.getSign(new Cell(0, 2)) == gameTable.getSign(new Cell(2, 0))
                && gameTable.getSign(new Cell(0, 2)) == sign) {
            return true;
        }
        return false;
    }
}
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

package vnavesnoj.tictactoe.model.game;

import java.util.Arrays;

import static vnavesnoj.tictactoe.model.game.Sign.EMPTY;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class GameTable {

    private final Sign[][] table = {
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY}
    };

    public boolean isEmpty(final Cell cell) {
        return table[cell.getRow()][cell.getCol()] == EMPTY;
    }

    public Cell[] allEmptyCell() {
        Cell[] emptyCells = new Cell[9];
        Cell cell;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                cell = new Cell(i, k);
                if (isEmpty(cell)) {
                    emptyCells[count++] = cell;
                }
            }
        }
        final Cell[] temp = new Cell[count];
        System.arraycopy(emptyCells, 0, temp, 0, count);
        emptyCells = temp;
        return emptyCells;
    }

    public Sign getSign(final Cell cell) {
        return table[cell.getRow()][cell.getCol()];
    }

    public void setSign(final Cell cell, Sign sign) {
        table[cell.getRow()][cell.getCol()] = sign;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameTable{");
        sb.append("table=");
        for (int i = 0; i < table.length; i++) {
            sb.append(Arrays.toString(table[i]));
            if (i < table.length - 1) {
                sb.append(';');
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

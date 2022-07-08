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

package vnavesnoj.tictactoe.component.console.keypad;

import vnavesnoj.tictactoe.component.console.CellNumberConverter;
import vnavesnoj.tictactoe.model.game.Cell;

import static java.lang.String.format;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class DesktopNumericKeypadCellNumberConverter implements CellNumberConverter {

    @Override
    public Cell toCell(final int number) {
        if (number >= 0 && number <= 9) {
            return new Cell((2 - ((number - 1) / 3)), (number - 1) % 3);
        }
        throw new IllegalArgumentException(
                format("Number parameter must be between '1' and '9'! Current value is '%s'!", number)
        );
    }

    @Override
    public int toNumber(final Cell cell) {
        return 7 - 3 * cell.getRow() + cell.getCol();
    }
}

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

import java.util.Scanner;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class UserTurn {

    private final CellNumberConverter cellNumberConverter;

    public UserTurn(final CellNumberConverter cellNumberConverter) {
        this.cellNumberConverter = cellNumberConverter;
    }

    public void makeMove(final GameTable gameTable) {
        while (true) {
            System.out.println("Please type number between 1 and 9: ");
            Cell cell = getUserInput();
            if (cell != null) {
                if (gameTable.isEmpty(cell)) {
                    gameTable.setSign(cell, 'X');
                    return;
                }
                System.out.println("Can't make a move, because the cell is not free! Try again!");
            }
        }
    }


    private Cell getUserInput() {
        final String enter = new Scanner(System.in).nextLine();
        if (enter.length() == 1) {
            if (enter.charAt(0) >= '1' && enter.charAt(0) <= '9') {
                final int enteredNumber = Integer.parseInt(enter);
                return cellNumberConverter.toCell(enteredNumber);
            }
        }
        return null;
    }
}

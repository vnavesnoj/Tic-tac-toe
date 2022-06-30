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

import vnavesnoj.tictactoe.model.game.Cell;
import vnavesnoj.tictactoe.model.game.GameTable;
import vnavesnoj.tictactoe.model.game.Player;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class UserTurn implements Turn {

    private final UserInputReader userInputReader;

    private final DataPrinter dataPrinter;

    public UserTurn(final UserInputReader userInputReader, final DataPrinter dataPrinter) {
        this.userInputReader = userInputReader;
        this.dataPrinter = dataPrinter;
    }

    @Override
    public void makeMove(final GameTable gameTable, final Player player) {
        while (true) {
            Cell cell = userInputReader.getUserInput();
            if (cell != null) {
                if (gameTable.isEmpty(cell)) {
                    gameTable.setSign(cell, player.getSign());
                    return;
                }
                dataPrinter.printErrorMessage("Can't make a move, because the cell is not free! Try again!");
            }
        }
    }
}

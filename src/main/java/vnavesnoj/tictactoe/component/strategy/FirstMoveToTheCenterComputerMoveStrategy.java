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

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class FirstMoveToTheCenterComputerMoveStrategy implements ComputerMoveStrategy {

    private final Cell centralCell = new Cell(1, 1);

    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Player player) {
        if (gameTable.isEmpty(centralCell)) {
            gameTable.setSign(centralCell, player.getSign());
            return true;
        }
        return false;
    }
}

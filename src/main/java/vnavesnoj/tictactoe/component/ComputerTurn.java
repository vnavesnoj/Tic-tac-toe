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
import vnavesnoj.tictactoe.model.Player;

import java.util.Random;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class ComputerTurn implements Turn {

    @Override
    public void makeMove(final GameTable gameTable, final Player player) {
        final Random random = new Random();
        while (true) {
            final int randomNumber = random.nextInt(9);
            final Cell cell = new Cell(2 - (randomNumber / 3), randomNumber % 3);
            if (gameTable.isEmpty(cell)) {
                gameTable.setSign(cell, player.getSign());
                return;
            }
        }
    }
}

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

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class CellVerifier {

    public boolean allCellsFilled(final GameTable gameTable) {
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (gameTable.isEmpty(new Cell(i, k))) {
                    return false;
                }
            }
        }
        return true;
    }
}

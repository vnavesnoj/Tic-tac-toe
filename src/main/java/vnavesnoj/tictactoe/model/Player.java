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

package vnavesnoj.tictactoe.model;

import vnavesnoj.tictactoe.component.Turn;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public final class Player {

    private final Sign sign;

    private final Turn turn;

    public Player(final Sign sign, final Turn turn) {
        this.sign = sign;
        this.turn = turn;
    }

    public Sign getSign() {
        return sign;
    }

    public Turn getTurn() {
        return turn;
    }

    public void makeMove(final GameTable gameTable) {
        turn.makeMove(gameTable, this);
    }
}
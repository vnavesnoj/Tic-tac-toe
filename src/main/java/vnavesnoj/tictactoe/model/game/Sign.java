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

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public enum Sign {

    X,

    O,

    EMPTY;


    public Sign oppositeSign() {
        if (this == O) {
            return X;
        }
        if (this == X) {
            return O;
        }
        throw new IllegalStateException("Empty value does not have an opposite one");
    }

    @Override
    public String toString() {
        return this == EMPTY ? " " : name();
    }
}

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

import vnavesnoj.tictactoe.model.PlayerType;
import vnavesnoj.tictactoe.model.UserInterface;

import static vnavesnoj.tictactoe.model.PlayerType.COMPUTER;
import static vnavesnoj.tictactoe.model.PlayerType.USER;

/**
 * @author vnavesnoj
 * @link vnavesnoj@gmail.com
 */
public class CommandLineArgumentParser {

    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;

    }

    public GameFeatures parse() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        UserInterface userInterface = null;
        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.println("Unsupported command line argument: '" + arg + "'");
                }
            } else if (UserInterface.CONSOLE.name().equalsIgnoreCase(arg) || UserInterface.GUI.name().equalsIgnoreCase(arg)) {
                if (userInterface == null) {
                    userInterface = UserInterface.valueOf(arg.toUpperCase());
                } else {
                    System.err.println("Unsupported command line argument: '" + arg + "'");
                }
            } else {
                System.err.println("Unsupported command line argument: '" + arg + "'");
            }
        }
        if (userInterface == null) {
            userInterface = UserInterface.CONSOLE;
        }
        if (player1Type == null) {
            return new GameFeatures(USER, COMPUTER, userInterface);
        } else if (player2Type == null) {
            return new GameFeatures(USER, player1Type, userInterface);
        } else {
            return new GameFeatures(player1Type, player2Type, userInterface);
        }
    }

    public static class GameFeatures {

        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private final UserInterface userInterface;

        private GameFeatures(final PlayerType player1Type,
                             final PlayerType player2Type,
                             final UserInterface userInterface) {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
            this.userInterface = userInterface;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }

        public UserInterface getUserInterface() {
            return userInterface;
        }
    }
}

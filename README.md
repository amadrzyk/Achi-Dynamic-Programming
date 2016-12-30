# Achi – Dynamic Programming

## Introduction
```PlayAchi.java``` is a program that plays achi. In achi, two players take turns putting tiles on a square game board of size n × n. The goal is for a player to place n of their tiles in the same row, column, or diagonal of the board, similarly as in the game of tic tac toe.

In achi, however, at most n^2 − 1 tiles can be placed on the board, and if by then no player has won, then the players take turns sliding onto the empty position of the board one of their tiles adjacent to that position. If on their turn, a player does not have any tiles adjacent to the empty position of the board, the game ends in a draw.

<p align="center">
  <br>
  <img src="https://github.com/amadrzyk/Achi-Dynamic-Programming/blob/master/PlayAchiLoop.gif" alt="Achi Gameplay"/>
  <br>
</p>

## Dynamic Programming

In this program, a game tree is used to implement the computer's moves. Every time that the "score" of a board configuration is computed,
the configuration and its score are stored in a dictionary. Then, when the algorithm ```computerPlay``` is exploring the game tree trying to determine the computer’s best move, before it expands a configuration b, it will look it up in the dictionary. If b is in the dictionary then its score is simply extracted from the dictionary instead of exploring the part of the game tree below b. This makes computer move calculation much faster. The ```depth``` parameter, which is the second command-line argument, determines how many levels of the game tree the computer will examine, when trying to calculate the best move.

## Getting Started

PlayAchi.java is the main file. Running ```javac PlayAchi.java``` will compile the code.
Then, running ```java PlayAchi board-size depth``` will run the Achi game. Here, ```board-size``` is the size of the n x n game board, and depth is the maximum number of levels of the game tree that the program will explore. The larger the value of depth is, the better the program will play, but the slower it will be.

## Built With

* Eclipse

## Acknowledgments

* Western's CS2210A Course Staff 

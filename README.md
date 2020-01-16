[![Build Status](https://travis-ci.com/EachOneChew/Dijkstra-s-Algorithm-FEH.svg?branch=master)](https://travis-ci.com/EachOneChew/Dijkstra-s-Algorithm-FEH)

# Welcome

This is exactly what the description says it is. I am working on a means to display the board in a less ugly way. Further into the future I might try to implement a rudimentary scoring system, but programming an AI opponent will probably be beyond my ability for some time.

# How to Run

* download the repo - you only need the 4 .java files (make sure they are in the same folder)
* compile with `javac Main.java` (related classes are automatically compiled) and run with `java Main`
* play Go :D

# How to Play

* choose a board size and begin the game (9 is recommended for beginners)
* a board will appear, with horizontal indices labelled with letters and vertical indices labelled with numbers
    * empty spots are denoted by "0"s
    * black pieces are denoted by "1"s
    * white pieces are denoted by "2"s
    * to play a piece at a location, simply enter the letter-number combination of the indices of that location
* when a piece or a group of pieces are completely surrounded by enemy pieces, ergo, there are no free "0"s adjacent to any member of the group - edges do not count as free - it is "captured" and removed from the board
    * for example, the black piece here is adjacent to only one empty spot, and would be captured if a white piece was placed above it
    
      ```
      0, 0, 0, 0, 0        0, 0, 0, 0, 0
      0, 0, 0, 0, 0        0, 0, 2, 0, 0
      0, 2, 1, 2, 0   ->   0, 2, 0, 2, 0
      0, 0, 2, 0, 0        0, 0, 2, 0, 0
      0, 0, 0, 0, 0        0, 0, 0, 0, 0
      ```
      
    * similarly, the the white group becomes captured when the last free "0" (called "liberty") adjacent to any of it's members is taken by a black piece
    
      ```
      0, 0, 1, 0, 0        0, 0, 1, 0, 0
      0, 1, 2, 1, 0        0, 1, 0, 1, 0
      1, 2, 2, 2, 1   ->   1, 0, 0, 0, 1
      0, 2, 1, 1, 0        1, 0, 1, 1, 0
      0, 1, 0, 0, 0        0, 1, 0, 0, 0
      ```
      
* the goal of the game is to end with your pieces convering as much area of the board as possible
* several rules to pay attention to:
    * you cannot play a piece at a location that will result in it or its group being immediately captured on the same turn
    * you cannot play a piece at a location that will return the board to the same state as your last turn (in practice, you cannot "fight over" the same spot as your opponent until you wait at least one turn)

A more detailed version of the rules of Go can be found [here](https://senseis.xmp.net/?InternationalRules). My particular version of Go implements the "no suicide" rule. As scoring is manual as of the moment, which scoring system to use is up to the players.

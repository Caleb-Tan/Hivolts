# The Hivolts Project

## Introduction:

The Hivolts project was made by Brion, Caleb, and Julius, and the goal of this project was to recreate the classic Hivolts game, in which a player avoids enemy mhos until all the mhos have run into electric fences.

## Specifications

The game involves several elements: </br>
**Board** - The board is initialized as a 720x720 square that is painted snow white. There is an imaginary grid system consisting of square cells that are each 60x60. This means that the grid 12 cells by 12 cells. </br>
**Fences** - There are outer perimeter fences that take up the outer perimeter of the board and prevent the player from exiting the board. There are also inner fences that are generated randomly when the game starts.  When the player(you) collide into the fences your character gets killed from the electric fence.  </br>
**Player** - The player’s goal is to avoid the mhos and fences at all costs, but move in the way that the mhos chasing it end up hitting fences. The player can move up, down, left, right, diagonal right up, diagonal left up, diagonal right down, diagonal left down, or remain in their current position by pressing the “s” key. </br>
**Mhos** The Mhos goal in this game it to kill the player(you).  They are your enemy. They move in the way that the player moves. This means that ALL the mhos will move to where your new location and to trap and kill you.  </br>
**Scoreboard, and Game Over Screen** - The scoreboard is below the playing board, and shows the creators of the game, as well as how many mhos are left alive. If the player ends up hitting a fence OR a mho catches the player, the game over screen is painted, which gives the player the ability to restart the game. </br>

## Errors
There were several errors that we encountered. The first major error that happened was that the images were not showing up on some people’s computers. Even though, the paths were correct and relative to the file that it was being referenced to, an IO File not found exception kept getting thrown when trying to run the program. In the end, we made the ImagePath class that stored the relative and absolute path sets as string variables, so that we could easily switch between the two paths. </br></br>
Another main error we encountered was that the mhos would sometimes be able to occupy the same spot as a fence, and two mhos could be able to occupy the same cell. When debugging this issue, we realized that the issue was that the mho would be 1 or 2 digits off of where it is supposed to be. As of right now, we have not been able to determine why it would be 1 or 2 digits off, so Brion was forced to  implement a quick fix that would round the mho coordinates to the nearest cell's digits. As crude as this fix was, it served its purpose.  

## Specifications
The project has multiple methods, classes, and objects. Each part is listed below: </br></br>
**Main.java** - The class contains the main method which initializes the game panel, the jframe to contain the panel, and it packs and adds the panel to the jframe.</br> 
**Game.java** - Game is the jpanel class that displays the game, and it also contains all the game logic. Methods:
- Game() constructor - This constructor is for the game method and is run upon intialization, it adds the key listener to the jpanel, and calls the startGame() method.
- startGame() - It starts the game by clearing the fences and mhos arraylists of all objects, as well as calling the generateElements() method. startGame() is called whenever the game is reset.
- generateElements() - This method generates every single element on the board. It first loops through the outer perimeter fences and adds each fence object to the fences array. Then, it shuffles the arraylist that contains each possible cell and its cell coordinates. Finally, the first 12 coord sets are given to mhos, the next 20 are given to the inner fences, and last one is given to the starting position of the player
- isEmpty() - This method can be called whenever needed. It returns one of three numbers to represent if the coords given in the parameter are already taken by a mho, fence, or nothing. 
- moveMhos() - Used to call the moveTowards() method in the mho class for each mho.
- resetCoord() - 
- gameOver() - In this method, there is a call to isEmpty to check if the player's coordinates are conflicting with a mho or fence's coordinates. If so, then 






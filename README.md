# Vampire Slayer Text Game

This is the completed version of "dungeon game" from University of Helsinki's Java programming course part II. Project description is listed at the bottom of [this page (Exercise 33)](https://materiaalit.github.io/2013-oo-programming/part2/week-10/)


## How to Run
**_Note_** _This is guide is targeted towards those with Bash terminal (e.g. GitBash). Procedure may differe based on your IDE/Text Editor/Terminal._

In order to play this game, make sure that you can run Java program. Then clone this repo to your local machine. Once you are in this repository, which is where this README is located, run following command in order:


```javac Main.java ```

```java Main```


Alternatively, there is .txt file that you can run using following command:

```bash run.txt```

to automate above two commands. This is suggested method since it will clean out garbage files before running the program.


## How to Play
You will be guided to following screen upon successful run:

![beginning][intro]


### Initial Run
___
You will then be asked for desired size of the field, numbers of enemies you want in your map, number of moves you can make, and difficulty settings. When you choose easy mode, your enemies will not move; in hard mode, all of your enemies will randomly move as much as you did. Following is the example of what would map look like:
![initial-run][first_run]

In this scenario, dimension of map is 10x10 with 10 moves left. Your character is represtended as _@_ and your enemies are represented with _v_.

First map that have numbers represents you and all of enemies positions in (height, width).
Second map shows the physical representation of what map looks like. You will need to use this as a reference to defeat all of your enemies.

**_Note_** _Since (0,0) is located on upper left corner, the height will increase as you go downward and the width will increase as you go to the right._


### N-th Run
___
Once it asks you for your moveset, you can enter any characters you want for many times to make movement. However, only letter 'w', 'a', 's', and 'd' will move your character. Following is the example of what happens when you move:

![n-th_run][n-th]

In this example, it recognized letter 'd' and 's'. Since there is two 'd' and one 's', you moved to position to the right and one position to the left. As a result, one of your enemies has been eliminated. You may also noticed that the number of vampires in both maps and timer has been reduced by one. In hard mode, all of the vampires in map above will move to different position after your turn.

### Last Run
___
Game will end in one of two conditions:

1. If you managed to kill all vampires in field, you will win.
2. If you ran out of time, you will lose.


## Additional Note
This project is one of the bigger project based off of University of Helsinki's Java Course. You may check the progress of making this game on that repository once it becomes public. 



[intro]: https://github.com/ruikawahara/Vampire-Slayer-Text-Game/blob/master/img/intro.PNG "Start Screen"
[first_run]: https://github.com/ruikawahara/Vampire-Slayer-Text-Game/blob/master/img/first_run.PNG
[n-th]: https://github.com/ruikawahara/Vampire-Slayer-Text-Game/blob/master/img/n-th.PNG

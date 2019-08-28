This is a Minesweeper game, just like the one on Windows but with not all features.
The purpose of this project was only for me to practice Test Driven Development.
The whole business logic (minesweeper project) has been written like that. And after this project was finished I wrote
the minesweeper-swing-gui project on it (and it worked right away, yay!)

Build and run:

```
cd minesweeper
mvn install

cd..
cd minesweeper-swing-gui
mvn install
cd target
java -jar minesweeper-gui-1.0-SNAPSHOT-jar-with-dependencies.jar
```

In the Menu you can set the difficulty and check your best scores.
Enjoy! :)

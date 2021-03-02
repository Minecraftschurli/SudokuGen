# SEW1 GK / Threads
## Sudoku-Spiel
Ausgangscode und zusätzliche Abgabe auf: https://classroom.github.com/a/C_Fb4-LI

Erstelle in Java eine Applikation, die Sudokus (siehe [Wikipedia-Eintrag](https://de.wikipedia.org/wiki/Sudoku)) erzeugt, anzeigt und es ermöglicht diese zu 
lösen. Dabei soll das generieren neuer Sudoku-Spiele im Hintergrund durch einen eigenen Thread erfolgen. Im 
github-Classroom gibt es dazu schon eine Klasse namens SudokuGenerator, die Methoden zum Erzeugen und Überprüfen von 
Sudokus zur Verfügung stellen.

Ein generiertes Sudoko-Spiel besteht immer aus zwei Spielfeldern, der eigentlichen Lösung und der Ausgangssituation und 
soll in einer eigenen Klasse namens `SudokuSpiel` gespeichert werden. Eine weitere Klasse übernimmt die Verwaltung der 
vorgenerierten `SudokuSpiel`-Objekte in einer eigenen Collection. Der Sudoku-Generator wird als eigener Thread realisiert, 
der so lange neue Sudokus generiert, bis zumindest 10 Sudoku-Spiele gespeichert sind. Danach unterbricht er seine Arbeit 
und setzt sie erst wieder fort, wenn wieder Platz für neue Sudokus ist. Auch die Ausgabe der Sudokus soll in einem 
eigenen Thread realisiert werden. Die Verwaltung der erzeugten Sudokus und die Steuerung der Threads soll sinvoller 
Weise in einer eigenen Klasse realisiert werden.

**Achtung: Die GK überwiegend/vollständig-Abgabe muss eigenständig hergezeigt werden, und darf nicht durch andere 
Abgaben (EK) überschrieben werden.**

Achte auch darauf, dass du nicht die Verantwortlichkeiten vermischt. Der Generator-Thread ist für das Erzeugen der 
Sudokus zuständig, die Verwaltungsklasse nur für das Speichern und die Verwaltung…

## GK überwiegend
Erstelle eine Thread-Klasse, die mit Hilfe der bereit gestellten Klasse Sudokus erzeugt und kopiere das vollständige 
Sudoku als Lösung in dein SudokuSpiel-Objekt. Anschließend werden an zufällig gewählten Postionen Ziffern entfernt, bis 
nur noch ein Drittel der Ziffern vorhanden ist (Achtung: dadurch kann es vorkommen dass eine Ausgangssituation 
geschaffen wird, die nicht eindeutig zur Lösung führt, dies muss aber für diese Aufgabenstellung nicht extra 
berücksichtig werden). Speichere die Ausgangssituation dann ebenfalls in das SudokuSpiel-Objekt. Das fertige 
SudokuSpiel-Objekt soll dann in der Sudoku-Verwaltungsklasse gespeichert werden.
Erstelle eine zweite Thread-Klasse, die die erzeugten Sudoku-Spiel-Felder aus der Verwaltungsklasse holt und auf der 
Konsole ausgibt.
Die Sudoku-Verwaltungs-Klasse speichert die SudokuSpiel-Objekte in einer Collection und bietet Methoden, um Sudokus zu 
speichern und ein gespeichertes Sudoku aus der Collection zu holen (und dabei aus der Collection zu löschen). Wenn 
allerdings 20 Sudokus in der Collection gespeichert sind, sollen alle erzeugenden Threads blockieren, bis wieder Platz 
ist, um neue Sudokus speichern zu können. Analog sollen lesende Threads blockieren, wenn keine Sudokus in der Collection 
vorhanden sind und nur dann weitermachen, wenn wieder zumindest ein Sudoku vorhanden ist.

Erstelle zwei Varianten für die Synchronisation der Threads (git branches wären schön 😉):

1. eine Variante mit den klassischen Java-Möglichkeiten `synchronized`, `wait`, `notify`
2. eine Variante basierend auf den Möglichkeiten von `java.util.concurrent.locks`.
Teste dein Program mit zumindest 2 Instanzen des erzeugenden Threads und 2 Instanzen des lesenden Threads
## GK vollständig
Um die Erzeugung bzw. das Lesen zu optimieren, ändere folgende Bedingungen:
- Die Lese-Threads sollen nach wie vor blockieren, sobald keine Elemente mehr in der Collection sind, aber erst wieder 
  weitermachen, wenn mind. 5 Elemente in der Collection sind.
- Die Sudoku-Generierer sollen nach wie vor blockeiren, sobald 20 Elemente in der Collection sind, aber erst wieder 
  weitermachen, wenn max. 15 Elemente in der Collection sind.
## EK
Erweitere die Aufgabe noch um folgende Punkte:

- Auch wenn dies nicht ein klassisches Beispiell für deren Verwendung ist, sollen die Threads nun nicht mehr selbst 
  erzeugt werden, sondern über einen sgn. Thread-Pool abgearbeitet werden (vgl. z.B. 
  https://www.geeksforgeeks.org/thread-pools-java/ oder 
  http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_14_004.htm). Erkläre außerdem in den Kommentaren und beim 
  Abgabegespräch, was Thread-Pools sind, warum sie eingesetzt werden und wie der Source-Code dafür aufgebaut ist.
- Anstelle der Lese-Threads soll nun eine einfache Java-GUI das Anzeigen und Lösen der Sudokus ermöglichen.
## Abgabe:
- Auf github (mehrere Commits sind in regellmäßigen Abständen vorhanden)
- Als jar inkl. source-codes hier im Kurs.
- In einem eigenen Abgabegespräch wird die Lösung demonstriert, wobei die verwendeten Thread-Mechanismen erklärt werden 
  können.
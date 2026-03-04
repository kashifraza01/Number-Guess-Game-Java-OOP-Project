# 🎯 Number Guessing Game (Java Swing)

A simple and interactive **Number Guessing Game** built using **Java Swing GUI**.
The player must guess a randomly generated number between **1 and 100** within **10 attempts**.

---

## 📌 Features

* 🎯 Random number generation (1–100)
* 🔢 Maximum 10 attempts per game
* 📉 Hints: Too High / Too Low
* 🏆 High Score tracking (stored in file)
* 🔄 Reset game option
* 🖼️ Custom background image support
* 🎨 Styled UI with colors and emojis
* 💾 Persistent high score using `highscore.txt`

---

## 🛠️ Technologies Used

* Java
* Java Swing (GUI)
* AWT
* File Handling (BufferedReader & BufferedWriter)
* Object-Oriented Programming (OOP)

---

## 🚀 How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/kashifraza01/number-guess-game.git
   ```

2. Open the project in any Java IDE (IntelliJ, Eclipse, NetBeans, etc.)

3. Make sure:

   * `kashif.png` is inside the same package directory.
   * `highscore.txt` exists (or it will be created automatically).

4. Run:

   ```bash
   NumberGuessGame.java
   ```

---

## 🎮 How to Play

1. Enter a number between **1 and 100**.
2. Click **Guess** (or press Enter).
3. You will receive hints:

   * 📉 Too Low
   * 📈 Too High
4. You have **10 attempts**.
5. If you guess correctly:

   * 🎉 You win!
   * 🏆 High score updates if your attempts are fewer.
6. Click **Reset Game** to play again.

---

## 📂 Project Structure

```
numberguessgame/
│── NumberGuessGame.java
│── kashif.png
│── highscore.txt
```

---

## 🏆 High Score System

* The lowest number of attempts is saved as the high score.
* Stored in `highscore.txt`
* Automatically loads when the game starts.

---

## 💡 Future Improvements

* Add difficulty levels (Easy / Medium / Hard)
* Add sound effects
* Add timer mode
* Improve UI animations
* Convert to multiplayer mode

---

## 👨‍💻 Author

Developed by **Kashif Raza**

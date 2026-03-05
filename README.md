# 🎯 Number Guessing Game (Java Swing)

A fun and interactive **Number Guessing Game** built using **Java Swing** and Object-Oriented Programming principles.  
Try to guess the number randomly chosen between **1 and 100** within a limited number of attempts!


---

## 🕹️ Game Features

✔ Random number between 1 and 100  
✔ Maximum of 10 attempts  
✔ Hints for each guess (Too High / Too Low)  
✔ Persistent high score tracking  
✔ Clean and colorful Java Swing GUI  
✔ File handling (`highscore.txt`)  

---

## 🛠️ Technologies Used

- ✅ Java
- ✅ Java Swing & AWT
- ✅ Object-Oriented Programming
- ✅ File I/O (BufferedReader & BufferedWriter)

---

## 🚀 How to Run (Step-by-Step)

1. **Clone the repository**

```bash
git clone https://github.com/kashifraza01/Number-Guess-Game-Java-OOP-Project.git
````

2. **Open the project** in a Java IDE
   (IntelliJ IDEA, Eclipse, NetBeans, etc.)

3. **Verify resource files**
   Make sure you have:

   * `kashif.png` (background image)
   * `highscore.txt` (created automatically on first run)

4. **Run the application**

```bash
java NumberGuessGame
```

---

## 🎮 How to Play

1. Enter a number between **1 and 100**.
2. Click **Guess** (or press Enter).
3. You will see:

   * 📉 Too Low!
   * 📈 Too High!
   * 🎉 Correct! if guessed right
4. You have **10 attempts** total.
5. If you guess correctly with fewer attempts than the current high score, the high score updates automatically!

---

## 🧠 How the High Score Works

✔ Successfully guessing with fewer attempts updates `highscore.txt`
✔ High score is shown every time the app launches
✔ If no high score exists yet, it displays as `--`

---

## 📁 Project Structure

```
Number-Guess-Game-Java-OOP-Project/
│── NumberGuessGame.java
│── kashif.png
│── highscore.txt
```

---
##  Screenshots
[ss1.png]
[ss2.png]
---

## 💡 Future Improvements

Here are some features you could add:

* 🔹 Difficulty levels (Easy / Medium / Hard)
* 🔹 Sound effects and animations
* 🔹 Timer to track speed of guesses
* 🔹 Better graphics & UX
* 🔹 Web or mobile version

---

## 👨‍💻 Author

**Kashif Raza**

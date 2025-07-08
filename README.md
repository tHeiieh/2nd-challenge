# Quantum Bookstore 🛒📚

A Java console application simulating an online bookstore for paper books, eBooks, and demo books.

## 💡 Challenge Overview

This project is built to fulfill the requirements of **Fawry N² Dev Slope #10 Challenge**.

### Features

- Add books to inventory
- Support multiple book types:
  - `PaperBook`: Physical book with limited stock
  - `EBook`: Digital file, no stock limit
  - `ShowcaseBook`: Display-only, not for sale
- Remove outdated books
- Buy books using ISBN
- Auto-dispatch based on book type:
  - Paper books → shipped to address
  - EBooks → sent to email

---

## 📁 Project Structure

All logic is in one file for simplicity:

- `QuantumBookstoreFullTest.java`: Contains all classes (Book, PaperBook, EBook, ShowcaseBook, QuantumBookstore) and the test runner.

---

## ▶️ How to Run

### Requirements

- Java 8 or higher

### Steps

1. Clone the repo or download the `.java` file.

2. Compile the code:


javac QuantumBookstoreFullTest.java

3. Run the program:

run:

java QuantumBookstoreFullTest

🧪 Sample Output

PS C:\Users\Taha Eltorkey\Downloads\2nd challenge> javac QuantumBookstoreFullTest.java

PS C:\Users\Taha Eltorkey\Downloads\2nd challenge> java QuantumBookstoreFullTest

Quantum book store: Total paid = 241.0

Quantum book store: Paper book shipped to 123 Cairo St 

Quantum book store: Total paid = 75.0

Quantum book store: EBook sent to blackbeauty@gmail.com

Quantum book store: Not enough quantity

Quantum book store: Removed outdated book: Secret Book

PS C:\Users\Taha Eltorkey\Downloads\2nd challenge> 

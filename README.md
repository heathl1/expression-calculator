# 🧮 Expression Calculator

**A command-line arithmetic expression evaluator built with Java.**

A Java calculator designed to evaluate mathematical expressions using stack-based algorithms. Supports operator precedence, nested parentheses, variable assignments, and arbitrary-precision integer arithmetic.

**Tech Stack:** Java · BigInteger · ArrayDeque · HashMap

---

## ✨ Features

- **Arithmetic Operations:** Addition, subtraction, multiplication, and integer division.
- **Operator Precedence:** Evaluates multiplication and division before addition and subtraction.
- **Nested Parentheses:** Processes complex expressions containing multiple levels of parentheses.
- **Variable Storage:** Assigns and retrieves values using a HashMap.
- **Large Integer Support:** Uses Java's `BigInteger` for calculations beyond standard integer limits.
- **Interactive CLI:** Accepts expressions and commands through a console interface.

## 💻 Example Usage

```text
> 10 + 5 * 2
20

> (10 + 5) * 2
30

> 3 + ((4 + 3) * 2)
17

> a = 100
> b = 25
> a + b * 2
150

> /exit
Bye!
```

## ⚙️ How It Works

The expression evaluator uses two stacks:

1. **Values Stack:** Stores numeric operands as `BigInteger` objects.
2. **Operators Stack:** Stores arithmetic operators and parentheses.

When evaluating an expression, the algorithm compares operator precedence and processes operations in the correct order. Parentheses are handled by evaluating the enclosed operations first.

Variables are stored in a `HashMap<String, BigInteger>` for retrieval during expression evaluation.

## 🧠 Concepts Demonstrated

- Stack-based expression evaluation
- Data structures and algorithms
- Operator precedence and parsing
- Java collections (`Deque`, `HashMap`)
- Arbitrary-precision arithmetic
- Input validation and exception handling

## 🚀 Getting Started

**Requirements:** Java JDK

Clone the repository:

```bash
git clone https://github.com/YOUR-USERNAME/expression-calculator.git
cd expression-calculator
```

Compile and run the program from the project root, assuming the source file is located at `src/calculator/Main.java`:

```bash
javac -d out src/calculator/Main.java
java -cp out calculator.Main
```

## 🔮 Future Improvements

- [ ] Improve handling of negative numbers and unary operators
- [ ] Expand expression validation and error messages
- [ ] Add automated unit tests
- [ ] Support additional mathematical operations
- [ ] Explore a graphical user interface

---

**Built with Java | Focused on algorithms, problem-solving, and expression evaluation.**
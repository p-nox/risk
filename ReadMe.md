
# Risk Board Game

A desktop implementation of the classic Risk strategy board game, developed in Java using JavaFX. The project focuses on delivering a faithful yet extensible version of the game with modular architecture, clean design, and professional development practices.

---

##  Overview

This project is a full-featured simulation of the Risk board game, including map-based strategy, resource management, and turn-based mechanics. It provides both the core gameplay logic and a JavaFX-based graphical user interface for interactive play.

Designed as an educational and professional-grade software project, it demonstrates strong focus on:

* Object-oriented design principles
* Clear separation of concerns between UI and game logic
* Expandable architecture for future game variants

---

##  Features

* **Game Mechanics**

  * Territory control, army placement, and reinforcement
  * Dice-based battle system
  * Turn-based player management
* **User Interface**

  * Interactive JavaFX GUI
  * FXML-based layout for maintainability
  * Dynamic game board updates

---

##  Tech Stack

* **Language:** Java 11
* **UI Framework:** JavaFX (Controls, FXML)
* **Build Tool:** Apache Maven
* **Development Environment:** IntelliJ IDEA
* **Version Control:** Git

---

##  Project Structure

```
risk_boardgame/
 ├─ src/main/java/        # Core game logic and controllers
 │   ├─ model/            # Entities and game rules
 │   ├─ controller/       # UI and game flow controllers
 │   └─ util/             # Helper classes
 ├─ src/main/resources/   # FXML files, assets, configuration
 ├─ pom.xml               
 └─ Risk.iml              
```

---

##  Getting Started

### Prerequisites

* Java 11+
* Maven 3.x
* IntelliJ IDEA (recommended)

### Installation

Clone the repository:

```bash
git clone [https://github.com/yourusername/risk_boardgame.git](https://github.com/p-nox/risk.git)
cd risk_boardgame
```

Build with Maven:

```bash
mvn clean install
```

Run the application:

```bash
mvn javafx:run
```

---

##  Future Enhancements

* **Multiplayer:** Add online mode with client-server communication
* **AI:** Implement computer-controlled opponents with difficulty levels
* **UI Enhancements:** Animations, sound effects, and advanced map design
* **Persistence:** Save and load game states
* **Testing:** Expand JUnit test coverage for game mechanics

---


# Simple Todo Application

This is a simple Java-based Todo application designed to help users manage their tasks efficiently. The project demonstrates basic Java programming concepts (OOP and exceptions handling, compiling, packaging as JAR, classpath, JVM), Maven build tool's life cycle and is suitable for learning project structure of Java.

## Features

- Add new tasks to the todo list.
- Mark tasks as completed.
- Delete tasks from the list.
- View all tasks with their status.
- Basic commands' source code are in src/main/.../subcommand

## Prerequisites

- Java Development Kit (JDK) 8 or higher.
- A Java IDE or text editor of your choice.
- Maven build tool.

## Getting Started

1. Clone the repository:
    ```bash
    git clone https://github.com/trungdung1711/simple-todo.git
    ```
2. Navigate to the project directory:
    ```bash
    cd simple-todo
    ```
3. Build the project using Maven:
    ```bash
    mvn clean package
    ```
4. Run the application:
    ```bash
    java -jar ./target/todo-application-1.0-SNAPSHOT.jar help
    ```

## Project Structure

```
simple-todo/
├── src/            # Source code files
├── target/         # Compiled class files and packaged JAR
├── fdb.txt
├── pom.xml         # Maven configuration file
├── README.md       # Project documentation
└── .gitignore      # Git ignore file
```

## Contributing

Contributions are welcome! Feel free to fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For any questions or feedback, please contact [trungdunglebui17112004@gmail.com].
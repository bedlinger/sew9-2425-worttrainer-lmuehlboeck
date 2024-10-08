# Word Trainer

This is a simple word trainer application written in Java and Kotlin, and built with Gradle. The application is designed to help users, particularly students of primary school, learn new words. It presents a word to the user and checks if the user's input matches the presented word.

## Features

- The application uses a `WordTrainer` class that holds a list of `WordEntry` objects. Each `WordEntry` represents a word and its associated URL.
- The `WordTrainer` class has methods to select a random entry or a specific entry by index.
- The `WordTrainer` class keeps track of the number of asked entries and the number of right answers.
- The `WordTrainer` class has a method to check if the user's input matches the selected word.
- The `Controller` class manages the interaction between the `WordTrainer` and the `View`.

## Usage

To use the application, create a `WordTrainer` object and add `WordEntry` objects to it. Then, you can select an entry and check the user's input against the selected word.

```java
WordEntry entry1 = new WordEntry("test1", "http://example.com");
WordEntry entry2 = new WordEntry("test2", "http://example.com");
List<WordEntry> entries = new ArrayList<>();
entries.add(entry1);
entries.add(entry2);
WordTrainer trainer = new WordTrainer(entries, 0, 1, 1);
Controller controller = new Controller(new WordTrainerJsonPersister());
```

## Testing

The application includes a test suite in the `ModelTest.java` and `ControllerTest.java` files. The tests cover the main functionalities of the `WordTrainer` and `Controller` classes.

## Continuous Integration

The project uses GitHub Actions for continuous integration. The workflow is defined in `.github/workflows/gradle.yml`. It runs the tests on every push and pull request to the `main` branch. It also generates and submits a dependency graph, enabling Dependabot Alerts for all project dependencies.

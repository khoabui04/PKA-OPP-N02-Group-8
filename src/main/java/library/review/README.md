# Review Project

This project implements a simple container class called `Sequence` that manages an array of objects. It provides functionality to add objects and retrieve a `Selector` interface implementation through an inner class called `SSelector`. The `Selector` interface defines methods for navigating through the elements in the `Sequence`.

## Project Structure

- **Sequence.java**: Contains the `Sequence` class, which manages an array of objects and provides methods to add objects and retrieve a selector.
- **Selector.java**: Defines the `Selector` interface with methods `end()`, `current()`, and `next()`.
- **TestSequence.java**: Contains unit tests to verify the functionality of the `Sequence` class and its `Selector` implementation.
- **README.md**: This file provides an overview of the project and instructions for usage.

## Instructions

### Compilation

To compile the project, navigate to the `Review` directory and run the following command:

```
javac *.java
```

### Running Tests

After compiling, you can run the tests in `TestSequence.java` using the following command:

```
java TestSequence
```

### Usage

1. Create an instance of `Sequence` by specifying the size of the array.
2. Use the `add(Object x)` method to add elements to the sequence.
3. Retrieve a `Selector` instance using the `getSelector()` method.
4. Use the `Selector` methods to navigate through the elements in the sequence.

## Purpose

The purpose of this project is to demonstrate the use of inner classes and interfaces in Java, as well as to provide a simple implementation of a container class with a selector pattern.
# Individual Project- CharlesFathima. B


The Credit Card App, created using Java, uses methods based on various design patterns to figure out what type of credit card is given based on the information provided. It's a flexible app that can handle different types of files like CSV, JSON, and XML for storing records. It carefully reads these files, makes sure the credit card numbers are real, and then creates the instances for the right kind of credit card.

Inside these files, there's all sorts of credit card info, like the cardholder's name, credit card number, and when it expires.

This app uses three special techniques: one for going through data efficiently, one for picking the best way to do things, and one for making new credit card instances for each correct credit card types easily.

All these tricks together make the app very flexible and strong, so it's really good at handling credit card information in an intelligent manner.

Design Patterns Used:
1. Iterator Pattern
2. Strategy Pattern
3. Factory Pattern

Why Iterator Pattern:
This pattern is utilized for sequentially accessing elements within a collection object.
It is particularly useful in this context because each file type comprises multiple records, and the pattern facilitates processing each record individually.

Why Strategy Pattern:
Strategy design patterns allow for dynamic behavioral changes in the application based on the selected strategy.
Given the diversity of file types, specific objects for each file type are created, with the methods employed being contingent on the type of the input file.
The strategy design pattern was employed to accommodate different file formats, leading to the creation of three interfaces: CsvFileparser, Json File Parser, and Xmlfileparser. The behavior of the file parser changes depending on the input.

Factory Pattern:
This pattern aids in handling multiple records in each file type by processing each record individually.
Due to the variety of card types, the cardFactory interface is implemented to generate a new object for each card type.


## Steps to run:

- To run the application, follow these steps:

- Clone the repository to your local machine.
- Open the project in Visual Studio Code(or IntelliJ-my suggestion).
- Place the input files in the /ProjectFiles/src/inputs directory.
- Build the project and execute the Client.java file(while executing in InelliJ, give the input and output files using edit configuration for the application".
- During execution, enter the names of the input and output files.
- The relevant output file will be generated in the /ProjectFiles/src/outputs folder.

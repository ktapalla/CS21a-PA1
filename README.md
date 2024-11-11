# README - COSI 21a Programming Assignment 1 

The code provided in this repository contains the solution to PA1 for COSI 21a - Data Structures and the Fundamentals of Computing. The assignment consists of writing/creating a simple text editor that allows the user to create, read, and edit text files. Since this assignment was completed for a Data Structures class, it's possible there were some limitations set in the instructions as to what students were and weren't allowed to use. Furthermore, skeleton files were provided to students, and files, if any, we were told not to edit have not had any code or comments changed from the original files provided for the assignment (a comment is usually included at the top of the file to indicate so). 


## Installation and Execution 

Get the files from GitHub and open your desired application to compile and run Java code. My preferred one is Eclipse. Import the files, and run the test files under the names ``` ExampleEditorTests.java ```, which was provided to the students, and ``` StudentEditorTests.java ```, which the students were left to write on their own to practice testing their code. Normally, I prefer running the program through my computer's terminal, but the JUnit tests aren't running through the Terminal, which is why I recommend running it through an application instead. Doing so allows the user to run the test files included to see that it is passing the test cases. For more extensive testing, the user may also edit these test files as they wish. 

However, if the user would like to run the program itself and not just the test files provided, they are also able to do so. To compile the files with the necessary code, they can navigate into the src folder of the project through their terminal/console and run the following line: 

``` bash
javac main/*.java
```

After compiling, start the program with the following line: 

``` bash
java main/EditorMain
```

Once the text editor appears, the user will be able to type into the editor/GUI and save the file. 

## Using the Text Editor GUI

Some functionality of the text editor will vary on the user's OS (Windows vs. MacOS). The following briefly describes the functionality available to users and the different key strokes to use between the two operating systems (if applicable): 

* Insert characters and new lines by typing the desired characters through the keyboard 
* Delete - removing the character after the cursor 
    * Windows: 'delete' 
    * Mac: 'Ctrl + D' 
* Backspace - removing the character before the cursor 
    * Windows: 'backspace' 
    * Mac: 'delete' 
* Move the cursor left and right using the left and right arrow keys 
* Jump to the beginning of the text using 'Ctrl + H'
* Jump to the end of the text using 'Ctrl + E'
* Clear the contents of the editor using 'Ctrl + K' 
* Save the contents of the editor as a new file using 'Ctrl + S'
    * After hitting 'Ctrl + S' on the keyboard, the cursor will automatically jump to the text area below the text displayed/being saved. Here, the user can type the name that they wish to save the file as. After typing in their desired filename, the user should then press enter to complete the saving process. 
    * If the user doesn't provide a full system file path, the file will be saved in the same folder as the project. 
    * If the user closes the window without saving, the changes will be lost. 

## Problem Description/Implementation 

The text editor has been implemented with a modified doubly linked list, by traversing, inserting, and deleting from the lists. The following classes were provided to students by the instructor and have not been edited: 

1. Node.java (main package) - This file contains the implementation of the doubly linked list node. Like in lecture, it has 2 fields, "next" and "prev". Instead of holding integer data, this class holds a character. 
2. EditorDisplay.java (main package) - This file contains the implementation of a simple graphical user interface (GUI) to view the ``` Editor ``` class working. 
3. EditorMain.java (main package) - This file starts the GUI version of the ``` Editor ```. There were originally 3 lines of code commented out. One starts the ``` Editor ``` with no input file, the second starts the ``` Editor ``` with a file containing a single line of text, and the third starts the ``` Editor ``` with multiple lines of text. My upload of this assignment has changed nothing aside from uncommenting the first line of code, so that the user will be able to use a blank editor to create a text file.  
4. ExampleEditorTests.java (test package) - This file contains basic tests of various capabilities of the text editor. They use and expand on the examples provided in the assignment instructions. 

The following classes were implemented by the student in order to satisfy the assignment requirements: 

1. Editor.java (main package) - This file contains the implementation of the text editor. It contains a variety of methods used for reading, inserting into, deleting from, and tracking the cursor in a text file. 
2. StudentEditorTests.java (test package) - This is the file where the student has written their own JUnit tests of the various methods of the ``` Editor ``` class. The provided, unchanged tests in ``` ExampleEditorTests.java ``` do not test edge cases or more advanced functionality. 

### Editor.java Class 

The ``` Editor ``` class contains all of the implementation of the text editor. The skeleton code provided contains five instance fields and a variety of functions that were to be implemented for assignment completion. Below, the implementation of the various capabilities of the editor are explained one at a time: 

**1. The Cursor** 

The text editor, like all basic text editors, maintains an internal cursor in the data structure, which is the vertical bar that appears when a user is typing. In general, if a text editor contains a string of **n**, then the cursor can be placed in **n + 1** possible locations. In the text editor implemented, if the cursor is at position **k**, then that means there are **k** characters in the editor's string present before the cursor. The following are the fields and methods required: 

* public int numChars - this field maintains a count of the number of characters currently stored in the editor 
* public int curPos - this field tracks the index of the cursor in the editor. It takes on values in the interval [0, numChars]. 
* public int size() - this method returns the number of characters stored in the editor 
* public int getCursorPosition() - this method returns the index of the cursor in the editor 

**2. Cursor Movement & Default Constructor**

The cursor is able to more through the text editor by moving one instance to the right, one instance to the left, moving to before the first character, and moving to the last character. The following fields and methods in ``` Editor ``` allow for this: 

* public Node head - this field holds a reference to the head of the doubly linked list that is used in the underlying implementation of the text editor 
* public Node tail - this field holds a reference to the tail of the doubly linked list that is used in the underlying implementation of the text editor 
* public Node cur - this field will hold a reference to the node that is after the cursor index, or ``` null ``` when ``` curPos = numChars ``` 
* public void moveRight() - this method moves the cursor one character to the right, updating both ``` cur ``` and ``` curPos ``` as it does so. If the user tries to move right when ``` curPos = numChars ```, an exception is not thrown and the movement is instead ignored. 
* public void moveLeft() - this method moves the cursor one character to the left, updating both ``` cur ``` and ``` curPos ``` as it does so. If the user tries to move left when ``` curPos = 0 ```, an exception is not thrown and the movement is instead ignored. 
* public void moveToHead() - this method moves the cursor to the first character in the editor 
* public void moveToHead() - this method moves the cursor to the end of the string in the text editor 
* public Editor() - this method constructs an empty ``` Editor ``` and initializes all necessary fields to signify there are no characters in it 

**3. Insertion** 

One reason why a linked list should be used over an array in implementation of this text editor is because it allows the user to insert characters at any cursor index in the editor's string. If one were to use an array, shifting would occur every time a new character is inserted. For this text editor, insertions will always be done before the Node referenced by ``` cur ```. Every insertion would require an update of ``` cur ```, ``` curPos ```, and ``` numChars ```. It may also result in an update to ``` head ``` and/or ``` tail ```, depending on where the insertion is occurring. The following method is used to implement thi: 

* public void insert(char c) - this inserts the provided character before ``` cur ``` into the doubly linked list. This includes insertion after the last ``` Node ``` in the list. 

**4. Delete and Backspace** 

Anther reason a linked list is better than an array for this assignment is that it allows for easier/faster deletion of characters. For this text editor, there will be two different ways to remove characters from the string: through delete and backspace. Using ``` delete ``` will remove the Node referenced by ``` cur ``` from the doubly linked list, while using ``` backspace ``` will remove the Node **before** the node referenced by ``` cur ``` from the doubly linked list. Deletions in this text editor will always require an update of ``` cur ```, ``` curPos ```, and ``` numChars ```. It may also result in an update to ``` head ``` and/or ``` tail ```, depending on where the deletion occurs. The following methods are used to implement the two different possible kinds of character removal: 

* public void delete() - this method removes the Node referenced by ``` cur ```. If the user calls ``` delete() ``` when the cursor has no following characters, an exception is not thrown and the command is instead ignored. 
* public void backspace() - this method removes the Node before the Node referenced by ``` cur ```. If the user calls ``` backspace() ``` when the cursor has no preceding characters, an exception is not thrown and the command is instead ignored. 

**5. Second Constructor & Remaining Methods** 

The text editor has two modes: the first is where the editor has no characters in it, and the second is where the editor can be loaded with characters from a text file. The constructor for the second mode, and three final methods required for the assignment will be described below: 

* public Editor(String filepath) - this constructs and editor with the characters from a text ``` file ``` at the provided file path. This is implemented by iterating over the text file character-by-character and inserting each character into the Editor, thus requiring the use of ``` java.util.Scanner ``` and ``` java.io.File ```. 
* public void toString() - this method returns a String concatenation of all the characters stored in the text editor. For example, if the Editor contains the characters 'b','l','u', and 'e', then a call to the ``` toString() ``` method should return "blue". 
* public void clear() - this method removes all characters stored in the text editor and updates all relevant/necessary fields 
* public void save(String savepath) - this method saves the contents of the text editor to a file at the provided save path, thus requiring the use of ``` java.io.PrintStream ``` and ``` java.io.File ```
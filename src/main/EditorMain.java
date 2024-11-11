package main;

import java.io.FileNotFoundException;

/**
 * This is the main file that you should run in order to see a GUI of your text
 * editor - see the PDF for more.
 * 
 * @author Chami Lamelas
 *
 */
public class EditorMain {

	public static void main(String[] args) throws FileNotFoundException {

		// Uncomment the line below to open the editor with no input file
		new EditorDisplay();

		// Uncomment the line below to open the editor with an input file that has a
		// single line of text
//		new EditorDisplay("single_line_file.txt");

		// Uncomment the line below to open the editor with an input file that has
		// multiple lines of text
//		new EditorDisplay("multi_line_file.txt");

	}

}
/**
* Editor class
* - Creates a text editor that allows user to insert characters, text from a single line file, and text from a multi line file 
* - Allows removal (character backspace, character deletion, clearing the text editor) 
* - Allows cursors movement (moving left and right using left/right arrows, jumping to first possible position, jumping to last possible position)
* - Allows saving of contents of text editor to a different file 
* - Tracks size and position of text editor and its contents 
*
* Known Bugs: No known bugs
* - Uses String.charAt(index) for given file content insertion, which I'm not too sure if we were allowed to use
*
* @author Kirsten Tapalla 
* ktapalla@brandeis.edu 
* October 8, 2021 
* COSI 21A PA1
*/

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Editor {

	public int numChars; /** KEEP THIS PUBLIC : use this to store the number of characters in your Editor */
	public int curPos; /** KEEP THIS PUBLIC : use this to store the current cursor index in [0, numChars] */

	public Node cur; /** KEEP THIS PUBLIC : use this to reference the node that is after the visual cursor or null if curPos = numChars */
	public Node head; /** KEEP THIS PUBLIC : use this to reference the first node in the Editor's doubly linked list */
	public Node tail; /** KEEP THIS PUBLIC : use this to reference the last node in the Editor's doubly linked list */

	/**
	 * Constructor, initializes public variables
	 * - Runtime: O(1)
	 */
	public Editor() {
		this.numChars = 0;
		this.curPos = 0;
		this.head = null;
		this.tail = null;
		this.cur = null;
	}

	/**
	 * Constructor when a filepath is provided, initializes public variables
	 * - Inserts file contents into the text editor
	 * - Gets text from file line by line, then gets each character from each line and inserts into text editor 
	 * - Runtime: O(n^2)
	 * @param filepath - provided file (path) by user 
	 * @throws FileNotFoundException - exception thrown if file isn't found 
	 */
	public Editor(String filepath) throws FileNotFoundException {
		new Editor();
		File inputFile = new File(filepath);
		Scanner inFileScan = new Scanner(inputFile);
		while (inFileScan.hasNextLine()) {
			String s = inFileScan.nextLine();
			for(int i = 0; i < s.length(); i++) {
				insert(s.charAt(i));
			}
			insert('\n');
		}
		//removes last insert('\n') so cursor is after last character in input file (not on a new line)
		backspace();
	}

	/**
	 * @returns int of current position of cursor
	 * - Runtime: O(1)
	 */
	public int getCursorPosition() {
		return this.curPos;
	}

	/**
	 * @returns int of number of characters in text editor
	 * - Runtime: O(1)
	 */
	public int size() {
		return this.numChars;
	}

	/**
	 * Moves cursor one position to the right when possible 
	 * - Runtime: O(1)
	 */
	public void moveRight() {
		if (this.curPos+1 <= this.numChars) {
			this.cur = this.cur.next;
			this.curPos++;
		}
	}

	/**
	 * Moves cursor one position to the left when possible
	 * - Runtime: O(1)
	 */
	public void moveLeft() {
		if (this.curPos-1 >= 0) {
			if (this.curPos == this.numChars) {
				this.cur = this.tail;
			} 
			else {
				this.cur = this.cur.prev;
			}
			this.curPos--;
		}
	}

	/**
	 * Moves cursor to head of linked list/text editor (before first char) 
	 * - Runtime: O(1)
	 */
	public void moveToHead() {
		this.cur = this.head;
		this.curPos = 0;
	}

	/**
	 * Moves cursor to tail of linked list/text editor (after last char)
	 * - Runtime: O(1) 
	 */
	public void moveToTail() {
		this.cur = null;
		this.curPos = this.numChars;
	}

	/**
	 * Inserts char provided by user input into text editor/LinkedList
	 * - numChars and curPos both increase
	 * - Runtime: O(1)
	 * @param c - character being inserted 
	 */
	public void insert(char c) { 
		Node ins = new Node(c);

		//empty list
		if (this.numChars == 0) {
			this.cur = ins;
			this.head = this.cur;
			this.tail = this.cur;
		} 
		//non empty list
		else {
			//inserting at head/beginning 
			if (this.curPos == 0) {
				ins.next = this.cur;
				this.cur.prev = ins;
				this.head = ins;
			}
			//inserting at tail/end 
			else if(this.curPos == this.numChars) {
				ins.prev = this.tail;
				this.tail.next = ins;
				this.tail = ins;
				this.cur = null;
			}
			//inserting between existing characters
			else {
				ins.prev = this.cur.prev;
				ins.next = this.cur;
				this.cur.prev.next = ins;
				this.cur.prev = ins;
			}
		}
		this.numChars++;
		this.curPos++;
	}

	/**
	 * Removes char after cursor/curPos (what cur is pointing at)
	 * - numChars decreases
	 * - Runtime: O(1)
	 */
	public void delete() {
		//non empty list, curPos < numChars bc at numChars nothing would be removed
		if (this.numChars-1 >= 0 && this.curPos < this.numChars) {
			//removing tail when there is more than one char in list
			if(this.curPos == this.numChars-1 && this.numChars > 1) {
				this.tail = this.cur.prev;
				this.tail.next = null;
				this.cur = null;
			}
			//curPos is 0, removing head
			else if (this.curPos == 0) {
				//only 1 char in list, head is also tail in this case, clears and immediately returns to exit primary if statement and avoid making numChars and curPos -1
				if (this.numChars == 1)	{
					clear();
					return;
				}
				//more than one char in list
				else {
					this.head = this.head.next;
					this.head.prev = null;
					this.cur = this.head;					
				}
			}
			//removing anywhere else in the list
			else {
				this.cur.prev.next = this.cur.next;
				this.cur.next.prev = this.cur.prev;
				this.cur = this.cur.next;				
			}
			this.numChars--;
		}
	}

	/**
	 * Removes char before the cursor/curPos (not what cur is pointing at)
	 * - numChars and curPos both decrease 
	 * - Runtime: O(1)
	 */
	public void backspace() {
		//non empty list, curPos > 0 bc at 0 nothing would be removed
		if (this.numChars-1 >= 0 && this.curPos > 0) {
			//removing tail when there's more than one char in list
			if (this.curPos == this.numChars && this.numChars > 1) {
				this.tail = this.tail.prev;
				this.tail.next = null;
			}
			//curPos is 1, removing head 
			else if (this.curPos == 1) {
				//only 1 char in list, head is also tail in this case, clears and immediately returns to exit primary if statement and avoid making numChars and curPos -1
				if (this.numChars == 1) {
					clear();
					return;
				}
				//more than 1 char in list
				else {
					this.cur.prev = null;
					this.head = this.cur;					
				}
			}
			//removing anywhere else in the list
			else {
				this.cur.prev = this.cur.prev.prev;
				this.cur.prev.next = this.cur;
			}
			this.curPos--;
			this.numChars--;
		} 
	}

	/**
	 * Returns String of text/chars within the text editor
	 * - creates temp pointer to log cur and return to same position after printing all characters
	 * - Runtime: O(n)
	 */
	public String toString() {
		String s = "";
		Node temp = this.cur;
		this.cur = this.head;
		while (this.cur != null) {
			s += this.cur.data;
			this.cur = this.cur.next;
		}
		this.cur = temp;;
		return s;
	}

	/**
	 * Clears text editor, everything is reset
	 * - Runtime: O(1)
	 */
	public void clear() {
		this.numChars = 0;
		this.curPos = 0;
		this.head = null;
		this.tail = null;
		this.cur = null;
	}

	/**
	 * Saves contents of text editor to a file at the provided save path
	 * - Runtime: O(1) (or maybe O(n) since 'printFile.print(this.toString());' runs O(n))
	 * @param savepath - provided save path by user
	 * @throws FileNotFoundException 
	 */
	public void save(String savepath) throws FileNotFoundException {
		File outputFile = new File(savepath);
		PrintStream printFile = new PrintStream(outputFile);
		printFile.print(this.toString());
	}


}

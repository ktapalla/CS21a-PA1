/**
* JUnit test class for the Editor class.
* - Does not test toString method as that is already tested in example test provided
* 
* Known Bugs: No known bugs
* - Uses String.charAt(index) for given file content insertion, which I'm not too sure if we were allowed to use
*
* @author Kirsten Tapalla 
* ktapalla@brandeis.edu 
* October 8, 2021 
* COSI 21A PA1
*/

package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.Editor;

public class StudentEditorTests {

	/** Editor used for testing */
	Editor e;

	/**
	 * Before each test, the Editor is re-initialized
	 */
	@Before
	public void reset() {
		e = new Editor();
	}

	/**
	 * Tests inserting new characters to the front/head of the editor/text
	 * - Checks if total number of characters changes
	 * - Checks if cursor position changes
	 * - Checks if head changes/has correct data
	 * - Checks if cur points to proper character/node (head)
	 * - Checks if cur.prev points to proper character/node (whatever character was previously inserted)
	 * - Checks if Editor contains the edited text with the newly inserted characters
	 */
	@Test
	public void testHeadInsert() {
		loadEditorTestPhrase(e);

		e.moveToHead();
		e.insert('H');
		assertEquals(45, e.numChars);
		assertEquals(1, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('H', e.cur.prev.data);
		assertEquals("HThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('e');
		assertEquals(46, e.numChars);
		assertEquals(2, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('e', e.cur.prev.data);
		assertEquals("HeThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('a');
		assertEquals(47, e.numChars);
		assertEquals(3, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('a', e.cur.prev.data);
		assertEquals("HeaThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('d');
		assertEquals(48, e.numChars);
		assertEquals(4, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('d', e.cur.prev.data);
		assertEquals("HeadThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('I');
		assertEquals(49, e.numChars);
		assertEquals(5, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('I', e.cur.prev.data);
		assertEquals("HeadIThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('n');
		assertEquals(50, e.numChars);
		assertEquals(6, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('n', e.cur.prev.data);
		assertEquals("HeadInThe quick brown fox jumped over the lazy dog", e.toString());
		
		e.insert('s');
		assertEquals(51, e.numChars);
		assertEquals(7, e.getCursorPosition());
		assertEquals('T', e.cur.data);
		assertEquals('H', e.head.data);
		assertEquals('s', e.cur.prev.data);
		assertEquals("HeadInsThe quick brown fox jumped over the lazy dog", e.toString());
	}

	/**
	 * Tests inserting new characters to the end/tail of the editor/text
	 * - Checks if total number of characters changes
	 * - Checks if cursor position changes
	 * - Checks if tail changes/has correct data
	 * - Checks if cur is null
	 * - Checks if tail.prev points to proper character/node (whatever character was previously inserted)
	 * - Checks if Editor contains the edited text with the newly inserted characters
	 */
	@Test
	public void testTailInsert() {
		loadEditorTestPhrase(e);

		e.moveToTail();
		e.insert('T');
		assertEquals(45, e.numChars);
		assertEquals(45, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('T', e.tail.data);
		assertEquals('g', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogT", e.toString());
		
		e.insert('a');
		assertEquals(46, e.numChars);
		assertEquals(46, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('a', e.tail.data);
		assertEquals('T', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTa", e.toString());
		
		e.insert('i');
		assertEquals(47, e.numChars);
		assertEquals(47, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('i', e.tail.data);
		assertEquals('a', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTai", e.toString());
		
		e.insert('l');
		assertEquals(48, e.numChars);
		assertEquals(48, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('l', e.tail.data);
		assertEquals('i', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTail", e.toString());
		
		e.insert('I');
		assertEquals(49, e.numChars);
		assertEquals(49, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('I', e.tail.data);
		assertEquals('l', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTailI", e.toString());
		
		e.insert('n');
		assertEquals(50, e.numChars);
		assertEquals(50, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('n', e.tail.data);
		assertEquals('I', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTailIn", e.toString());
		
		e.insert('s');
		assertEquals(51, e.numChars);
		assertEquals(51, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('s', e.tail.data);
		assertEquals('n', e.tail.prev.data);
		assertEquals("The quick brown fox jumped over the lazy dogTailIns", e.toString());
	}

	/**
	 * Tests inserting new characters to the middle/body of the editor/text
	 * - Checks if total number of characters changes
	 * - Checks if cursor position changes
	 * - Checks if tail has correct data
	 * - Checks if head has correct data
	 * - Checks if cur points to proper character/node ('m')
	 * - Checks if cur.prev points to proper character/node (whatever character was previously inserted)
	 * - Checks if Editor contains the edited text with the newly inserted characters
	 */
	@Test
	public void testMidInsert() {
		loadEditorTestPhrase(e);

		for(int i = e.getCursorPosition(); i > e.numChars/2; i--) {
			e.moveLeft();
		}
		assertEquals(22, e.getCursorPosition());

		e.insert('M');
		assertEquals(45, e.numChars);
		assertEquals(23, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('M', e.cur.prev.data);
		assertEquals("The quick brown fox juMmped over the lazy dog", e.toString());
		
		e.insert('i');
		assertEquals(46, e.numChars);
		assertEquals(24, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('i', e.cur.prev.data);
		assertEquals("The quick brown fox juMimped over the lazy dog", e.toString());
		
		e.insert('d');
		assertEquals(47, e.numChars);
		assertEquals(25, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('d', e.cur.prev.data);
		assertEquals("The quick brown fox juMidmped over the lazy dog", e.toString());
		
		e.insert('I');
		assertEquals(48, e.numChars);
		assertEquals(26, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('I', e.cur.prev.data);
		assertEquals("The quick brown fox juMidImped over the lazy dog", e.toString());
		
		e.insert('n');
		assertEquals(49, e.numChars);
		assertEquals(27, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('n', e.cur.prev.data);
		assertEquals("The quick brown fox juMidInmped over the lazy dog", e.toString());
		
		e.insert('s');
		assertEquals(50, e.numChars);
		assertEquals(28, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('s', e.cur.prev.data);
		assertEquals('T', e.head.data);
		assertEquals('g', e.tail.data);
		assertEquals("The quick brown fox juMidInsmped over the lazy dog", e.toString());
	}

	/**
	 * Tests more complex combination of movement 
	 * - Example editor already tests basic movement, this tests if it works when more than one kind are done together
	 * - Checks if movement properly changes cursor position (movetoHead, moveToTail, moveLeft, moveRight)
	 * - Checks if cur points to the proper characters/nodes
	 */
	@Test
	public void testCombinedMovement() {
		loadEditorTestPhrase(e);

		//Tests movement to the head
		e.moveToHead();
		assertEquals(0, e.getCursorPosition());
		assertEquals('T', e.head.data);
		assertEquals('T', e.cur.data);

		//Tests moving right from the head
		e.moveRight();
		assertEquals(1, e.getCursorPosition());
		assertEquals('h', e.cur.data);
		
		e.moveRight();
		assertEquals(2, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		
		e.moveRight();
		assertEquals(3, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		
		e.moveRight();
		assertEquals(4, e.getCursorPosition());
		assertEquals('q', e.cur.data);
		
		e.moveRight();
		assertEquals(5, e.getCursorPosition());
		assertEquals('u', e.cur.data);

		//Tests movement to the Tail
		e.moveToTail();
		assertEquals(44, e.getCursorPosition());
		assertEquals('g', e.tail.data);
		assertEquals(null, e.cur);

		//Tests moving left from the tail
		e.moveLeft();
		assertEquals(43, e.getCursorPosition());
		assertEquals('g', e.cur.data);
		
		e.moveLeft();
		assertEquals(42, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		
		e.moveLeft();
		assertEquals(41, e.getCursorPosition());
		assertEquals('d', e.cur.data);
		
		e.moveLeft();
		assertEquals(40, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		
		e.moveLeft();
		assertEquals(39, e.getCursorPosition());
		assertEquals('y', e.cur.data);
	}

	/**
	 * Tests removal using delete at the head/front of the editor/text
	 * - Deletes first word and the white space following it
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur points to proper character/node (head or 'q')
	 * - Checks if cur.prev is null
	 * - Checks if head is updated and contains proper character/node ('q')
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testHeadDelete() {
		loadEditorTestPhrase(e);

		e.moveToHead();
		e.delete();
		assertEquals(43, e.numChars);
		assertEquals(0, e.getCursorPosition());
		assertEquals('h', e.cur.data);
		assertEquals(null, e.cur.prev);
		assertEquals('h', e.head.data);
		assertEquals("he quick brown fox jumped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(42, e.numChars);
		assertEquals(0, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		assertEquals(null, e.cur.prev);
		assertEquals('e', e.head.data);
		assertEquals("e quick brown fox jumped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(41, e.numChars);
		assertEquals(0, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		assertEquals(null, e.cur.prev);
		assertEquals(' ', e.head.data);
		assertEquals(" quick brown fox jumped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(40, e.numChars);
		assertEquals(0, e.getCursorPosition());
		assertEquals('q', e.cur.data);
		assertEquals(null, e.cur.prev);
		assertEquals('q', e.head.data);
		assertEquals("quick brown fox jumped over the lazy dog", e.toString());
	}

	/**
	 * Tests removal using delete at the tail/end of the editor/text
	 * - Deletes last word and the white space preceding it (begins removal from curPos=40, or before white space preceding 'dog' and after 'y' from 'lazy')
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur is null
	 * - Checks if cur.prev points to proper character/node ('y')
	 * - Checks if tail is updated and contains proper character/node ('y')
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testTailDelete() {
		loadEditorTestPhrase(e);

		e.moveLeft();
		assertEquals(43, e.getCursorPosition());
		
		e.moveLeft();
		assertEquals(42, e.getCursorPosition());
		
		e.moveLeft();
		assertEquals(41, e.getCursorPosition());
		
		e.moveLeft();
		assertEquals(40, e.getCursorPosition());
		
		e.delete();
		assertEquals(43, e.numChars);
		assertEquals(40, e.getCursorPosition());
		assertEquals('d', e.cur.data);
		assertEquals('y', e.cur.prev.data);
		assertEquals('g', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazydog", e.toString());
		
		e.delete();
		assertEquals(42, e.numChars);
		assertEquals(40, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('y', e.cur.prev.data);
		assertEquals('g', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazyog", e.toString());
		
		e.delete();
		assertEquals(41, e.numChars);
		assertEquals(40, e.getCursorPosition());
		assertEquals('g', e.cur.data);
		assertEquals('y', e.cur.prev.data);
		assertEquals('g', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazyg", e.toString());
		
		e.delete();
		assertEquals(40, e.numChars);
		assertEquals(40, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('y', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazy", e.toString());
	}


	/**
	 * Tests removal using delete in the middle/body of the editor/text (anywhere within)
	 * - Deletes the word 'jumped' and the white space surrounding it
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur points to proper character/node ('o')
	 * - Checks if cur.prev is updated and points to proper character/node ('x')
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testMidDelete() {
		loadEditorTestPhrase(e);


		//moves cursor in front of 'jumped' and its preceding white space (is right after 'x')
		e.moveToHead();
		for(int i = 0; i < 19; i++) {
			e.moveRight();
		}
		e.delete();
		assertEquals(43, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('j', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxjumped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(42, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('u', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxumped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(41, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('m', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxmped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(40, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('p', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxped over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(39, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('e', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxed over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(38, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('d', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxd over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(37, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals(' ', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown fox over the lazy dog", e.toString());
		
		e.delete();
		assertEquals(36, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxover the lazy dog", e.toString());
	}

	/**
	 * Tests removal using backspace at the head/front of the editor/text
	 * - Deletes first word and the white space following it (begins removal from curPos=4, or after white space following 'The' and before 'q' from 'quick')
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur points to proper character/node (head or 'q')
	 * - Checks if cur.prev points to proper character/node 
	 * - Checks if head is updated and contains proper character/node ('q')
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testHeadBackspace() {
		loadEditorTestPhrase(e);

		e.moveToHead();
		assertEquals(0, e.getCursorPosition());
		
		e.moveRight();
		assertEquals(1, e.getCursorPosition());
		
		e.moveRight();
		assertEquals(2, e.getCursorPosition());
		
		e.moveRight();
		assertEquals(3, e.getCursorPosition());
		
		e.moveRight();
		assertEquals(4, e.getCursorPosition());
		
		e.backspace();
		assertEquals(43, e.numChars);
		assertEquals(3, e.getCursorPosition());
		assertEquals('q', e.cur.data);
		assertEquals('e', e.cur.prev.data);
		assertEquals("Thequick brown fox jumped over the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(42, e.numChars);
		assertEquals(2, e.getCursorPosition());
		assertEquals('q', e.cur.data);
		assertEquals('h', e.cur.prev.data);
		assertEquals("Thquick brown fox jumped over the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(41, e.numChars);
		assertEquals(1, e.getCursorPosition());
		assertEquals('q', e.cur.data);
		assertEquals('T', e.cur.prev.data);
		assertEquals("Tquick brown fox jumped over the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(40, e.numChars);
		assertEquals(0, e.getCursorPosition());
		assertEquals(null, e.cur.prev);
		assertEquals('q', e.cur.data);
		assertEquals('q', e.head.data);
		assertEquals("quick brown fox jumped over the lazy dog", e.toString());
	}

	/**
	 * Tests removal using backspace at the tail/end of the editor/text
	 * - Deletes last word and the white space preceding it
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur is null
	 * - Checks if tail is updated and contains proper character/node ('y')
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testTailBackspace() {
		loadEditorTestPhrase(e);

		e.backspace();
		assertEquals(43, e.numChars);
		assertEquals(43, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('o', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazy do", e.toString());
		
		e.backspace();
		assertEquals(42, e.numChars);
		assertEquals(42, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('d', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazy d", e.toString());
		
		e.backspace();
		assertEquals(41, e.numChars);
		assertEquals(41, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals(' ', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazy ", e.toString());
		
		e.backspace();
		assertEquals(40, e.numChars);
		assertEquals(40, e.getCursorPosition());
		assertEquals(null, e.cur);
		assertEquals('y', e.tail.data);
		assertEquals("The quick brown fox jumped over the lazy", e.toString());
	}


	/**
	 * Tests removal using backspace in the middle/body of the editor/text (anywhere within)
	 * - Deletes the word 'jumped' and the white space surrounding it
	 * - Checks if total number of characters changes
	 * - Checks if cursor is at proper position
	 * - Checks if cur points to proper character/node ('o')
	 * - Checks if cur.prev is updated and points to proper character/node
	 * - Checks if Editor contains the edited text without the deleted characters 
	 */
	@Test
	public void testMidBackspace() {
		loadEditorTestPhrase(e);


		//moves cursor after 'jumped' and its following white space (is right before 'o')
		for(int i = 0; i < 17; i++) {
			e.moveLeft();
		}
		e.backspace();
		assertEquals(43, e.numChars);
		assertEquals(26, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('d', e.cur.prev.data);
		assertEquals("The quick brown fox jumpedover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(42, e.numChars);
		assertEquals(25, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('e', e.cur.prev.data);
		assertEquals("The quick brown fox jumpeover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(41, e.numChars);
		assertEquals(24, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('p', e.cur.prev.data);
		assertEquals("The quick brown fox jumpover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(40, e.numChars);
		assertEquals(23, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('m', e.cur.prev.data);
		assertEquals("The quick brown fox jumover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(39, e.numChars);
		assertEquals(22, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('u', e.cur.prev.data);
		assertEquals("The quick brown fox juover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(38, e.numChars);
		assertEquals(21, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('j', e.cur.prev.data);
		assertEquals("The quick brown fox jover the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(37, e.numChars);
		assertEquals(20, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals(' ', e.cur.prev.data);
		assertEquals("The quick brown fox over the lazy dog", e.toString());
		
		e.backspace();
		assertEquals(36, e.numChars);
		assertEquals(19, e.getCursorPosition());
		assertEquals('o', e.cur.data);
		assertEquals('x', e.cur.prev.data);
		assertEquals("The quick brown foxover the lazy dog", e.toString());
	}
	
	/**
	 * Helper method that loads characters into an Editor
	 * - All characters of the alphabet are used
	 * - "The quick brown fox jumped over the lazy dog"
	 * - Uses insert method from editor
	 * @param editor an Editor to load with "The quick brown fox jumped over the lazy dog".
	 */
	public static void loadEditorTestPhrase(Editor editor) {
		String s = "The quick brown fox jumped over the lazy dog";
		for (int i = 0; i < s.length(); i++) {
			editor.insert(s.charAt(i));
		}
	}
}

import java.io.*;
import java.util.*;

/**
 * Play the game of 20 questions.
 * 
 * @author Nikolai Sazonov, Nikola Stanic
 */
public class TwentyQuestions {

	/**
	 * Play a game of 20 Questions.
	 * 
	 * @param root
	 *          the root of a 20 Questions subtree
	 * @param scanner
	 *          the scanner to read user input from
	 */
	public static void play ( TreeNode<String> root, Scanner scanner ) {
		
		boolean isQuestion = root.getElement().endsWith("?");
		String input = "";
		
		if(!isQuestion) {
			while(!input.equals("yes") && !input.equals("no")) {
				System.out.print("you're thinking of " + root.getElement() + "!"
						+ "\n\tright? ");
				input = scanner.nextLine();
			}
			
			if(!input.equals("no")) { return; }
			
			System.out.print("what were you thinking of? ");
			String newAnswer = scanner.nextLine();
			
			System.out.println("a question which 'yes' for " 
			+ newAnswer + " and 'no' for " + root.getElement());
			String newQuestion = scanner.nextLine();
			
			// THIS MAY NOT WORK
			// Impliment adding to the tree node
			
			TreeNode<String> newNode = new TreeNode<>(newAnswer);
			TreeNode<String> oldNode = new TreeNode<>(root.getElement());
			root.setElement(newQuestion);
			root.setLeft(newNode);
			root.setRight(oldNode);

			newNode.setParent(root);
			oldNode.setParent(root);
			// return;
		}
		else { // Is question

			while( !(input.equals("yes") || input.equals("no")) ) {
				System.out.print(root.getElement());
				input = scanner.nextLine();
			}
			
			if(input.equals("yes")) {
				play(root.getLeft(), scanner);
			}
			else if(input.equals("no")) {
				play(root.getRight(), scanner);
			}
			
		}
		
	}

	/**
	 * Print the sequence of questions and answers needed to get to identify the
	 * specified thing in the form:
	 * 
	 * <pre>
	 *   cat -- 
	 *     does it have fur? yes 
	 *     does it lay eggs? no
	 * </pre>
	 * 
	 * @param leaf
	 *          leaf node of a 20 Questions tree (i.e. it contains a thing)
	 * @param tree
	 *          the 20 Questions tree
	 */
	public static void printQs ( TreeNode<String> leaf ) {
		
		System.out.println("\n" + leaf.getElement() + " --");
		
		Stack<String> questions = new Stack<>();	// does it have fur?
		Stack<String> qAnswers = new Stack<>();		// yes no
		
		TreeNode<String> child;
		
		while(leaf.getParent() != null) {

			child = leaf;
			leaf = leaf.getParent();
			questions.push(leaf.getElement());
			
			// impliment yes no stuff
			if(leaf.getLeft() == child) {
				qAnswers.push("yes");
			}
			else {
				qAnswers.push("no");
			}
			
			
		}
		
		while(!questions.isEmpty()) {
			System.out.println("  " + questions.pop() + "  " + qAnswers.pop());
		}
		
	}

	/**
	 * Find the node of a 20 Questions tree containing the specified thing, if it
	 * exists.
	 * 
	 * @param root
	 *          the root of a 20 Questions subtree
	 * @param thing
	 *          the thing
	 * @return the node (a leaf) containing the thing, or null if there is no such
	 *         thing in the tree
	 */
	public static TreeNode<String> find ( TreeNode<String> root, String thing ) {
		
		if(root == null) {
			return null;
		}
		
		if(root.getElement().equals(thing)) {
			return root;
		}
		
		TreeNode<String> left = find(root.getLeft(), thing);
		if(left != null) {
			return left;
		}
		
		TreeNode<String> right = find(root.getRight(), thing);
		if(right != null) {
			return right;
		}
		
		return null;
	}

	/**
	 * Save a subtree to a file.
	 * 
	 * @param root
	 *          the root of the subtree to save
	 * @param filename
	 *          name of file to save the tree in
	 * @throws IOException
	 *           if there is an error opening the file or writing the tree
	 */
	public static void save ( TreeNode<String> root, String filename )
	    throws IOException {
		// TODO: implement this!
	}

	/**
	 * Load a subtree from a file.
	 * 
	 * @param filename
	 *          name of file to load from
	 * @return the loaded subtree
	 * @throws IOException
	 *           if there is an error opening the file or reading the tree
	 */
	public static TreeNode<String> load ( String filename ) throws IOException {
		// TODO: implement this!
		return new TreeNode<String>("load JJBA PT 7 Is GOATED");
	}

	/**
	 * Print a subtree.
	 * 
	 * @param root
	 *          the root of the subtree to print
	 */
	public static void print ( TreeNode<String> root ) {
		print(root,"");
	}

	/**
	 * Print the subtree rooted at the specified node.
	 * 
	 * @param root
	 *          the root of the subtree to print
	 * @param indent
	 *          indentation level for the current node
	 */
	private static void print ( TreeNode<String> root, String indent ) {
		if ( root.getLeft() == null ) {
			System.out.println(indent + root.getElement());

		} else {
			System.out.println(indent + root.getElement());
			print(root.getLeft(),indent + " ");
			print(root.getRight(),indent + " ");
		}
	}

	public static void main ( String[] args ) {

		Scanner scanner = new Scanner(System.in);
		TreeNode<String> root = new TreeNode<String>("duck");

		for ( ; true ; ) {
			System.out
			    .print("(p)lay, print (t)ree, (f)ind thing, (s)ave, (l)oad, or (q)uit?  ");
			char choice = scanner.nextLine().charAt(0);
			System.out.println();

			if ( choice == 'q' ) { // quit game
				System.out.println("goodbye");
				break;

			} else if ( choice == 't' ) { // print tree
				print(root);

			} else if ( choice == 'f' ) { // find thing
				System.out.print("enter the thing to look for: ");
				String thing = scanner.nextLine();
				System.out.println();
				
				// TreeNode<String> node = TwentyQOps.find(root,thing);
				TreeNode<String> node = find(root,thing);
				
				if ( node == null ) {
					System.out.println("  " + thing + " is not a thing in this tree");
				} else {
					printQs(node);
					// TwentyQOps.printQs(node);
				}

			} else if ( choice == 's' ) { // save tree
				try {
					System.out.print("enter filename to save in: ");
					String filename = scanner.nextLine();
					// TODO change this
					TwentyQOps.save(root,filename);
					
				} catch ( IOException e ) {
					System.out.println("error saving");
				}

			} else if ( choice == 'l' ) { // load tree
				try {
					System.out.print("enter filename to load from: ");
					String filename = scanner.nextLine();
					// TODO change this
					root = TwentyQOps.load(filename);
					//root = load(filename);
					
				} catch ( IOException e ) {
					System.out.println("error loading file");
				}

			} else if ( choice == 'p' ) { // play game
				play(root, scanner);
				// TwentyQOps.play(root,scanner);
			}

			System.out.println();
		}
	}
}

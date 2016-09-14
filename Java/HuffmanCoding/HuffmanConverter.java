import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanConverter
{ 
  // The # of chars in the ASCII table dictates
  // the size of the count[] & code[] arrays.
  public static final int NUMBER_OF_CHARACTERS = 256;
 
  // the contents of our message...
  private String contents;
 
  // the tree created from the msg
  private HuffmanTree huffmanTree;
 
  // tracks how often each character occurs
  private int count[];
 
  // the huffman code for each character
  private String code[];
 
  // stores the # of unique chars in contents
  private int uniqueChars = 0; //(optional)
 
  /** Constructor taking input String to be converted */
  public HuffmanConverter(String input)
  {
    this.contents = input;
    this.count = new int[NUMBER_OF_CHARACTERS];
    this.code = new String[NUMBER_OF_CHARACTERS];
  }
 
  /**
   * Records the frequencies that each character of our
   * message occurs...
   * I.e., we use 'contents' to fill up the count[] list...
   */
  public void recordFrequencies(){
	  
	  //populating the count array
	  for(int i =0; i<this.contents.length(); i++){
		  char c = this.contents.charAt(i);
		  int wordInt = (int) c;
		  count[wordInt] ++; 
		  
	  }
	  
  }
  
 
  /**
   * Converts our frequency list into a Huffman Tree. We do this by
   * taking our count[] list of frequencies, and creating a binary
   * heap in a manner similar to how a heap was made in HuffmanTree's
   * fileToHeap method. Then, we print the heap, and make a call to
   * HuffmanTree.heapToTree() method to get our much desired
   * HuffmanTree object, which we store as huffmanTree.
   */
  public void frequenciesToTree(){
	  
	//counting unique characters
	  for (int i =0; i<this.count.length; i++){
		  if (this.count[i] != 0){
			  this.uniqueChars++;
		  }
	  }
	  //create array of element HuffmanNodes
	  HuffmanNode[] nodes = new HuffmanNode[uniqueChars];
	  int nodeIndex = 0;
	  
	  //populating nodes from count array
	  for(int i =0; i<count.length; i++){
		  
		  if (count[i] != 0){
			  
			  //manipulating i into string
			  char Character = (char) i; 
			  Character characterObject = (Character) Character;
			  String letter = characterObject.toString();
			  //accounting for "\n" character
			  
			  
			  
			  //manipulating count[i] into Double
			  double freq = (double) count[i];
			  Double frequency = (Double) freq;
			  
			  
			  nodes [nodeIndex] = new HuffmanNode(letter,frequency);
			  nodeIndex++;
		  }
	  }
	  //System.out.println("test");
	  
	  //create Binary Heap and print it
	  BinaryHeap bHeap = new BinaryHeap(nodes);
	  bHeap.printHeap();
	  
	  //System.out.println("test2");
	  //create HuffmanTree and return it
	  this.huffmanTree = HuffmanTree.createFromHeap(bHeap);
	  
	  
	  
  }
  

  
 
  /**
   * Iterates over the huffmanTree to get the code for each letter.
   * The code for letter i gets stored as code[i]... This method
   * behaves similarly to HuffmanTree's printLegend() method...
   * Warning: Don't forget to initialize each code[i] to ""
   * BEFORE calling the recursive version of treeToCode...
   */
  
  public void treeToCode(){
	  for (int i = 0; i<code.length; i++){
		  code[i] = "";
	  }
	  treeToCode(this.huffmanTree.root, this.code[0]);
  }
 
  /*
   * A private method to iterate over a HuffmanNode t using s, which
   * contains what we know of the HuffmanCode up to node t. This is
   * called by treeToCode(), and resembles the recursive printLegend
   * method in the HuffmanTree class. Note that when t is a leaf node,
   * t's letter tells us which index i to access in code[], and tells
   * us what to set code[i] to...
   */
  private void treeToCode(HuffmanNode t, String s){
	  String letter = (String) t.letter;
	  int letterLength = letter.length();
	  
	  //traverses down each leaf of HuffmanTree, adding 0 if left and 1 if right
	  if (letterLength>1){
		  treeToCode(t.left, s+"1");
		  treeToCode(t.right, s+"0");
	  }
	  
	  //when leaves are reached, assign the Huffman Code to code[(int)c]
	  if (letterLength ==1){
		  char c = t.letter.charAt(0);
		  code[(int)c]=s;
		  
		  //account for \n character when printing
		  if (t.letter.equals( "\n")){
			  System.out.println("'\\n'" + "=" + s);
		  } else{
			  System.out.println("'" + t.letter+ "'"+"="+s);
		  }
	  }
  }
 
  /**
   * Using the message stored in contents, and the huffman conversions
   * stored in code[], we create the Huffman encoding for our message
   * (a String of 0's and 1's), and return it...
   */
  public String encodeMessage(){
	  
	  String output = "";
	  
	  for(int i =0; i<contents.length(); i++){
		  char c = contents.charAt(i);
		  int wordInt = (int) c;
		  output = output + code[wordInt]; 
	  }
	  return output;
	  
  }
 
  /**
   * Reads in the contents of the file named filename and returns
   * it as a String. The main method calls this method on args[0]...
   */
  public static String readContents(String filename) throws IOException{
	  BufferedReader reader = new BufferedReader( new FileReader (filename));
	    String line = null;
	    StringBuilder stringBuilder = new StringBuilder();

	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line + '\n' );
	    }

	    return stringBuilder.toString();
	}
 
  /**
   * Using the encoded String argument, and the huffman codings,
   * re-create the original message from our
   * huffman encoding and return it...
   */
  public String decodeMessage(String encodedStr) {
	  String output = "";
	  String tempString = "";
	  
	  // loop through encodedStr, parsing off one character at a time until the String of binary digits is a recognizable Huffman Code for a character
	  while (!encodedStr.isEmpty()){
		  tempString = tempString + encodedStr.charAt(0);
		  //System.out.println(tempString);
		  
		  //loop through the code[] array to check if tempString is a recognizable character in Huffman Code
		  for(int i =0; i<code.length; i++){
			  //if tempString is a recognizable character, reset tempString, and add the character into our output string
			  String stringHuffCode = code[i];
			  
			  
			  if (stringHuffCode.equals(tempString)){
				  tempString = "";
				  String c = (char) i +"";
				  output = output+c;
				  break;
			  }
			  
			  
		  }
		  
		  
		  
		  //cut off the first character of encodedStr
		  encodedStr = encodedStr.substring(1);
	  }
	  
	  return output;
  }
 
  
  
 
  /**
   * Uses args[0] as the filename, and reads in its contents. Then
   * instantiates a HuffmanConverter object, using its methods to
   * obtain our results and print the necessary output. Finally,
   * decode the message and compare it to the input file.<p>
   * NOTE: Example method provided below...
   */
  
  public static void main(String args[]) throws IOException{
	   Scanner in = new Scanner(System.in);
	   System.out.println("Enter the file name: ");
	   String fileName = in.nextLine();
	   
	   
	   
	   String contents = readContents(args[0]);
	   //System.out.println(contents);
	   
	   
	   
	   HuffmanConverter huff = new HuffmanConverter(contents);
	   
	   huff.recordFrequencies();
	   huff.frequenciesToTree();
	   
	   huff.treeToCode();
	   String encodedMessage = huff.encodeMessage();
	   System.out.println(encodedMessage);
	   
	   String decodedMessage = huff.decodeMessage(encodedMessage);
	   System.out.println(decodedMessage);
	   
	   
	   
	   //System.out.println("end");
	   
	   

	   
    
  }


}

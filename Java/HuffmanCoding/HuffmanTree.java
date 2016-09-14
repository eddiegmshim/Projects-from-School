import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.IOException;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

public class HuffmanTree
{
   HuffmanNode root;
 
   // - add a constructor to init the Tree from a HuffmanNode
   // - the main method will go here, as well as code to take
   //   a command-line argument for the input file name
   
   
   public HuffmanTree(HuffmanNode huff){
	   this.root = huff;
   }
   
   private void printLegend(HuffmanNode t, String s){
	   
	   String letter = (String)t.letter; 
	   int letterLength = letter.length();
	   if (letterLength>1){
		   printLegend(t.left, s+"0");
		   printLegend(t.right, s+"1");
	   } 
	   if (letterLength == 1){
		   System.out.println(t.letter+"="+s);
	   }
	   
		   
   }
   
   public void printLegend(){
	   printLegend(root, "");
   }
   
   public static BinaryHeap fileToHeap(String filename) throws IOException{
	   
	   //pulling file content as one whole String
	   
	   
	   String fileString = new String(readAllBytes(get(filename)));
	   //String fileString = new Scanner(filename).useDelimiter("\\Z").next();
	   
	   //splitting fileString as tokens
	   StringTokenizer tokens = new StringTokenizer(fileString);
	   
	   //putting tokens into an array list
	   ArrayList arrayListTokens= new ArrayList();
	   while (tokens.hasMoreTokens()){
		   arrayListTokens.add(tokens.nextElement());
		   //System.out.println("test");
	   }
	   
	   
	   Object[] arrayTokens = arrayListTokens.toArray();
	   HuffmanNode[] nodes = new HuffmanNode[arrayTokens.length/2]; //there will be half as many nodes as tokens, as tokens account for both letters and freqeuency
	   
	   
	   //populating array nodes with HuffmanNodes of input data
	   int letterPosition =0;
	   int freqPosition = 1;
	   for (int i =0; i<nodes.length; i++){
		   
		   String letter =(String) arrayTokens[letterPosition];
		   Double frequency = Double.valueOf((String)arrayTokens[freqPosition]);
		   //System.out.println(letter + " " + frequency);
		   nodes[i] = new HuffmanNode(letter, frequency);
		   letterPosition= letterPosition +2; 
		   freqPosition = freqPosition +2;
	   }
	   
	   
	   
	   //create the binary heap from the array of HuffmanNodes
	   BinaryHeap bHeap = new BinaryHeap(nodes);
	   //System.out.println("test");
	   return bHeap;
   }
   
   public static HuffmanTree createFromHeap(BinaryHeap b){
	   
	   //HuffmanTree tree = new HuffmanTree(null);
	   
	   
		   
	   //first tree node; on the bottom
	   HuffmanTree tree = new HuffmanTree(null);
	   
	   //building tree		   
	   while (b.getSize()>1){
		   
		// two lowest nodes
		   HuffmanNode n1 = (HuffmanNode) b.deleteMin();
		   HuffmanNode n2 = (HuffmanNode) b.deleteMin();
		   
		   HuffmanNode node = new HuffmanNode(n1, n2);
		   
		   b.insert(node);
		   //System.out.println("test");
	   }
	   
	   
	   if (b.getSize()==1){
		   tree = new HuffmanTree((HuffmanNode) b.deleteMin());
	   }
	   
	   
	   
	   return tree;	   
   }
   
   
   
   public static void main (String args[]) throws IOException{
	   
	   Scanner in = new Scanner(System.in);
	   
	   System.out.println("Enter the file name: ");
	   String string = in.nextLine();
	   
	   fileToHeap(string);
	   
	   //System.out.println("beforeTest");
	   BinaryHeap b = fileToHeap(args[0]);
	   //System.out.println("endTest");
	   
	   b.printHeap();
	   
	   
	   HuffmanTree htree = createFromHeap(b);
	   htree.printLegend();
	   
   }
   }

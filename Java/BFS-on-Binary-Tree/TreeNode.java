import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TreeNode<T> implements Comparable
{
	// Used to hold references to BST nodes for the linked implementation
	protected T info;                // The info in a BST node
	protected TreeNode<T> left;       // A link to the left child node
	protected TreeNode<T> right;      // A link to the right child node
	protected Double distance;	

	public TreeNode(T info, Double distance)
	{
		this.info = info;
		left = null;
		right = null;
		this.distance=distance;
	}

	public double getDistance(){
		return distance;
	}

	//comparing by distance value
	public int compareTo(Object object) {
		Object tempObject = object;
		TreeNode treeNode = ((TreeNode) object);
		return distance.compareTo(treeNode.distance);
	}

	public void setInfo(T info)
	// Sets info of this BSTNode.
	{
		this.info = info;
	}

	public T getInfo()
	// Returns info of this BSTNode.
	{
		return info;
	}

	public void setLeft(TreeNode<T> link)
	// Sets left link of this BSTNode.
	{
		left = link;
	}

	public void setRight(TreeNode<T> link)
	// Sets right link of this BSTNode.
	{
		right = link;
	}


	public TreeNode<T> getLeft()
	// Returns left link of this BSTNode.
	{
		return left;
	}

	public TreeNode<T> getRight()
	// Returns right link of this BSTNode.
	{
		return right;
	}


	//findClosest method instructions:
	/*findClosest(Node root, Object target) { 
	make new min-heap and insert root with distance 0. 
  	while min-heap is not empty {
    	q <- get min element 
    	while q not null, and q does not hold target: 
    		place q's children into heap, using distance from root as the sort key. 
  		}
	 */
	public static void findClosest(TreeNode root, Object target){

		BinaryHeap minHeap= new BinaryHeap();
		root.distance = (Double) 0.0;
		minHeap.insert(root);
		boolean isFound = false;

		while (!minHeap.isEmpty()){
			TreeNode q = (TreeNode) minHeap.deleteMin();

			if( q.info!=target && q!=null&& q.left != null || q.right != null){

				TreeNode childLeft = q.left;
				TreeNode childRight = q.right;

				childLeft.distance += q.distance;
				childRight.distance += q.distance;

				minHeap.insert(childLeft);
				minHeap.insert(childRight);

			}

			else if(q.info.equals(target)){
				System.out.println( "\""+target +"\""+ " at distance " + q.distance);
				isFound = true;
				break;
			}
			

		}
		
		if (isFound == false){
			System.out.println("Target not found");
		}

	}




	//method input directions:
	/*While hasNextToken {
		  temp <- getNextToken
		  if temp equals "("
		    do nothing
		  if temp equals ")"
		    pop off last two nodes (call them left_child, right_child)
		    pop off a third node (call it parent)
		    assign left_child, right_child as parent's children
		    push parent back onto the stack
		  else //we read in a node label
		    push a new TreeNode(label=temp, distance=getNextToken)
		}
	 */
	public static TreeNode input(String string) throws IOException {

		StringTokenizer tokenizer = new StringTokenizer(string);

		BoundedStackInterface<TreeNode> stack = new ArrayStack<TreeNode>();

		while (tokenizer.hasMoreTokens()){

			String temp = tokenizer.nextToken();

			if (temp.equals("(")){
			}

			else if (temp.equals(")")){

				TreeNode leftChild = stack.top();
				stack.pop();

				TreeNode rightChild = stack.top();
				stack.pop();

				TreeNode parent= stack.top();
				stack.pop();


				parent = new TreeNode(parent.info, parent.distance);
				parent.left = leftChild;
				parent.right = rightChild;

				stack.push(parent);

			}
			else {
				stack.push(new TreeNode(temp, Double.parseDouble(tokenizer.nextToken())));
			}
		}
		return stack.top();
	}

	public static String readContents(String filename) throws IOException{
		BufferedReader reader = new BufferedReader( new FileReader (filename));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		while( ( line = reader.readLine() ) != null ) {
			stringBuilder.append( line + '\n' );
		}

		return stringBuilder.toString();
	}

	public static void main(String[] args) throws IOException{
		Scanner in = new Scanner(System.in);

		System.out.println("Enter file name: ");
		String fileName = in.nextLine();

		String treeInput = readContents(args[0]);

		System.out.println("Enter target to find: ");
		String target = in.nextLine();

		TreeNode tree = input(treeInput); 	     
		TreeNode.findClosest(tree, target);
	}
}

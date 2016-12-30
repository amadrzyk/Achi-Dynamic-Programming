
public class isRegular {
	
	public static class Node {
		int val;
		Node left, right;
		Node(int x){
			val = x;
			left = null;
			right = null;
		}
	}
	
	public static boolean isRegular(Node r){
		
		boolean regular = false;
		// If 1 node
		if (r == null) return true;
		
		else {
			if (r.left != null){
				regular = isRegular(r.left);
			}
			
			if (r.right != null){
				regular = isRegular(r.right);
			}
			
			// VISIT R
			
			if (r.left == null && r.right == null){
				return true;
			}
			
			else if (r.left != null && r.right == null ||
					r.left == null && r.right != null){
				return false;
			}
	 		else {
				// Check if children's properties are the same on both sides
				if (r.left.left == null && r.left.right == null && 
						r.right.left == null && r.right.right == null){
					return true;
				} else if (r.left.left != null && r.left.right != null &&
							r.right.left != null && r.right.right != null){
					return true;		
				}
				else {
					return false;
				}
			}
		}
	}
	
	public static void main(String[] args){
		Node root = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		
		root.left = node2;
		root.right = node3;
		node3.left = node4;
		node3.right = node5;
		
		System.out.println(isRegular(root));
	}
}

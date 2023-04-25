
public class FlipTree {

	static TreeNode res;
	public Info flipTree(TreeNode root, int find) {
		if(null == root) {
			return new Info(false, null);
		}
		if(root.left == null && root.right == null) {
			if(root.val == find) {
				res = root;
			}
			return new Info(root.val == find, root);
		}
		Info left = flipTree(root.left, find);
		if(left.found) {
			TreeNode temp = root.left;
			TreeNode tempRight = temp.right;
			temp.right = root;
			root.left = null;
			if(tempRight != null) {
				temp.left = tempRight;
			}
			return new Info(true, root);
		} else {
			Info right = flipTree(root.right, find);
			if(right.found) {
				TreeNode temp = root.right;
				TreeNode tempLeft = temp.left;
				temp.left = root;
				root.right = null;
				if(tempLeft != null) {
					temp.right = tempLeft;
				}
				return new Info(true, root);
			}
		}
		return new Info(false, root);
	}

	public static void main(String[] args) {
		TreeNode node = new TreeNode(1);
		node.left = new TreeNode(2);
		node.right = new TreeNode(3);

		TreeNode temp = new TreeNode(6);
		node.right.right = temp;
		node.right.right.parent = node.right;

		node.left.left = new TreeNode(4);
		node.left.right = new TreeNode(5);


		//TreeNode temp = new TreeNode(7);
		node.left.left.left = new TreeNode(7);
		node.left.left.right = new TreeNode(8);

		//temp = new TreeNode(9);
		node.left.right.right = new TreeNode(9);

		FlipTree ft = new FlipTree();
		Info info = ft.flipTree(node, 6);
		inorder(res);
	}

	private static void inorder(TreeNode node) {
		if(node == null) {
			return;
		}
		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}

	static class TreeNode {
		int val;
		TreeNode left, right;
		TreeNode parent;
		TreeNode(int val) {
			this.val = val;
		}
	}
	class Info {
		boolean found;
		TreeNode node;
		Info(boolean found, TreeNode node) {
			this.found = found;
			this.node = node;
		}
	}
}
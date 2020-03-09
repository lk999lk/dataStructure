package tree.binarysorttree;

public class BinarySortTree {
	private Node root;

	public Node getRoot() {
		return root;
	}

	/**
	 * 查找要删除的节点
	 * @param value
	 * @return
	 */
	public Node search(int value){
		if (root == null){
			return null;
		} else{
			return root.search(value);
		}
	}

	/**
	 * 查找父节点
	 * @param value
	 * @return
	 */
	public Node searchParent(int value){
		if (root == null){
			return null;
		} else {
			return root.searchParent(value);
		}
	}

	/**
	 * 删除以node为根节点的二叉排序树的最小节点
	 * 并且返回最小节点的值
	 * @param node 传入的节点（当作二叉树的根节点）
	 * @return 返回以node为根节点的二叉排序树的最小节点的值
	 */
	public int delRightTreeMin(Node node){
		Node target = node;

		while (target.left != null){
			target = target.left;
		}
		//这时target就指向了最小节点
		//删除最小节点
		delNode(target.value);
		return target.value;
	}

	/**
	 * 删除节点
	 * @param value
	 */
	public void delNode(int value){
		if (root == null){
			return;
		}

		Node targetNode = search(value);
		//如果没有找到要删除的节点
		if (targetNode == null) {
			return;
		}

		//如果要删除的二叉树只有一个节点
		if (root.left == null || root.right == null){
			root = null;
			return;
		}

		Node parent = searchParent(value);
		//如果要删除的是叶子节点
		if(targetNode.left == null && targetNode.right == null){
			if (parent.left != null && parent.left.value == value){
				parent.left = null;
			} else if(parent.right != null && parent.right.value == value){
				parent.right = null;
			}
		} else if (targetNode.left != null && targetNode.right != null){//删除有两个子树的节点
			int minval = delRightTreeMin(targetNode.right);
			targetNode.value = minval;
		} else {//删除只有一颗子树的节点
			if (targetNode.left != null){//只有左节点
				if (parent != null){
					if (parent.left.value == value){
						parent.left = targetNode.left;
					}else{
						parent.right = targetNode.left;
					}
				} else{
					root = targetNode.left;
				}
			} else{//只有右节点
				if (parent != null){
					if (parent.left.value == value){
						parent.left = targetNode.right;
					}else{
						parent.right = targetNode.right;
					}
				} else{
					root = targetNode.right;
				}
			}
		}
	}

	/**
	 * 添加节点的方法
	 * @param node
	 */
	public void add(Node node){
		if (root == null){
			root = node;
		} else {
			root.add(node);
		}
	}

	/**
	 * 中序遍历
	 */
	public void infixOrder(){
		if(root!= null){
			root.infixOrder();
		} else {
			System.out.println("二叉树为空，不能遍历");
		}
	}
}

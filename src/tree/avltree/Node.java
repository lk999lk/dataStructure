package tree.avltree;

public class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	/**
	 * 返回左子树的高度
	 * @return
	 */
	public int leftHeight(){
		if (left == null){
			return 0;
		}
		return left.height();
	}

	/**
	 * 返回右子树的高度
	 * @return
	 */
	public int rightHeight(){
		if (right == null){
			return 0;
		}
		return right.height();
	}

	/**
	 * 返回以该节点为根节点的树的高度
	 * @return
	 */
	public int height(){
		return Math.max(left==null ? 0 : left.height(), right == null ? 0 : right.height()) +1;
	}

	/**
	 * 左旋转方法
	 */
	public void leftRotate(){
		//以当前根节点的值创建新的节点
		Node newNode = new Node(value);
		//把新的节点的左子树设置成当前节点的左子树
		newNode.left = left;
		//把新的节点的右子树设置成根节点的右子树的左子树
		newNode.right = right.left;
		//把当前节点的值替换成右子节点的值
		value = right.value;
		//把当前节点的右子树设置成当前节点右子树的右子树
		right = right.right;
		//把当前节点的左子树设置成新的节点
		left = newNode;
	}

	/**
	 * 右旋转
	 */
	public void rightRotate(){
		Node newNode = new Node(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}
	/**
	 * 查找要删除的节点
	 * @param value 希望删除节点的值
	 * @return 如果找到返回该节点，否则返回null
	 */
	public Node search(int value){
		if (value == this.value){//找到的就是该节点
			return this;
		} else if (value < this.value){//如果查找的值小于当前节点，向左子树递归查找
			if (this.left == null){
				return null;
			}
			return this.left.search(value);
		} else {//如果找到的值大于当前节点，向右子树递归查找‘
			if (this.right == null){
				return null;
			}
			return this.right.search(value);
		}
	}

	/**
	 * 查找要删除节点的父节点
	 * @param value 要找到节点的值
	 * @return 返回的是要删除节点的父节点，如果没有就返回null
	 */
	public Node searchParent(int value){
		if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
			return this;
		} else {
			if (value < this.value && this.left != null){
				return this.left.searchParent(value);
			} else if(value > this.value && this.right != null){
				return this.right.searchParent(value);
			} else {
				return null;
			}
		}
	}

	/**
	 * 添加节点的方法
	 * 递归的形式添加节点，注意需要满足二叉排序树的要求
	 * @param node 需要添加的节点
	 */
	public void add(Node node){
		if (node == null){
			return;
		}

		if (node.value < this.value){
			if (this.left == null){
				this.left = node;
			} else {
				this.left.add(node);
			}
		} else {
			if (this.right == null){
				this.right = node;
			} else {
				this.right.add(node);
			}
		}

		//当添加完一个节点后，如果(右子树的高度-左子树的高度)>1,左旋转
		if (rightHeight()-leftHeight() > 1){
			//如果它的右子树的左子树的高度大于它的右子树的右子树的高度
			if (right!=null && right.leftHeight()>right.rightHeight()){
				//先对右子树进行右旋转
				right.rightRotate();
				//对当前节点进行左旋转
				leftRotate();
			} else{
				leftRotate();
			}
			return;
		}

		//当添加完一个节点后，如果(左子树的高度-右子树的高度)>1,右旋转
		if (leftHeight()-rightHeight() > 1){
			if (left!=null && left.rightHeight()>left.leftHeight()){
				left.leftRotate();
				rightRotate();
			} else{
				rightRotate();
			}
		}
	}

	/**
	 * 中序遍历
	 */
	public void infixOrder() {
		if (this.left != null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null) {
			this.right.infixOrder();
		}
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
	}
}

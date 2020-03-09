package tree.binarysorttree;

/**
 * 创建Node节点
 */
public class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Node{" +
				"value=" + value +
				'}';
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
	}

	/**
	 * 中序遍历
	 */
	public void infixOrder(){
		if (this.left != null){
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right != null){
			this.right.infixOrder();
		}
	}
}

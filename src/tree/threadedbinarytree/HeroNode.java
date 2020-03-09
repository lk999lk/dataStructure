package tree.threadedbinarytree;

/**
 * 创建HeroNode节点
 */
public class HeroNode {
	private int no;
	private String name;
	private HeroNode left;//默认为null
	private HeroNode right;//默认为null

	private HeroNode parent;//父节点的指针，为了后序线索化使用

	/*
	如果 leftType==0 表示指向的是左子树，如果是1表示指向前驱结点
	如果 rightType==0 表示指向的是右子树，如果是1表示指向后继结点
	 */
	private int leftType;
	private int rightType;

	public HeroNode getParent() {
		return parent;
	}

	public void setParent(HeroNode parent) {
		this.parent = parent;
	}

	public int getLeftType() {
		return leftType;
	}

	public void setLeftType(int leftType) {
		this.leftType = leftType;
	}

	public int getRightType() {
		return rightType;
	}

	public void setRightType(int rightType) {
		this.rightType = rightType;
	}

	public HeroNode(int no, String name) {
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HeroNode getLeft() {
		return left;
	}

	public void setLeft(HeroNode left) {
		this.left = left;
	}

	public HeroNode getRight() {
		return right;
	}

	public void setRight(HeroNode right) {
		this.right = right;
	}

	@Override
	public String toString() {
		return "HeroNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				'}';
	}

	/**
	 * 续写前序遍历的方法
	 */
	public void preOrder(){
		System.out.println(this);//先输出父节点
		//递归向左子树前序遍历
		if (this.left != null){
			this.left.preOrder();
		}
		//递归向右子树前序遍历
		if (this.right != null){
			this.right.preOrder();
		}
	}

	/**
	 * 中序遍历
	 */
	public void infixOrder(){
		//递归向左子树中序遍历
		if (this.left != null){
			this.left.infixOrder();
		}
		//输出父节点
		System.out.println(this);
		//递归向右子树中序遍历
		if(this.right != null){
			this.right.infixOrder();
		}
	}

	/**
	 * 后续遍历
	 */
	public void postOrder(){
		if (this.left != null){
			this.left.postOrder();
		}
		if (this.right != null){
			this.right.postOrder();
		}
		System.out.println(this);
	}

	/**
	 * 前序遍历查找
	 * @param no 查找no
	 * @return 如果找到就返回该Node，如果没有找到就返回null
	 */
	public HeroNode preOrderSearch(int no){
		System.out.println("进入前序遍历");
		//比较当前节点是不是
		if (no == this.no){
			return this;
		}

		/*
		判断当前节点的左子节点是否为空，如果不为空，则递归前序查找
		如果左递归前序查找，找到节点，就返回
		 */
		HeroNode resNode = null;
		if (this.left != null){
			resNode = this.left.preOrderSearch(no);
		}

		if (resNode != null){//说明我们左子树找到
			return resNode;
		}

		/*
		左递归前序查找，找到节点，则返回，否则继续判断
		当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
		 */
		if (this.right != null){
			return this.right.preOrderSearch(no);
		}

		return resNode;
	}

	/**
	 * 中序遍历查找
	 * @param no
	 * @return
	 */
	public HeroNode infixOrderSearch(int no){
		HeroNode resNode = null;
		//判断当前节点的左子节点是否为空，如果不为空，则递归中序查找
		if (this.left != null) {
			resNode = this.left.infixOrderSearch(no);
		}
		if (resNode != null){//说明找到了
			return resNode;
		}

		System.out.println("进入中序查找");
		//如果左递归中序查找没有找到，就和当前节点作比较
		if (this.no == no) {

			return this;
		}

		//否则继续进行右递归中序查找
		if (this.right != null){
			resNode = this.right.infixOrderSearch(no);
		}
		return resNode;
	}

	/**
	 * 后序遍历查找
	 * @param no
	 * @return
	 */
	public HeroNode postOrderSearch(int no){
		HeroNode resNode = null;

		if (this.left != null) {
			resNode = this.left.postOrderSearch(no);
		}
		if (resNode != null){//说明找到了
			return resNode;
		}

		if (this.right != null){
			resNode = this.right.infixOrderSearch(no);
		}
		if (resNode != null){//说明找到了
			return resNode;
		}

		System.out.println("进入后序查找");

		if (this.no == no){
			return this;
		}
		return resNode;
	}

	/**
	 * 如果删除的节点是叶子节点，则删除该节点
	 * 如果山的节点是非叶子节点，则删除该子树
	 * @param no
	 */
	public void delNode(int no){
		/*
		1.因为二叉树是单向的，所以要判断当前节点的子节点是否需要删除，不能直接判断当前这个节点是不是需要删除的节点
		2.如果当前节点的左子节点不为空，并且左子节点就是要删除的节点，就将this.left = null,并且就返回（结束递归删除）
		3.如果当前节点的右子节点不为空，并且右子节点就是要删除的节点，就将this.right = null,并且就返回（结束递归删除）
		4.如果第2步和第3步没有删除节点，那么我们就需要向左子树进行递归删除
		5.如果第4步也没有删除节点，则应当向右子树进行递归删除
		 */
		//第2步
		if (this.left != null && this.left.no == no){
			this.left = null;
			return;
		}
		//第3步
		if (this.right != null && this.right.no == no){
			this.right = null;
			return;
		}
		//第4步
		if (this.left != null){
			this.left.delNode(no);
		}
		//第5步
		if (this.right != null){
			this.right.delNode(no);
		}

	}
}

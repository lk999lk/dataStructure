package tree.threadedbinarytree;

public class ThreadedBinaryTree {
	private HeroNode root;

	/*
	为了实现线索化，需要创建要给指向当前节点的前驱结点的指针
	在递归进行线索化时，pre总是保留前一个节点
	 */
	private HeroNode pre = null;

	public void setRoot(HeroNode root) {
		this.root = root;
	}

	/**
	 * 遍历中序线索化二叉树的方法
	 */
	public void threadedList_infix(){
		//定义一个变量，存储当前遍历的节点，从root开始
		HeroNode node = root;
		while (node != null){
			while (node.getLeftType() == 0){
				node = node.getLeft();
			}
			//打印当前这个节点
			System.out.println(node);
			//如果当前节点的右指针指向的是后继节点，就一直输出
			while (node.getRightType() == 1){
				//获取当前节点的后继节点
				node = node.getRight();
				System.out.println(node);
			}
			//替换这个遍历的节点
			node = node.getRight();
		}
	}

	/**
	 * 遍历前序线索化二叉树的方法
	 */
	public void threadedList_pre(){
		//定义一个变量，存储当前遍历的节点，从root开始
		HeroNode node = root;
		while (node != null){
			while ( node.getLeftType() == 0 ) {
				//如果有左节点，打印，并替换左节点
				System.out.println(node);
				node = node.getLeft();
			}
			//如果没有，打印并替换右节点
			System.out.println(node);
			//替换这个遍历的结点
			node = node.getRight();
		}
	}

	/**
	 * 遍历后序线索化二叉树的方法
	 */
	public void threadedList_post(){
		//定义一个变量，存储当前遍历的节点，从root开始
		HeroNode node = root;
		while (node != null && node.getLeftType()==0){
			node = node.getLeft();
		}
		while (node != null){
			//右节点是线索
			if (node.getRightType() == 1){
				System.out.println(node);
				pre = node;
				node = node.getRight();
			} else {
				//如果上个处理的节点是当前节点的右节点
				if (node.getRight() == pre){
					System.out.println(node);
					if (node == root){
						return;
					}
					pre = node;
					node = node.getParent();
				} else{
					node = node.getRight();
					while (node != null && node.getLeftType() == 0){
						node = node.getLeft();
					}
				}
			}
		}
	}

	/**
	 * 重写二叉树中序线索化的方法
	 */
	public void threadedNodes_infix(){
		pre = null;
		this.threadedNodes_infix(root);
	}

	/**
	 * 对二叉树进行中序线索化的方法
	 * @param node 当前需要线索化的节点
	 */
	public void threadedNodes_infix(HeroNode node){
		if (node == null){
			return;
		}
		//先线索化左子树
		threadedNodes_infix(node.getLeft());
		/*
		线索化当前节点
		 */
		if (node.getLeft() == null){
			//当前节点的左指针指向前驱结点，修改左指针类型
			node.setLeft(pre);
			node.setLeftType(1);
		}

		//处理后继节点
		if (pre != null && pre.getRight() == null){
			//让前驱节点的右指针指向当前节点，修改右指针类型
			pre.setRight(node);
			pre.setRightType(1);
		}
		//每处理一个节点后，让当前节点是下一个节点的前驱结点
		pre = node;

		//线索化右子树
		threadedNodes_infix(node.getRight());
	}

	/**
	 * 重写二叉树前序线索化的方法
	 */
	public void threadedNodes_pre(){
		pre = null;
		this.threadedNodes_pre(root);
	}

	/**
	 * 对二叉树进行前序线索化的方法
	 * @param node
	 */
	public void threadedNodes_pre(HeroNode node){
		if (node == null){
			return;
		}
		/*
		线索化当前节点
		 */
		if (node.getLeft() == null){
			//当前节点的左指针指向前驱结点，修改左指针类型
			node.setLeft(pre);
			node.setLeftType(1);
		}

		//处理后继节点
		if (pre != null && pre.getRight() == null){
			//让前驱节点的右指针指向当前节点，修改右指针类型
			pre.setRight(node);
			pre.setRightType(1);
		}

		//每处理一个节点后，让当前节点是下一个节点的前驱结点
		pre = node;

		//先线索化左子树
		if (node.getLeftType() == 0){
			threadedNodes_pre(node.getLeft());
		}

		//线索化右子树
		if (node.getRightType() == 0){
			threadedNodes_pre(node.getRight());
		}
	}

	/**
	 * 重写二叉树后序线索化的方法
	 */
	public void threadedNodes_post(){
		pre = null;
		this.threadedNodes_post(root);
	}

	/**
	 * 对二叉树进行后序线索化的方法
	 * @param node
	 */
	public void threadedNodes_post(HeroNode node){
		if (node == null){
			return;
		}

		//先线索化左子树
		threadedNodes_post(node.getLeft());
		threadedNodes_post(node.getRight());

		/*
		线索化当前节点
		 */
		if (node.getLeft() == null){
			//当前节点的左指针指向前驱结点，修改左指针类型
			node.setLeft(pre);
			node.setLeftType(1);
		}

		//处理后继节点
		if (pre != null && pre.getRight() == null){
			//让前驱节点的右指针指向当前节点，修改右指针类型
			pre.setRight(node);
			pre.setRightType(1);
		}

		//每处理一个节点后，让当前节点是下一个节点的前驱结点
		pre = node;

	}
}

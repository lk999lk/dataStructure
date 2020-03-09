package tree.arrbinarytree;


/**
 * 编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
 */
class ArrayBinaryTree{
	private int[] arr;//存储数据节点的数据

	public ArrayBinaryTree(int[] arr) {
		this.arr = arr;
	}

	/**
	 * 重载preOrder
	 */
	public void preOrder(){
		this.preOrder(0);
		System.out.println();
	}

	/**
	 * 重载infixOrder
	 */
	public void infixOrder(){
		this.infixOrder(0);
		System.out.println();
	}

	/**
	 * 重载postOrder
	 */
	public void postOrder(){
		this.postOrder(0);
	}

	/**
	 * 顺序存储二叉树的前序遍历
	 * @param index 数组的下标
	 */
	public void preOrder(int index){
		if(arr == null || arr.length == 0){
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}
		System.out.printf("%d\t",arr[index]);

		//向左递归遍历
		if ((index*2+1) < arr.length){
			preOrder(2*index+1);
		}

		//向右递归遍历
		if ((index*2+2) < arr.length){
			preOrder(2*index+2);
		}
	}

	/**
	 * 顺序存储二叉树的中序遍历
	 * @param index 数组的下标
	 */
	public void infixOrder(int index){
		if(arr == null || arr.length == 0){
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}

		//向左递归遍历
		if ((index*2+1) < arr.length){
			infixOrder(2*index+1);
		}
		System.out.printf("%d\t",arr[index]);

		//向右递归遍历
		if ((index*2+2) < arr.length){
			infixOrder(2*index+2);
		}
	}

	/**
	 * 顺序存储二叉树的后序遍历
	 * @param index 数组的下标
	 */
	public void postOrder(int index){
		if(arr == null || arr.length == 0){
			System.out.println("数组为空，不能按照二叉树的前序遍历");
		}

		//向左递归遍历
		if ((index*2+1) < arr.length){
			postOrder(2*index+1);
		}

		//向右递归遍历
		if ((index*2+2) < arr.length){
			postOrder(2*index+2);
		}
		System.out.printf("%d\t",arr[index]);
	}
}
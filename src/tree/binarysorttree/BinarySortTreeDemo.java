package tree.binarysorttree;

public class BinarySortTreeDemo {
	public static void main(String[] args) {
		int[] arr = {7,3,10,12,5,1,9,2};

		BinarySortTree binarySortTree = new BinarySortTree();
		//循环的添加节点到二叉排序树
		for (int i = 0; i < arr.length; i++) {
			binarySortTree.add(new Node(arr[i]));
		}//7 3 10 1 5 9 12 2

		System.out.println("============= 中序遍历 ===========");
		binarySortTree.infixOrder(); // 1 2 3 5 7 9 10 12

		//测试一下删除叶子节点
		binarySortTree.delNode(12);

		System.out.println("======= 删除节点后 ===========");
		binarySortTree.infixOrder();//1 2 3 5 7 9 10

		binarySortTree.delNode(3);
		binarySortTree.infixOrder();
	}
}

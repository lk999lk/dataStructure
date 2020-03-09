package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
	public static void main(String[] args) {
		HeroNode root = new HeroNode(1, "一");
		HeroNode node2 = new HeroNode(2, "三");
		HeroNode node3 = new HeroNode(3, "六");
		HeroNode node4 = new HeroNode(4, "八");
		HeroNode node5 = new HeroNode(5, "十");
		HeroNode node6 = new HeroNode(6, "十四");

		root.setLeft(node2);
		root.setRight(node3);
		node2.setRight(node4);
		node3.setLeft(node5);
		node3.setRight(node6);

		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);

		//线索化以后不能使用原来的遍历方法，会陷入死循环
//		threadedBinaryTree.infixOrder();

//		System.out.println("----- 进行中序线索化  -----");
//		threadedBinaryTree.threadedNodes_infix();
//		System.out.println("===========使用线索化的方式遍历中序线索二叉树===========");
//		threadedBinaryTree.threadedList_infix();//425163

//		System.out.println("--- 进行前序线索化 ---");
//		threadedBinaryTree.threadedNodes_pre();
//		System.out.println("===========使用线索化的方式遍历前序线索二叉树===========");
//		threadedBinaryTree.threadedList_pre();//124356

		node2.setParent(root);
		node3.setParent(root);
		node4.setParent(node2);
		node5.setParent(node3);
		node6.setParent(node3);

		System.out.println("--- 进行后序线索化 ---");
		threadedBinaryTree.threadedNodes_post();
		System.out.println("===========使用线索化的方式遍历后序线索二叉树===========");
		threadedBinaryTree.threadedList_post();//425631
	}
}

package tree.binarytree;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		//先需要创建一颗二叉树
		BinaryTree binaryTree = new BinaryTree();
		//创建需要的节点
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "关胜");

		//说明，我们先手动创建该二叉树
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);

		binaryTree.setRoot(root);

		System.out.println("=======前序遍历========");
		binaryTree.preOrder();//12354

		System.out.println("=========中序遍历=======");
		binaryTree.infixOrder();//21534

		System.out.println("=======后续遍历========");
		binaryTree.postOrder();//25431

		//前序遍历查找: 4次
		System.out.println("===============前序遍历查找=============");
		HeroNode heroNode = binaryTree.preOrderSearch(5);
		if (heroNode != null){
			System.out.println("找到了，信息为 no = "+heroNode.getNo()+", name = "+heroNode.getName());
		} else {
			System.out.println("没有找到");
		}

		//中序遍历查找：3次
		System.out.println("===============中序遍历查找=============");
		HeroNode heroNode2 = binaryTree.infixOrderSearch(5);
		if (heroNode != null){
			System.out.println("找到了，信息为 no = "+heroNode2.getNo()+", name = "+heroNode2.getName());
		} else {
			System.out.println("没有找到");
		}

		//后序遍历查找：2次
		System.out.println("===============后序遍历查找=============");
		HeroNode heroNode3 = binaryTree.postOrderSearch(5);
		if (heroNode != null){
			System.out.println("找到了，信息为 no = "+heroNode3.getNo()+", name = "+heroNode3.getName());
		} else {
			System.out.println("没有找到");
		}

		System.out.println("==============删除节点=================");
		binaryTree.delNode(5);
		binaryTree.preOrder();

		binaryTree.delNode(2);
		binaryTree.preOrder();

	}
}

package linkedlist;

public class SingleLinkedListDemo {
	public static void main(String[] args) {
		//先创建节点
		HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
		HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
		HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
		HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

		//加入链表
		SingleLinkedList singleLinkedList = new SingleLinkedList();
//		singleLinkedList.add(hero1);
//		singleLinkedList.add(hero4);
//		singleLinkedList.add(hero2);
//		singleLinkedList.add(hero3);

		//按照编号的顺序加入
		singleLinkedList.addByOrder(hero3);
		singleLinkedList.addByOrder(hero2);
		singleLinkedList.addByOrder(hero1);
		singleLinkedList.addByOrder(hero4);

		//显示
		singleLinkedList.list();

		//测试修改节点的代码
		HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟---");
		singleLinkedList.update(newHeroNode);

		System.out.println("----修改后的链表情况-----");
		singleLinkedList.list();

		System.out.println("----返回链表中倒数第2个节点-----");
		System.out.println(SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 2));

		System.out.println("----反转链表----");
		SingleLinkedList.reverseList(singleLinkedList.getHead());
		singleLinkedList.list();

		System.out.println("-------单链表的逆序打印-------");
		SingleLinkedList.reversePrint(singleLinkedList.getHead());

		//删除一个节点
		singleLinkedList.del(1);
		singleLinkedList.del(3);
		System.out.println("-------删除节点后的链表情况-----------");
		singleLinkedList.list();

		System.out.println("-----有效节点的个数------");
		System.out.println(SingleLinkedList.getLength(singleLinkedList.getHead()));
	}
}



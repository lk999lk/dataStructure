package linkedlist;

import java.util.Stack;

/*
定义SingleLinkedList管理水浒英雄
 */
class SingleLinkedList{
	//先初始化一个头节点，头节点不要动，不存放具体的数据
	private HeroNode head = new HeroNode(0, null,null);

	public HeroNode getHead() {
		return head;
	}

	/*
		添加节点到单向链表
		第一种：当不考虑编号顺序时：
			1.找到当前编号的最后节点
			2.将最后这个节点的next指向新的节点
		 */
	public void add(HeroNode heroNode){
		//因为head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode temp = head;

		//遍历链表，找到最后
		while (true){
			if (temp.next == null){
				break;
			}

			temp=temp.next;
		}

		//当退出while循环时，temp就指向了链表的最后
		//将最后这个节点的next指向新的节点
		temp.next=heroNode;
	}

	/*
	第二种方式在添加英雄时，根据排名将英雄插入到指定位置
	如果有这个排行，则添加失败
	 */
	public void addByOrder(HeroNode heroNode){
		/*
		因为头节点不能动，通过一个辅助指针来帮助找到添加的位置
		temp位于添加位置的前一个节点
		 */
		HeroNode temp = head;
		boolean flag = false;//flag标志添加的编号是否存在，默认为false
		while (true){
			if (temp.next == null)//说明temp已经在链表的最后
				break;
			if (temp.next.no > heroNode.no){
				//位置找到，就在temp的后面插入
				break;
			} else if (temp.next.no == heroNode.no){//说明编号已经存在
				flag = true;
				break;
			}
			temp = temp.next;//后移，遍历当前链表
		}

		//判断flag的值
		if (flag){//不能添加，说明编号存在
			System.out.println("准备插入的英雄的编号 %d 已经存在了，不能插入" + heroNode.no);
		}else {
			//插入到链表中，temp的后面
			heroNode.next=temp.next;
			temp.next=heroNode;
		}
	}

	//修改节点的信息，根据no编号来修改，即no编号不能改
	public void update(HeroNode newHeroNode){
		if (head.next == null){
			System.out.println("链表为空--");
			return;
		}

		/*
		找到需要的节点，根据no编号
		 */
		HeroNode temp = head.next;
		boolean flag = false;

		while (true){
			if (temp==null){
				break;//已经遍历完链表
			}
			if (temp.no == newHeroNode.no){//找到了
				flag = true;
				break;
			}
			temp = temp.next;
		}

		//根据flag判断是否找到要修改的节点
		if (flag){
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else{
			System.out.println("没有找到编号为 %d 的节点，不能修改" + newHeroNode.no);
		}
	}

	//删除节点
	/*
	head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
	说明在比较时，是 temp.next.no 和需要删除节点的 no 比较
	 */
	public void del(int no){
		HeroNode temp = head;
		boolean flag = false;//标志是否找到待删除的节点
		while (true){
			if (temp.next == null){//已经到链表的最后
				break;
			}
			if (temp.next.no == no){//找到的待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;//temp后移，遍历
		}
		if (flag){
			temp.next = temp.next.next;
		}else{
			System.out.println("要删除的 %d 节点不存在" + no);
		}

	}

	//显示链表（遍历）
	public void list(){
		if (head.next == null){
			System.out.println("链表为空");
			return;
		}
		HeroNode temp = head.next;
		while (true){
			if (temp == null){
				break;
			}
			System.out.println(temp);
			temp = temp.next;
		}
	}

	/**
	 * 获取到单链表的节点的个数，如果是带头节点的链表，需求不统计节点
	 * @param head 链表的头节点
	 * @return 返回有效节点的个数
	 */
	public static int getLength(HeroNode head){
		if (head.next == null){//空链表
			return 0;
		}
		int length = 0;
		//定义一个辅助的变量，这里我们没有统计头节点
		HeroNode cur = head.next;
		while (cur != null){
			length++;
			cur=cur.next;
		}
		return length;
	}

	/**
	 * 查找单链表中的倒数第k个节点
	 * @param head
	 * @param index
	 * @return
	 */
	public static HeroNode findLastIndexNode(HeroNode head,int index){
		if (head.next == null){//链表为空
			return null;
		}
		//第一次遍历得到链表的长度（节点个数）
		int size = getLength(head);
		//第二次遍历 size-index位置，就是我们倒数的第K个节点
		//先做一个index的校验
		if (index <= 0 || index > size){
			return null;
		}

		//定义给辅助变量， for循环定位到倒数的index
		HeroNode cur = head.next;
		for (int i = 0; i < size-index; i++) {
			cur = cur.next;
		}
		return cur;
	}

	/**
	 * 单链表的反转
	 * @param head
	 */
	public static void reverseList(HeroNode head){
		//如果当前链表为空，或者只有一个节点，无需反转，直接返回
		if (head.next == null || head.next.next == null){
			return;
		}

		//定义一个辅助的指针，帮助我们遍历原来的链表
		HeroNode cur = head.next;
		HeroNode next = null;//指向当前节点cur的下一个节点
		HeroNode reverseHead = new HeroNode(0,null,null);
		//遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
		while (cur != null){
			next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
			cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
			reverseHead.next = cur;//将cur连接到新的链表上
			cur = next;//让cur后移
		}
		//将head.next指向reverseHead.next,实现单链表的反转
		head.next = reverseHead.next;
	}

	public static void reversePrint(HeroNode head){
		if (head.next == null){
			return;//空链表，不能打印
		}

		//创建一个栈，将各个节点压入栈
		Stack<HeroNode> stack = new Stack<>();
		HeroNode cur = head.next;
		//将链表的所有节点压入栈
		while (cur != null){
			stack.push(cur);
			cur = cur.next;//cur后移，这样就可以压入下一个节点
		}
		//将栈中的节点进行打印，pop出栈
		while (stack.size()>0){
			System.out.println(stack.pop());
		}
	}
}

/*
定义HeroNode,每个HeroNode对象就是一个节点
 */
class HeroNode{
	public int no;
	public String name;
	public String nickname;
	public HeroNode next;//指向下一个节点

	public HeroNode(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickname='" + nickname +"'}";
	}
}


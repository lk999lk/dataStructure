package linkedlist;

public class DoubleLinkedList {
	//先初始化一个头节点，头节点不要动，不存放具体的数据
	private HeroNode2 head = new HeroNode2(0,null,null);

	//返回头节点
	public HeroNode2 getHead(){
		return head;
	}

	//遍历双面链表的方法
	//显示链表[遍历]
	public void list(){
		//判断链表是否为空
		if (head.next == null){
			System.out.println("链表为空");
			return;
		}
		//因为头节点，不能动，因此我们需要一个辅助变量来遍历
		HeroNode2 temp = head.next;
		while (true){
			//判断是否到链表最后
			if (temp == null){
				break;
			}
			//输出节点的信息
			System.out.println(temp);
			//将temp后移，一定小心
			temp = temp.next;
		}
	}

	//添加一个节点到双向链表的最后
	public void add(HeroNode2 heroNode){
		//因为head节点不能动，因此我们需要一个辅助遍历temp
		HeroNode2 temp = head;
		//遍历链表，找到最后
		while (true){
			//找到链表的最后
			if (temp.next == null){
				break;
			}
			//如果没有找到最后，将temp后移
			temp = temp.next;
		}
		//当退出while循环时，temp就指向了链表的最后
		//形成了一个双向链表
		temp.next = heroNode;
		heroNode.pre = temp;
	}

	/**
	 * 	当退出一个节点的内容，可以看到双向链表的节点内容修改和单向链表一样
	 * 	只是节点类型改成HeroNode2
	 * @param newHeroNode
	 */
	public void update(HeroNode2 newHeroNode){
		//判断是否为空
		if (head.next == null){
			System.out.println("链表为空--");
			return;
		}
		//找到需要修改的节点，根据no编号
		//定义一个辅助变量
		HeroNode2 temp = head.next;
		boolean flag = false;//表示是否找到该节点
		while (true){
			if (temp == null){
				break;//已经遍历完链表
			}
			if (temp.no == newHeroNode.no){
				//找到
				flag = true;
				break;
			}
			temp = temp.next;
		}

		//根据flag判断是否找到修改的节点
		if (flag){
			temp.name = newHeroNode.name;
			temp.nickname = newHeroNode.nickname;
		} else{//没有找到
			System.out.println("没有找到编号为 %d 的节点，不能修改\n" + newHeroNode.no);
		}
	}

	/**
	 * 从双向链表中删除一个节点
	 * 我们可以直接找到要删除的这个节点，找到后，自我删除即可。
	 * @param no 要删除的节点
	 */
	public void del(int no){
		//判断当前链表是否为空
		if (head.next == null){//空链表
			System.out.println("链表为空，无法删除");
			return;
		}
		HeroNode2 temp = head.next;//辅助变量
		boolean flag = false;//标志是否找到待删除节点的
		while (true){
			if (temp == null){//已经到链表的最后
				break;
			}
			if (temp.no == no){
				//找到待删除节点的前一个节点temp
				flag = true;
				break;
			}
			temp = temp.next;//temp后移，遍历
		}

		//判断flag
		if (flag){//找到
			//可以删除
			//temp.next = temp.next.next;//单向链表
			temp.pre.next = temp.next;

			//如果时最后一个节点，就不需要执行下面这句话，否则会出现空指针
			if (temp.next != null){
				temp.next.pre = temp.pre;
			}
		}else{
			System.out.println("要删除的 %d 节点不存在\n" + no);
		}
	}



}


/**
 * 定义HeroNode2，每个HeroNode对象就是一个节点
 */
class HeroNode2{
	public int no;
	public String name;
	public String nickname;
	public HeroNode2 next;//指向下一个节点，默认是null
	public HeroNode2 pre;//指向前一个节点，默认是null

	public HeroNode2(int no, String name, String nickname) {
		this.no = no;
		this.name = name;
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "HeroNode2{" +
				"no=" + no +
				", name='" + name + '\'' +
				", nickname='" + nickname +	'}';
	}
}
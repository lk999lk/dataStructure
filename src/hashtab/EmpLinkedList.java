package hashtab;

/**
 * 创建EmpLinkedList，表示链表
 */
public class EmpLinkedList {

	//头指针。执行第一个Emp，因此我们这个链表的head是直接指向第一个Emp
	private Emp head;//默认为null

	/**
	 * 添加雇员到链表
	 * 假定雇员的id是自增长的，因此我们将该雇员直接加入到本链表的最后即可
	 * @param emp
	 */
	public void add(Emp emp){
		if (head == null){//如果是第一个雇员
			head = emp;
			return;
		}

		//如果不是第一个，则使用一个辅助的指针，帮助定位到最后
		Emp curEmp = head;
		while (true){
			if (curEmp.next == null){//说明到了链表最后
				break;
			}
			curEmp = curEmp.next;//后移
		}
		//退出时直接将emp加入链表
		curEmp.next = emp;
	}

	/**
	 * 遍历链表的雇员信息
	 * @param no
	 */
	public void list(int no){
		if (head == null){//说明链表为空
			System.out.println("第 " +(no+1)+ " 链表为空");
			return;
		}

		System.out.println("第 " +(no+1)+ " 链表的信息为： ");
		Emp curEmp = head;//辅助指针
		while (true){
			System.out.println("id="+curEmp.id+", name="+ curEmp.name);
			if (curEmp.next == null){//说明curEmp已经是最后节点
				break;
			}
			curEmp = curEmp.next;//后移，遍历
		}
	}

	/**
	 * 根据id查找雇员
	 * @param id
	 * @return 如果找到，就返回Emp，如果没有找到，就返回null
	 */
	public Emp findEmpById(int id){
		if (head == null){
			System.out.println("链表为空");
			return null;
		}

		Emp curEmp = head;//辅助指针
		while (true){
			if (curEmp.id == id){//找到
				break;//这时curEmp就指向要查找的雇员
			}
			if (curEmp.next == null){//说明遍历当前链表没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;//后移
		}
		return curEmp;
	}

}

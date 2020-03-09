package hashtab;

/**
 * 创建HashTab管理多条链表
 */
public class HashTab {
	private EmpLinkedList[] empLinkedLists;
	private int size;//表示有多少条链表

	/**
	 * 构造器
	 * @param size
	 */
	public HashTab(int size) {
		this.size = size;
		//初始化empLinkedLists
		empLinkedLists = new EmpLinkedList[size];
		//?这里不要分别初始化每个链表
		for (int i = 0; i < size; i++) {
			empLinkedLists[i] = new EmpLinkedList();
		}
	}

	/**
	 * 添加雇员
	 * @param emp
	 */
	public void add(Emp emp){
		//根据员工的id，得到该员工应当添加到哪条链表
		int i = hashFun(emp.id);
		//将emp添加到对应的链表中
		empLinkedLists[i].add(emp);
	}

	public void list(){
		for (int i = 0; i < size; i++) {
			empLinkedLists[i].list(i);
		}
	}

	/**
	 * 根据id查找雇员
	 * @param id
	 */
	public void findEmpById(int id){
		//使用散列函数确定到哪条链表查找
		int no = hashFun(id);
		Emp emp = empLinkedLists[no].findEmpById(id);
		if (emp != null){//找到
			System.out.printf("在第 %d 条链表中找到 雇员 id = %d \n ",(no+1), id);
		} else {
			System.out.println("在哈希表中，没有找到该雇员");
		}

	}


	//编写散列函数，使用一个简单取模法
	public int hashFun(int id){
		return id % size;
	}
}

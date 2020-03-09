package linkedlist;

public class Josepfu {
	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(5);
		circleSingleLinkedList.showBoy();

		//测试小孩出圈是否正确
		circleSingleLinkedList.countBoy(1,2,5);
	}
}

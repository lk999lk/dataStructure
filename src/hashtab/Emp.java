package hashtab;

/**
 * 表示一个雇员
 */
public class Emp {
	public int id;
	public String name;
	public Emp next;//next默认为null

	public Emp(int id, String name){
		super();
		this.id = id;
		this.name = name;
	}
}

package tree.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

	/**
	 * 完成对压缩文件的解压
	 *
	 * @param zipFile 准备解压的文件
	 * @param dstFile  解压后文件存放的路径
	 */
	public static void unZipFile(String zipFile, String dstFile) {

		//定义文件输入流
		InputStream is = null;
		//定义一个对象输入流
		ObjectInputStream ois = null;
		//定义文件的输出流
		OutputStream os = null;
		try {
			//创建文件输入流
			is = new FileInputStream(zipFile);
			//创建一个和  is关联的对象输入流
			ois = new ObjectInputStream(is);
			//读取byte数组  huffmanBytes
			byte[] huffmanBytes = (byte[])ois.readObject();
			//读取赫夫曼编码表
			Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

			//解码
			byte[] bytes = decode(huffmanCodes, huffmanBytes);
			//将bytes 数组写入到目标文件
			os = new FileOutputStream(dstFile);
			//写数据到 dstFile 文件
			os.write(bytes);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {

			try {
				os.close();
				ois.close();
				is.close();
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}

		}
	}

	/**
	 * 将一个文件进行压缩
	 * @param srcFile 需要压缩的文件的全路径
	 * @param dstFile 压缩后文件需要存放的目录
	 */
	public static void zipFile(String srcFile, String dstFile) {

		//创建输出流
		OutputStream os = null;
		ObjectOutputStream oos = null;
		//创建文件的输入流
		FileInputStream is = null;
		try {
			//创建文件的输入流
			is = new FileInputStream(srcFile);
			//创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			//读取文件
			is.read(b);
			//直接对源文件压缩
			byte[] huffmanBytes = huffmanZip(b);
			//创建文件的输出流, 存放压缩文件
			os = new FileOutputStream(dstFile);
			//创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			//把 赫夫曼编码后的字节数组写入压缩文件
			oos.writeObject(huffmanBytes); //我们是把
			//这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
			//注意一定要把赫夫曼编码 写入压缩文件
			oos.writeObject(huffmanCodes);


		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				is.close();
				oos.close();
				os.close();
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}

	}
	/**
	 * 完成对压缩数据的解码
	 *
	 * @param huffmanCodes 赫夫曼编码表Map
	 * @param huffmanBytes 赫夫曼编码压缩得到的字节数组
	 * @return 原来的字符串对应的数组
	 */
	public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}

		/*
		把字符串按照指定的赫夫曼编码进行解码
		把赫夫曼编码进行调换，因为反向查询 根据二进制查询字符
		 */
		Map<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}

		//创建要给集合，存放byte
		List<Byte> list = new ArrayList<>();
		for (int i = 0; i < stringBuilder.length(); ) {
			int count = 1;
			boolean flag = true;
			Byte b = null;
			while (flag) {
				//递增的取出key
				String key = stringBuilder.substring(i, i + count);
				//i不动，让count移动，指定匹配一个字符
				b = map.get(key);
				if (b == null) { //说明没有匹配
					count++;
				} else {//匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count;//i向后移动count
		}

		/*
		当for循环结束后，list中就存放了所有的字符
		把list中的数据放入到byte[]并返回
		 */
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	/**
	 * 将一个byte转成一个二进制的字符串
	 *
	 * @param flag 标志是否需要补高位，如果是true，表示要补高位，如果是false表示不补。如果是最有一个字节，无需补高位
	 * @param b    传入的byte
	 * @return 按补码返回该b对应的二进制的字符串
	 */
	public static String byteToBitString(boolean flag, byte b) {
		int temp = b;
		//如果是正数，我们还需要补高位
		if (flag) {
			temp |= 256; //按位与256   1 0000 0000 | 0000 0001 ==> 1 0000 0001
		}
		String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}


	/**
	 * 重写 zip 方法
	 * 字符串对应的数组转换为压缩后的数组
	 *
	 * @param bytes 原始的字符串对应的字节数组
	 * @return 经过赫夫曼编码处理后的字节数组(压缩后的数组)
	 */
	public static byte[] huffmanZip(byte[] bytes) {

		//统计字符出现的次数，并存入List
		List<Node> nodes = getNodes(bytes);
		//根据nodes创建赫夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		//根据赫夫曼树得到对应的赫夫曼编码
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		//根据生成的赫夫曼编码。压缩得到压缩后的赫夫曼编码字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;

	}

	/**
	 * 将字符串对应的的byte[]数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
	 *
	 * @param bytes        原始的字符串对应的byte[]
	 * @param huffmanCodes 生成的赫夫曼编码Map
	 * @return 返回赫夫曼编码处理后的byte[]
	 */
	public static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		//利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历bytes数组
		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}

		int len;
		if (stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}

		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;//记录是第几个byte
		for (int i = 0; i < stringBuilder.length(); i += 8) {
			String strByte;
			if (i + 8 > stringBuilder.length()) {//不够8位
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}

			//将strByte转成一个byte，放入到huffmanCodeBytes
			//parseInt(1010,2) 意思就是：输出2进制数1010在十进制下的数.
			huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
			index++;
		}

		return huffmanCodeBytes;
	}

	/*
	生成赫夫曼对应的赫夫曼编码
	将赫夫曼编码表存放在Map<Byte,String>形式
	在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder存储某个叶子节点的路径
	 */
	static Map<Byte, String> huffmanCodes = new HashMap<>();
	static StringBuilder stringBuilder = new StringBuilder();

	/**
	 * 重载getCodes方法
	 *
	 * @param root
	 * @return
	 */
	public static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		getCodes(root.left, "0", stringBuilder);
		getCodes(root.right, "1", stringBuilder);

		return huffmanCodes;
	}

	/**
	 * 将传入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
	 *
	 * @param node          传入节点
	 * @param code          路径：左子节点是0，右子节点是1
	 * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

		//将code加入到stringBuilder2
		stringBuilder2.append(code);
		if (node != null) {
			if (node.data == null) {//非叶子节点
				getCodes(node.left, "0", stringBuilder2);
				getCodes(node.right, "1", stringBuilder2);
			} else {//说明是一个叶子节点
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}

	/**
	 * 前序遍历
	 *
	 * @param root
	 */
	public static void preOrder(Node root) {
		if (root != null) {
			root.preOrder();
		} else {
			System.out.println("赫夫曼树为空");
		}
	}


	/**
	 * 将字符和出现的次数以键值对的形式加入List<Node>
	 *
	 * @param bytes 要处理的字符数组
	 * @return 返回字符和次数的List<Node>
	 */
	public static List<Node> getNodes(byte[] bytes) {
		ArrayList<Node> nodes = new ArrayList<>();
		//遍历bytes，统计每一个byte出现的次数
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {//Map还没有这个字符数据，第一次
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}

		//把每一个键值对转成一个 Node 对象，并加入到 nodes 集合
		//遍历map
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	/**
	 * 通过List创建赫夫曼树
	 *
	 * @param nodes 需要创建成哈夫曼树的List
	 * @return 创建好后的赫夫曼树的root节点
	 */
	public static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);

			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;

			nodes.remove(leftNode);
			nodes.remove(rightNode);

			nodes.add(parent);
		}
		return nodes.get(0);
	}


}

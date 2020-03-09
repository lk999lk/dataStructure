package tree.huffmancode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;

public class HuffmanCodeDemo {
	public static void main(String[] args) {

		String content = "i like like java do you like a java ?";
		byte[] contentBytes = content.getBytes();
		System.out.println(Arrays.toString(contentBytes));
		System.out.println(contentBytes.length);

		//如何将数据解压
		System.out.println("=============== 字符及其个数对应的List==========");
		List<Node> nodes = HuffmanCode.getNodes(contentBytes);
		System.out.println("node="+nodes);
		//创建的赫夫曼树
		System.out.println("============== 赫夫曼树 ===========");
		Node huffmanTree = HuffmanCode.createHuffmanTree(nodes);
		System.out.println("前序遍历");
		huffmanTree.preOrder();

		//赫夫曼编码
		System.out.println("\n============== 赫夫曼编码 ===========");
		Map<Byte, String> huffmanCodes = HuffmanCode.getCodes(huffmanTree);
		System.out.println(huffmanCodes);
		//压缩
		System.out.println("============== 进行压缩 ===========");
		byte[] huffmanCodeBytes = HuffmanCode.zip(contentBytes, huffmanCodes);
		System.out.println(Arrays.toString(huffmanCodeBytes));

		System.out.println("=========== 压缩后的字符数组  ========");
		byte[] codesBytes = HuffmanCode.huffmanZip(contentBytes);
		System.out.println(Arrays.toString(codesBytes));
		System.out.println(codesBytes.length);

		System.out.println("============== 解压 ===============");
		byte[] sourceBytes = HuffmanCode.decode(HuffmanCode.huffmanCodes, codesBytes);
		System.out.println("原来的字符串为："+new String(sourceBytes));


		System.out.println("======== 压缩文件与解压 ===========");
		String srcFile = "C:\\Users\\lk\\Desktop\\src.png";
		String dstFile = "C:\\Users\\lk\\Desktop\\src.zip";
		HuffmanCode.zipFile(srcFile,dstFile);
		System.out.println("压缩成功");

		String zipFile = "C:\\Users\\lk\\Desktop\\txt.zip";
		String unzipFile = "C:\\Users\\lk\\Desktop\\unzip.txt";
		HuffmanCode.unZipFile(zipFile,unzipFile);
		System.out.println("解压成功");




	}
}

package wechat;

import java.io.File;

public class SearchFile {

	public static void main(String[] args) {
		String path = "D:/hg-env7/workspace/FundReconConsole_20170331/WebContent";
		getFiles(path);
		
	}
	
	public static void getFiles(String path){
		File file = new File(path);
		File[] tempList = file.listFiles();
		System.out.println(path + "目录下对象个数：" + tempList.length);
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				// 读取某个文件夹下的所有文件
				//System.out.println("文件：" + tempList[i]);
			}
			if (tempList[i].isDirectory()) {
				// 读取某个文件夹下的所有文件夹
				System.out.println("文件夹：" + tempList[i]);
				getFiles(tempList[i].toString());
			}
		}
		
	}
}

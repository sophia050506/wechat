package wechat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipTest {

	public static void main(String[] args) {
		String line = "1|2|3|4|5|6|7|8|9|10|11|||";
//		String lines = line.replaceAll("\\.00000","").replaceAll("\\s{2,}", " ").replaceAll(" ", "|");
//		System.out.println(lines);
		
		
		String[] lines = line.split("\\|");
		System.out.println(lines.length);
		for(String c:lines){
			
			System.out.println(c);
		}
		//System.out.println(Long.parseLong("000014800000")*100);
		
		/*int s=239  ;
	    int p =187 ;
	    System.out.println( p	);
        System.out.println( s	);
        System.out.println(s<<8);
        System.out.println( (s<<8)+p	);
        System.out.println( 0xefbb	);*/
		
		
//		DecimalFormat myformat = new DecimalFormat("0.00");
//	    
//        String money = myformat.format(1234567890.02).replace(".", "");
//        System.out.println(money);
		
		/*try {
			readZipFile("G:\\test.zip");
			//printZipTxt("G:\\test.zip");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}

	public static void readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			if (ze.isDirectory()) {
			} else {
				System.err.println("file - " + ze.getName() + " : " + ze.getSize() + " bytes");
				long size = ze.getSize();
				if (size > 0) {
					BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze),"UTF-8"));
					String line;
					while ((line = br.readLine()) != null) {
						System.out.println(line);
					}
					br.close();
				}
				System.out.println();
			}
		}
		zin.closeEntry();
	}

	public static void printZipTxt(String zipPath) throws IOException {
		ZipFile zipFile = new ZipFile(zipPath);
		for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements();) {
			ZipEntry entry = e.nextElement();
			System.out.println("文件名:" +zipPath+ entry.getName() + ", 内容如下:");
			if (entry.getName().toLowerCase().endsWith(".txt")) {
				InputStream is = null;
				is = zipFile.getInputStream(entry);
				byte[] b = new byte[1024];
				int leng = -1;
				String txtStr = "";
				while ((leng = is.read(b)) != -1) {
					txtStr += new String(b, 0, leng);
				}
				System.out.println(txtStr);
				if (is != null) {
					is.close();
				}
			}
		}
	}

}

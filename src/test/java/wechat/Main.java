package wechat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
	//地址
	private static final String URL = "http://www.tooopen.com/view/1439719.html";
	
	private static final String WECHAT_URL = "http://mp.weixin.qq.com/s/E5UIYVPxb8f2fGNMGVOqxw";
	
	// 获取img标签正则
    private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
    
 // 获取src路径的正则
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";
    
    
    
    public static void main(String[] args) {
    	 Document doc;
    	 try {
			doc = Jsoup.connect(WECHAT_URL).get();
			Elements list = doc.select("a[href]");
			for(Element a : list){
			
				Elements imgList = a.getElementsByTag("a");
				for(Element e : imgList){
					 String linkHref = e.attr("href");
				 //String linkName = e.attr("alt");
				     String linkText = e.text().trim();
				     System.out.println(linkHref);  
				     System.out.println(linkText); 
				     if(linkHref.contains("http")){
				    	 getText(linkHref);
				     }else{
				    	 System.out.println("链接不合法");
				     }
				 }
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 
	}

	private static void getText(String url) {
		Document doc;
		File file = new File("D:\\KnowYouserlf.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file,true);
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("创建文件出错");
		}
		PrintWriter pw = new PrintWriter(fw);

		//BufferedWriter bw = new BufferedWriter(fw);
		try {
			doc = Jsoup.connect(url).get();
			//获取标题
			Elements title = doc.getElementsByAttributeValue("class", "rich_media_title");
			pw.println(title.text().trim());
//			bw.write(title.text().trim());
//			bw.write("\r\n");
			//获取内容
			Elements list = doc.getElementsByAttributeValue("class", "rich_media_content");
			for(Element contant : list){
				Elements span = contant.getElementsByTag("span");
				for(Element e : span){
					String text = e.text();
					pw.println(text+"\r\n");
				}
			}
			pw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("获取文章内容出错");
		}
		
	}
	
	/*	public static void main(String[] args) {
		Main cm = new Main();
		//获取html文本内容
		String html = cm.getHtml(URL);
		//获取图片标签
		List<String> imgUrl = cm.getImageUrl(html);
		//获取图片地址
		List<String> imgSrc = cm.getImageSrc(imgUrl);
		//下载图片
		cm.download(imgSrc);
	}*/

	private String getHtml(String url2) {
		String result = null;
		try {
			URL url = new URL(url2);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(isr);
			
			String line;
			StringBuffer sb = new StringBuffer();
			while((line = br.readLine())!= null){
				sb.append(line,0,line.length());
				sb.append("\n");
			}
			br.close();
			isr.close();
			in.close();
			result = sb.toString();
			
		} catch (MalformedURLException e) {
			result = "new URL出错";
		} catch (IOException e) {
			result = "openConnection出错";
		}
		return result;
	}
	
	private List<String> getImageUrl(String html) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
		List<String> listImgUrl = new ArrayList<String>();
		while(matcher.find()){
			listImgUrl.add(matcher.group());
		}
		return listImgUrl;
	}
	
	private List<String> getImageSrc(List<String> imgUrl) {
		List<String> listImgSrc = new ArrayList<String>();
		for(String url:imgUrl){
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(url);
			while(matcher.find()){
				listImgSrc.add(matcher.group());
			}
		}
		return listImgSrc;
	}
	
	private void download(List<String> imgSrc) {
		try {
			Date beginDate = new Date();
			for(String url : imgSrc){
				Date beginDate2 = new Date();
				String imageName = url.substring(url.lastIndexOf("/"), url.length());
				URL uri = new URL(url);
				InputStream in = uri.openStream();
				File temp = new File("src/res/"+imageName);
				FileOutputStream fo = new FileOutputStream(temp);
				byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
                //结束时间
                Date overdate2 = new Date();
                double time = overdate2.getTime() - beginDate2.getTime();
                System.out.println("耗时：" + time / 1000 + "s");
            }
            Date overdate = new Date();
            double time = overdate.getTime() - beginDate.getTime();
            System.out.println("总耗时：" + time / 1000 + "s");
		} catch (MalformedURLException e) {
			System.out.println("new url error");
		} catch (IOException e) {
			System.out.println("openStream error");
		}
	}

}

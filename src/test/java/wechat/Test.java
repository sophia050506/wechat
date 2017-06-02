package wechat;

public class Test {

	public static void main(String[] args) {
		String article = "article:452";
		System.out.println(article.indexOf(":"));
		System.out.println(article.indexOf(":")+1);
		System.out.println(article.substring(8));
		System.out.println(article.substring(article.indexOf(":")+1));
	}
}

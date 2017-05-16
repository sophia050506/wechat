package com.wei.wechat.core.bean.message.resp;

import java.util.List;

/**
 * 回复图文消息.
 * @author sophia
 *
 */
public class NewsMessage extends RespBaseMassage{

	private int ArticleCount;
	
	private List<Article> Articles;
	
	public static class Article{
		
		//图文消息标题
		private String Title;
		
		//图文消息描述
		private String Description;
		
		//图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
		private String PicUrl;
		
		//点击图文消息跳转链接
		private String Url;
		
		public String getTitle() {
			return Title;
		}
		public void setTitle(String title) {
			Title = title;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getPicUrl() {
			return PicUrl;
		}
		public void setPicUrl(String picUrl) {
			PicUrl = picUrl;
		}
		public String getUrl() {
			return Url;
		}
		public void setUrl(String url) {
			Url = url;
		}
		
		
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		Articles = articles;
	}
}

package redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ZParams;

/**
 * redis实战第一章节案例.
 * @author sophia
 *
 */
public class redis_chapter01 {
	
	private static final int ONE_WEEK_SECOND = 7*24*60*60;
	private static final int VOTE_SCORE = 432;
	private static final int ARTICLES_PER_PAGE = 25;
	
	public static void main(String[] args) {
		new redis_chapter01().run();
	}
	
	private void run() {
		//链接redis
		Jedis conn = new Jedis("192.168.88.128",6379);
		conn.auth("admin");
		
		String articleId = postArticle(conn,"username","A title","http://www.baidu.com");
		System.out.println("我们上传了一篇新的文章，编号为"+articleId);
		
		Map<String, String> articleData = conn.hgetAll("article:"+articleId);
		for(Map.Entry<String, String> entry : articleData.entrySet()){
			System.out.println("    "+entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println();
		
		articleVote(conn,"other_user","article:"+articleId);
		String votes = conn.hget("article:"+articleId, "votes");
		System.out.println("投票"+votes);
		assert Integer.parseInt(votes) > 1;
		
		System.out.println("------------");
		List<Map<String,String>> articles = getArticles(conn,1);
		printArticles(articles);
		assert articles.size() >= 1;
		
		addGroups(conn,articleId,new String[]{"new-group"});
		System.out.println("------------");
		articles = getGroupArticles(conn,"new-group",1);
		printArticles(articles);
		assert articles.size() >= 1;
	}

	private String postArticle(Jedis conn, String user, String title, String link) {
		String articleId = String.valueOf(conn.incr("article:"));//将键的整数值递增1
		
		String voted = "voted:"+articleId;
		conn.sadd(voted, user);
		conn.expire(voted, ONE_WEEK_SECOND);//设置生存时间
		
		long now = System.currentTimeMillis()/1000;//当前时间   单位秒
		String article = "article:"+articleId;
		
		HashMap<String, String> articleData = new HashMap<String,String>();
		articleData.put("title", title);
		articleData.put("link", link);
		articleData.put("user", user);
		articleData.put("now", String.valueOf(now));
		articleData.put("voted", "1");
		conn.hmset(article, articleData);
		conn.zadd("score:",now+VOTE_SCORE,article);
		conn.zadd("time:", now,article);
		return articleId;
	}

	private void articleVote(Jedis conn, String user, String article) {
		long cutoff = System.currentTimeMillis()/1000-ONE_WEEK_SECOND;
		if(conn.zscore("time:", article) < cutoff){ //查看是否存在
			return;
		}
		
		String articleId = article.substring(article.indexOf(":")+1);//取出articleId
		if(conn.sadd("voted:"+articleId, user) == 1){
			conn.zincrby("score:", VOTE_SCORE, article); //对有序集合中指定成员的分数加上增量
			conn.hincrBy(article, "votes", 11);//为哈希表中的字段值加上指定增量值。
		}
	}

	private List<Map<String, String>> getArticles(Jedis conn, int page) {
		return getArticles(conn,page,"score:");
	}
	
	private List<Map<String, String>> getArticles(Jedis conn, int page,String order) {
		int start = (page-1) * ARTICLES_PER_PAGE;
		int end = start + ARTICLES_PER_PAGE - 1;
		
		Set<String> ids = conn.zrevrange(order, start, end);//降序排列
		List<Map<String, String>> articles = new ArrayList<Map<String,String>>();
		for(String id : ids){
			Map<String,String> articleData = conn.hgetAll(id);
			articleData.put("id", id);
			articles.add(articleData);
		}
		return articles;
	}

	private void addGroups(Jedis conn, String articleId, String[] toAdd) {
		String article = "article:"+articleId;
		for(String group : toAdd){
			conn.sadd("group:"+group ,article);
		}
	}

	private List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page) {
		return getGroupArticles(conn,group,page,"score:");
	}
	
	private List<Map<String, String>> getGroupArticles(Jedis conn, String group, int page,String order) {
		String key = order + group;
		if(!conn.exists(key)){
			ZParams params = new ZParams().aggregate(ZParams.Aggregate.MAX);////指定分数操作:+,最小,最大 
			conn.zinterstore(key, params,"group:"+group,order); //计算给定的一个或多个有序集的交集
			conn.expire(key, 60); //设置键的到期时间
		}
		return getArticles(conn,page,key);
	}

	private void printArticles(List<Map<String, String>> articles) {
		for(Map<String,String> article : articles){
			System.out.println("id:" + article.get("id"));
			for(Map.Entry<String, String> entry : article.entrySet()){
				if(entry.getKey().equals("id")){
					continue;
				}
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}
	}

}

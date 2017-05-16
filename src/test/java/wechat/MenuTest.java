package wechat;

import com.wei.wechat.core.bean.button.Button;
import com.wei.wechat.core.bean.button.ClickButton;
import com.wei.wechat.core.bean.button.ComplexButton;
import com.wei.wechat.core.bean.button.Menu;
import com.wei.wechat.core.bean.button.ViewButton;

import net.sf.json.JSONObject;

public class MenuTest {

	public static void main(String[] args) {
		ClickButton b1 = new ClickButton();
		b1.setName("今日歌曲");
		b1.setType("click");
		b1.setKey("V1001_TODAY_MUSIC");
		
		ViewButton b2 = new ViewButton();
		b2.setName("歌手简介");
		b2.setType("view");
		b2.setUrl("http://www.qq.com/");
		
		ClickButton b31 = new ClickButton();
		b31.setName("测试");
		b31.setType("click");
		b31.setKey("V1001_TEST");
		
		ClickButton b32 = new ClickButton();
		b32.setName("赞");
		b32.setType("click");
		b32.setKey("V1001_GOOD");
		
		ComplexButton b3 = new ComplexButton();
		b3.setName("菜单");
		b3.setSub_button(new Button[]{b31,b32});
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{b1,b2,b3});
		
		//将菜单转换为JSON字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		
		
		
		
		
		
		
	}

}

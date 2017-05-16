package com.wei.wechat.core.bean;

import org.apache.log4j.Logger;

import com.wei.wechat.core.bean.button.Button;
import com.wei.wechat.core.bean.button.ClickButton;
import com.wei.wechat.core.bean.button.ComplexButton;
import com.wei.wechat.core.bean.button.Menu;
import com.wei.wechat.core.bean.button.ViewButton;
import com.wei.wechat.core.util.WechatUtil;


public class MenuManager {

	public static Logger logger = Logger.getLogger(MenuManager.class);
	
	private static Menu getMenu(){
		ClickButton b11 = new ClickButton();
		b11.setName("开源中国");
		b11.setType("click");
		b11.setKey("oschina");
		
		ClickButton b12 = new ClickButton();
		b12.setName("ITeye");
		b12.setType("click");
		b12.setKey("iteye");
		
		ViewButton b13= new ViewButton();
		b13.setName("CocoaChina");
		b13.setType("view");
		b13.setUrl("http://iteye.com");
		
		ViewButton b21= new ViewButton();
		b21.setName("淘宝");
		b21.setType("view");
		b21.setUrl("http://m.taobao.com");
		
		ViewButton b22= new ViewButton();
		b22.setName("京东");
		b22.setType("view");
		b22.setUrl("http://m.jd.com");
		
		ViewButton b23= new ViewButton();
		b23.setName("唯品会");
		b23.setType("view");
		b23.setUrl("http://m.vipshop.com");
		
		ViewButton b24= new ViewButton();
		b24.setName("当当网");
		b24.setType("view");
		b24.setUrl("http://m.dangdang.com");
		
		ViewButton b25= new ViewButton();
		b25.setName("苏宁易购");
		b25.setType("view");
		b25.setUrl("http://m.suning.com");
		
		ViewButton b31= new ViewButton();
		b31.setName("多泡");
		b31.setType("view");
		b31.setUrl("http://www.duopao.com");
		
		ViewButton b32= new ViewButton();
		b32.setName("一窝88");
		b32.setType("view");
		b32.setUrl("http://www.yi588.com");
		
		ComplexButton b1= new ComplexButton();
		b1.setName("技术交流");
		b1.setSub_button(new Button[]{b11,b12,b13});
		
		ComplexButton b2= new ComplexButton();
		b2.setName("购物");
		b2.setSub_button(new Button[]{b21,b22,b23,b24,b25});
		
		ComplexButton b3= new ComplexButton();
		b3.setName("网页游戏");
		b3.setSub_button(new Button[]{b31,b32});
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{b1,b2,b3});
		
		return menu;
	}
	
	
	public static void main(String[] args) {
		//AppID：wx0b8bd06b060caedf
		//AppSecret:4f151b2952bbe33b5229fc2bac061c62
		
		String appId="wx0b8bd06b060caedf";
		
		String appSecret="4f151b2952bbe33b5229fc2bac061c62";
		
		Token token = WechatUtil.getToken(appId, appSecret);
		
		if(null != token){
			boolean result =  WechatUtil.createMenu(getMenu(), token.getAccessToken());
			if(result){
				logger.info("菜单创建成功！");
			}else{
				logger.info("菜单创建失败！");
			}
		}
	}

	
}

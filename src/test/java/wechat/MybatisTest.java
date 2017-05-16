package wechat;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wei.wechat.core.bean.User;
import com.wei.wechat.web.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"}) 
public class MybatisTest {

	private static Logger logger = Logger.getLogger(MybatisTest.class);  
	
	@Resource 
    private UserService userService = null;  
	
	@Test
	public void getUserByIdtest(){
		User user = userService.getUserById(1);
		logger.info("name="+user.getUserName());
	}
}

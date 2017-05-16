package wechat;

import javax.servlet.http.HttpServletRequest;

import com.wei.wechat.core.service.CoreService;

public class CoreServiceTest {

	public static void main(String[] args) {
		HttpServletRequest request = null;
		CoreService.processRequest(request);
	}
}

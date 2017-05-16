package com.wei.wechat.core.bean.message.resp;

/**
 * 回复图片消息.
 * @author sophia
 *
 */
public class ImageMessage extends RespBaseMassage{

	
	private Image Image;
	
	public class Image{
		
		//通过素材管理接口上传多媒体文件，得到的id。
		private String MediaId;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
	}

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}


}

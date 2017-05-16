package com.wei.wechat.core.bean.message.resp;

/**
 * 回复语音消息.
 * @author sophia
 *
 */
public class VoiceMessage extends RespBaseMassage{

	private Voice Voice;
	
	public class Voice{
		
		//通过素材管理接口上传多媒体文件，得到的id
		private String MediaId;

		public String getMediaId() {
			return MediaId;
		}

		public void setMediaId(String mediaId) {
			MediaId = mediaId;
		}
	}

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}

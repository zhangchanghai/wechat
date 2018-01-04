package com.wechat.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wechat.bean.ArticleItem;
import com.wechat.util.FeignUtil;
import com.wechat.util.RedisUtils;
import com.wechat.util.WeChatContant;
import com.wechat.util.WeChatUtil;

/**
 * 核心服务类
 */
@Service
public class WeChatServiceImpl implements WeChatService{
	@Autowired
	private FeignUtil feignUtil;
	@Autowired
	private RedisUtils redisUtils;
    public String processRequest(HttpServletRequest request) {
        // xml格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent;
        try {
            // 调用parseXml方法解析请求消息
            Map<String,String> requestMap = WeChatUtil.parseXml(request);
             // 消息类型
            String msgType = (String) requestMap.get(WeChatContant.MsgType);

            String mes=requestMap.get(WeChatContant.Content).toString();
            // 文本消息
            if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_TEXT)) {
                if(mes!=null&&mes.length()<2){
                	List<ArticleItem> items = new ArrayList<>();
                	ArticleItem item = new ArticleItem();
                	item.setTitle("照片墙");
                	item.setDescription("阿狸照片墙");
                	item.setPicUrl("http://changhaiwx.pagekite.me/photo-wall/a/iali11.jpg");
                	item.setUrl("http://changhaiwx.pagekite.me/page/photowall");
                	items.add(item);
                	
                	item = new ArticleItem();
                	item.setTitle("哈哈");
                	item.setDescription("一张照片");
                	item.setPicUrl("http://changhaiwx.pagekite.me/images/me.jpg");
                	item.setUrl("http://changhaiwx.pagekite.me/page/index");
                	items.add(item);
                	
                	item = new ArticleItem();
                	item.setTitle("小游戏2048");
                	item.setDescription("小游戏2048");
                	item.setPicUrl("http://changhaiwx.pagekite.me/images/2048.jpg");
                	item.setUrl("http://changhaiwx.pagekite.me/page/game2048");
                	items.add(item);
                	
                	item = new ArticleItem();
                	item.setTitle("百度");
                	item.setDescription("百度一下");
                	item.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505100912368&di=69c2ba796aa2afd9a4608e213bf695fb&imgtype=0&src=http%3A%2F%2Ftx.haiqq.com%2Fuploads%2Fallimg%2F170510%2F0634355517-9.jpg");
                	item.setUrl("http://www.baidu.com");
                	items.add(item);
                	
                	respXml = WeChatUtil.sendArticleMsg(requestMap, items);
                }else if("我的信息".equals(mes)){
                	Map<String, String> userInfo = getUserInfo(requestMap.get(WeChatContant.FromUserName));
                	System.out.println(userInfo.toString());
                	String nickname = userInfo.get("nickname");
                	String city = userInfo.get("city");
                	String province = userInfo.get("province");
                	String country = userInfo.get("country");
                	String headimgurl = userInfo.get("headimgurl");
                	List<ArticleItem> items = new ArrayList<>();
                	ArticleItem item = new ArticleItem();
                	item.setTitle("你的信息");
                	item.setDescription("昵称:"+nickname+"  地址:"+country+" "+province+" "+city);
                	item.setPicUrl(headimgurl);
                	item.setUrl("http://www.baidu.com");
                	items.add(item);
                	
                	respXml = WeChatUtil.sendArticleMsg(requestMap, items);
                }
            }
            // 图片消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 语音消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是语音消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 视频消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_VIDEO)) {
                respContent = "您发送的是视频消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 地理位置消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 链接消息
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
                respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
            }
            // 事件推送
            else if (msgType.equals(WeChatContant.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = (String) requestMap.get(WeChatContant.Event);
                // 关注
                if (eventType.equals(WeChatContant.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                    respXml = WeChatUtil.sendTextMsg(requestMap, respContent);
                }
                // 取消关注
                else if (eventType.equals(WeChatContant.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户不会再收到公众账号发送的消息，因此不需要回复
                }
                // 扫描带参数二维码
                else if (eventType.equals(WeChatContant.EVENT_TYPE_SCAN)) {
                    // TODO 处理扫描带参数二维码事件
                }
                // 上报地理位置
                else if (eventType.equals(WeChatContant.EVENT_TYPE_LOCATION)) {
                    // TODO 处理上报地理位置事件
                }
                // 自定义菜单
                else if (eventType.equals(WeChatContant.EVENT_TYPE_CLICK)) {
                    // TODO 处理菜单点击事件
                }
            }
            if(respXml == null)
            	respXml = WeChatUtil.sendTextMsg(requestMap, mes);
            System.out.println(respXml);
            return respXml;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
        
    }

	@Override
	public String getToken() {
		String token = (String) redisUtils.get("token");
		if(token == null){
			Map<String, String> tokenMap = feignUtil.getToken(WeChatContant.appID, WeChatContant.appsecret);
			System.out.println("token:\t"+tokenMap.toString());
			token = tokenMap.get("access_token") ;
			if(token != null){
				redisUtils.set("token", token, 7000L);
			}
				
		}
		return token;
	}

	@Override
	public Map<String, String> getUserInfo(String openid) {
		// TODO Auto-generated method stub
		return feignUtil.getUserInfo(getToken(), openid);
	}
   
}
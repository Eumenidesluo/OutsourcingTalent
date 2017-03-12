package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import component.StatusCode;
import dao.NoticeDao;
import entity.NoticeEntity;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

	@Autowired
	NoticeDao noticeDao;
	
	/**
     * <p>�ӿ����ƣ�query
     * <p>��Ҫ��������ѯ��¼
     * <p>���ʷ�ʽ��post
     * <p>URL: /notice/query
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * userId   	String 		Y    	 NULL  �û�Id
     * number		Integer		Y		 2   	��ѯ���� -1:��ѯȫ��֪ͨ
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     *     notices��..
     * }
     * </pre>
     * <p>�޸���:����
     * <pre>
     * 
     */
	@RequestMapping(value = "/query")
	@ResponseBody
	public String query(HttpServletRequest request,HttpSession session){
		Map<String, Object> result = new HashMap<>();
		String personId = request.getParameter("userId");
		String number = request.getParameter("number");
		if (personId == null || number == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		String userId = session.getAttribute("userId").toString();
		if (userId == null||userId != personId) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		if (Integer.parseInt(number) == -1) {
			List<?> notices = noticeDao.queryByUserId(userId);
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return JSON.toJSONString(result);
		}else {
			List<NoticeEntity> notices = noticeDao.queryByUserId(userId,Integer.parseInt(number));
			result.put("status", StatusCode.SUCCESS);
			result.put("notices", notices);
			return JSON.toJSONString(result);
		}		
	}
}

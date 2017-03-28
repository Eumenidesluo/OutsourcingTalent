package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import component.StatusCode;

@RequestMapping(value = "/photo")
@Controller
public class PhotoController {

	private static final String FILE_PATH = "C:/upload";

	/**
     * <p>�ӿ����ƣ�upload
     * <p>��Ҫ�������������������Ƭ
     * <p>���ʷ�ʽ��post
     * <p>URL: /photo/upload
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * file   		MultipartFile Y    	 NULL  ��Ƭ��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, �μ�״̬���
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/upload")
	@ResponseBody
	public String upload(@RequestParam MultipartFile file,HttpSession session){
		Map< String, Object> result = new HashMap<>();
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			result.put("status", StatusCode.AUTHENTICATION_FAILED);
			return JSON.toJSONString(result);
		}
		if (file == null) {
			result.put("status", StatusCode.PARAMETER_ERROR);
			return JSON.toJSONString(result);
		}
		if (savePhoto(file,userId)) {
			result.put("status", StatusCode.SUCCESS);
			return JSON.toJSONString(result);
		}
		result.put("status", StatusCode.UNKNOW_ERROR);
		return JSON.toJSONString(result);
	}
	
	
	private Boolean savePhoto(MultipartFile file,Integer userId) {
		String fileName = "personal"+userId;  
	    File tempFile = new File(FILE_PATH, String.valueOf(fileName));  
	    if (!tempFile.getParentFile().exists()) {  
	        tempFile.getParentFile().mkdir();  
	    }  
	    try {
	    	if (!tempFile.exists()) {  
	    		tempFile.createNewFile();  
		    }  
		    file.transferTo(tempFile);
		    System.out.println(tempFile.getAbsolutePath());
		} catch (Exception e) {
			return false;
		}		        
	    return true;
	}
	
	/**
     * <p>�ӿ����ƣ�download
     * <p>��Ҫ�������ӷ�����������Ƭ
     * <p>���ʷ�ʽ��post
     * <p>URL: /photo/download
     * <p>����˵��:
     * <pre>
     * |����              |����         |�Ƿ����   |Ĭ��ֵ    |˵��
     * companyId   	Integer		 N  	 NULL  ֵ���ڣ����ѯ��˾logo�����������ѯ����ͷ��
     * </pre>
     * <p>��������:JSON
     * <pre>
     * {
     *     ��Ƭ
     * }
     * </pre>
     * <p>�޸���:����
     * 
     */
	@RequestMapping(value = "/download")
	public void download(HttpServletResponse response,HttpServletRequest request,HttpSession session){
		Integer userId = (Integer)session.getAttribute("userId");
		if (userId == null) {
			response.reset();
			try {
				PrintWriter writer = response.getWriter();
				writer.write("status:"+StatusCode.PARAMETER_ERROR);
				writer.flush();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String fileName = "";
		String companyId = request.getParameter("companyId");
		if (companyId == null) {
			fileName  = "personal" + userId;
		}else{
			fileName = "company"+companyId;
		}
		OutputStream os = null;
		try {
			os = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
			return ;
		}  
        try {         	
            response.reset();  
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
            response.setContentType("image/jpeg; charset=utf-8");  
            try {
				os.write(FileUtils.readFileToByteArray(new File("C:/upload/"+fileName)));
				os.flush();  
			} catch (IOException e) {
				e.printStackTrace();
				return ;
			}  
           
        } finally {  
            if (os != null) {  
                try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}  
            }  
        }  
	}
}

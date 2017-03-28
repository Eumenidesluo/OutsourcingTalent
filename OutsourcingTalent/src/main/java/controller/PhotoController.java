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
     * <p>接口名称：upload
     * <p>主要描述：向服务器传送照片
     * <p>访问方式：post
     * <p>URL: /photo/upload
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * file   		MultipartFile Y    	 NULL  照片体
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     status: ${StatusCode}, 参见状态码表
     * }
     * </pre>
     * <p>修改者:陈琦
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
     * <p>接口名称：download
     * <p>主要描述：从服务器下载照片
     * <p>访问方式：post
     * <p>URL: /photo/download
     * <p>参数说明:
     * <pre>
     * |名称              |类型         |是否必须   |默认值    |说明
     * companyId   	Integer		 N  	 NULL  值存在，则查询公司logo，不存在则查询个人头像
     * </pre>
     * <p>返回数据:JSON
     * <pre>
     * {
     *     照片
     * }
     * </pre>
     * <p>修改者:陈琦
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

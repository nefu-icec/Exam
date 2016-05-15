package edu.nefu.icec.exam.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.nefu.icec.exam.bean.SystemDefaultParameter;
import edu.nefu.icec.exam.service.StudentManager;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String task;
	private static final int FILE_MAX_SIZE=512*1024*1024;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		task=request.getParameter("task");
		switch (task)
		{
		case "download":
			download(request,response);
			break;  
			
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		task=request.getParameter("task");
		switch (task)
		{
		case "uploadExcel":
			uploadExcel(request,response);
			break;  
			
		default:
			break;
		}
	}
	
	/**
	 * 上传Excel
	 * @throws IOException 
	 * @throws Exception 
	 */
	private void uploadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter");
		System.out.println(parameter.getExternalStoragePath());
		System.out.println(parameter.isEnableExternalStorage());
		request.setCharacterEncoding("UTF-8");
		String filepath=getServletConfig().getServletContext().getRealPath("/")+SystemDefaultParameter.CACHE_PATH;
		System.out.println(filepath);
		String fileName = upload(request,filepath);
		System.out.println(fileName);
		//解析Excel
		String  filePath = filepath+"/"+fileName;
		StudentManager sm = (StudentManager)context.getBean("studentManager");
		sm.addStudentByExcel(filePath);
		String data="{'filename':'"+fileName+"'}";
		response.setContentType("text/json");
		response.getWriter().print(data);
	}
	
	/**
	 * 指定路径上传文件
	 * @param request HttpServletRequest
	 * @param filepath 文件路径
	 * @return 文件名
	 */
	@SuppressWarnings("unchecked")
	private String upload(HttpServletRequest request,String filepath)
	{
		String fileName=null;
		DiskFileItemFactory factory = new DiskFileItemFactory();//为文件对象产生工厂对象。
		factory.setSizeThreshold(1024*4); //设置缓冲区的大小，此处为4kb
		factory.setRepository(new File(filepath)); //设置上传文件的目的地
		ServletFileUpload upload = new ServletFileUpload(factory);//产生servlet上传对象
		upload.setSizeMax(FILE_MAX_SIZE);  //设置上传文件的大小
		try 
		{
			List<FileItem> list=upload.parseRequest(request); //取得所有的上传文件信息
			Iterator<FileItem> it=list.iterator();
			while(it.hasNext())
			{
			    FileItem item=it.next();
			    if(item.isFormField()==false)
			    { 
				    fileName=item.getName();   //文件名
				    //取文件名  
				    fileName=fileName.substring(fileName.lastIndexOf("\\")+1,fileName.length());               
				    if(!fileName.equals("")&&!(fileName==null))
				    {
				    	//如果fileName为null，即没有上传文件  
				    	File uploadedFile=new File(filepath,fileName);  
				        try 
				        {
				        	item.write(uploadedFile);
				        } 
				        catch (Exception e)
				        {
				        	e.printStackTrace();
				        }  
				    }            
			    }
			}
		} 
		catch (FileUploadException e) 
		{
			e.printStackTrace();
		}
		return fileName;
	}
	
	
	@SuppressWarnings("static-access")
	private void download(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{ 
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter"); 
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filePath=rootPath+parameter.TEMPLATE_PATH;   
		String fileName = "student.xls";//此处设置文件名
		downloadExamers(filePath, fileName, response);
	}
	
	/**
	 * 打印考试信息 
	 * @param filePath 文件路径
	 * @param fileName
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void downloadExamers(String filePath,String fileName,HttpServletResponse response) throws UnsupportedEncodingException 
	{
		FileInputStream in=null;
		ServletOutputStream out=null;
		response.setContentType("application/vnd.ms-excel; charset=UTF-8");
		response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1"));
		try
		{
			in=new FileInputStream(filePath+"\\"+fileName);
			out=response.getOutputStream();
			out.flush();
			int aRead=0;
			while((aRead=in.read())!=-1&in!=null)
				out.write(aRead);
			out.flush();
			in.close();
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

}

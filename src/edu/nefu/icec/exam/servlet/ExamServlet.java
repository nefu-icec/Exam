package edu.nefu.icec.exam.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import edu.nefu.icec.exam.service.ExamManager;
import edu.nefu.icec.exam.service.ExamerManager;

/**
 * Servlet implementation class ExamServlet
 */
@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String task;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamServlet() {
        super();
        // TODO Auto-generated constructor stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	} 
	
	private void download(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{ 
		String eid = request.getParameter("eid");
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filePath=rootPath+"excel";  
		
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ExamManager em = (ExamManager)context.getBean("examManager");
		String excelName = em.get(eid).getName()+"的考生信息";
		
		String fileName = excelName+".xls";//此处设置文件名
		downloadExamers(eid,filePath, fileName, response);
	}
	
	/**
	 * 打印考试信息
	 * @param eid 考试ID
	 * @param filePath 文件路径
	 * @param fileName
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void downloadExamers(String eid,String filePath,String fileName,HttpServletResponse response) throws UnsupportedEncodingException 
	{
		//服务器端生成考生信息的Excel
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ExamerManager em = (ExamerManager)context.getBean("examerManager");
		em.uploadExamerInfo(filePath+"\\"+fileName, eid);
		System.out.println(filePath);
		
		//下载考生信息的Excel
		FileInputStream in=null;
		ServletOutputStream out=null;
		response.setContentType("application/x-msdownload; charset=UTF-8");
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

package edu.nefu.icec.exam.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import edu.nefu.common.util.FileTool;
import edu.nefu.icec.exam.bean.ExamBean;
import edu.nefu.icec.exam.bean.ExamerBean;
import edu.nefu.icec.exam.bean.QuestionBean;
import edu.nefu.icec.exam.bean.StudentBean;
import edu.nefu.icec.exam.bean.SystemDefaultParameter;
import edu.nefu.icec.exam.bean.TeacherBean;
import edu.nefu.icec.exam.service.AnswerManager;
import edu.nefu.icec.exam.service.ExamManager;
import edu.nefu.icec.exam.service.ExamerManager;
import edu.nefu.icec.exam.service.QuestionManager;
import edu.nefu.icec.exam.service.StudentManager;
import edu.nefu.icec.exam.service.TeacherManager;

/**
 * Servlet implementation class AnswerServlet
 */
@WebServlet("/AnswerServlet")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String task; 
	private static final int FILE_MAX_SIZE=512*1024*1024; 
	public static final String ANSWER_FOLDER="answer";
	public static final String CACHE_FOLDER="cahce";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
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
		case "downloadbyexamer":
			downloadByExamer(request, response);
			break;
		case "download":
			download(request,response);
			break;
		case "uploadAnswer":
			uploadAnswer(request,response);
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		task=request.getParameter("task");
		switch (task)
		{
		case "uploadAnswer":
			uploadAnswer(request,response);
			break;

		default:
			break;
		}
	}

	/**
	 * 上传答案
	 * @throws IOException 
	 * @throws Exception 
	 */
	private void uploadAnswer(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter");
		ExamManager em = (ExamManager)context.getBean("examManager");
		AnswerManager am = (AnswerManager)context.getBean("answerManager");
		
		//得到考生的考试id
		ExamerBean examer = (ExamerBean)request.getSession().getAttribute(ExamerManager.FLAG);
		String erid = examer.getErid();
		//得到考试id
		String eid = examer.getEid();
		//得到老师id
		String tid = em.get(eid).getTid();
		
		//得到aid
		String aid = request.getParameter("aid");
		am.updateAnswerState(aid, 3);
		
		//如果不可以使用外部存储
		if(parameter.isEnableExternalStorage()==false){
			request.setCharacterEncoding("UTF-8");
			
			//考生上传题目路径
			String filepath=createUploadDirectory(tid, eid, erid);
			System.out.println(filepath);
			
			String fileName = upload(aid,request,filepath);
			System.out.println(fileName);
			
			//返回
			//String data="{'filename':'"+fileName+"'}";
			String data="{\"filename\":\""+fileName+"\",\"tid\":\""+tid+"\",\"eid\":\""+eid+"\",\"erid\":\""+erid+"\"}";
//			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			response.getWriter().print(data);
		}
		else{
			
		}
	}
	
	/**
	 * 如果需要的照片上传路径不存在，则创建
	 * @param sid 用户id
	 * @return 上传文件路径
	 */
	private String createUploadDirectory(String tid,String eid,String erid)
	{
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filepath=rootPath+ANSWER_FOLDER;
		//如果Answer文件夹不存在，新建Answer文件夹
		FileTool.createDirectoryIfNotExsit(filepath);
		
		filepath+="/"+tid;
		//如果老师不存文件夹，新建老师文件夹
		FileTool.createDirectoryIfNotExsit(filepath);
		//如果考试不存在文件夹，新建考试文件夹
		filepath+="/"+eid;
		FileTool.createDirectoryIfNotExsit(filepath);
		//如果考生不存在文件夹，新建考生文件夹
		filepath+="/"+erid;
		FileTool.createDirectoryIfNotExsit(filepath); 
		return filepath;
	}
	
	/**
	 * 指定路径上传文件
	 * @param request HttpServletRequest
	 * @param filepath 文件路径
	 * @return 文件名
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	private String upload(String aid,HttpServletRequest request,String filepath)
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
			    	WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
					SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter");
				    //fileName=item.getName();   //文件名
			    	fileName = aid+"."+parameter.FILEEXTENSION;	//文件名
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
	
	/**
	 * 通过教师下载考生答案文件
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings({ "static-access", "unused" })
	private void download(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{ 
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter"); 
		//得到老师id
		TeacherBean tb = (TeacherBean)request.getSession().getAttribute(TeacherManager.FLAG);
		String tid = tb.getId();
		//String tid = request.getParameter("tid");
		//得到考试id
		String eid = request.getParameter("eid");
		//得到考生id
		String erid = request.getParameter("erid");
		//得到答案id
		String aid = request.getParameter("aid");
		

		 
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filePath=rootPath+ANSWER_FOLDER+"/"+tid+"/"+eid+"/"+erid;     
		String fileName = aid+"."+parameter.FILEEXTENSION;//此处设置文件名
		
		File fileFlag = new File(filePath+"\\"+fileName);
		
		if(fileFlag.exists()){
		//转移文件到缓存
		FileInputStream in=null;
		FileOutputStream out1 = null;
		 
		StudentManager sm = (StudentManager)context.getBean("studentManager");
		StudentBean sb = sm.getByExamerId(erid);
		QuestionManager qm = (QuestionManager)context.getBean("questionManager");
		QuestionBean qb = qm.getByAid(aid);
		ExamManager em = (ExamManager)context.getBean("examManager");
		ExamBean eb = em.get(eid);
		 
		String pathCatch = rootPath+CACHE_FOLDER;

		//如果cache文件夹不存在，新建cache文件夹 
		FileTool.createDirectoryIfNotExsit(pathCatch);
		System.out.println(pathCatch);
		
		String fileName1 = eb.getName()+"_"+sb.getNumber()+"_"+sb.getName()+"_"+"_"+qb.getTitle()+"."+parameter.FILEEXTENSION;
		out1 = new FileOutputStream(pathCatch+"\\"+fileName1);
		 
		response.setContentType("application/x-msdownload; charset=UTF-8");
		response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1"));
		try
		{
			in=new FileInputStream(filePath+"\\"+fileName); 
			out1.flush();
			int aRead=0;
			while((aRead=in.read())!=-1&in!=null)
				out1.write(aRead);
			out1.flush();
			in.close();
			out1.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		downloadExamers(pathCatch, fileName1, response);
		
		//返回 
		String data="{\"filename\":\""+fileName1+"\"}";
		response.setContentType("text/json");
		//response.getWriter().print(data);
		}
		else{
			System.out.println("文件不存在");
			//返回 
			String data="{\"filename\":\""+"文件不存在"+"\"}";
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/json");
			//response.getWriter().print(data);
		}
	}
	
	/**
	 * 通过考生下载答案文件
	 * @param request
	 * @param response
	 * @throws IOException
	 */ 
	@SuppressWarnings("static-access")
	private void downloadByExamer(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{ 
		WebApplicationContext context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		SystemDefaultParameter parameter=(SystemDefaultParameter)context.getBean("systemDefaultParameter"); 
		//得到老师id
		ExamerBean eb1 = (ExamerBean)request.getSession().getAttribute(ExamerManager.FLAG);
	
		//得到考试id
		String eid = eb1.getEid();
		//得到考生id
		String erid = eb1.getErid();
		
		TeacherManager tm = (TeacherManager)context.getBean("teacherManager");
		//得到教师id
		String tid = tm.getTidByErid(erid);
		
		//得到答案id
		String aid = request.getParameter("aid");
		 
		String rootPath=getServletConfig().getServletContext().getRealPath("/");
		String filePath=rootPath+ANSWER_FOLDER+"/"+tid+"/"+eid+"/"+erid;     
		String fileName = aid+"."+parameter.FILEEXTENSION;//此处设置文件名
		
		File fileFlag = new File(filePath+"\\"+fileName);
		
		if(fileFlag.exists()){
		//转移文件到缓存
		FileInputStream in=null;
		FileOutputStream out1 = null;
		 
		StudentManager sm = (StudentManager)context.getBean("studentManager");
		StudentBean sb = sm.getByExamerId(erid);
		QuestionManager qm = (QuestionManager)context.getBean("questionManager");
		QuestionBean qb = qm.getByAid(aid);
		ExamManager em = (ExamManager)context.getBean("examManager");
		ExamBean eb = em.get(eid);
		 
		String pathCatch = rootPath+CACHE_FOLDER;

		//如果cache文件夹不存在，新建cache文件夹 
		FileTool.createDirectoryIfNotExsit(pathCatch);
		System.out.println(pathCatch);
		 
		String fileName1 = eb.getName()+"_"+sb.getNumber()+"_"+sb.getName()+"_"+"_"+qb.getTitle()+"."+parameter.FILEEXTENSION;
		out1 = new FileOutputStream(pathCatch+"\\"+fileName1);
		 
		response.setContentType("application/x-msdownload; charset=UTF-8");
		response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("UTF-8"),"iso8859-1"));
		try
		{
			in=new FileInputStream(filePath+"\\"+fileName); 
			out1.flush();
			int aRead=0;
			while((aRead=in.read())!=-1&in!=null)
				out1.write(aRead);
			out1.flush();
			in.close();
			out1.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		
		downloadExamers(pathCatch, fileName1, response);
		
		//返回 
		//String data="{\"filename\":\""+fileName1+"\"}";
		//response.setCharacterEncoding("UTF-8");
		//response.setContentType("text/json");
		//response.getWriter().print(data);
		}
		else{
			System.out.println("文件不存在");
			//返回 
			//String data="{\"filename\":\""+"文件不存在"+"\"}";
			//response.setCharacterEncoding("UTF-8");
			//response.setContentType("text/json");
			//response.getWriter().print(data);
		}
	}
	
	/**
	 * 下载考生答案
	 * @param filePath 文件路径
	 * @param fileName
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	private void downloadExamers(String filePath,String fileName,HttpServletResponse response) throws UnsupportedEncodingException 
	{
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

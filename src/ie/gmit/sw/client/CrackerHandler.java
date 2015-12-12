package ie.gmit.sw.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.sw.client.InQueue;
import ie.gmit.sw.client.MessageRequest;
import ie.gmit.sw.client.OutQueue;

/*
 * CrackerHandler takes the request for the webpage, passes in the parameters and puts the request in the InQueue in for of a MessageRequest with a joNumber
 * 
 * @author Aaron Flanagan
 */
public class CrackerHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String remoteHost = null;
	private static long jobNumber = 0;
	
	private InQueue in = new InQueue();
	private OutQueue outQ = new OutQueue();
	private Worker worker = new Worker(in, outQ);

	/*
	 * Invoked when CrackerHandler is called, it starts up the servlet
	 */
	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

	/*
	 * Retrieves the parameters from clients request, encapsulates them in MessageRequest and puts it in InQueue
	 * It then tells Worker to do its job and checks OutQueue every 10 seconds for result
	 * 
	 * @see InQueue
	 * @see OutQueue
	 * @see Worker
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		int maxKeyLength = Integer.parseInt(req.getParameter("frmMaxKeyLength"));
		String cypherText = req.getParameter("frmCypherText");
		String taskNumber = req.getParameter("frmStatus");

		out.print("<html><head><title>Distributed Systems Assignment</title>");
		out.print("</head>");
		out.print("<body>");

		if (taskNumber == null){
			taskNumber = new String("T" + jobNumber);
			jobNumber++;
			MessageRequest message = new MessageRequest(maxKeyLength, cypherText, maxKeyLength);
			in.add(message);
		}else{
			outQ.remove(jobNumber);
		}
		
		try {
			worker.lookAndInvoke();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.print("<H1>Processing request for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"r\"></div>");

		out.print("RMI Server is located at " + remoteHost);
		out.print("<P>Maximum Key Length: " + maxKeyLength);
		out.print("<P>CypherText: " + cypherText);

		out.print("<form name=\"frmCracker\">");
		out.print("<input name=\"frmMaxKeyLength\" type=\"hidden\" value=\"" + maxKeyLength + "\">");
		out.print("<input name=\"frmCypherText\" type=\"hidden\" value=\"" + cypherText + "\">");
		out.print("<input name=\"frmStatus\" type=\"hidden\" value=\"" + taskNumber + "\">");
		out.print("</form>");
		out.print("</body>");
		out.print("</html>");

		out.print("<script>");
		out.print("var wait=setTimeout(\"document.frmCracker.submit();\", 10000);");
		out.print("</script>");
		
		
		out.print("<html><head><title>Result</title>");
		out.print("</head>");
		out.print("<body>");
		
		out.print("<H1>Result for Job#: " + taskNumber + "</H1>");
		out.print("<div id=\"result\"></div>");
		
		out.println("<H1>" + outQ.get(jobNumber) + "</H1>");
		
		out.print("</body>");
		out.print("</html>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}

package ie.gmit.sw.client;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import ie.gmit.sw.client.InQueue;
import ie.gmit.sw.client.MessageRequest;
import ie.gmit.sw.client.OutQueue;

public class CrackerHandler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private String remoteHost = null;
	private static long jobNumber = 0;
	
	private InQueue in = new InQueue();
	private OutQueue outQ = new OutQueue();
	private Worker worker = new Worker(in, outQ);

	public void init() throws ServletException {
		ServletContext ctx = getServletContext();
		remoteHost = ctx.getInitParameter("RMI_SERVER"); //Reads the value from the <context-param> in web.xml
	}

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
			in.offer(message);
		}else{
			//out.println();
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
		out.print("<P>This servlet should only be responsible for handling client request and returning responses. Everything else should be handled by different objects.");
		out.print("<P>Note that any variables declared inside this doGet() method are thread safe. Anything defined at a class level is shared between HTTP requests.");

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
		
		/*-----------------------------------------------------------------------
		 *  Next Steps: just in case you removed the above....
		 *-----------------------------------------------------------------------
		 * 7) Return the cyphertext to the client next time a request for the jobNumber is received and delete the key / value pair from the Map.
		 */

		//You can use this method to implement the functionality of an RMI client

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
 	}
}

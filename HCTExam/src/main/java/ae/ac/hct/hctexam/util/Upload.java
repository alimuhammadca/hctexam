/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam.util;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author amuhammad1
 */
@Path("/upload")
public class Upload {

    @POST
    @Path("/excel")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public boolean uploaExcelFile(@FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("YYYY-MM-dd");
        
        try {
            String UPLOAD_PATH = "/Users/sufianbinidris/NetBeansProjects/tempdir/";
            //String UPLOAD_PATH = "d:/temp/";
            StringBuffer buff = new StringBuffer();
            int read = 0;
            byte[] bytes = new byte[1024];
            String inputFileName = UPLOAD_PATH + fileMetaData.getFileName();
            OutputStream out = new FileOutputStream(new File(inputFileName));
            while ((read = fileInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                buff.append(bytes);
            }
            out.flush();
            out.close();
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/hctdb");
            Connection conn = dataSource.getConnection();
            //List<String> userList = new ArrayList();
            
            /******* sufian
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM appuser");
            while (rs.next()) {
                userList.add(rs.getString("id"));
            }
            */
            
            PreparedStatement examStmt = conn.prepareStatement("INSERT INTO exam (semester, head_inv, course_code, delivery, student_count, password1, password2, exam_date, start_time, head_inv_name) VALUES (?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement appuserStmt = conn.prepareStatement("INSERT INTO appuser (id, role_id, firstname, password) VALUES (?,?,?,?)");
            PreparedStatement headInvStmt = conn.prepareStatement("SELECT * FROM appuser WHERE id=?");
            PreparedStatement courseFindStmt = conn.prepareStatement("SELECT * FROM course WHERE course_code=?");
            PreparedStatement courseInsertStmt = conn.prepareStatement("INSERT INTO course VALUES (?, ?)");
                //********** sufian
            Scanner io = new Scanner(new FileInputStream(inputFileName));
            
            if (io.hasNextLine()) io.nextLine();
            
            String lineText;
            while (io.hasNextLine()) {
                lineText = io.nextLine();
                System.out.println(lineText);
                String[] data = lineText.split(",");
                System.out.println("1");
                String semester = data[0];
                String[] course = data[2].split(":");
                String courseCode = course[0].trim();
                String courseName = course[1].trim();
                int numOfStudents = Integer.parseInt(data[3]);
                String examDate = data[4];
                Date dt = sdf1.parse(examDate);
                examDate = sdf2.format(dt);
                String examTime = data[5];
                String mode = data[8];
                String password1 = data[9];
                String password2 = data[10];
                String headInvigilatorName = data[11];
                String headInvigilatorId = data[12].split("@")[0];
                
                System.out.println("2");
                examStmt.setString(1, semester);
                examStmt.setString(2, headInvigilatorId);
                examStmt.setString(3, courseCode);
                examStmt.setString(4, mode);
                examStmt.setInt(5, numOfStudents);
                examStmt.setString(6, password1);
                examStmt.setString(7, password2);
                examStmt.setString(8, examDate);
                examStmt.setString(9, examTime);
                examStmt.setString(10, headInvigilatorName);
                System.out.println("3");
                System.out.println("Returns: "+examStmt.executeUpdate());
                System.out.println("Inserted into exam");
                
                // *********** sufian
//                if (!userList.contains(headInvigilatorId)) {
                //ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM appuser WHERE id='"+headInvigilatorId+"'");
                headInvStmt.setString(1, headInvigilatorId);
                ResultSet headInvRs = headInvStmt.executeQuery();
                if (!headInvRs.next()) {
                    appuserStmt.setString(1, headInvigilatorId);
                    appuserStmt.setInt(2, 2);
                    appuserStmt.setString(3, headInvigilatorName);
                    appuserStmt.setString(4, "changeme!");
                    appuserStmt.executeUpdate();
                    System.out.println("Inserted into appuser");
                }
                
                courseFindStmt.setString(1, courseCode);
                ResultSet courseRs = courseFindStmt.executeQuery();
                if (!courseRs.next()) {
                    courseInsertStmt.setString(1, courseCode);
                    courseInsertStmt.setString(2, courseName);
                    courseInsertStmt.executeUpdate();
                    System.out.println("Inserted into course.");
                }
                
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws Exception {
        String in = "15-Dec-20";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yy");
        Date dt = sdf1.parse(in);
        String out = new SimpleDateFormat("YYYY-MM-dd").format(dt);
        System.out.println(out);
    }
}

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
        try {
            //String UPLOAD_PATH = "/Users/sufianbinidris/NetBeansProjects/tempdir/";
            String UPLOAD_PATH = "d:/temp/";
            StringBuffer buff = new StringBuffer();
            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
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
            
            PreparedStatement examStmt = conn.prepareStatement("INSERT INTO exam (semester, head_inv, course_code, delivery, student_count, password1, password2, exam_date, start_time) VALUES (?,?,?,?,?,?,?,?,?)");
            PreparedStatement appuserStmt = conn.prepareStatement("INSERT INTO appuser (id, role_id, firstname, password) VALUES (?,?,?,?)");
            PreparedStatement headInvStmt = conn.prepareStatement("SELECT * FROM appuser WHERE id=?");
            PreparedStatement courseFindStmt = conn.prepareStatement("SELECT * FROM course WHERE course_code=?");
            PreparedStatement courseInsertStmt = conn.prepareStatement("INSERT INTO course VALUES (?, ?)");
                //********** sufian
            Scanner io = new Scanner(new FileInputStream(UPLOAD_PATH+"exam.csv"));
            
            String lineText;
            while (io.hasNextLine()) {
                lineText = io.nextLine();
                System.out.println(lineText);
                String[] data = lineText.split(",");
                System.out.println("1");
                String semester = data[0];
                String headInvigilatorId = data[1];
                String headInvigilatorName = data[2];
                String courseCode = data[3];
                String courseName = data[4];
                String mode = data[5];
                int numOfStudents = Integer.parseInt(data[6]);
                String password1 = data[7];
                String password2 = data[8];
                String examDate = data[9];
                String examTime = data[10];
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

}

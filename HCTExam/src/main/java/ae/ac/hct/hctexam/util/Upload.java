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
import java.util.ArrayList;
import java.util.List;
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
            List<String> userList = new ArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM appuser");
            while (rs.next()) {
                userList.add(rs.getString("id"));
            }
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO exam (semester, head_inv, course_code, delivery, student_count, password1, password2, exam_date, start_time) VALUES (?,?,?,?,?,?,?,?,?)");
            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO appuser (id, role_id, password) VALUES (?,?,?)");
            Scanner io = new Scanner(new FileInputStream("d:/temp/exam.csv"));
            String lineText;
            while (io.hasNextLine()) {
                lineText = io.nextLine();
                System.out.println(lineText);
                String[] data = lineText.split(",");
                String semester = data[0];
                String headInvigilatorId = data[1];
                String headInvigilatorName = data[2];
                String courseCode = data[3];
                String mode = data[4];
                int numOfStudents = Integer.parseInt(data[5]);
                String password1 = data[6];
                String password2 = data[7];
                String examDate = data[8];
                String examTime = data[9];
                stmt.setString(1, semester);
                stmt.setString(2, headInvigilatorId);
                stmt.setString(3, courseCode);
                stmt.setString(4, mode);
                stmt.setInt(5, numOfStudents);
                stmt.setString(6, password1);
                stmt.setString(7, password2);
                stmt.setString(8, examDate);
                stmt.setString(9, examTime);
                stmt.executeUpdate();
                if (!userList.contains(headInvigilatorId)) {
                    stmt2.setString(1, headInvigilatorId);
                    stmt2.setInt(2, 2);
                    stmt2.setString(3, "changeme!");
                    stmt2.executeUpdate();
                }
            }
            conn.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

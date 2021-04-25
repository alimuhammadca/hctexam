/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amuhammad1
 */
@Entity
@Table(name = "exam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e"),
    @NamedQuery(name = "Exam.findById", query = "SELECT e FROM Exam e WHERE e.id = :id"),
    @NamedQuery(name = "Exam.findBySemester", query = "SELECT e FROM Exam e WHERE e.semester = :semester"),
    @NamedQuery(name = "Exam.findByHeadInv", query = "SELECT e FROM Exam e, Appuser a WHERE e.headInv = :headInv"),
    @NamedQuery(name = "Exam.findByInv1", query = "SELECT e FROM Exam e WHERE e.inv1 = :inv1"),
    @NamedQuery(name = "Exam.findByInv2", query = "SELECT e FROM Exam e WHERE e.inv2 = :inv2"),
    @NamedQuery(name = "Exam.findByInv3", query = "SELECT e FROM Exam e WHERE e.inv3 = :inv3"),
    @NamedQuery(name = "Exam.findByBkupInv", query = "SELECT e FROM Exam e WHERE e.bkupInv = :bkupInv"),
    @NamedQuery(name = "Exam.findByFloater", query = "SELECT e FROM Exam e WHERE e.floater = :floater"),
//    @NamedQuery(name = "Exam.findByCourseCode", query = "SELECT e FROM Exam e WHERE e.courseCode = :courseCode"),
//    @NamedQuery(name = "Exam.findByCourseCode", query = "SELECT e FROM Exam e WHERE e.course.courseCode = :courseCode"),
    @NamedQuery(name = "Exam.findByDelivery", query = "SELECT e FROM Exam e WHERE e.delivery = :delivery"),
    @NamedQuery(name = "Exam.findByStudentCount", query = "SELECT e FROM Exam e WHERE e.studentCount = :studentCount"),
    @NamedQuery(name = "Exam.findByExamDate", query = "SELECT e FROM Exam e WHERE e.examDate = :examDate"),
    @NamedQuery(name = "Exam.findByRegTime", query = "SELECT e FROM Exam e WHERE e.regTime = :regTime"),
    @NamedQuery(name = "Exam.findByStartTime", query = "SELECT e FROM Exam e WHERE e.startTime = :startTime"),
    @NamedQuery(name = "Exam.findByEndTime", query = "SELECT e FROM Exam e WHERE e.endTime = :endTime"),
    @NamedQuery(name = "Exam.findByBuilding", query = "SELECT e FROM Exam e WHERE e.building = :building"),
    @NamedQuery(name = "Exam.findByRoom", query = "SELECT e FROM Exam e WHERE e.room = :room"),
    @NamedQuery(name = "Exam.findByCampus", query = "SELECT e FROM Exam e WHERE e.campus = :campus"),
    @NamedQuery(name = "Exam.findByPassword1", query = "SELECT e FROM Exam e WHERE e.password1 = :password1"),
    @NamedQuery(name = "Exam.findByPassword2", query = "SELECT e FROM Exam e WHERE e.password2 = :password2")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "semester")
    private String semester;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "head_inv")
    private String headInv;
    @Size(max = 25)
    @Column(name = "inv_1")
    private String inv1;
    @Size(max = 25)
    @Column(name = "inv_2")
    private String inv2;
    @Size(max = 25)
    @Column(name = "inv_3")
    private String inv3;
    @Size(max = 25)
    @Column(name = "bkup_inv")
    private String bkupInv;
    @Size(max = 25)
    @Column(name = "floater")
    private String floater;
    
    @OneToOne
    @JoinColumn(name = "course_code")
    private Course course;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "delivery")
    private String delivery;
    @Basic(optional = false)
    @NotNull
    @Column(name = "student_count")
    private int studentCount;
    @Size(max = 25)
    @Column(name = "exam_date")
    private String examDate;
    @Size(max = 25)
    @Column(name = "reg_time")
    private String regTime;
    @Size(max = 25)
    @Column(name = "start_time")
    private String startTime;
    @Size(max = 25)
    @Column(name = "end_time")
    private String endTime;
    @Size(max = 25)
    @Column(name = "building")
    private String building;
    @Size(max = 25)
    @Column(name = "room")
    private String room;
    @Size(max = 25)
    @Column(name = "campus")
    private String campus;
    @Size(max = 20)
    @Column(name = "password1")
    private String password1;
    @Size(max = 20)
    @Column(name = "password2")
    private String password2;
    @Size(max = 30)
    @Column(name = "head_inv_name")
    private String headInvName;

    public Exam() {
    }

    public Exam(Integer id) {
        this.id = id;
    }
    public Exam(Integer id, String semester, String headInv, Course course, String delivery, int studentCount) {
        this.id = id;
        this.semester = semester;
        this.headInv = headInv;
        this.course = course;
        this.delivery = delivery;
        this.studentCount = studentCount;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getHeadInv() {
        return headInv;
    }

    public void setHeadInv(String headInv) {
        this.headInv = headInv;
    }

    public String getInv1() {
        return inv1;
    }

    public void setInv1(String inv1) {
        this.inv1 = inv1;
    }

    public String getInv2() {
        return inv2;
    }

    public void setInv2(String inv2) {
        this.inv2 = inv2;
    }

    public String getInv3() {
        return inv3;
    }

    public void setInv3(String inv3) {
        this.inv3 = inv3;
    }

    public String getBkupInv() {
        return bkupInv;
    }

    public void setBkupInv(String bkupInv) {
        this.bkupInv = bkupInv;
    }

    public String getFloater() {
        return floater;
    }

    public void setFloater(String floater) {
        this.floater = floater;
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getHeadInvName() {
        return headInvName;
    }

    public void setHeadInvName(String headInvName) {
        this.headInvName = headInvName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.ac.hct.hctexam.Exam[ id=" + id + " ]";
    }
    
}

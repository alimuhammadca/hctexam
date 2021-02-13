/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ae.ac.hct.hctexam;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amuhammad1
 */
@Entity
@Table(name = "irr_form")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IrrForm.findAll", query = "SELECT i FROM IrrForm i"),
    @NamedQuery(name = "IrrForm.findById", query = "SELECT i FROM IrrForm i WHERE i.id = :id"),
    @NamedQuery(name = "IrrForm.findBySemester", query = "SELECT i FROM IrrForm i WHERE i.semester = :semester"),
    @NamedQuery(name = "IrrForm.findByCourseName", query = "SELECT i FROM IrrForm i WHERE i.courseName = :courseName"),
    @NamedQuery(name = "IrrForm.findByCourseCode", query = "SELECT i FROM IrrForm i WHERE i.courseCode = :courseCode"),
    @NamedQuery(name = "IrrForm.findByExamDate", query = "SELECT i FROM IrrForm i WHERE i.examDate = :examDate"),
    @NamedQuery(name = "IrrForm.findByExamTime", query = "SELECT i FROM IrrForm i WHERE i.examTime = :examTime"),
    @NamedQuery(name = "IrrForm.findByCampus", query = "SELECT i FROM IrrForm i WHERE i.campus = :campus"),
    @NamedQuery(name = "IrrForm.findByHeadInv", query = "SELECT i FROM IrrForm i WHERE i.headInv = :headInv"),
    @NamedQuery(name = "IrrForm.findByInv1", query = "SELECT i FROM IrrForm i WHERE i.inv1 = :inv1"),
    @NamedQuery(name = "IrrForm.findByInv2", query = "SELECT i FROM IrrForm i WHERE i.inv2 = :inv2"),
    @NamedQuery(name = "IrrForm.findByInv3", query = "SELECT i FROM IrrForm i WHERE i.inv3 = :inv3")})
public class IrrForm implements Serializable {

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
    @Size(max = 50)
    @Column(name = "course_name")
    private String courseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "course_code")
    private String courseCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "exam_date")
    private String examDate;
    @Size(max = 25)
    @Column(name = "exam_time")
    private String examTime;
    @Size(max = 25)
    @Column(name = "campus")
    private String campus;
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

    public IrrForm() {
    }

    public IrrForm(Integer id) {
        this.id = id;
    }

    public IrrForm(Integer id, String semester, String courseCode, String examDate, String headInv) {
        this.id = id;
        this.semester = semester;
        this.courseCode = courseCode;
        this.examDate = examDate;
        this.headInv = headInv;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IrrForm)) {
            return false;
        }
        IrrForm other = (IrrForm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.ac.hct.hctexam.IrrForm[ id=" + id + " ]";
    }
    
}

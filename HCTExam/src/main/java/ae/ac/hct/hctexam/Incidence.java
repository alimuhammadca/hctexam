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
@Table(name = "incidence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidence.findAll", query = "SELECT i FROM Incidence i"),
    @NamedQuery(name = "Incidence.findById", query = "SELECT i FROM Incidence i WHERE i.id = :id"),
    @NamedQuery(name = "Incidence.findByExamId", query = "SELECT i FROM Incidence i WHERE i.examId = :examId"),
    @NamedQuery(name = "Incidence.findByStudentName", query = "SELECT i FROM Incidence i WHERE i.studentName = :studentName"),
    @NamedQuery(name = "Incidence.findByStudentId", query = "SELECT i FROM Incidence i WHERE i.studentId = :studentId"),
    @NamedQuery(name = "Incidence.findByIncidenceTime", query = "SELECT i FROM Incidence i WHERE i.incidenceTime = :incidenceTime"),
    @NamedQuery(name = "Incidence.findByDeskNumber", query = "SELECT i FROM Incidence i WHERE i.deskNumber = :deskNumber"),
    @NamedQuery(name = "Incidence.findByObservation", query = "SELECT i FROM Incidence i WHERE i.observation = :observation"),
    @NamedQuery(name = "Incidence.findByEvidence", query = "SELECT i FROM Incidence i WHERE i.evidence = :evidence")})
public class Incidence implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "exam_id")
    private Integer examId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "student_name")
    private String studentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "student_id")
    private String studentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "incidence_time")
    private String incidenceTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "desk_number")
    private int deskNumber;
    @Size(max = 255)
    @Column(name = "observation")
    private String observation;
    @Size(max = 255)
    @Column(name = "evidence")
    private String evidence;

    public Incidence() {
    }

    public Incidence(Integer id) {
        this.id = id;
    }

    public Incidence(Integer id, String studentName, String studentId, String incidenceTime, int deskNumber) {
        this.id = id;
        this.studentName = studentName;
        this.studentId = studentId;
        this.incidenceTime = incidenceTime;
        this.deskNumber = deskNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getIncidenceTime() {
        return incidenceTime;
    }

    public void setIncidenceTime(String incidenceTime) {
        this.incidenceTime = incidenceTime;
    }

    public int getDeskNumber() {
        return deskNumber;
    }

    public void setDeskNumber(int deskNumber) {
        this.deskNumber = deskNumber;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getEvidence() {
        return evidence;
    }

    public void setEvidence(String evidence) {
        this.evidence = evidence;
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
        if (!(object instanceof Incidence)) {
            return false;
        }
        Incidence other = (Incidence) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ae.ac.hct.hctexam.Incidence[ id=" + id + " ]";
    }
    
}

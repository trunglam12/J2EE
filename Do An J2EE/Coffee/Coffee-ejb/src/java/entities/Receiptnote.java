/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHRIST
 */
@Entity
@Table(name = "receiptnote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receiptnote.findAll", query = "SELECT r FROM Receiptnote r"),
    @NamedQuery(name = "Receiptnote.findByReceiptNoteId", query = "SELECT r FROM Receiptnote r WHERE r.receiptNoteId = :receiptNoteId"),
    @NamedQuery(name = "Receiptnote.findByDate", query = "SELECT r FROM Receiptnote r WHERE r.date = :date"),
    @NamedQuery(name = "Receiptnote.findByMonth", query = "SELECT r FROM Receiptnote r WHERE FUNC('MONTH',r.date) = :month AND FUNC('YEAR',r.date) = :year"),
    @NamedQuery(name = "Receiptnote.findByTotalCount", query = "SELECT r FROM Receiptnote r WHERE r.totalCount = :totalCount")})
public class Receiptnote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReceiptNoteId")
    private Integer receiptNoteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalCount")
    private int totalCount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiptnote")
    private List<Detailreceiptnote> detailreceiptnoteList;
    @JoinColumn(name = "ProviderId", referencedColumnName = "ProviderId")
    @ManyToOne(optional = false)
    private Provider providerId;

    public Receiptnote() {
    }

    public Receiptnote(Integer receiptNoteId) {
        this.receiptNoteId = receiptNoteId;
    }

    public Receiptnote(Integer receiptNoteId, Date date, int totalCount) {
        this.receiptNoteId = receiptNoteId;
        this.date = date;
        this.totalCount = totalCount;
    }

    public Integer getReceiptNoteId() {
        return receiptNoteId;
    }

    public void setReceiptNoteId(Integer receiptNoteId) {
        this.receiptNoteId = receiptNoteId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @XmlTransient
    public List<Detailreceiptnote> getDetailreceiptnoteList() {
        return detailreceiptnoteList;
    }

    public void setDetailreceiptnoteList(List<Detailreceiptnote> detailreceiptnoteList) {
        this.detailreceiptnoteList = detailreceiptnoteList;
    }

    public Provider getProviderId() {
        return providerId;
    }

    public void setProviderId(Provider providerId) {
        this.providerId = providerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptNoteId != null ? receiptNoteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receiptnote)) {
            return false;
        }
        Receiptnote other = (Receiptnote) object;
        if ((this.receiptNoteId == null && other.receiptNoteId != null) || (this.receiptNoteId != null && !this.receiptNoteId.equals(other.receiptNoteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Receiptnote[ receiptNoteId=" + receiptNoteId + " ]";
    }
    
}

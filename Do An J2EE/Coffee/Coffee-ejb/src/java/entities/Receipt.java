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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHRIST
 */
@Entity
@Table(name = "receipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
    @NamedQuery(name = "Receipt.findByReceiptId", query = "SELECT r FROM Receipt r WHERE r.receiptId = :receiptId"),
    @NamedQuery(name = "Receipt.findByDate", query = "SELECT r FROM Receipt r WHERE r.date = :date"),
    @NamedQuery(name = "Receipt.findByMonth", query = "SELECT r FROM Receipt r WHERE FUNC('MONTH',r.date) = :month AND FUNC('YEAR',r.date) = :year"),
    @NamedQuery(name = "Receipt.findByCustomerName", query = "SELECT r FROM Receipt r WHERE r.customerName = :customerName"),
    @NamedQuery(name = "Receipt.findByTotalPrice", query = "SELECT r FROM Receipt r WHERE r.totalPrice = :totalPrice"),
    @NamedQuery(name = "Receipt.findByStatus", query = "SELECT r FROM Receipt r WHERE r.status = :status")})
public class Receipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReceiptId")
    private Integer receiptId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CustomerName")
    private String customerName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TotalPrice")
    private long totalPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @JoinColumn(name = "EmployeeId", referencedColumnName = "EmployeeId")
    @ManyToOne(optional = false)
    private Employee employeeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receipt")
    private List<Detailreceipt> detailreceiptList;
    @OneToMany(mappedBy = "receiptId")
    private List<Tablecoffee> tablecoffeeList;

    public Receipt() {
    }

    public Receipt(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Receipt(Integer receiptId, Date date, String customerName, long totalPrice, boolean status) {
        this.receiptId = receiptId;
        this.date = date;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @XmlTransient
    public List<Detailreceipt> getDetailreceiptList() {
        return detailreceiptList;
    }

    public void setDetailreceiptList(List<Detailreceipt> detailreceiptList) {
        this.detailreceiptList = detailreceiptList;
    }

    @XmlTransient
    public List<Tablecoffee> getTablecoffeeList() {
        return tablecoffeeList;
    }

    public void setTablecoffeeList(List<Tablecoffee> tablecoffeeList) {
        this.tablecoffeeList = tablecoffeeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (receiptId != null ? receiptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.receiptId == null && other.receiptId != null) || (this.receiptId != null && !this.receiptId.equals(other.receiptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Receipt[ receiptId=" + receiptId + " ]";
    }
    
}

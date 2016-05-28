/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Lam
 */
@Entity
@Table(name = "tablecoffee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tablecoffee.findAll", query = "SELECT t FROM Tablecoffee t"),
    @NamedQuery(name = "Tablecoffee.findByTableId", query = "SELECT t FROM Tablecoffee t WHERE t.tableId = :tableId"),
    @NamedQuery(name = "Tablecoffee.findByTableName", query = "SELECT t FROM Tablecoffee t WHERE t.tableName = :tableName"),
    @NamedQuery(name = "Tablecoffee.findByStatus", query = "SELECT t FROM Tablecoffee t WHERE t.status = :status"),
    @NamedQuery(name = "Tablecoffee.findByProductIds", query = "SELECT t FROM Tablecoffee t WHERE t.productIds = :productIds")})
public class Tablecoffee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TableId")
    private Integer tableId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TableName")
    private String tableName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 20)
    @Column(name = "ProductIds")
    private String productIds;
    @OneToMany(mappedBy = "tableId")
    private List<Receipt> receiptList;

    public Tablecoffee() {
    }

    public Tablecoffee(Integer tableId) {
        this.tableId = tableId;
    }

    public Tablecoffee(Integer tableId, String tableName, boolean status) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.status = status;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    @XmlTransient
    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableId != null ? tableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tablecoffee)) {
            return false;
        }
        Tablecoffee other = (Tablecoffee) object;
        if ((this.tableId == null && other.tableId != null) || (this.tableId != null && !this.tableId.equals(other.tableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tablecoffee[ tableId=" + tableId + " ]";
    }
    
}

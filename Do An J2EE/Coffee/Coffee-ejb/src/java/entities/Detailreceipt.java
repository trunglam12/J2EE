/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CHRIST
 */
@Entity
@Table(name = "detailreceipt")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailreceipt.findAll", query = "SELECT d FROM Detailreceipt d"),
    @NamedQuery(name = "Detailreceipt.findByReceiptId", query = "SELECT d FROM Detailreceipt d WHERE d.detailreceiptPK.receiptId = :receiptId"),
    @NamedQuery(name = "Detailreceipt.findByProductId", query = "SELECT d FROM Detailreceipt d WHERE d.detailreceiptPK.productId = :productId"),
    @NamedQuery(name = "Detailreceipt.findByReceiptIdAndProductId", query = "SELECT d FROM Detailreceipt d WHERE d.detailreceiptPK.receiptId = :receiptId AND d.product.productId = :productId"),
    @NamedQuery(name = "Detailreceipt.findByCount", query = "SELECT d FROM Detailreceipt d WHERE d.count = :count")})
public class Detailreceipt implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailreceiptPK detailreceiptPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Count")
    private int count;
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "ReceiptId", referencedColumnName = "ReceiptId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Receipt receipt;

    public Detailreceipt() {
    }

    public Detailreceipt(DetailreceiptPK detailreceiptPK) {
        this.detailreceiptPK = detailreceiptPK;
    }

    public Detailreceipt(DetailreceiptPK detailreceiptPK, int count) {
        this.detailreceiptPK = detailreceiptPK;
        this.count = count;
    }

    public Detailreceipt(int receiptId, int productId) {
        this.detailreceiptPK = new DetailreceiptPK(receiptId, productId);
    }

    public DetailreceiptPK getDetailreceiptPK() {
        return detailreceiptPK;
    }

    public void setDetailreceiptPK(DetailreceiptPK detailreceiptPK) {
        this.detailreceiptPK = detailreceiptPK;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailreceiptPK != null ? detailreceiptPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailreceipt)) {
            return false;
        }
        Detailreceipt other = (Detailreceipt) object;
        if ((this.detailreceiptPK == null && other.detailreceiptPK != null) || (this.detailreceiptPK != null && !this.detailreceiptPK.equals(other.detailreceiptPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Detailreceipt[ detailreceiptPK=" + detailreceiptPK + " ]";
    }
    
}

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
@Table(name = "detailreceiptnote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailreceiptnote.findAll", query = "SELECT d FROM Detailreceiptnote d"),
    @NamedQuery(name = "Detailreceiptnote.findByReceiptNoteId", query = "SELECT d FROM Detailreceiptnote d WHERE d.detailreceiptnotePK.receiptNoteId = :receiptNoteId"),
    @NamedQuery(name = "Detailreceiptnote.findByProductId", query = "SELECT d FROM Detailreceiptnote d WHERE d.detailreceiptnotePK.productId = :productId"),
    @NamedQuery(name = "Detailreceiptnote.findByPrice", query = "SELECT d FROM Detailreceiptnote d WHERE d.price = :price"),
    @NamedQuery(name = "Detailreceiptnote.findByCount", query = "SELECT d FROM Detailreceiptnote d WHERE d.count = :count"),
    @NamedQuery(name = "Detailreceiptnote.findByReceiptnoteIdAndProductId", query = "SELECT d FROM Detailreceiptnote d WHERE d.detailreceiptnotePK = :receiptnoteId AND d.product.productId = :productId"),})
public class Detailreceiptnote implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailreceiptnotePK detailreceiptnotePK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    protected long price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Count")
    protected int count;
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Product product;
    @JoinColumn(name = "ReceiptNoteId", referencedColumnName = "ReceiptNoteId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    protected Receiptnote receiptnote;

    public Detailreceiptnote() {
    }

    public Detailreceiptnote(DetailreceiptnotePK detailreceiptnotePK) {
        this.detailreceiptnotePK = detailreceiptnotePK;
    }

    public Detailreceiptnote(DetailreceiptnotePK detailreceiptnotePK, long price, int count) {
        this.detailreceiptnotePK = detailreceiptnotePK;
        this.price = price;
        this.count = count;
    }

    public Detailreceiptnote(int receiptNoteId, int productId) {
        this.detailreceiptnotePK = new DetailreceiptnotePK(receiptNoteId, productId);
    }

    public DetailreceiptnotePK getDetailreceiptnotePK() {
        return detailreceiptnotePK;
    }

    public void setDetailreceiptnotePK(DetailreceiptnotePK detailreceiptnotePK) {
        this.detailreceiptnotePK = detailreceiptnotePK;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    public Receiptnote getReceiptnote() {
        return receiptnote;
    }

    public void setReceiptnote(Receiptnote receiptnote) {
        this.receiptnote = receiptnote;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailreceiptnotePK != null ? detailreceiptnotePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailreceiptnote)) {
            return false;
        }
        Detailreceiptnote other = (Detailreceiptnote) object;
        if ((this.detailreceiptnotePK == null && other.detailreceiptnotePK != null) || (this.detailreceiptnotePK != null && !this.detailreceiptnotePK.equals(other.detailreceiptnotePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Detailreceiptnote[ detailreceiptnotePK=" + detailreceiptnotePK + " ]";
    }
    
}

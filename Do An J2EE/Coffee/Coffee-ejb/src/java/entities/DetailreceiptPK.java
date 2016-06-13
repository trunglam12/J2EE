/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author CHRIST
 */
@Embeddable
public class DetailreceiptPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReceiptId")
    private int receiptId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductId")
    private int productId;

    public DetailreceiptPK() {
    }

    public DetailreceiptPK(int receiptId, int productId) {
        this.receiptId = receiptId;
        this.productId = productId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) receiptId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailreceiptPK)) {
            return false;
        }
        DetailreceiptPK other = (DetailreceiptPK) object;
        if (this.receiptId != other.receiptId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DetailreceiptPK[ receiptId=" + receiptId + ", productId=" + productId + " ]";
    }
    
}

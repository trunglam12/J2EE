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
public class DetailreceiptnotePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReceiptNoteId")
    private int receiptNoteId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductId")
    private int productId;

    public DetailreceiptnotePK() {
    }

    public DetailreceiptnotePK(int receiptNoteId, int productId) {
        this.receiptNoteId = receiptNoteId;
        this.productId = productId;
    }

    public int getReceiptNoteId() {
        return receiptNoteId;
    }

    public void setReceiptNoteId(int receiptNoteId) {
        this.receiptNoteId = receiptNoteId;
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
        hash += (int) receiptNoteId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailreceiptnotePK)) {
            return false;
        }
        DetailreceiptnotePK other = (DetailreceiptnotePK) object;
        if (this.receiptNoteId != other.receiptNoteId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DetailreceiptnotePK[ receiptNoteId=" + receiptNoteId + ", productId=" + productId + " ]";
    }
    
}

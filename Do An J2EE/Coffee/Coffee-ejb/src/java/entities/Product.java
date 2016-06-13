/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author CHRIST
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByProductId", query = "SELECT p FROM Product p WHERE p.productId = :productId"),
    @NamedQuery(name = "Product.findByProductName", query = "SELECT p FROM Product p WHERE p.productName = :productName"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByCount", query = "SELECT p FROM Product p WHERE p.count = :count"),
    @NamedQuery(name = "Product.findByStatus", query = "SELECT p FROM Product p WHERE p.status = :status"),
    @NamedQuery(name = "Product.findByImage", query = "SELECT p FROM Product p WHERE p.image = :image"),
    @NamedQuery(name = "Product.findByOgrinalPrice", query = "SELECT p FROM Product p WHERE p.ogrinalPrice = :ogrinalPrice")})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ProductId")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ProductName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Price")
    private long price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Count")
    private int count;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status;
    @Size(max = 100)
    @Column(name = "Image")
    private String image;
    @Column(name = "OgrinalPrice")
    private Long ogrinalPrice;
    @JoinColumn(name = "GroupId", referencedColumnName = "GroupId")
    @ManyToOne(optional = false)
    private Groupproduct groupId;
    @JoinColumn(name = "UnitId", referencedColumnName = "UnitId")
    @ManyToOne(optional = false)
    private Unit unitId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Detailreceiptnote> detailreceiptnoteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Detailreceipt> detailreceiptList;

    public Product() {
    }

    public Product(Integer productId) {
        this.productId = productId;
    }

    public Product(Integer productId, String productName, long price, int count, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.count = count;
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getOgrinalPrice() {
        return ogrinalPrice;
    }

    public void setOgrinalPrice(Long ogrinalPrice) {
        this.ogrinalPrice = ogrinalPrice;
    }

    public Groupproduct getGroupId() {
        return groupId;
    }

    public void setGroupId(Groupproduct groupId) {
        this.groupId = groupId;
    }

    public Unit getUnitId() {
        return unitId;
    }

    public void setUnitId(Unit unitId) {
        this.unitId = unitId;
    }

    @XmlTransient
    public List<Detailreceiptnote> getDetailreceiptnoteList() {
        return detailreceiptnoteList;
    }

    public void setDetailreceiptnoteList(List<Detailreceiptnote> detailreceiptnoteList) {
        this.detailreceiptnoteList = detailreceiptnoteList;
    }

    @XmlTransient
    public List<Detailreceipt> getDetailreceiptList() {
        return detailreceiptList;
    }

    public void setDetailreceiptList(List<Detailreceipt> detailreceiptList) {
        this.detailreceiptList = detailreceiptList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Product[ productId=" + productId + " ]";
    }
    
}

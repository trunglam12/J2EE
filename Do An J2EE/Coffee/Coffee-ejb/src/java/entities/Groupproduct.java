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
@Table(name = "groupproduct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupproduct.findAll", query = "SELECT g FROM Groupproduct g"),
    @NamedQuery(name = "Groupproduct.findByGroupId", query = "SELECT g FROM Groupproduct g WHERE g.groupId = :groupId"),
    @NamedQuery(name = "Groupproduct.findByGroupName", query = "SELECT g FROM Groupproduct g WHERE g.groupName = :groupName")})
public class Groupproduct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GroupId")
    private Integer groupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GroupName")
    private String groupName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupId")
    private List<Product> productList;

    public Groupproduct() {
    }

    public Groupproduct(Integer groupId) {
        this.groupId = groupId;
    }

    public Groupproduct(Integer groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupId != null ? groupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupproduct)) {
            return false;
        }
        Groupproduct other = (Groupproduct) object;
        if ((this.groupId == null && other.groupId != null) || (this.groupId != null && !this.groupId.equals(other.groupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Groupproduct[ groupId=" + groupId + " ]";
    }
    
}

package entities;

import entities.Detailreceipt;
import entities.Detailreceiptnote;
import entities.Groupproduct;
import entities.Unit;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-29T00:19:53")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Integer> productId;
    public static volatile SingularAttribute<Product, Long> price;
    public static volatile SingularAttribute<Product, Groupproduct> groupId;
    public static volatile SingularAttribute<Product, Integer> count;
    public static volatile SingularAttribute<Product, Unit> unitId;
    public static volatile ListAttribute<Product, Detailreceiptnote> detailreceiptnoteList;
    public static volatile ListAttribute<Product, Detailreceipt> detailreceiptList;
    public static volatile SingularAttribute<Product, String> productName;
    public static volatile SingularAttribute<Product, Boolean> status;

}
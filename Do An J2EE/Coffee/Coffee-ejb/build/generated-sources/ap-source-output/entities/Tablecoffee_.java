package entities;

import entities.Receipt;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-29T00:19:53")
@StaticMetamodel(Tablecoffee.class)
public class Tablecoffee_ { 

    public static volatile ListAttribute<Tablecoffee, Receipt> receiptList;
    public static volatile SingularAttribute<Tablecoffee, String> productIds;
    public static volatile SingularAttribute<Tablecoffee, Integer> tableId;
    public static volatile SingularAttribute<Tablecoffee, String> tableName;
    public static volatile SingularAttribute<Tablecoffee, Boolean> status;

}
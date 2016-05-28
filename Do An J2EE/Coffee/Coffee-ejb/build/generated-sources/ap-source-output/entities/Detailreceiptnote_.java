package entities;

import entities.DetailreceiptnotePK;
import entities.Product;
import entities.Receiptnote;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-29T00:19:53")
@StaticMetamodel(Detailreceiptnote.class)
public class Detailreceiptnote_ { 

    public static volatile SingularAttribute<Detailreceiptnote, Product> product;
    public static volatile SingularAttribute<Detailreceiptnote, Long> price;
    public static volatile SingularAttribute<Detailreceiptnote, DetailreceiptnotePK> detailreceiptnotePK;
    public static volatile SingularAttribute<Detailreceiptnote, Integer> count;
    public static volatile SingularAttribute<Detailreceiptnote, Receiptnote> receiptnote;

}
package entities;

import entities.Receiptnote;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-29T00:19:53")
@StaticMetamodel(Provider.class)
public class Provider_ { 

    public static volatile SingularAttribute<Provider, String> address;
    public static volatile SingularAttribute<Provider, Integer> providerId;
    public static volatile ListAttribute<Provider, Receiptnote> receiptnoteList;
    public static volatile SingularAttribute<Provider, String> providerName;

}
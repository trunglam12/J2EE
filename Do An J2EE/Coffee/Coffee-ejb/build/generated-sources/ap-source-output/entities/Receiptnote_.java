package entities;

import entities.Detailreceiptnote;
import entities.Provider;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-05-29T00:19:53")
@StaticMetamodel(Receiptnote.class)
public class Receiptnote_ { 

    public static volatile SingularAttribute<Receiptnote, Date> date;
    public static volatile SingularAttribute<Receiptnote, Provider> providerId;
    public static volatile ListAttribute<Receiptnote, Detailreceiptnote> detailreceiptnoteList;
    public static volatile SingularAttribute<Receiptnote, Integer> receiptNoteId;
    public static volatile SingularAttribute<Receiptnote, Integer> totalCount;

}
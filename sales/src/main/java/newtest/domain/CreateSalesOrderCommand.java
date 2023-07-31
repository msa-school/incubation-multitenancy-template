package newtest.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Data
public class CreateSalesOrderCommand {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private String salesPerson;
        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private String companyId;
        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private String salesType;
        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private List<SalesItem> salesItems;


}

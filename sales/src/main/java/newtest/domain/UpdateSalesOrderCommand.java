package newtest.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Data
public class UpdateSalesOrderCommand {

        private String salesPerson;
        private String companyId;
        private String salesType;
        private List<SalesItem> salesItems;


}

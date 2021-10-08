package bewerbung.RESTController;

import org.springframework.stereotype.Repository;

@Repository
public interface DBConnection {

    ObjectData findById(Integer objectId);

}

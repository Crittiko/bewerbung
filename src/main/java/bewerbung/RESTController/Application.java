package bewerbung.RESTController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("object")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Map fuers testen
    Map<Integer, ObjectData> objects;

    // DB Connection
    //private DBConnection dbConnection;

    @GetMapping
    public ResponseEntity<ObjectData> getObject(Integer objectId){
        return getObjectById(objectId);
    }

    public ResponseEntity<ObjectData> getObjectById(Integer objectId){

        // Mit MAP
        if(objects.containsKey(objectId)) {
            return new ResponseEntity<>(objects.get(objectId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // Mit DB
        //return dbConnection.findById(objectId);
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<ObjectData> createObject(@RequestBody ObjectData objectDataNew)
    {
        ObjectData objectData = new ObjectData();
        objectData.setObjectId(objectDataNew.getObjectId());
        objectData.setObjectName(objectDataNew.getObjectName());
        objectData.setObjectRNG( (int) (Math.random() * ((100 - 1) + 1)) + 1);

        if (objects == null) {
            objects = new HashMap<>();
            objects.put(objectDataNew.getObjectId(), objectData);
        }

        return new ResponseEntity<>(objectData, HttpStatus.CREATED);
    }

}
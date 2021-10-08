package bewerbung.RESTController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("object")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping
    public ObjectData getObject()
    {
        ObjectData objectData = new ObjectData();
        objectData.setObjectId(1);
        objectData.setObjectName("Banana");
        objectData.setObjectRNG( (int) (Math.random() * ((100 - 1) + 1)) + 1);
        return objectData;
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<ObjectData> createObject(@RequestBody ObjectData objectDataNew)
    {
        ObjectData objectData = new ObjectData();
        objectData.setObjectId(objectDataNew.getObjectId());
        objectData.setObjectName(objectDataNew.getObjectName());
        objectData.setObjectRNG( (int) (Math.random() * ((100 - 1) + 1)) + 1);

        return new ResponseEntity<>(objectData, HttpStatus.CREATED);
    }

}
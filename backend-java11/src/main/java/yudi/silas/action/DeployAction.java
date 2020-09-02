package yudi.silas.action;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DeployAction {

    @GetMapping("deploy")
    public ResponseEntity<Map<String, Object>> action() {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        response.put("success", true);
        response.put("datetime", Calendar.getInstance().getTime().toString());

        return new ResponseEntity<>(response, status);
    }
}

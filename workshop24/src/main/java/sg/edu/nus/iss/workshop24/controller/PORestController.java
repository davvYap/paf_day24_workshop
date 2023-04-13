package sg.edu.nus.iss.workshop24.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.workshop24.service.PODetailsService;
import sg.edu.nus.iss.workshop24.service.POService;

@RestController
public class PORestController {

    @Autowired
    PODetailsService poDetailsService;

    @Autowired
    POService poService;

    // @GetMapping(path = "/all")
    // public ResponseEntity<String> getAllPo() {
    // JsonArrayBuilder jsonArr = Json.createArrayBuilder();
    // List<JsonObjectBuilder> listOfPo = poService.getAllPo().stream()
    // .map(po -> po.toJSONObject())
    // .toList();
    // for (JsonObjectBuilder jsonObjectBuilder : listOfPo) {
    // jsonArr.add(jsonObjectBuilder);
    // }

    // return ResponseEntity.status(HttpStatus.OK)
    // .contentType(MediaType.APPLICATION_JSON)
    // .body(jsonArr.build().toString());
    // }

    @GetMapping(path = "/all")
    public ResponseEntity<String> getAllPo() {
        JsonArrayBuilder productArray = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfPo = poService.getAllPo().stream()
                .map(po -> po.toJSONObjectProduct())
                .toList();
        for (JsonObjectBuilder jsonObjectBuilder : listOfPo) {
            productArray.add(jsonObjectBuilder);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productArray.build().toString());
    }

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<String> getPODetails(@PathVariable int orderId) {
        JsonArrayBuilder jsonArr = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfPo = poService.getPo(orderId).stream()
                .map(po -> po.toJSONObject())
                .toList();
        for (JsonObjectBuilder jsonObjectBuilder : listOfPo) {
            jsonArr.add(jsonObjectBuilder);
        }

        if (listOfPo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Json.createObjectBuilder()
                            .add("Error Message", "No order id found.")
                            .build().toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonArr.build().toString());
    }

}

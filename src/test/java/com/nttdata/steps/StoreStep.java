package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;

public class StoreStep {
    private String apiUrl;
    private Response response;
    private RequestSpecification request;

    @Step("Set API URL")
    public void laAPIPetStoreEstaDisponible(String url) {
        this.apiUrl = url;
        RestAssured.baseURI = this.apiUrl;
        this.request = RestAssured.given();

        // Realizamos una petición GET a la URL base para verificar que la API está disponible
        response = this.request.when().get("/");
        // Verifica que la respuesta tenga un código 200 (OK)
        int statusCode = response.getStatusCode();
        // validamos con un assert que esté disponible
        assert statusCode == 200;
    }

    public void crearOrden(String id, String petId, String quantity, String shipDate, String status, String complete) {
        request.header("Content-Type", "application/json");
        request.body("{\n" +
                "  \"id\": " + id + ",\n" +
                "  \"petId\": " + petId + ",\n" +
                "  \"quantity\": " + quantity + ",\n" +
                "  \"shipDate\": \"" + shipDate + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"complete\": " + complete + "\n" +
                "}");
        response = request.post("/store/order");
    }

    public void validarCodigoRespuesta(int statusCode) {
        response.then().statusCode(statusCode);
    }

    public void validarRespuesta(String id, String petId, String quantity, String shipDate, String status, String complete) {
        response.then().assertThat().body("id", org.hamcrest.Matchers.equalTo(Integer.parseInt(id)));
        response.then().assertThat().body("petId", org.hamcrest.Matchers.equalTo(Integer.parseInt(petId)));
        response.then().assertThat().body("quantity", org.hamcrest.Matchers.equalTo(Integer.parseInt(quantity)));
        //response.then().assertThat().body("shipDate", org.hamcrest.Matchers.equalTo(shipDate));
        response.then().assertThat().body("status", org.hamcrest.Matchers.equalTo(status));
        response.then().assertThat().body("complete", org.hamcrest.Matchers.equalTo(Boolean.parseBoolean(complete)));

        //En el caso de ShipDate, el formato de la fecha que se envía y devuelve el diferente
        // Si ingresamos esta fecha: "2024-08-23T22:38:32" el body responde "2024-08-23T22:38:32.000+0000"
        // Podemos comparar que contenga la fecha que enviamos
        response.then().assertThat().body("shipDate", org.hamcrest.Matchers.containsString(shipDate));

    }

    public void consultarOrden(String id) {
        response = request.get("/store/order/" + id);
    }
}

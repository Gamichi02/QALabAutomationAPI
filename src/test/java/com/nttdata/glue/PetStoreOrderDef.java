package com.nttdata.glue;

import com.nttdata.steps.StoreStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreOrderDef {

    @Steps
    StoreStep store;


    @Given("la API PetStore está disponible")
    public void APIdePetStoreDisponible() {
        store.laAPIPetStoreEstaDisponible("https://petstore.swagger.io/v2");
    }


    @When("creo la orden con id {string}, petId {string}, quantity {string}, shipDate {string}, status {string}, complete {string}")
    public void crearOrden(String id, String petId, String quantity, String shipDate, String status, String complete) {
        store.crearOrden(id, petId, quantity, shipDate, status, complete);
    }

    @Then("el código de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int statusCode) {
        store.validarCodigoRespuesta(statusCode);
    }

    @And("la respuesta contiene el id {string}, petId {string}, quantity {string}, shipDate {string}, status {string}, complete {string}")
    public void validarRespuesta(String id, String petId, String quantity, String shipDate, String status, String complete) {
        store.validarRespuesta(id, petId, quantity, shipDate, status, complete);
    }

    @When("consulto la orden con id {string}")
    public void consultoLaOrdenConId(String id) {
        store.consultarOrden(id);
    }
}

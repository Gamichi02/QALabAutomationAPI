Feature: Creación y Consulta de Orden de PetStore

  @crearOrden
  Scenario Outline: Crear orden
    Given la API PetStore está disponible
    When creo la orden con id "<id>", petId "<petId>", quantity "<quantity>", shipDate "<shipDate>", status "<status>", complete "<complete>"
    Then el código de respuesta es 200
    And la respuesta contiene el id "<id>", petId "<petId>", quantity "<quantity>", shipDate "<shipDate>", status "<status>", complete "<complete>"

    Examples:
      | id | petId | quantity | shipDate | status | complete |
      | 1 | 1 | 1 | 2024-08-23T00:00:00 | placed | true |
      | 2 | 2 | 2 | 2021-09-02T00:00:00 | approved | false |
      | 3 | 3 | 3 | 2021-09-03T00:00:00 | delivered | true |

  @consultarOrden
  Scenario Outline: Consultar orden
    Given la API PetStore está disponible
    When consulto la orden con id "<id>"
    Then el código de respuesta es 200
    And la respuesta contiene el id "<id>", petId "<petId>", quantity "<quantity>", shipDate "<shipDate>", status "<status>", complete "<complete>"

    Examples:
      | id | petId | quantity | shipDate | status | complete |
      | 1 | 1 | 1 | 2024-08-23T00:00:00 | placed | true |
      | 2 | 2 | 2 | 2021-09-02T00:00:00 | approved | false |
      | 3 | 3 | 3 | 2021-09-03T00:00:00 | delivered | true |
# ReTest TPE (consignas y extras)

***

## ABM entities DONE

| Entidad      | Post   | Delete   | Update | Get      | Get All  |
|--------------|--------|----------|--------|----------|----------|
| Scooter      | passed | passed   | passed | passed   | passed   |
| Trip         | passed | passed   | passed | passed   | passed   |
| ScooterTrip  | passed | passed   | passed | passed   | passed   |
| PauseTrip    | passed | passed   | passed | not done | not done |
| ScooterStop  | passed | passed   | passed | passed   | passed   |
| Ubication    | passed | passed   | passed | passed   | passed   |
| Tariff       | passed | passed   | passed | not done | not done |
| GeneralPrice | passed | not done | passed | not done | passed   |

*Al eliminar una scooter se eliminan sus trip, scootertrip y ubicacion asociados*

*Al eliminar un trip se elimina su tariff asociada (no tiene sentido que siga existiendo sin su viaje*

*la eliminacion d elos scooterTrip se determina por la eliminacion de Scooters y trips*

*El ABM de una Ubication esta determinado por una Scooter o un ScooterStop*
*El ABM de una PauseTrip esta determinado por su Trip asociado*

*el update de generalprice esta definido por las altas de trips*

***

## Extras

### Scooter

- Registrar monopatín en mantenimiento (debe marcarse como no disponible para su uso) *PASSED*
- 
- Registrar fin de mantenimiento de monopatín *PASSED*
- 
- Ubicar monopatín en parada (opcional)
- 
- consultar los monopatines con más de X viajes en un cierto año. *PASSED*
- 
- consultar la cantidad actualmente en operación, 
- versus la cantidad actualmente en mantenimiento. *PASSED*
- 
- listado de los monopatines cercanos a mi zona, para poder encontrar
  un monopatín cerca de mi ubicación *PASSED*

***

### Trip

- consultar el total facturado en un rango de meses de cierto año. *PASSED*

***

### Tariff

- Definir precio *PASSED*
- Definir tarifa extra para reinicio por pausa extensa *PASSED*
- hacer un ajuste de precios, y que a partir de cierta fecha el sistema
  habilite los nuevos precios. *PASSED*

***

### REPORTS

- Generar reporte de uso de monopatines por kilómetros *PASSED*
- 
- Generar reporte de uso de monopatines por tiempo con pausas *PASSED*
- 
- Generar reporte de uso de monopatines por tiempo sin pausas *PASSED*

***

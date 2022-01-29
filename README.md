# drones_app
- registering a drone  -> POST http://localhost:8080/drones  then put a json with these attributes (model,weight,battery)
- registering a medication ->POST http://localhost:8080/medications  then put a json with these attributes (name,weight,imagePath ex:"images\\Cetal.jpg")
- loading a drone with medication items ->PUT http://localhost:8080/drones/{serial}/{load} set load = 1 then put array of medication jsons with (code) 
- checking loaded medication items for a given drone ->GET http://localhost:8080/drones/{serial}/{medications} medications = 1
- delevering medications -> PUT http://localhost:8080/drones/{serial}/{load} set load = 2
- checking available drones for loading -> GET http://localhost:8080/drones
- check drone battery level -> GET http://localhost:8080/drones/{serial}
- updating drone state or battery capacity PUT http://localhost:8080/drones/{serial} then put a json with one or more of these attributes (state,battery)



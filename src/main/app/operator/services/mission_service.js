var module = require('main_module');

function Service ($http) {

    this.getAllMissionsResults = function() {
       return $http.get("/api/missionsUI",{});
    };

     this.getMissionsResultsByName = function(name) {
           return $http.get("/api/missionsByName/"+name,{});
        };

    this.publish = function(orderId) {
       return $http.put("/api/order/publish/" + orderId,{});
    };

    this.redo = function(id) {
       return $http.put("/api/order/redo/" + id,{});
    };

    this.getMissionNames = function() {
               return $http.get("/api/order/names",{});
            };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);




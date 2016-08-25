var module = require('main_module');

function Service ($http) {

    this.getAllMissionsResults = function() {
       return $http.get("/api/missionsUI",{});
    };

     this.getAllMissionsResultsByOperatorID = function() {
           return $http.get("/api/missionsUIByOperator",{});
        };

    this.publish = function(orderId) {
       return $http.put("/api/order/publish/" + orderId,{});
    };

    this.redo = function(id) {
       return $http.put("/api/order/redo/" + id,{});
    };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);




var module = require('main_module');

function Service ($http) {

    this.getAllMissionsResults = function() {
       return $http.get("/api/missionsUI",{});
    };

    this.publish = function(orderId) {
       return $http.put("/api/order/publish/" + orderId,{});
    };

    this.redo = function(orderId) {
       return $http.put("/api/order/redo/" + orderId,{});
    };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);

var module = require('main_module');

function Service ($http) {

    this.getAllMissionsResults = function() {
       return $http.get("/api/missionsUI",{});
    };

     this.getOrderResultsByMissionId = function(missionId) {
           return $http.get("/api/missionsByMissionId/"+missionId,{});
        };

    this.publish = function(id) {
       return $http.put("/api/order/publish/" + id,{});
    };

    this.redo = function(id) {
       return $http.put("/api/order/redo/" + id,{});
    };

    this.getMissionIds = function() {
               return $http.get("/api/order/missionIds",{});
            };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);




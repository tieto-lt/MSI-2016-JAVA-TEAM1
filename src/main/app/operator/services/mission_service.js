var module = require('main_module');

function Service ($http) {

    this.getAllMissionsResults = function() {
       return $http.get("/api/missionsUI",{});
    };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);

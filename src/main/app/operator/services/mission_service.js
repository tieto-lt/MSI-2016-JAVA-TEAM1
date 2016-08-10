var module = require('main_module');

function Service ($http) {

    this.getAllMissions = function() {
       return $http.get('/api/missionsUI',{});
    };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);

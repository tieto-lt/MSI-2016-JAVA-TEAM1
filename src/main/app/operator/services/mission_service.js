var module = require('main_module');

function Service ($http) {

    this.getAllMissions = function() {
       return $http.get('/api/internalMissions',{});
    };
}
Service.$inject = ['$http'];
module.service('MissionService', Service);

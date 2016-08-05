var module = require('main_module');

function Service ($http) {

    this.all = function() {
        return $http.get('/api/roles');
    };
}

Service.$inject = ['$http'];
module.service('RoleService', Service);
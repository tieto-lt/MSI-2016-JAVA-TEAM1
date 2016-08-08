var module = require('main_module');

function Service ($http) {

    this.all = function() {
        return $http.get('/api/roles');
    };

    this.update = function(role) {
        return $http.put('/api/roles/', role);
    };
}

Service.$inject = ['$http'];
module.service('RoleService', Service);
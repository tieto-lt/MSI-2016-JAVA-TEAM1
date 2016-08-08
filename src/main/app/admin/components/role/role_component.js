var module = require('main_module');

var module = require('main_module');

function Controller(RoleService) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.roles = [];

    vm.update = update;
    vm.$onInit = function() {
        _loadList();
    };

    //Underscore because private function which is not exposed in controller interface
    function _loadList() {
        RoleService.all().then(
            function (response) {
                vm.roles = response.data;
            },
            function(err) {
                console.log('Error', err);
            });
    }

    function update(index) {

        RoleService.update(vm.roles[index]).then(
            function () {
                console.log('Update success');
            },
            function (err) {
                if (err.status === 400) {
                    vm.errors = err.data;
                } else {
                    console.log('Error', err);
                }
            });
    }
}

//Angular Component Configuration
Controller.$inject = ['RoleService'];

module.component('rolesManagement', {
    controller: Controller,
    templateUrl: require('./role.html')
});
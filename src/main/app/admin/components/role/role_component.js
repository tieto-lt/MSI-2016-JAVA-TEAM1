var module = require('main_module');

var module = require('main_module');

function Controller(RoleService) {
    //Convention to call controller instance 'vm'
    var vm = this;

    vm.roles = [];

    vm.error = '';
    vm.success = '';

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
                vm.error = err.statusText;
            });
    }

    function update(index) {
        vm.error = '';
        vm.success = '';
        RoleService.update(vm.roles[index]).then(
            function () {
                console.log('Update success');
                var username = vm.roles[index].username;
                vm.success = username + " role updated successfully.";
            },
            function (err) {
                vm.error = err.statusText;
            });
    }
}

//Angular Component Configuration
Controller.$inject = ['RoleService'];

module.component('rolesManagement', {
    controller: Controller,
    templateUrl: require('./role.html')
});
var module = require('main_module');

function Controller($state, UserService) {
    var vm = this;

    vm.user = {};

    vm.create = create;
    vm.errors = [];

    function create() {
            UserService.create(vm.user).then(
                function () {
                    $state.go('root.login');
                },
                function (err) {
                    console.log('Error', err);
                }
            );
        }

}
Controller.$inject = ['$state','UserService'];

require('./user.scss');
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./user.html')
});
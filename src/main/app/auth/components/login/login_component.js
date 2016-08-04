var module = require('main_module');

function Controller($state, AuthService, Session) {

    var vm = this;
    vm.username = undefined;
    vm.password = undefined;

    vm.login = login;
    vm.goToRegistration = goToRegistration;
    vm.error = undefined;

    function login() {
        AuthService.login(vm.username, vm.password).then(
            function (response) {
                vm.error = undefined;
                var role = Session.getRole();
                if ("ADMIN" == role) {
                   $state.go('root.admin');
                }
            },
            function (err) {
                vm.error = err.data.error_description;
            });
    }
    function goToRegistration(){
        $state.go('root.registration');
    }

}

Controller.$inject = ['$state', 'AuthService', 'Session'];
require('login.scss');
module.component('login', {
    controller: Controller,
    templateUrl: require('./login.html')
});

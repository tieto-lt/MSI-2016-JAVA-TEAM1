var module = require('main_module');

function Controller($rootScope, $state, AuthService, Session) {

    var vm = this;
    vm.username = undefined;
    vm.password = undefined;

    vm.login = login;
    vm.goToRegistration = goToRegistration;
    vm.error = undefined;

    //roles
    var ROLE_ADMIN = "ROLE_ADMIN";
    var ROLE_OPERATOR ="ROLE_OPERATOR";
    var ROLE_CUSTOMER = "ROLE_CUSTOMER";

    vm.$onInit = function() {
        AuthService.redirectToHomePage();
    }

    function login() {
        AuthService.login(vm.username, vm.password).then(
            function (response) {
                vm.error = undefined;
                var role = Session.getRole();
                role = role && role[0];
                if (ROLE_ADMIN == role) {
                    $state.go('root.orders');
                } else if  (ROLE_OPERATOR == role) {
                    $state.go('root.missions');
                } else if  (ROLE_CUSTOMER == role) {
                    $state.go('root.customerOrders');
                }
                $rootScope.$emit('userLoggedIn', {});
            },
            function (err) {
                vm.error = err.data.error_description;
            });
    }
    function goToRegistration(){
        $state.go('root.registration');
    }
}

Controller.$inject = ['$rootScope', '$state', 'AuthService', 'Session'];
require('login.scss');
module.component('login', {
    controller: Controller,
    templateUrl: require('./login.html')
});

var module = require('main_module');

function Controller($state, $scope, UserService, Session, AuthService) {
    $.material.init()
    var vm = this;

    vm.user = {};
    vm.passwordRepeat = undefined;

    vm.create = create;
    vm.errors = [];
    vm.doPasswordNotMatch = doPasswordNotMatch;

    vm.$onInit = function() {
        AuthService.redirectToHomePage();
    }

    function create() {
        vm.errors = [];
        UserService.create(vm.user).then(
            function () {
                $state.go('root.login');
            },
            function (err) {
                console.log('Error', err);
                err.data.forEach(function(e) {
                    var errorMessage = e.name + "    " + e.message;
                    vm.errors.push(errorMessage );
                })
            }
        );
    }

    function doPasswordNotMatch() {
        return vm.user.password != vm.passwordRepeat;
    }
}

Controller.$inject = ['$state', '$scope', 'UserService', 'Session', 'AuthService'];

require('./user.scss');
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./user.html')
});

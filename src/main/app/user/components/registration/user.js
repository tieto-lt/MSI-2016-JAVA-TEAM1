var module = require('main_module');

function Controller($state, $scope, UserService) {
    var vm = this;

    vm.user = {};
    vm.passwordRepeat = undefined;

    vm.create = create;
    vm.errors = [];
    vm.doPasswordNotMatch = doPasswordNotMatch;

    console.log(vm.user);

    function create() {
        console.log(vm.user);
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
    console.log($scope);
        return vm.user.password != vm.passwordRepeat;
    }
}

Controller.$inject = ['$state', '$scope', 'UserService'];

require('./user.scss');
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./user.html')
});

var module = require('main_module');

module.controller('ValidationController', function($scope) {

        $scope.submitForm = function() {
            if ($scope.userForm.$valid) {
                alert('Signed up successfully!');
            }
        };
});

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

/*$scope.isSuitable = function(){
    if(username.toLowerCase().localeCompare("admin")||username.toLowerCase().localeCompare("operator")||username.toLowerCase().localeCompare("user"){
        return false;
    }
    return true;
}*/

Controller.$inject = ['$state','UserService'];

require('./user.scss');
module.component('newUser', {
    controller: Controller,
    templateUrl: require('./user.html')
});

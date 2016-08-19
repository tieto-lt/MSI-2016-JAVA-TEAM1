var module = require('main_module');

function Controller($rootScope, $state, $interval, Session, AuthService, VerificationService) {

    var vm = this;

    vm.isLogoutVisible = isLogoutVisible;
    vm.isCustomer = isCustomer;
    vm.isOperator = isOperator;
    vm.isAdmin = isAdmin;
    vm.isLoggedIn = isLoggedIn;
    vm.isLogoutVisible = isLogoutVisible;
    vm.logout = logout;
    //vm.whatUsername = whatUsername;
    vm.getCurrentState = getCurrentState;

    vm.isOperatorVerified = false;


    vm.$onInit = function() {
       whatUsername();
       $interval(checkOperator, 5000);
       $rootScope.$on('userLoggedIn', function() {
            whatUsername();
            checkOperator();
       });
    };

    function isLogoutVisible() {
        return Session.isSessionActive();
    }

    function isCustomer() {
        var role = Session.getRole();
        role = role && role[0];
        return "ROLE_CUSTOMER" == role;
    }

    function isAdmin() {
          var role = Session.getRole();
          role = role && role[0];
          return "ROLE_ADMIN" == role;
     }
    function isOperator() {
          var role = Session.getRole();
          role = role && role[0];
          return "ROLE_OPERATOR" == role
     }

    function whatUsername() {
          vm.whatUsername = Session.getUsername();
           console.log(vm.whatUsername);
           return vm.whatUsername;
    }

    function isLoggedIn() {
         return Session.isSessionActive();
    }

    function isLogoutVisible() {
            return Session.isSessionActive();
        }

    function logout() {
            Session.invalidate();
            AuthService.logout();
            $state.go('root.login');
    }

    function getCurrentState() {
            return $state.current.name;
    }

    function checkOperator() {
        if (isLoggedIn() && isOperator()) {
            VerificationService.getStatus().then(
                function(response){
                    vm.isOperatorVerified = response.data == "VERIFIED";
                },
                function(err){
                     console.log('Error',err);
                }
            )
        }
    }

}

Controller.$inject = ['$rootScope', '$state', '$interval', 'Session', 'AuthService', 'VerificationService'];


var templateUrl = require('./main.html');
require('main.scss');
module.component('main', {
    controller: Controller,
    templateUrl: templateUrl
});

var module = require('main_module');

function Controller($state, Session, AuthService) {

    var vm = this;

    vm.isLogoutVisible = isLogoutVisible;
    vm.isCustomer = isCustomer;
    vm.isOperator = isOperator;
    vm.isAdmin = isAdmin;

    function isLogoutVisible() {
        return Session.isSessionActive();
    }

    function isCustomer() {
        var role = Session.getRole();
        role = role && role[0];
        if ("ROLE_CUSTOMER" == role) {
        return true;
        }
    }

    function isAdmin() {
          var role = Session.getRole();
          role = role && role[0];
          if ("ROLE_ADMIN" == role)
          return true;
     }
    function isOperator() {
          var role = Session.getRole();
          role = role && role[0];
          if ("ROLE_OPERATOR" == role)
          return true;
     }

}

Controller.$inject = ['$state', 'Session', 'AuthService'];


var templateUrl = require('./main.html');
require('main.scss');
module.component('main', {
    controller: Controller,
    templateUrl: templateUrl
});

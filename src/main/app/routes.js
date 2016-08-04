var angular = require('angular');
var module = angular.module('AngularSpringRestDemo');

module.config(function($stateProvider, $urlRouterProvider) {

  // For any unmatched url, redirect to /
  $urlRouterProvider.otherwise("/");
  //
  // Now set up the states
  $stateProvider
    .state('root', {
      template: "<main></main>",
    })
    .state('root.home', {
      url: '/',
      template: "<h4>This is home</h4>",
      data: {
        isPublic: true
      }
    })
    .state('root.login', {
      url: "/login",
      template: "<login></login>",
      data: {
        isPublic: true
      }
    })
    .state('root.itemList', {
      url: "/items",
      template: "<item-list></item-list>"
    })
    .state('root.itemNew', {
      url: "/items/new",
      template: "<item-new></item-new>"
    })
    .state('root.ItemDetails', {
      url: "/items/:id",
      template: "<item-details></item-details>"
    })
    .state('root.admin', {
       url: "/admin",
       template: "<admin-home></admin-home>"
    })

    .state('root.rolesList', {
          url: "/roles",
          template: "<roles-list></roles-list>"
    })
    .state('root.registration', {
      url: "/registration",
      template:"<new-user></new-user>",
      data: {
        isPublic: true
      }
    })

    .state('root.operator', {
          url: "/operator",
          template:"<home-operator></home-operator>",
    })

     .state('root.customerFirst', {
              url: "/customer",
              template:"<customer-first></customer-first>",
        })

});

module.run(['$transitions', 'Session', '$state', function($transitions, Session, $state) {

  Session.initHttp();

  $transitions.onStart(
    {
      to: function (state) { return !state.data || !state.data.isPublic; }
    },
    function () {
      if (!Session.isSessionActive()) {
        return $state.target("root.login");
      }
    });
}]);

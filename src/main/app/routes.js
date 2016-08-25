var angular = require('angular');
var module = angular.module('AngularSpringRestDemo');

module.config(function($stateProvider, $urlRouterProvider, $httpProvider) {


  $httpProvider.interceptors.push('sessionInvalidationInterceptor');

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
      template: "<home-first></home-first>",
      /*template: "<login></login",*/
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
       template: "<admin-home></admin-home>",
       data: {
         roles: ["ROLE_ADMIN"]
       }

    })
    .state('root.adminRole', {
       url: "/admin/role",
       template: "<roles-management></roles-management>",
       data: {
        roles: ["ROLE_ADMIN"]
       }
    })

    .state('root.rolesList', {
          url: "/roles",
          template: "<roles-list></roles-list>",
          data: {
            roles: ["ROLE_ADMIN"]
          }
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
          data: {
            roles: ["ROLE_OPERATOR"]
          }
    })
    .state('root.customerFirst', {
          url: "/customer",
          template:"<customer-first></customer-first>",
          data: {
            roles: ["ROLE_CUSTOMER"]
          }
    })
     .state('root.missions',{
                url:  "/operator/missions",
                template:"<mission-list></mission-list",
                data: {
                    roles: ["ROLE_OPERATOR"]
                }
         })
    .state('root.token', {
              url: "/operator/token",
              template:"<operator-verification></operator-verification>",
              data: {
                roles: ["ROLE_OPERATOR"]
              }
     })
     .state('root.orders', {
              url: "/admin/orders",
              template:"<admin-orders></admin-orders>",
              data: {
                 roles: ["ROLE_ADMIN"]
              }
     })
     .state('root.information', {
               url: "/customer/information",
               template:"<customer-information></customer-information>",
               data: {
               roles: ["ROLE_CUSTOMER"]
               }
     })
    .state('root.orderComponent', {
             url:"/customer/order",
             template:"<order-component></order-component>",
             data: {
               roles: ["ROLE_CUSTOMER"]
             }
    })
    .state('root.password', {
              url:"/customer/password",
              template:"<customer-password></customer-password>",
              data: {
                 roles: ["ROLE_CUSTOMER"]
              }
    })
     .state('root.customerOrders', {
             url:"/customer/orders",
             template:"<customer-orders></customer-orders>",
             data: {
                roles: ["ROLE_CUSTOMER"]
             }
        })
     .state('root.customerDeposit', {
             url:"/customer/deposit",
             template:"<customer-deposit></customer-deposit>",
             data: {
                roles: ["ROLE_CUSTOMER"]
             }
        })

});


module.factory('sessionInvalidationInterceptor', ['Session', '$state', '$q', function(Session, $state, $q) {
      return {
          request: function(config) {
            if (Session.getToken()){
                if (Session.isSessionActive()) {
                    config.headers.Authorization = 'Bearer ' + Session.getToken();
                } else {
                    Session.invalidate();
                    if (config.headers.Authorization) {
                        delete config.headers.Authorization;
                    }
                    $state.go('root.login');
                }
            }
            return config;
          },
          responseError: function(rejection){
              if(rejection.status == 401){
                  Session.invalidate();
                  $state.go('root.login');
              }
              return $q.reject(rejection);
          }
      }
}]);



module.run(['$transitions', 'Session', '$state', '$http', function($transitions, Session, $state, $http) {

  // check public pages
  $transitions.onStart(
    {
      to: function (state) { return !state.data || !state.data.isPublic; }
    },
    function () {
      if (!Session.isSessionActive()) {
        return $state.target("root.login");
      }
    });

    $transitions.onStart(
        {
          to: function (state) { return state.data && state.data.roles && state.data.roles.indexOf("ROLE_ADMIN") >= 0; }
        },
        function () {
           if (Session.getRole().indexOf("ROLE_ADMIN") < 0) {
             return $state.target('root.home');
           }
        });



     $transitions.onStart(
             {
               to: function (state) { return state.data && state.data.roles && state.data.roles.indexOf("ROLE_OPERATOR") >= 0; }
             },
             function () {
                if (Session.getRole().indexOf("ROLE_OPERATOR") < 0) {
                  return $state.target('root.home');
                }
             });

         $transitions.onStart(
                    {
                      to: function (state) { return state.data && state.data.roles && state.data.roles.indexOf("ROLE_CUSTOMER") >= 0; }
                    },
                    function () {
                       if (Session.getRole().indexOf("ROLE_CUSTOMER") < 0) {
                         return $state.target('root.home');
                       }
                    });


}]);

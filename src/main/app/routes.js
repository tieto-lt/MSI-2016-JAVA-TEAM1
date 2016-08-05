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
       template: "<admin-home></admin-home>",
       data: {
                         roles: ["ROLE_ADMIN"]
                        }

    })
    .state('root.adminRole', {
       url: "/admin/role",
       template: "<roles-management></roles-management>"
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

});

module.run(['$transitions', 'Session', '$state', function($transitions, Session, $state) {

  Session.initHttp();

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

    // check is page allowed by role

    //Ssession.getRole()  - esamos roles
    //state.data.roles - roles kuriu reikia puslapiui

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


//    $transitions.onStart(
//        {
//          to: function (state) {
////          console.log(state.data.roles);
//            return state.data && state.data.roles;
//          }
//        },
//        function () {
//                   console.log("Im in protected page~!")
//                   console.log($state.data.roles);
//                   var currentUserRole = Session.getRole();
//                   console.log($state);
//                   //console.log($state.data.roles);
//                   if (!$state.data.roles.indexOf( currentUserRole  )) {
//                     return $state.target('root.home');
//                   }
//
//        });






}]);

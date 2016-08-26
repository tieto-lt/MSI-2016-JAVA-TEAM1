module = require('main_module');

function Service ($http, $httpParamSerializer, $cookies, $state, Session) {

    this.login = login;
    this.logout = logout;
    this.redirectToHomePage = redirectToHomePage;

    function login(username, password) {
        var data = { grant_type:"password", username: username, password: password, client_id: "web-ui" };

        var encoded = btoa("web-ui:");
        var req = {
            method: 'POST',
            url: "/oauth/token",
            headers: {
                "Authorization": "Basic " + encoded,
                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
            },
            data: $httpParamSerializer(data)
        };
        return $http(req).then(
            function(data) {
                Session.storeToken(data.data.access_token);
                return data;

            });
    }

    function logout() {
        return $http.delete('/api/logout')
            .then(function() {
                Session.invalidate();
                $state.go('root.home');
            });
    }

    function redirectToHomePage() {
        var ROLE_ADMIN = "ROLE_ADMIN";
        var ROLE_OPERATOR ="ROLE_OPERATOR";
        var ROLE_CUSTOMER = "ROLE_CUSTOMER"

        if (Session.isSessionActive()) {
            var role = Session.getRole();
            console.log("You are already logged in as "+role);
            role = role && role[0];
            if (ROLE_ADMIN == role) {
                $state.go('root.orders');
            } else if  (ROLE_OPERATOR == role) {
                $state.go('root.missions');
            } else if  (ROLE_CUSTOMER == role) {
                $state.go('root.customerOrders');
            }
        }
    }
}

Service.$inject = ['$http', '$httpParamSerializer', '$cookies', '$state', 'Session'];
module.service('AuthService', Service);

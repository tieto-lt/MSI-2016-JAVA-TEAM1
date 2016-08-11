//Main module which gets required 'module = require('main_module')' in all custom written angular modules
require('angular');
require('angular-ui-router');
require('angular-messages');
require('angular-cookies');
require('angular-jwt');
require('angular-ui-bootstrap');
//require('bootstrap-ui-bootstrap');

require("bootstrap/dist/css/bootstrap.css");

var _module = angular.module('AngularSpringRestDemo', ['ui.router', 'ngMessages', 'ngCookies', 'angular-jwt', 'ui.bootstrap']);

module.exports = _module;

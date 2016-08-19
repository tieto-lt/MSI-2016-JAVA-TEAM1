//Main module which gets required 'module = require('main_module')' in all custom written angular modules
require('angular');
require('angular-ui-router');
require('angular-messages');
require('angular-cookies');
require('angular-jwt');
require('angular-ui-bootstrap');


require('jquery');
require("bootstrap-material-design");

require("bootstrap/dist/css/bootstrap.css");
require("bootstrap-material-design/dist/css/bootstrap-material-design.css");
require("bootstrap-material-design/dist/css/ripples.css");

$.material.init()
var _module = angular.module('AngularSpringRestDemo', ['ui.router', 'ngMessages', 'ngCookies', 'angular-jwt', 'ui.bootstrap']);

module.exports = _module;
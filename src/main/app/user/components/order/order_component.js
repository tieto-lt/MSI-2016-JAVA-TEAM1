var module = require('main_module');

function Controller() {

}

require('./order_component.scss');
module.component('orderComponent', {
    controller: Controller,
    templateUrl: require('./order_component.html')
});
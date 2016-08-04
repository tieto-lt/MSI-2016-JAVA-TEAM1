var module = require('main_module');

function Controller() {
}

require('./customer_first.scss');
module.component('customerFirst', {
    controller: Controller,
    templateUrl: require('./customer_first.html')
});
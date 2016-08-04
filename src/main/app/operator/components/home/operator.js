var module = require('main_module');

function Controller() {
}

require('./operator.scss');
module.component('homeOperator', {
    controller: Controller,
    templateUrl: require('./operator.html')
});
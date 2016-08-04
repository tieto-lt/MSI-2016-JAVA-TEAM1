var module = require('main_module');



require('./operator.scss');
module.component('homeOperator', {
    controller: Controller,
    templateUrl: require('./operator.html')
});
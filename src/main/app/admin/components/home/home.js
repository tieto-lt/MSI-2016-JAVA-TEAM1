var module = require('main_module');

function Controller() {
}

module.component('adminHome', {
    controller: Controller,
    templateUrl: require('./home.html')
});


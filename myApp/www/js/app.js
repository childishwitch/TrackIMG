// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
var showFullScreen = false;
var showStatusBar = true;

angular.module('starter', ['ionic', 'starter.controllers', 'ng-mfb'])
.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
	  ionic.Platform.fullScreen(showFullScreen,showStatusBar);
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
/*
    .state('app', {
    url: '/app',
    abstract: true,
    templateUrl: 'templates/menu.html',
    controller: 'AppCtrl'
  })*/
  
	.state('eventmenu', {
      url: '/event',
      abstract: true,
      templateUrl: 'templates/event-menu.html',
	  controller: 'EventMenuCtrl'
    })
	.state('eventmenu.port', {
      url: '/port',
      views: {
        'menuContent' :{
          templateUrl: 'index_port.html',
		  controller: 'PortCtrl'
        }
      }
    })
    .state('eventmenu.land', {
      url: '/land',
      views: {
        'menuContent' :{
          templateUrl: 'index_land.html',
          controller: 'LandCtrl'
        }
      }
    })
  
  $urlRouterProvider.otherwise("/event/port");
});
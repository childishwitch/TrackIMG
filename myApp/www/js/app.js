// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
var showFullScreen = false;
var showStatusBar = true;
var JSONString = '';
function omfbClose(){
	document.getElementsByClassName('orient-mfb')[0].setAttribute("data-mfb-state", "closed");
}
angular.module('starter', ['ionic', 'starter.controllers', 'ng-mfb'])
.run(function($ionicPlatform, $window, sharedService) {
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
	
	  		cordova.exec(function(param) {
						alert("send success:"+param);
						//alert((JSON.parse(param)).items);
						//sharedProperties.setProperty((JSON.parse(param)).items);
							  var jsonString = '{"items": [{"value": 4.567401619860497, "text": "\u82f1\u96c4"}, {"value": 3.9425907538571296, "text": "\u904a\u6232"}, {"value": 3.3218091146784494, "text": "\u5b98\u65b9"}, {"value": 2.4552017601000378, "text": "\u653b\u7565"}, {"value": 2.0474363460659375, "text": "\u66f4\u65b0"}, {"value": 1.9091345934520476, "text": "\u806f\u76df"}, {"value": 1.836866560249032, "text": "\u4e2d\u5fc3"}, {"value": 1.5869972927321079, "text": "\u65b0\u805e"}, {"value": 1.4278583209634514, "text": "\u5be6\u6cc1"}, {"value": 1.3085430613891669, "text": "\u96fb\u7af6"}]}';
							  JSONString = jsonString;
			sharedService.setProperty((JSON.parse(jsonString)).items);
 //sharedProperties.setProperty((JSON.parse(jsonString)).items);
  alert((sharedService.getProperty())[0].text);
					}, function(param){
						alert("send fails"+param);
					}, "Device",
					"webViewJSON", ["getJSON"]);
					
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
})

.directive('barsChart', function ($parse) {
     //explicitly creating a directive definition variable
     //this may look verbose but is good for clarification purposes
     //in real life you'd want to simply return the object {...}
     var directiveDefinitionObject = {
         //We restrict its use to an element
         //as usually  <bars-chart> is semantically
         //more understandable
         restrict: 'E',
         //this is important,
         //we don't want to overwrite our directive declaration
         //in the HTML mark-up
         replace: false,
         //our data source would be an array
         //passed thru chart-data attribute
         scope: {data: '=chartData'},
         link: function (scope, element, attrs) {
           //in D3, any selection[0] contains the group
           //selection[0][0] is the DOM node
           //but we won't need that this time
           var chart = d3.select(element[0]);
           //to our original directive markup bars-chart
           //we add a div with out chart stling and bind each
           //data entry to the chart
            chart.append("div").attr("class", "chart")
             .selectAll('div')
             .data(scope.data).enter().append("div")
             .transition().ease("elastic")
             .style("width", function(d) { return d + "%"; })
             .text(function(d) { return d + "%"; });
           //a little of magic: setting it's width based
           //on the data value (d) 
           //and text all with a smooth transition
         } 
      };
      return directiveDefinitionObject;
   });
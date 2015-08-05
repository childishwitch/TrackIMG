// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'ng-mfb'])
.controller("ContentCtrl", function($scope, $element, $window) {
  $scope.getWindowOrientation = function () {
    return $window.orientation;
  };
  
  $scope.$watch($scope.getWindowOrientation, function (newValue, oldValue) {
    $scope.degrees = newValue;
  }, true);

  angular.element($window).bind('orientationchange', function () {
    $scope.$apply();
  });
});
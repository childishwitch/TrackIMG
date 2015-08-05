// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'ng-mfb'])
.controller("ContentCtrl", function($scope, $element, $window) {
	/*
    $scope.takePicture = function() {
        var options = { 
            quality : 75, 
            destinationType : Camera.DestinationType.DATA_URL, 
            sourceType : Camera.PictureSourceType.CAMERA, 
            allowEdit : true,
            encodingType: Camera.EncodingType.JPEG,
            targetWidth: 300,
            targetHeight: 300,
            popoverOptions: CameraPopoverOptions,
            saveToPhotoAlbum: false
        };
 
        $cordovaCamera.getPicture(options).then(function(imageData) {
            $scope.imgURI = "data:image/jpeg;base64," + imageData;
        }, function(err) {
            // An error occured. Show a message to the user
        });
    }*/
/*	
	$scope.getWindowOrientation = function () {
		return $window.orientation;
	}
 
	$scope.$watch($scope.getWindowOrientation, function (newValue, oldValue) {
		//if(newValue != oldValue){
			if(newValue == 0)
				parent.location = "index.html";
			else if(newValue == 90 || newValue == -90) 
				parent.location = "index_land.html";
		//}
	},true);*/
	
	$scope.myItems = ["台北","101大樓"];
	$scope.sysItems = ["臺灣","煙火","跨年","台北市"];
	
	/*
	//$window.onorientationchange = function () {
	angular.element($window).on('orientationchange', function ($window,$scope) {
			
			//$scope.$apply(function($window,$scope){
				if($window.orientation == 0)
					$window.parent.location = "index.html";
				else if($window.orientation == 90 || $window.orientation == -90)
					$window.parent.location = "index_land.html";
				//$window.location.reload();
				$scope.degrees = $window.orientation;
			//});
	//}
	});*/
});
/*
function onorientationchange(){
	var orientation = window.orientation;
	if(orientation == 90 || orientation == -90)
		parent.location = "index_land.html";
	else
		parent.location = "index.html";
	//window.location.reload();
}
window.addEventListenter('orientationchange',onorientationchange);
onorientationchange();*/
/*
	//$window.onorientationchange = function () {
.element($window).bind('orientationchange', function ($window,$scope) {
			//$window.location.reload();
			if($window.orientation == 0)
				parent.location = "index.html";
			else
				parent.location = "index_land.html";
			$scope.$apply();
	//};
});*/
	
/*
.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider

  .state('app', {
    url: "/app",
    abstract: true,
    templateUrl: "templates/menu.html",
    controller: 'AppCtrl'
  })

  .state('app.search', {
    url: "/search",
    views: {
      'menuContent': {
        templateUrl: "templates/search.html"
      }
    }
  })

  .state('app.browse', {
    url: "/browse",
    views: {
      'menuContent': {
        templateUrl: "templates/browse.html"
      }
    }
  })
    .state('app.playlists', {
      url: "/playlists",
      views: {
        'menuContent': {
          templateUrl: "templates/playlists.html",
          controller: 'PlaylistsCtrl'
        }
      }
    })

  .state('app.single', {
    url: "/playlists/:playlistId",
    views: {
      'menuContent': {
        templateUrl: "templates/playlist.html",
        controller: 'PlaylistCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/playlists');
});*/

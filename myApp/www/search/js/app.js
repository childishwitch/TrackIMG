// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js
angular.module('starter', ['ionic', 'starter.controllers'])

.run(function($ionicPlatform,$rootScope, $timeout, $ionicHistory,$state) {
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
    }
	if(top.name){
		document.getElementById('cse-search-input-box-id').value=top.name;
		executeQuery();
	}
  });
  /**watch the state change**/
  /**
  	var backDeregister;
      $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {
		if($state.current.name=="eventmenu.home"){
				backDeregister = $ionicPlatform.registerBackButtonAction(
					function () {
					location = "../index.html";
				}, 101);
		}
		else{
			backDeregister();//backview is always null??????
		}
        console.log('stateChangeSuccess');
        console.log('State change from: ' + fromState.name + ' to: ' + toState.name);
		console.log($ionicHistory.backView() === null ? "<null>" : $ionicHistory.backView().stateName);
        //$timeout(function() {
        //    console.log('$timeout after 1 sec $ionicHistory.backView().stateName');
        //    console.log($ionicHistory.backView() === null ? "<null>" : $ionicHistory.backView().stateName);
        //}, 1000);
    });
	**/
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
      templateUrl: 'templates/event-menu.html'
    })
	.state('eventmenu.home', {
      url: '/home',
      views: {
        'searchContent' :{
          templateUrl: 'templates/home.html'
        }
      }
    })
    .state('eventmenu.checkin', {
      url: '/check-in',
      views: {
        'searchContent' :{
          templateUrl: 'templates/check-in.html',
          controller: 'CheckinCtrl'
        }
      }
    })
    .state('eventmenu.attendees', {
      url: "/attendees",
      views: {
        'searchContent' :{
          templateUrl: 'templates/attendees.html',
          controller: "AttendeesCtrl"
        }
      }
    })
  
  $urlRouterProvider.otherwise("/event/home");
/*
  .state('app.search', {
    url: '/search',
    views: {
      'searchContent': {
        templateUrl: 'templates/search.html'
      }
    }
  })

  .state('app.browse', {
      url: '/browse',
      views: {
        'searchContent': {
          templateUrl: 'templates/browse.html'
        }
      }
    })
    .state('app.playlists', {
      url: '/playlists',
      views: {
        'searchContent': {
          templateUrl: 'templates/playlists.html',
          controller: 'PlaylistsCtrl'
        }
      }
    })

  .state('app.single', {
    url: '/playlists/:playlistId',
    views: {
      'searchContent': {
        templateUrl: 'templates/playlist.html',
        controller: 'PlaylistCtrl'
      }
    }
  });
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/app/playlists');*/
});

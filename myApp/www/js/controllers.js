angular.module('starter.controllers', ['ionic', 'ng-mfb'])
.controller('IndexCtrl', function($scope, $ionicPlatform) {
/*
 $scope.leftButtons = [{
    type: 'button-icon button-clear ion-navicon',
    tap: function(e) {
      $ionicSideMenuDelegate.toggleLeft($scope.$$childHead);
    }
  }];*/

  	  var updateOrientation=function(){
			if(window.orientation == 0){
				showFullScreen = false;
				showStatusBar = true;
				ionic.Platform.fullScreen(showFullScreen,showStatusBar);
				parent.location = "#/event/port";
			}
			else if(window.orientation == 90 || window.orientation == -90){
				showFullScreen = true;
				showStatusBar = false;
				parent.location = "#/event/land";
				ionic.Platform.fullScreen(showFullScreen,showStatusBar);
			}
			//window.location.reload();
               /*var body=document.body;  
               var viewport=document.getElementById("viewport");  
               if(body.getAttribute("orient")=="landscape"){  
                   body.setAttribute("orient","portrait");  
               }else{  
                   body.setAttribute("orient","landscape");  
               }*/  
   };
		
  var init=function(){  
      updateOrientation();  
      window.addEventListener("orientationchange",updateOrientation,false);  
  };
  window.addEventListener("DOMContentLoaded",init,false);
  //$scope.$on('pause', mfbDeregister);
  $scope.myItems = ["台北","101大樓"];
  $scope.sysItems = ["臺灣","煙火","跨年","台北市"];  
})
.controller('PortContentCtrl', function($scope) {

})

.controller('LandContentCtrl"', function($scope, $element, $window) {

})

.controller('LandCtrl', function($scope) {
 
})

.controller('PortCtrl', function($scope) {

})

.controller('EventMenuCtrl', function($scope) {

})

.controller('OmfbCtrl', function($scope,$ionicPlatform) {
		var mfbDeregister = $ionicPlatform.registerBackButtonAction(
		function () {
			var omfb = document.getElementsByClassName('orient-mfb')[0];
			if(omfb.getAttribute("data-mfb-state") == 'open')
				onTouch();
			else
				alert('back to camera?');
			}, 101
		);
});
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
  $scope.myItems = [{text:"台北"},{text:"101大樓"}];
  $scope.sysItems = [{text:"臺灣"},{text:"煙火"},{text:"跨年"},{text:"台北市"}];  
})

.controller('LandCtrl', function($scope) {
 
})

.controller('PortCtrl', function($scope) {
		
		/**click the item on hold*/
		$scope.onHold = function(item){
		if(!item)
			return;
		item.checked = !(item.checked);
		};
		$scope.onClick = function(item){//will be fired on "ionic serve" when on-Hold
			if(!item)
				return;
			top.name=item.text;
			location="search/search.html";
		};
})

.controller('EventMenuCtrl', function($scope) {

})

.controller('StarCtrl', function($scope,$ionicPlatform,$ionicPopup) {
	$scope.omfbClose = function(){omfbClose();};
			 var showConfirm = function() {
			   var confirmPopup = $ionicPopup.confirm({
				 title: '閱讀下張圖片',
				 template: '卻定要返回相機,讀取下張圖片?'
			   });
			   confirmPopup.then(function(res) {
				 if(res) {
					/**close the webview*/
					cordova.exec(function(param) {
						alert("send success:"+param);
					}, function(param){
						alert("send fails"+param);
					}, "Device",
					"webViewMsg", ["close"]);
				 } else {
				   console.log('not sure to close webview');
				 }
			   });
			 };
	/**when click on the back button on the hardware*/
	var myBackDeregister = $ionicPlatform.registerBackButtonAction(
	function () {
		var omfb = document.getElementsByClassName('orient-mfb')[0];
		if(omfb.getAttribute("data-mfb-state") == 'open')
			omfbClose();
		else{
			showConfirm();
			}
		}, 101
	);
    $scope.myData = [10,20,30,40,60, 80, 20, 50];
});